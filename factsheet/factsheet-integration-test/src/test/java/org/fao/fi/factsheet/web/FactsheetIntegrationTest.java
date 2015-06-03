package org.fao.fi.factsheet.web;

import static org.junit.Assert.assertNotNull;

import org.fao.fi.commons.integrationtest.tools.RestWebserviceIntegrationTest;
import org.junit.Test;

public class FactsheetIntegrationTest {

	RestWebserviceIntegrationTest test = new RestWebserviceIntegrationTest(
			"http://hqldvfigis2:9999/figis/ws/factsheets", "http://localhost:8080/factsheet-web/");

	String response;

	/**
	 * Can only run with a running server.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRetrieveDomainList() throws Exception {
		response = test.doGetOn("/");
		assertNotNull(response);
		response = test.doGetOn("domain/");
		assertNotNull(response);
	}

	@Test
	public void testRetrieveFactsheetList() throws Exception {
		response = test.doGetOn("domain/resource");
		assertNotNull(response);
		response = test.doGetOn("domain/resource/language");
		assertNotNull(response);
		response = test.doGetOn("domain/countrysector/factsheet");
		assertNotNull(response);
	}

	@Test
	public void testRetrieveLanguageList() throws Exception {
		response = test.doGetOn("domain/countrysector/factsheet/naso_albania");
		assertNotNull(response);
		response = test.doGetOn("domain/countrysector/factsheet/naso_albania/language");
		assertNotNull(response);
	}

	@Test
	public void testRetrieveFactsheet() throws Exception {
		response = test.doGetOn("domain/resource/factsheet/10315/language/en");
		assertNotNull(response);
	}

	@Test
	public void testPerformXpathOnFactsheet() throws Exception {
		response = test.doGetOn("domain/resource/factsheet/10315/language/en/xpath/FIGISDoc/DataEntry");
		assertNotNull(response);
	}

}
