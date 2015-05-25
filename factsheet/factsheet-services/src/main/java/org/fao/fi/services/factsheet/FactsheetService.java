/**
 * 
 */
package org.fao.fi.services.factsheet;

import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.fao.fi.commons.FigisException;
import org.fao.fi.factsheetwebservice.domain.DomainList;
import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;
import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.factsheetwebservice.domain.FactsheetList;
import org.fao.fi.factsheetwebservice.domain.LanguageList;
import org.fao.fi.logical.domain.RetrieveFactsheetListResponse;
import org.fao.fi.logical.domain.RetrieveFactsheetPerDomainListRequest;
import org.fao.fi.logical.domain.RetrieveFactsheetRequest;
import org.fao.fi.services.factsheet.logic.FactsheetAggregator;
import org.fao.fi.services.factsheet.logic.FactsheetUrlComposer;
import org.fao.fi.services.factsheet.logic.FactsheetUrlComposerImpl;
import org.fao.fi.services.factsheet.operation.PerformXpathOnFactsheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.googlecode.ehcache.annotations.Cacheable;

/**
 * @author Erik van Ingen
 * 
 */
@Component
public class FactsheetService {
	private FactsheetUrlComposer factsheetUrlComposer = new FactsheetUrlComposerImpl();
	private PerformXpathOnFactsheet performXpathOnFactsheet;
	private static final String CACHE = "FactsheetServicesCache";
	public static final String FIGISDOC_START = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><fi:FIGISDoc xmlns:fi=\"http://www.fao.org/fi/figis/devcon/\" ><!-- This is the result of an Xpath descendant query, this xml is not valid according the FIMES schema -->";
	public static final String FIGISDOC_END = "</fi:FIGISDoc>";

	private static Set<String> blackSet = new HashSet<String>();
	static {
		blackSet.add("http://www.fao.org/fishery/xml/species/3302/en");
	}

	private FactsheetAggregator factsheetAggregator;

	@Cacheable(cacheName = CACHE)
	public URL retrieveFactsheet(RetrieveFactsheetRequest request) {
		// precondition
		new RetrieveFactsheetRequestValidation(request);

		// logic
		String factsheetUrl = factsheetUrlComposer.composeFromDomainAndFactsheet(request);

		if (blackSet.contains(factsheetUrl)) {
			throw new FactsheetException("This url is on the blacklist, because it was not working at a certain point!");
		}

		try {
			return new URL(factsheetUrl);
		} catch (MalformedURLException e) {
			throw new FactsheetException(e);
		}
	}

	@Cacheable(cacheName = CACHE)
	public RetrieveFactsheetListResponse retrieveFactsheetList(FactsheetLanguage factsheetLanguage) {
		RetrieveFactsheetListResponse response = new RetrieveFactsheetListResponse();
		response.setFactsheetDiscriminatorList(factsheetAggregator.getFactsheetsOfAllDomains(factsheetLanguage));
		return response;
	}

	/**
	 * 
	 * @deprecated replaced by
	 *             retrieveFactsheetListPerDomainAndLanguage(FactsheetDomain
	 *             domain, FactsheetLanguage language
	 * 
	 */
	@Cacheable(cacheName = CACHE)
	public RetrieveFactsheetListResponse retrieveFactsheetListPerDomainAndLanguage(
			RetrieveFactsheetPerDomainListRequest request) {
		RetrieveFactsheetListResponse response = new RetrieveFactsheetListResponse();
		response.setFactsheetDiscriminatorList(factsheetAggregator.getFactsheetsPerDomainAndLanguage(request
				.getDomain(), request.getLanguage()));
		return response;
	}

	@Cacheable(cacheName = CACHE)
	public FactsheetList retrieveFactsheetListPerDomain(FactsheetDomain domain) {
		if (domain == null) {
			throw new FactsheetException("domain cannot be empty");
		}
		FactsheetList response = new FactsheetList();
		response.setFactsheetList(factsheetAggregator.getFactsheetsPerDomain(domain));
		response.setRowCount(response.getFactsheetList().size());
		return response;
	}

	@Cacheable(cacheName = CACHE)
	public DomainList retrieveDomainList() {
		DomainList domainList = new DomainList();
		FactsheetDomain[] list = FactsheetDomain.values();
		for (FactsheetDomain factsheetDomain : list) {
			domainList.getDomainList().add(factsheetDomain);
		}
		domainList.setRowCount(list.length);
		return domainList;
	}

	@Cacheable(cacheName = CACHE)
	public FactsheetList retrieveFactsheetListPerDomainAndLanguage(FactsheetDomain domain, FactsheetLanguage language) {
		if (domain == null || language == null) {
			throw new FactsheetException("domain or language cannot be empty");
		}
		FactsheetList fl = new FactsheetList();
		fl.setFactsheetList(factsheetAggregator.getFactsheetsPerDomainAndLanguage(domain.name(), language));
		fl.setRowCount(fl.getFactsheetList().size());
		return fl;
	}

	@Cacheable(cacheName = CACHE)
	public LanguageList retrieveLanguageListInDomain4ThisFactsheet(FactsheetDomain domain, String factsheet) {
		if (domain == null || factsheet == null) {
			throw new FactsheetException("domain of factsheet cannot be empty");
		}
		LanguageList languageList = new LanguageList();
		List<FactsheetDiscriminator> list = factsheetAggregator.getFactsheetsPerDomain(domain);
		for (FactsheetDiscriminator factsheetDiscriminator : list) {
			if (factsheetDiscriminator.getFactsheet().equals(factsheet)) {
				languageList.getLanguageList().add(factsheetDiscriminator.getLanguage());
			}
		}
		languageList.setRowCount(languageList.getLanguageList().size());
		return languageList;
	}

	@Cacheable(cacheName = CACHE)
	public Node pickNodeWithXpath(RetrieveFactsheetRequest request, String xpath) {
		URL url = this.retrieveFactsheet(request);
		return performXpathOnFactsheet.pickNode(url, xpath);
	}

	@Cacheable(cacheName = CACHE)
	public String pickNodeListWithXpath(RetrieveFactsheetRequest request, String xpath) {
		URL url = this.retrieveFactsheet(request);
		NodeList nodeList = performXpathOnFactsheet.pickNodeList(url, xpath);
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			try {
				// Set up the output transformer
				TransformerFactory transfac = TransformerFactory.newInstance();
				Transformer trans = transfac.newTransformer();
				trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				trans.setOutputProperty(OutputKeys.INDENT, "no");// Print the
				// DOM node
				DOMSource source = new DOMSource(node);
				trans.transform(source, result);
			} catch (TransformerException e) {
				throw new FigisException(e);
			}
		}
		return FIGISDOC_START + sw.toString() + FIGISDOC_END;

	}

	@Autowired
	public final void setFactsheetAggregator(FactsheetAggregator factsheetAggregator) {
		this.factsheetAggregator = factsheetAggregator;
	}

	@Autowired
	public final void setPerformXpathOnFactsheet(PerformXpathOnFactsheet performXpathOnFactsheet) {
		this.performXpathOnFactsheet = performXpathOnFactsheet;
	}

}
