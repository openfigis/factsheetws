package org.fao.fi.fisheryresources.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.fao.fi.fisheryresources.FisheryResourcesBaseTest;
import org.fao.fi.fisheryresources.domain.stocksby.AqRes;
import org.fao.fi.fisheryresources.domain.stocksby.StocksBySpecies;
import org.fao.fi.fisheryresources.services.vo.ThreeAlphaCode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FisheryResourcesServiceOwnerTest extends FisheryResourcesBaseTest {

	FisheryResourcesService fisheryResourcesService;

	/**
	 * Testing whether the AqRes has also an owner.
	 * 
	 * 
	 */
	@Test
	public void testRetrieveStocksBySpeciesResponseForOwner() {
		ThreeAlphaCode threeAlphaCode = new ThreeAlphaCode();
		threeAlphaCode.setValue("ALB");
		StocksBySpecies response = fisheryResourcesService.stocksBySpecies(threeAlphaCode);
		List<AqRes> aqResList = response.getAqResList();
		for (AqRes aqRes : aqResList) {
			assertNotNull(aqRes.getOwner());
			assertNotNull(aqRes.getOwner().getForeignID());
			assertNotNull(aqRes.getOwner().getTitleList().size() > 0);
		}
		assertEquals("SCRS", response.getAqResList().get(0).getOwner().getForeignID().getCode());

	}

	@Autowired
	public final void setFisheryResourcesService(FisheryResourcesService fisheryResourcesService) {
		this.fisheryResourcesService = fisheryResourcesService;
	}

}
