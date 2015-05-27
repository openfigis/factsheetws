package org.fao.fi.fisheryresources.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.fao.fi.fisheryresources.domain.stocksby.StocksBySpecies;
import org.fao.fi.fisheryresources.services.vo.ThreeAlphaCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Used for FisheryResourcesServiceIsolationTest
 * 
 * In order to have a testcase where a factsheet has a representation in 2 languages and where from these 2 the
 * languages should be added to the title list.
 * 
 * 
 * @author Erik van Ingen
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-fisheryresources-isolation.xml")
public class FisheryResourcesServiceIsolationTest {

	FisheryResourcesService fisheryResourcesService;

	@Test
	public void testRetrieveStocksBySpeciesResponseIsolated() {
		ThreeAlphaCode threeAlphaCode = new ThreeAlphaCode();
		threeAlphaCode.setValue("ALB");
		StocksBySpecies response = fisheryResourcesService.stocksBySpecies(threeAlphaCode);
		assertEquals(2, response.getAqResList().get(0).getTitleList().size());
		assertEquals(2, response.getAqResList().get(0).getFigisIdList().size());

		// the both titles should not be the same because these are different
		// languages
		assertFalse(response.getAqResList().get(0).getTitleList().get(0).getContent()
				.equals(response.getAqResList().get(0).getTitleList().get(1).getContent()));
		// the both urls should not be the same because the language is
		// different.
		assertFalse(response.getAqResList().get(0).getIdentifierList().get(0).getContent()
				.equals(response.getAqResList().get(0).getIdentifierList().get(1).getContent()));

	}

	@Autowired
	public final void setFisheryResourcesService(FisheryResourcesService fisheryResourcesService) {
		this.fisheryResourcesService = fisheryResourcesService;
	}

}
