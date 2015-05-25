package org.fao.fi.factsheet.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.xml.transform.Source;

import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;
import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.factsheetwebservice.domain.FactsheetList;
import org.fao.fi.factsheetwebservice.domain.LanguageList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-factsheet.xml" })
public class FactsheetControllerTest {

	FactsheetController factsheetController;

	@Test
	public void testRetrieveDomains() {
		factsheetController.retrieveDomains1();
		assertEquals(FactsheetDomain.values().length, factsheetController.retrieveDomains1().getRowCount());
		factsheetController.retrieveDomains2();
		assertEquals(FactsheetDomain.values().length, factsheetController.retrieveDomains2().getRowCount());
	}

	@Test
	public void testRetrieveFactsheetList() {
		assertTrue(factsheetController.retrieveFactsheets4Domain1(FactsheetDomain.area.name()).getRowCount() > 18);
		assertTrue(factsheetController.retrieveFactsheets4Domain2(FactsheetDomain.area.name()).getRowCount() > 18);
		FactsheetList fl = factsheetController.retrieveFactsheets4Domain2(FactsheetDomain.resource.name());
		assertTrue(fl.getRowCount() > 18);
		List<FactsheetDiscriminator> list = fl.getFactsheetList();
		int english = 0;
		int french = 0;
		for (FactsheetDiscriminator factsheetDiscriminator : list) {
			if (factsheetDiscriminator.getLanguage().equals(FactsheetLanguage.en)) {
				english++;
			}
			if (factsheetDiscriminator.getLanguage().equals(FactsheetLanguage.fr)) {
				french++;
			}
		}
		assertTrue(english > 10);
		assertTrue(french > 10);
		assertEquals(english + french, fl.getRowCount());
	}

	@Test
	public void testRetrieveFactsheets4DomainAndLanguage() {
		FactsheetList fl = factsheetController.retrieveFactsheetListPerDomainAndLanguage(FactsheetDomain.area.name(),
				FactsheetLanguage.en.name());
		assertTrue(fl.getRowCount() > 18);
		List<FactsheetDiscriminator> list = fl.getFactsheetList();
		for (FactsheetDiscriminator factsheetDiscriminator : list) {
			if (!factsheetDiscriminator.getLanguage().equals(FactsheetLanguage.en)) {
				fail();
			}
		}

	}

	@Test
	public void testRetrieveLanguageListInDomain4ThisFactsheet() {
		LanguageList l = factsheetController.retrieveLanguageListInDomain4ThisFactsheet1(FactsheetDomain.countrysector
				.name(), "naso_albania");
		assertEquals(5, l.getRowCount());
		assertEquals(l.getLanguageList().size(), l.getRowCount());
		l = factsheetController.retrieveLanguageListInDomain4ThisFactsheet2(FactsheetDomain.countrysector.name(),
				"naso_albania");
		assertEquals(5, l.getRowCount());
		assertEquals(l.getLanguageList().size(), l.getRowCount());
	}

	@Test
	public void testRetrieveFactsheet() {
		Source i = factsheetController.retrieveFactsheet(FactsheetDomain.countrysector.name(), "naso_albania",
				FactsheetLanguage.en.name());
		assertNotNull(i);
	}

	@Test
	public void testPerformXpathOnFactsheet1() {
		String factsheet = "10315";
		String xpath = "fi:FIGISDoc/fi:AqRes/fi:AqResIdent";
		Source node = factsheetController.performXpathOnFactsheet1(FactsheetDomain.resource.name(), factsheet,
				FactsheetLanguage.en.name(), xpath);
		assertNotNull(node);
	}

	@Test
	public void testPerformXpathOnFactsheet2() {
		String factsheet = "10315";
		String xpath = "fi:FIGISDoc/fi:AqRes/fi:AqResIdent";
		Source node = factsheetController.performXpathOnFactsheet2(FactsheetDomain.resource.name(), factsheet,
				FactsheetLanguage.en.name(), xpath, xpath);
		assertNotNull(node);
	}

	@Test
	public void testPerformXpathOnFactsheet3() {
		String factsheet = "10315";
		String xpath = "fi:FIGISDoc/fi:AqRes/fi:AqResIdent";
		Source node = factsheetController.performXpathOnFactsheet3(FactsheetDomain.resource.name(), factsheet,
				FactsheetLanguage.en.name(), xpath, xpath, xpath);
		assertNotNull(node);
	}

	@Test
	public void testPerformXpathOnFactsheet4() {
		String factsheet = "10315";
		String xpath = "fi:FIGISDoc/fi:AqRes/fi:AqResIdent";
		Source node = factsheetController.performXpathOnFactsheet4(FactsheetDomain.resource.name(), factsheet,
				FactsheetLanguage.en.name(), xpath, xpath, xpath, xpath);
		assertNotNull(node);
	}

	@Test
	public void testPerformXpathDescendendants() {
		String factsheet = "10102";
		String xpath = "fi:StandardExploitRate";
		Source nodelist = factsheetController.performXpathDescendant(FactsheetDomain.resource.name(), factsheet,
				FactsheetLanguage.en.name(), xpath);
		assertNotNull(nodelist);
	}

	@Autowired
	public final void setFisheryResourceController(FactsheetController factsheetController) {
		this.factsheetController = factsheetController;
	}

}
