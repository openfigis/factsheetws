package org.fao.fi.services.factsheet;

import static org.junit.Assert.assertNotNull;

import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.logical.domain.RetrieveFactsheetRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Node;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-factsheet.xml" })
public class FactsheetServicePerformXpathTest {

	FactsheetService factsheetService ;
	FactsheetLanguage lang = FactsheetLanguage.en;

	@Test
	public void testPickNodeWithXpath() {
		RetrieveFactsheetRequest request = new RetrieveFactsheetRequest();
		request.setLanguage(FactsheetLanguage.en);
		request.setDomain(FactsheetDomain.resource);
		request.setFactsheet("10315");
		String xpath = "/fi:FIGISDoc/fi:AqRes/fi:AqResIdent";
		Node node = factsheetService.pickNodeWithXpath(request, xpath);
		assertNotNull(node);
	}

	@Test
	public void testPickNodeListWithXpath() {
		RetrieveFactsheetRequest request = new RetrieveFactsheetRequest();
		request.setLanguage(FactsheetLanguage.en);
		request.setDomain(FactsheetDomain.resource);
		request.setFactsheet("10102");
		String xpath = "//fi:StandardExploitRate";
		String nodeListSource = factsheetService.pickNodeListWithXpath(request, xpath);
		assertNotNull(nodeListSource);

	}

	@Autowired
	public final void setFishStatFactsheetService(FactsheetService factsheetService) {
		this.factsheetService = factsheetService;
	}

}
