package org.fao.fi.factsheet.web;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-factsheet.xml" })
public class FactsheetControllerNullTest {

	FactsheetController factsheetController;

	@Test
	public void testRetrieveDomains() {
		assertNotNull(factsheetController.performXpathDescendant(null, null, null, null));
		assertNotNull(factsheetController.performXpathOnFactsheet1(null, null, null, null));
		assertNotNull(factsheetController.performXpathOnFactsheet2(null, null, null, null, null));
		assertNotNull(factsheetController.performXpathOnFactsheet3(null, null, null, null, null, null));
		assertNotNull(factsheetController.performXpathOnFactsheet4(null, null, null, null, null, null, null));
		assertNotNull(factsheetController.performXpathOnFactsheet5(null, null, null, null, null, null, null, null));
		assertNotNull(factsheetController.retrieveDomains1());
		assertNotNull(factsheetController.retrieveDomains2());
		assertNotNull(factsheetController.retrieveFactsheet(null, null, null));
		assertNotNull(factsheetController.retrieveFactsheetListPerDomainAndLanguage(null, null));
		assertNotNull(factsheetController.retrieveFactsheets4Domain1(null));
		assertNotNull(factsheetController.retrieveFactsheets4Domain2(null));
		assertNotNull(factsheetController.retrieveFactsheets4Domain3(null));
		assertNotNull(factsheetController.retrieveLanguageListInDomain4ThisFactsheet1(null, null));
		assertNotNull(factsheetController.retrieveLanguageListInDomain4ThisFactsheet2(null, null));
	}

	@Autowired
	public final void setFisheryResourceController(FactsheetController factsheetController) {
		this.factsheetController = factsheetController;
	}

}
