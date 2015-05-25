package org.fao.fi.fisheryresources.services;

import static org.junit.Assert.assertEquals;

import org.fao.fi.fisheryresources.FisheryResourcesBaseTest;
import org.fao.fi.fisheryresources.domain.stocksby.StocksByFaoArea;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FisheryResourcesServiceStocksByAreaTest extends FisheryResourcesBaseTest {

	FisheryResourcesService fisheryResourcesService;

	@Test
	public void testRetrieveStocksByFaoArea() {
		String faoArea = "27";
		StocksByFaoArea response = fisheryResourcesService.retrieveStocksByFaoArea(faoArea);

		assertEquals(148, response.getInclusion().getAqResList().size(), 5);
		assertEquals(133, response.getIntersection().getAqResList().size(), 5);

	}

	@Autowired
	public final void setFisheryResourcesService(FisheryResourcesService fisheryResourcesService) {
		this.fisheryResourcesService = fisheryResourcesService;
	}

}
