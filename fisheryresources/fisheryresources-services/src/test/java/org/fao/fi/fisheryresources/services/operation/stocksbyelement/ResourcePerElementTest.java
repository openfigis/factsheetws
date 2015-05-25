package org.fao.fi.fisheryresources.services.operation.stocksbyelement;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.fisheryresources.FisheryResourcesBaseTest;
import org.fao.fi.fisheryresources.services.operation.stocksbyspecies.FactsheetSpeciesDigger;
import org.fao.fi.fisheryresources.services.operation.stocksbyspecies.SpeciesDecision;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ResourcePerElementTest extends FisheryResourcesBaseTest {

	ResourcePerElement resourcePerElement;
	ElementResourceMapIntelligence elementResourceMapIntelligence;
	CodeAdditionDecission cad = new SpeciesDecision();

	FactsheetDigger factsheetDigger = new FactsheetSpeciesDigger();

	@Test
	public void testGetResourceListOf1() {
		String element3AlphaCode = "ALB";
		assertTrue(resourcePerElement.getResourceListOf(elementResourceMapIntelligence, element3AlphaCode,
				FactsheetLanguage.en, factsheetDigger, cad).size() > 0);
		// assertTrue(resourcePerElement.get);
		element3AlphaCode = "SKJ";
		assertTrue(resourcePerElement.getResourceListOf(elementResourceMapIntelligence, element3AlphaCode,
				FactsheetLanguage.en, factsheetDigger, cad).size() > 0);
	}

	@Test
	public void testGetResourceListOf2() {
		String element3AlphaCode = "SKJ";
		Long long16001 = new Long(16001);
		List<Long> list = resourcePerElement.getResourceListOf(elementResourceMapIntelligence, element3AlphaCode,
				FactsheetLanguage.en, factsheetDigger, cad);
		list.contains(long16001);
	}

	@Autowired
	public final void setResourcePerElement(ResourcePerElement resourcePerElement) {
		this.resourcePerElement = resourcePerElement;
	}

	@Autowired
	public final void setElementResourceMapIntelligence(ElementResourceMapIntelligence elementResourceMapIntelligence) {
		this.elementResourceMapIntelligence = elementResourceMapIntelligence;
	}

}
