package org.fao.fi.services.factsheet.client;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.fao.fi.factsheetwebservice.domain.DomainList;
import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.factsheetwebservice.domain.FactsheetList;
import org.fao.fi.factsheetwebservice.domain.LanguageList;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * 
 * FactsheetWebServiceClient in order to use the REST webservices offered at http://www.fao.org/figis/ws/factsheets/<br>
 * <br>
 * Usage Example: <br>
 * FactsheetWebServiceClient c = new FactsheetWebServiceClient(); <br>
 * DomainList dl = c.retrieveDomainList(); <br>
 * 
 * @author Erik van Ingen
 * 
 */
public class FactsheetWebServiceClient {

	private String restWebserverUrl;
	private JaxbRestClient jaxbRestClient;

	/**
	 * Default constructor. It is recommended to use this one.
	 * 
	 */
	public FactsheetWebServiceClient() {
		this.restWebserverUrl = "http://www.fao.org/figis/ws/factsheets/";
		this.jaxbRestClient = new JaxbRestClient(restWebserverUrl);
	}

	/**
	 * The webservice endpoint might have been moved, use this constructor to explicitly point to a certain webservice
	 * endpoint.
	 * 
	 * @param restWebserverUrl
	 */
	public FactsheetWebServiceClient(String restWebserverUrl) {
		this.jaxbRestClient = new JaxbRestClient(restWebserverUrl);
		this.restWebserverUrl = restWebserverUrl;
	}

	/**
	 * Lists all the available domains
	 * 
	 */
	public DomainList retrieveDomainList() {
		return (DomainList) jaxbRestClient.doGet("domain/", DomainList.class);
	}

	/**
	 * List of the available languages for that factsheet, given the domain.
	 * 
	 */

	public FactsheetList retrieveFactsheetListPerDomain(FactsheetDomain domain) {
		return (FactsheetList) jaxbRestClient.doGet("domain/" + domain, FactsheetList.class);
	}

	/**
	 * List of the factsheets in all languages for the given domain and language.
	 */
	public FactsheetList retrieveFactsheetListPerDomainAndLanguage(FactsheetDomain domain, FactsheetLanguage language) {
		return (FactsheetList) jaxbRestClient.doGet("domain/" + domain + "/language/" + language, FactsheetList.class);
	}

	/**
	 * List of the available languages for that factsheet, given the domain.
	 */
	public LanguageList retrieveLanguageListInDomain4ThisFactsheet(FactsheetDomain domain, String factsheet) {
		return (LanguageList) jaxbRestClient.doGet("domain/" + domain.toString() + "/factsheet/" + factsheet,
				LanguageList.class);
	}

	/**
	 * The actual factsheet URL, given the factsheet, domain and language.
	 * 
	 * @param request
	 * @return
	 */
	public Document retrieveFactsheet(String factsheet, FactsheetDomain domain, FactsheetLanguage language) {
		String factsheetUrl = restWebserverUrl + "domain/" + domain + "/factsheet/" + factsheet + "/language/"
				+ language;
		return uri2Document(factsheetUrl);
	}

	/**
	 * The xpath result given the xpath expression, factsheet, domain, and language. The xpath expression can go up to 5
	 * levels deep.
	 */
	public Document pickNodeWithXpath(String xpath) {
		String completeUrl = restWebserverUrl + xpath;
		return uri2Document(completeUrl);
	}

	/**
	 * Given the name of an element, this operation returns whatever element including its descendants.
	 */
	public Document pickNodeListWithXpath(String xpath) {
		String completeUrl = restWebserverUrl + xpath;
		return uri2Document(completeUrl);
	}

	private Document uri2Document(String uri) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docFactory.setNamespaceAware(true); // never forget this!
			URL xmlUrl = new URL(uri);
			DocumentBuilder builder = docFactory.newDocumentBuilder();
			URLConnection urlConnection = (URLConnection) xmlUrl.openConnection();
			urlConnection.connect();
			return builder.parse(urlConnection.getInputStream());
		} catch (ClassCastException e) {
			throw new FactsheetClientException(e);
		} catch (ParserConfigurationException e) {
			throw new FactsheetClientException(e);
		} catch (SAXException e) {
			throw new FactsheetClientException(e);
		} catch (IOException e) {
			throw new FactsheetClientException(e);
		}
	}

}