package org.fao.fi.factsheet.web;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URLConnection;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.fao.fi.commons.FigisException;
import org.fao.fi.factsheetwebservice.domain.DomainList;
import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.factsheetwebservice.domain.FactsheetList;
import org.fao.fi.factsheetwebservice.domain.LanguageList;
import org.fao.fi.logical.domain.RetrieveFactsheetRequest;
import org.fao.fi.services.factsheet.FactsheetException;
import org.fao.fi.services.factsheet.FactsheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Node;

/**
 * TODO add log4j
 * 
 * 
 * @author Erik van Ingen
 * 
 */

@Controller
@RequestMapping
public class FactsheetController {

	private Logger logger = Logger.getLogger(FactsheetController.class);

	private FactsheetService factsheetService;
	private ExceptionHandler exceptionHandler = new ExceptionHandler();
	private static final String SEPARATOR = "/fi:";
	private static final String DOMAIN = "domain";
	private static final String LANGUAGE = "language";
	private static final String FACTSHEET = "factsheet";

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public final DomainList retrieveDomains1() {
		try {
			return factsheetService.retrieveDomainList();
		} catch (FactsheetException e) {
			logger.error(e);
			return new DomainList();
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public final DomainList retrieveDomains2() {
		try {
			return factsheetService.retrieveDomainList();
		} catch (FactsheetException e) {
			logger.error(e);
			return new DomainList();
		}
	}

	/**
	 * List of factsheets available in all languages for the given domain.
	 */
	@RequestMapping(value = "/{domain}", method = RequestMethod.GET)
	@ResponseBody
	public final FactsheetList retrieveFactsheets4Domain1(@PathVariable(DOMAIN) String domain) {
		try {
			return factsheetService.retrieveFactsheetListPerDomain(FactsheetDomain.parseDomain(domain));
		} catch (FactsheetException e) {
			logger.error(e);
			return new FactsheetList();
		} catch (FigisException e) {
			logger.error(e);
			return new FactsheetList();
		}
	}

	/**
	 * List of factsheets available in all languages for the given domain.
	 */
	@RequestMapping(value = "/{domain}/language", method = RequestMethod.GET)
	@ResponseBody
	public final FactsheetList retrieveFactsheets4Domain2(@PathVariable(DOMAIN) String domain) {
		try {
			return factsheetService.retrieveFactsheetListPerDomain(FactsheetDomain.parseDomain(domain));
		} catch (FactsheetException e) {
			logger.error(e);
			return new FactsheetList();
		} catch (FigisException e) {
			logger.error(e);
			return new FactsheetList();
		}
	}

	@RequestMapping(value = "/{domain}/language/{language}", method = RequestMethod.GET)
	@ResponseBody
	public final FactsheetList retrieveFactsheetListPerDomainAndLanguage(@PathVariable(DOMAIN) String domain,
			@PathVariable(LANGUAGE) String language) {
		try {
			return factsheetService.retrieveFactsheetListPerDomainAndLanguage(FactsheetDomain.parseDomain(domain),
					FactsheetLanguage.parseLanguage(language));
		} catch (FactsheetException e) {
			logger.error(e);
			return new FactsheetList();
		} catch (FigisException e) {
			logger.error(e);
			return new FactsheetList();
		}
	}

	/**
	 * List of factsheets available in all languages for the given domain.
	 */
	@RequestMapping(value = "/{domain}/factsheet", method = RequestMethod.GET)
	@ResponseBody
	public final FactsheetList retrieveFactsheets4Domain3(@PathVariable(DOMAIN) String domain) {
		try {
			return factsheetService.retrieveFactsheetListPerDomain(FactsheetDomain.parseDomain(domain));
		} catch (FactsheetException e) {
			logger.error(e);
			return new FactsheetList();
		} catch (FigisException e) {
			logger.error(e);
			return new FactsheetList();
		}
	}

	/**
	 * List of the available languages for that factsheet, given the domain.
	 */
	@RequestMapping(value = "/{domain}/factsheet/{factsheet}", method = RequestMethod.GET)
	@ResponseBody
	public final LanguageList retrieveLanguageListInDomain4ThisFactsheet1(@PathVariable(DOMAIN) String domain,
			@PathVariable(FACTSHEET) String factsheet) {
		try {
			return factsheetService.retrieveLanguageListInDomain4ThisFactsheet(FactsheetDomain.parseDomain(domain),
					factsheet);
		} catch (FactsheetException e) {
			logger.error(e);
			return new LanguageList();
		} catch (FigisException e) {
			logger.error(e);
			return new LanguageList();
		}
	}

	/**
	 * List of the available languages for that factsheet, given the domain.
	 */
	@RequestMapping(value = "/{domain}/factsheet/{factsheet}/language", method = RequestMethod.GET)
	@ResponseBody
	public final LanguageList retrieveLanguageListInDomain4ThisFactsheet2(@PathVariable(DOMAIN) String domain,
			@PathVariable(FACTSHEET) String factsheet) {
		try {
			return factsheetService.retrieveLanguageListInDomain4ThisFactsheet(FactsheetDomain.parseDomain(domain),
					factsheet);
		} catch (FactsheetException e) {
			logger.error(e);
			return new LanguageList();
		} catch (FigisException e) {
			logger.error(e);
			return new LanguageList();
		}
	}

	@RequestMapping(value = "/{domain}/factsheet/{factsheet}/language/{language}", method = RequestMethod.GET)
	@ResponseBody
	public final Source retrieveFactsheet(@PathVariable(DOMAIN) String domain,
			@PathVariable(FACTSHEET) String factsheet, @PathVariable(LANGUAGE) String language) {
		try {
			RetrieveFactsheetRequest request = new RetrieveFactsheetRequest();
			request.setDomain(FactsheetDomain.parseDomain(domain));
			request.setFactsheet(factsheet);
			request.setLanguage(FactsheetLanguage.parseLanguage(language));
			URLConnection urlConnection = factsheetService.retrieveFactsheet(request).openConnection();
			urlConnection.connect();
			return new StreamSource(urlConnection.getInputStream());
		} catch (IOException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		} catch (FactsheetException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		} catch (FigisException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		}
	}

	@RequestMapping(value = "/{domain}/factsheet/{factsheet}/language/{language}/xpath/{xpath}", method = RequestMethod.GET)
	@ResponseBody
	public final Source performXpathOnFactsheet1(@PathVariable(DOMAIN) String domain,
			@PathVariable(FACTSHEET) String factsheet, @PathVariable(LANGUAGE) String language,
			@PathVariable("xpath") String xpath) {
		try {
			xpath = SEPARATOR + xpath;
			return performXpathOnFactsheetShort(domain, factsheet, language, xpath);
		} catch (FactsheetException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		} catch (FigisException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		}

	}

	@RequestMapping(value = "/{domain}/factsheet/{factsheet}/language/{language}/xpath/{xpath1}/{xpath2}", method = RequestMethod.GET)
	@ResponseBody
	public final Source performXpathOnFactsheet2(@PathVariable(DOMAIN) String domain,
			@PathVariable(FACTSHEET) String factsheet, @PathVariable(LANGUAGE) String language,
			@PathVariable("xpath1") String xpath1, @PathVariable("xpath2") String xpath2) {
		String xpath = SEPARATOR + xpath1 + SEPARATOR + xpath2;
		try {
			return performXpathOnFactsheetShort(domain, factsheet, language, xpath);
		} catch (FactsheetException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		} catch (FigisException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		}
	}

	@RequestMapping(value = "/{domain}/factsheet/{factsheet}/language/{language}/xpath/{xpath1}/{xpath2}/{xpath3}", method = RequestMethod.GET)
	@ResponseBody
	public final Source performXpathOnFactsheet3(@PathVariable(DOMAIN) String domain,
			@PathVariable(FACTSHEET) String factsheet, @PathVariable(LANGUAGE) String language,
			@PathVariable("xpath1") String xpath1, @PathVariable("xpath2") String xpath2,
			@PathVariable("xpath3") String xpath3) {
		String xpath = SEPARATOR + xpath1 + SEPARATOR + xpath2 + SEPARATOR + xpath3;
		try {
			return performXpathOnFactsheetShort(domain, factsheet, language, xpath);
		} catch (FactsheetException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		} catch (FigisException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		}

	}

	@RequestMapping(value = "/{domain}/factsheet/{factsheet}/language/{language}/xpath/{xpath1}/{xpath2}/{xpath3}/{xpath4}", method = RequestMethod.GET)
	@ResponseBody
	public final Source performXpathOnFactsheet4(@PathVariable(DOMAIN) String domain,
			@PathVariable(FACTSHEET) String factsheet, @PathVariable(LANGUAGE) String language,
			@PathVariable("xpath1") String xpath1, @PathVariable("xpath2") String xpath2,
			@PathVariable("xpath3") String xpath3, @PathVariable("xpath4") String xpath4) {
		String xpath = SEPARATOR + xpath1 + SEPARATOR + xpath2 + SEPARATOR + xpath3 + SEPARATOR + xpath4;
		try {
			return performXpathOnFactsheetShort(domain, factsheet, language, xpath);
		} catch (FactsheetException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		} catch (FigisException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		}
	}

	@RequestMapping(value = "/{domain}/factsheet/{factsheet}/language/{language}/xpath/{xpath1}/{xpath2}/{xpath3}/{xpath4}/{xpath5}", method = RequestMethod.GET)
	@ResponseBody
	public final Source performXpathOnFactsheet5(@PathVariable(DOMAIN) String domain,
			@PathVariable(FACTSHEET) String factsheet, @PathVariable(LANGUAGE) String language,
			@PathVariable("xpath1") String xpath1, @PathVariable("xpath2") String xpath2,
			@PathVariable("xpath3") String xpath3, @PathVariable("xpath4") String xpath4,
			@PathVariable("xpath5") String xpath5) {
		String xpath = SEPARATOR + xpath1 + SEPARATOR + xpath2 + SEPARATOR + xpath3 + SEPARATOR + xpath4 + xpath5;
		try {
			return performXpathOnFactsheetShort(domain, factsheet, language, xpath);
		} catch (FactsheetException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		} catch (FigisException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		}
	}

	@RequestMapping(value = "/{domain}/factsheet/{factsheet}/language/{language}/xpathdescendant/{xpathdescendant}", method = RequestMethod.GET)
	@ResponseBody
	public final Source performXpathDescendant(@PathVariable(DOMAIN) String domain,
			@PathVariable(FACTSHEET) String factsheet, @PathVariable(LANGUAGE) String language,
			@PathVariable("xpathdescendant") String xpathdescendant) {
		try {
			String xpath = "/" + SEPARATOR + xpathdescendant;
			RetrieveFactsheetRequest request = new RetrieveFactsheetRequest();
			request.setDomain(FactsheetDomain.parseDomain(domain));
			request.setFactsheet(factsheet);
			request.setLanguage(FactsheetLanguage.parseLanguage(language));

			String nodeListString = factsheetService.pickNodeListWithXpath(request, xpath);
			Reader nodeListReader = new CharArrayReader(nodeListString.toCharArray());
			return new StreamSource(nodeListReader);
		} catch (FactsheetException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		} catch (FigisException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		}
	}

	private Source performXpathOnFactsheetShort(String domain, String factsheet, String language, String xpath) {
		try {
			RetrieveFactsheetRequest request = new RetrieveFactsheetRequest();
			request.setDomain(FactsheetDomain.parseDomain(domain));
			request.setFactsheet(factsheet);
			request.setLanguage(FactsheetLanguage.parseLanguage(language));
			Node node = (Node) factsheetService.pickNodeWithXpath(request, xpath);
			Source document = new DOMSource(node);
			return document;
		} catch (FactsheetException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		} catch (FigisException e) {
			logger.error(e);
			return exceptionHandler.composeSource(e);
		}
	}

	@Autowired
	public final void setFishStatFactsheetService(FactsheetService factsheetService) {
		this.factsheetService = factsheetService;
	}

}
