package org.fao.fi.fisheryresources.web.it;

import static org.junit.Assert.assertEquals;

import org.fao.fi.commons.integrationtest.tools.RestWebserviceIntegrationTest;
import org.fao.fi.fisheryresources.domain.stocksby.StocksByFaoArea;
import org.fao.fi.fisheryresources.domain.stocksby.StocksBySpecies;
import org.junit.Test;

public class FisheryResourceControllerIntegrationTest {

	RestWebserviceIntegrationTest test = new RestWebserviceIntegrationTest(
			"http://hqldvfigis2:9999/figis/ws/fisheryresources/", "http://localhost:8080/fisheryresources-web/");

	/**
	 * Can only run with a running server.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStocksbyspecies3AlphaCode() throws Exception {
		StocksBySpecies response = (StocksBySpecies) test.unMarshall("stocksbyspecies/fao3alphacode/ALB",
				StocksBySpecies.class);
		assertEquals(5, response.getAqResList().size(), 2);
	}

	@Test
	public void testStocksbyFaoArea() throws Exception {
		StocksByFaoArea response = (StocksByFaoArea) test.unMarshall("stocksbyfaoarea/faoarea/51",
				StocksByFaoArea.class);
		assertEquals(26, response.getInclusion().getAqResList().size(), 5);
		assertEquals(26, response.getIntersection().getAqResList().size(), 5);
	}

}
