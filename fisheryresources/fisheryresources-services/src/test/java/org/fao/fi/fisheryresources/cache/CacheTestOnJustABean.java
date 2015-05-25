package org.fao.fi.fisheryresources.cache;

import static org.junit.Assert.assertEquals;

import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.fisheryresources.services.operation.stocksbyarea.AreaIntersectionDecision;
import org.fao.fi.fisheryresources.services.operation.stocksbyarea.FactsheetAreaDigger;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.CodeAdditionDecission;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.FactsheetDigger;
import org.fao.fi.fisheryresources.services.operation.stocksbyspecies.FactsheetSpeciesDigger;
import org.fao.fi.fisheryresources.services.operation.stocksbyspecies.SpeciesDecision;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CacheTestOnJustABean {

	private CacheTestObject cacheTestObject;

	@Test
	public void cacheTest() {
		FactsheetLanguage factsheetLanguage = FactsheetLanguage.ar;
		FactsheetDigger factsheetDigger = new FactsheetAreaDigger();
		CodeAdditionDecission cad = new SpeciesDecision();
		assertEquals(0, cacheTestObject.getIndex());
		cacheTestObject.getElementResourceMap(factsheetLanguage, factsheetDigger, cad);
		assertEquals(1, cacheTestObject.getIndex());
		cacheTestObject.getElementResourceMap(factsheetLanguage, factsheetDigger, cad);
		assertEquals(1, cacheTestObject.getIndex());

		factsheetDigger = new FactsheetSpeciesDigger();
		cacheTestObject.getElementResourceMap(factsheetLanguage, factsheetDigger, cad);
		assertEquals(2, cacheTestObject.getIndex());

		cad = new AreaIntersectionDecision("287");
		cacheTestObject.getElementResourceMap(factsheetLanguage, factsheetDigger, cad);
		assertEquals(3, cacheTestObject.getIndex());

		cad = new AreaIntersectionDecision("288");
		cacheTestObject.getElementResourceMap(factsheetLanguage, factsheetDigger, cad);
		assertEquals(4, cacheTestObject.getIndex());

		cad = new AreaIntersectionDecision("287");
		cacheTestObject.getElementResourceMap(factsheetLanguage, factsheetDigger, cad);
		assertEquals(4, cacheTestObject.getIndex());

	}

	@Autowired
	public final void setCacheTestObject(CacheTestObject cacheTestObject) {
		this.cacheTestObject = cacheTestObject;
	}

}
