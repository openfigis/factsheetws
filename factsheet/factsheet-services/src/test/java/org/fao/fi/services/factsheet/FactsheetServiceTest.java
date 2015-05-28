package org.fao.fi.services.factsheet;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.exceptions.ConfigurationException;
import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;
import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.factsheetwebservice.domain.FactsheetList;
import org.fao.fi.factsheetwebservice.domain.LanguageList;
import org.fao.fi.logical.domain.RetrieveFactsheetListRequest;
import org.fao.fi.logical.domain.RetrieveFactsheetListResponse;
import org.fao.fi.logical.domain.RetrieveFactsheetPerDomainListRequest;
import org.fao.fi.logical.domain.RetrieveFactsheetRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSParser;
import org.xml.sax.SAXException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-factsheet.xml" })
public class FactsheetServiceTest extends XMLTestCase {

	FactsheetService factsheetService;
	FactsheetLanguage lang = FactsheetLanguage.en;

	/**
	 * This test takes a long time.
	 */
	public void testRetrieveFactsheetList() {
		RetrieveFactsheetListRequest request = new RetrieveFactsheetListRequest();
		request.setLanguage(lang);
		// System.out.println(factsheetService.retrieveFactsheetList(request).getFactsheetDiscriminatorList()
		// .size());
		assertTrue(factsheetService.retrieveFactsheetList(FactsheetLanguage.en).getFactsheetDiscriminatorList().size() > 1500);
	}

	/**
	 * English
	 */

	@Test
	public void testRetrieveFactsheetListPerDomainEnglish() {
		delegateTest(FactsheetLanguage.en);
	}

	/**
	 * French
	 * 
	 * These are the french factsheets at 23 april 2010 10112 10113 10114 10115 10119 10120 10121 10123 10131 10132
	 * 10133 10135 10145 10148
	 * 
	 */
	@Test
	public void testRetrieveFactsheetListPerDomainFrench() {
		delegateTest(FactsheetLanguage.fr);
	}

	/**
	 * English and French There are no cases in which a French one has also an English one.
	 * 
	 */
	public void testRetrieveFactsheetListPerDomainEnglishAndFrench() {
		RetrieveFactsheetPerDomainListRequest request = new RetrieveFactsheetPerDomainListRequest();
		String domain = "resource";
		request.setLanguage(FactsheetLanguage.en);
		request.setDomain(domain);
		RetrieveFactsheetListResponse response = factsheetService.retrieveFactsheetListPerDomainAndLanguage(request);
		List<FactsheetDiscriminator> list = response.getFactsheetDiscriminatorList();
		for (FactsheetDiscriminator d1 : list) {
			if (d1.getLanguage().equals(FactsheetLanguage.en)) {
				for (FactsheetDiscriminator d2 : list) {
					if (d1.getFactsheet().equals(d2.getFactsheet()) && d2.getLanguage().equals(FactsheetLanguage.fr)) {
						System.out.println(d1.getFactsheet());
					}
				}
			}
		}
	}

	/**
	 * test wheter the resources 10213 and 10112 do occur
	 */
	// //////////@Test
	// public void testRetrieveFactsheetListPerDomain10114() {
	// RetrieveFactsheetPerDomainListRequest request = new
	// RetrieveFactsheetPerDomainListRequest();
	// String domain = "resource";
	// request.setLanguage(FactsheetLanguage.en);
	// request.setDomain(domain);
	// RetrieveFactsheetListResponse response =
	// factsheetService.retrieveFactsheetListPerDomain(request);
	// List<FactsheetDiscriminator> list =
	// response.getFactsheetDiscriminatorList();
	//
	// Set<String> resourceSet = new HashSet<String>();
	// for (FactsheetDiscriminator factsheetDiscriminator : list) {
	// resourceSet.add(factsheetDiscriminator.getFactsheet());
	// }
	// // assertTrue(resourceSet.contains("10213"));
	// // assertTrue(resourceSet.contains("10112"));
	// assertTrue(resourceSet.contains("10114"));
	// }

	private void delegateTest(FactsheetLanguage factsheetLanguage) {
		RetrieveFactsheetPerDomainListRequest request = new RetrieveFactsheetPerDomainListRequest();
		String domain = "resource";
		request.setLanguage(factsheetLanguage);
		request.setDomain(domain);
		RetrieveFactsheetListResponse response = factsheetService.retrieveFactsheetListPerDomainAndLanguage(request);
		List<FactsheetDiscriminator> list = response.getFactsheetDiscriminatorList();
		for (FactsheetDiscriminator factsheetDiscriminator : list) {
			assertEquals(factsheetLanguage, factsheetDiscriminator.getLanguage());
			System.out.println(factsheetDiscriminator.getFactsheet());
		}
		int size = list.size();
		System.out.println(size);
		assertTrue(size > 0 && size < 1000);
	}

	public void testRetrieveFactsheetDelegate(FactsheetDiscriminator d) throws IOException, ConfigurationException,
			SAXException {
		RetrieveFactsheetRequest request = new RetrieveFactsheetRequest();
		request.setLanguage(d.getLanguage());
		request.setDomain(d.getDomain());
		request.setFactsheet(d.getFactsheet());
		URL url = factsheetService.retrieveFactsheet(request);
		assertNotNull(url);
		System.out.println(url);
		try {
			DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
			LSParser builder = impl.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
			builder.parseURI(url.getProtocol() + "://" + url.getHost() + url.getPath());
		} catch (ClassCastException e) {
			throw new FactsheetException(e);
		} catch (ClassNotFoundException e) {
			throw new FactsheetException(e);
		} catch (InstantiationException e) {
			throw new FactsheetException(e);
		} catch (IllegalAccessException e) {
			throw new FactsheetException(e);
		}
	}

	@Test
	public void testRetrieveDomains() {
		assertEquals(FactsheetDomain.values().length, factsheetService.retrieveDomainList().getRowCount());
		assertEquals(FactsheetDomain.values().length, factsheetService.retrieveDomainList().getDomainList().size());
	}

	@Test
	public void testRetrieveLanguageListInDomain4ThisFactsheet() {
		LanguageList l = factsheetService.retrieveLanguageListInDomain4ThisFactsheet(FactsheetDomain.countrysector,
				"naso_albania");
		assertEquals(5, l.getRowCount());
		assertEquals(l.getLanguageList().size(), l.getRowCount());
	}

	@Test
	public void testRetrieveFactsheetListPerDomainAndLanguage() {
		FactsheetList l = factsheetService.retrieveFactsheetListPerDomainAndLanguage(FactsheetDomain.countrysector,
				FactsheetLanguage.zh);
		assertTrue(l.getRowCount() > 90);
		assertEquals(l.getFactsheetList().size(), l.getRowCount());
	}

	@Autowired
	public final void setFishStatFactsheetService(FactsheetService factsheetService) {
		this.factsheetService = factsheetService;
	}

}
