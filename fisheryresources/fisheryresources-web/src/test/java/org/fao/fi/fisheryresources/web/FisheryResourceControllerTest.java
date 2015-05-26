package org.fao.fi.fisheryresources.web;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.fao.fi.fisheryresources.FisheryResourcesBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FisheryResourceControllerTest extends FisheryResourcesBaseTest {

	FisheryResourceController fisheryResourceController;

	@Test
	public void testRetrieveStocksBySpeciesResponseBy3alphacode() {
		String fao3AlphaCode = "ALB";
		fao3AlphaCode = "ARA";
		assertNotNull(fisheryResourceController.retrieveStocksBySpeciesResponseBy3alphacode(fao3AlphaCode));
		assertNotNull(fisheryResourceController.retrieveStocksBySpeciesResponseBy3alphacode(fao3AlphaCode));

	}

	@Test
	public void testRetrieveStocksByFaoArea1() {
		String area = "27";
		assertTrue(fisheryResourceController.retrieveStocksByFaoArea(area).getInclusion().getAqResList().size() > 5);
		assertTrue(fisheryResourceController.retrieveStocksByFaoArea(area).getIntersection().getAqResList().size() > 5);
	}

	@Autowired
	public void setFisheryResourceController(FisheryResourceController fisheryResourceController) {
		this.fisheryResourceController = fisheryResourceController;
	}

}
