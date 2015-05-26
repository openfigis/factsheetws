package org.fao.fi.fisheryresources.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.fao.fi.fisheryresources.FisheryResourcesBaseTest;
import org.fao.fi.fisheryresources.domain.stocksby.AqRes;
import org.fao.fi.fisheryresources.domain.stocksby.FigisID;
import org.fao.fi.fisheryresources.domain.stocksby.Identifier;
import org.fao.fi.fisheryresources.domain.stocksby.StocksBySpecies;
import org.fao.fi.fisheryresources.domain.stocksby.Type;
import org.fao.fi.fisheryresources.services.vo.ThreeAlphaCode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FisheryResourcesServiceTest extends FisheryResourcesBaseTest {

	FisheryResourcesService fisheryResourcesService;

	/**
	 * 
	 * 
	 * 
	 */

	@Test
	public void testRetrieveStocksBySpeciesResponse1() {
		ThreeAlphaCode threeAlphaCode = new ThreeAlphaCode();
		threeAlphaCode.setValue("ALB");
		StocksBySpecies response = fisheryResourcesService.stocksBySpecies(threeAlphaCode);
		assertNotNull(response);
		assertNotNull(response.getAqResList());
		List<AqRes> aqResList = response.getAqResList();
		for (AqRes aqRes : aqResList) {
			assertTrue(aqRes.getFigisIdList().size() > 0);
			assertNotNull(aqRes.getTitleList());
			// assertTrue(aqRes.getSpeciesRefList().size() > 0);
			// assertTrue(aqRes.getWaterAreaRefList().size() > 0);
		}

	}

	@Test
	public void testRetrieveStocksBySpeciesResponse2() {
		ThreeAlphaCode threeAlphaCode = new ThreeAlphaCode();
		threeAlphaCode.setValue("XXX");
		StocksBySpecies response = fisheryResourcesService.stocksBySpecies(threeAlphaCode);
		assertNotNull(response);
		assertNull(response.getAqResList());
		// assertEquals(0, response.getAqResList().size());

	}

	/**
	 * Bug reported by Aureliano
	 * 
	 * This one has more species lists http://firms.fao.org/firms/xml/resource/16001/en
	 * 
	 * 
	 */
	@Test
	public void testRetrieveStocksBySpeciesResponseALB() {
		ThreeAlphaCode threeAlphaCode = new ThreeAlphaCode();
		threeAlphaCode.setValue("ALB");
		StocksBySpecies response = fisheryResourcesService.stocksBySpecies(threeAlphaCode);

		assertEquals(9, response.getAqResList().size());
		assertEquals(1, response.getAqResList().get(0).getSpeciesListList().size());
		assertEquals(1, response.getAqResList().get(1).getSpeciesListList().size());
		assertEquals(1, response.getAqResList().get(0).getSpeciesListList().get(0).getSpeciesRefList().size());
		assertEquals(1, response.getAqResList().get(1).getSpeciesListList().get(0).getSpeciesRefList().size());

		assertEquals(1, response.getAqResList().get(0).getWaterAreaRefListList().size());
		assertEquals(1, response.getAqResList().get(1).getWaterAreaRefListList().size());

	}

	/**
	 * SKJ appears as species in the resource factsheet 16001.
	 * 
	 * 16001 should appear in the stocks per species SKJ
	 * 
	 */
	@Test
	public void testRetrieveStocksBySpeciesResponseSKJ() {
		testDelegateFindReourceInSpeciesResponse("15", "SKJ", "http://firms.fao.org/firms/resource/15/en");
	}

	/**
	 * 
	 * Resource 10123 has HKM and HKB. So HKM should refer to 10123
	 * 
	 * At this moment 10123 has only the french one published. So it should find the french factsheet:
	 * http://firms.fao.org/firms/xml/resource/10123/fr
	 * 
	 * 
	 */
	public void testRetrieveStocksBySpeciesResponseHKM() {
		testDelegateFindReourceInSpeciesResponse("10123", "HKM", "http://firms.fao.org/firms/resource/10123/fr");
	}

	private void testDelegateFindReourceInSpeciesResponse(String resource, String fao3alphaSpeciesCode,
			String expectedFirstUrl) {
		ThreeAlphaCode threeAlphaCode = new ThreeAlphaCode();
		threeAlphaCode.setValue(fao3alphaSpeciesCode);
		StocksBySpecies response = fisheryResourcesService.stocksBySpecies(threeAlphaCode);
		boolean foundResourceInResponse = false;
		boolean foundUrlInResponse = false;
		List<AqRes> aqResList = response.getAqResList();
		for (AqRes aqRes : aqResList) {
			List<FigisID> figisIdList = aqRes.getFigisIdList();
			for (FigisID figisID : figisIdList) {
				if (figisID.getType().equals(Type.Object) && figisID.getId().equals(resource)) {
					foundResourceInResponse = true;
				}
				System.out.println(figisID.getType().toString());
				if (figisID.getType().equals(Type.Object)) {
					System.out.println(figisID.getId());
				}
			}
			assertTrue(aqRes.getTitleList().size() > 0);
			assertTrue(aqRes.getIdentifierList().size() > 0);

			List<Identifier> list = aqRes.getIdentifierList();
			for (Identifier identifier : list) {
				if (identifier.getContent().equals(expectedFirstUrl)) {
					foundUrlInResponse = true;
				}
			}
		}
		assertTrue(foundUrlInResponse);
		assertTrue(foundResourceInResponse);
	}

	/**
	 * french resource factsheets 10113 10114 10115 10119 10120 10121 10123 10131 10132 10133 10135 10145 10148 10372
	 * 
	 * 
	 */

	@Autowired
	public final void setFisheryResourcesService(FisheryResourcesService fisheryResourcesService) {
		this.fisheryResourcesService = fisheryResourcesService;
	}

}
