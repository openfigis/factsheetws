package org.fao.fi.fisheryresources.services.operation.stocksbyelement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.fisheryresources.FisheryResourcesBaseTest;
import org.fao.fi.fisheryresources.services.operation.stocksbyarea.AreaIntersectionDecision;
import org.fao.fi.fisheryresources.services.operation.stocksbyarea.FactsheetAreaDigger;
import org.fao.fi.fisheryresources.services.operation.stocksbyspecies.FactsheetSpeciesDigger;
import org.fao.fi.fisheryresources.services.operation.stocksbyspecies.SpeciesDecision;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ElementResourceMapIntelligenceTest extends FisheryResourcesBaseTest {

	ElementResourceMapIntelligence elementResourceMapIntelligence;
	FactsheetDigger factsheetDigger = new FactsheetSpeciesDigger();
	CodeAdditionDecission cad = new SpeciesDecision();

	/**
	 * 27.14.b 27.5.a 27.3.d.27 27.3.d.26 27.3.d.25 27.9.a 27.3.d.24 27.9.b 27.3.a 27.3.d.29 27.3.d.28 27.6 27.2.a 27.5
	 * 27.2.b 27.12 27.14 27.1 27.7.a 27.7.b 27.3.b.23 27.7.c 27.7.d 27.7.e 27.7.f 27.7.g 27.8.c 27.7.h 27.8.d 27.5.b.1
	 * 27.8.e 27.7.j 27.3.c.22 27.7.k 27 27.8.a 27.5.b.2 27.8.b 27.3.d.31 27.3.d.32 27.6.b 27.6.a 27.3.d.30 27.4.c
	 * 27.4.b 27.14.a 27.4.a
	 */

	// @Test
	public void testPrintCodes() {

		List<ResourceElementsList> list = elementResourceMapIntelligence.create1ResourceElementRelation(
				FactsheetLanguage.en, factsheetDigger);
		Set<String> found = new HashSet<String>();
		for (ResourceElementsList resourceElementsList : list) {
			List<String> elementlist = resourceElementsList.getElementList();
			for (String element : elementlist) {
				found.add(element);
			}
		}
		Object o[] = found.toArray();
		for (Object object : o) {
			System.out.println(object);
		}

	}

	/**
	 * should have resources 10112 and 10213 http://firms.fao.org/firms/xml/resource/10213/en
	 * http://firms.fao.org/firms/xml/resource/10112/en
	 */
	// @Test
	public void testGetElementResourceMapEN() {
		FactsheetLanguage factsheetLanguage = FactsheetLanguage.en;
		List<ResourceElementsList> listOfresourceElementList = elementResourceMapIntelligence
				.create1ResourceElementRelation(FactsheetLanguage.en, factsheetDigger);
		Map<String, List<Long>> map = elementResourceMapIntelligence.create2ElementResourceMap(factsheetLanguage,
				factsheetDigger, cad, listOfresourceElementList);
		List<Long> resourceList = map.get("SKJ");
		assertTrue(resourceList.size() > 1);
	}

	// @Test
	public void testGetElementResourceMapFR() {
		FactsheetLanguage factsheetLanguage = FactsheetLanguage.fr;
		List<ResourceElementsList> listOfresourceElementList = elementResourceMapIntelligence
				.create1ResourceElementRelation(FactsheetLanguage.en, factsheetDigger);
		Map<String, List<Long>> map = elementResourceMapIntelligence.create2ElementResourceMap(factsheetLanguage,
				factsheetDigger, cad, listOfresourceElementList);
		List<Long> resourceList = map.get("HKM");
		assertTrue(resourceList.size() > 0);
	}

	// @Test
	public void testcreate2ElementResourceMap1() {
		Long resource = 5l;
		String area = "27";
		String areaRequested = "27.1";

		FactsheetDigger ad = new FactsheetAreaDigger();

		FactsheetLanguage factsheetLanguage = FactsheetLanguage.en;
		List<ResourceElementsList> listOfresourceElementList = new ArrayList<ResourceElementsList>();
		ResourceElementsList rel = new ResourceElementsList();
		List<String> elementList = new ArrayList<String>();
		elementList.add(area);
		rel.setElementList(elementList);
		rel.setResource(resource);
		listOfresourceElementList.add(rel);

		CodeAdditionDecission areaDecission = new AreaIntersectionDecision(areaRequested);
		Map<String, List<Long>> map = elementResourceMapIntelligence.create2ElementResourceMap(factsheetLanguage, ad,
				areaDecission, listOfresourceElementList);
		assertNull(map.get(areaRequested));
		areaDecission = new AreaIntersectionDecision(area);
		map = elementResourceMapIntelligence.create2ElementResourceMap(factsheetLanguage, ad, areaDecission,
				listOfresourceElementList);
		assertNotNull(map.get(area));

	}

	@Test
	public void testcreate2ElementResourceMap2() {
		Long resource = 5l;
		String area = "27.1";
		String areaRequested = "27";
		FactsheetDigger ad = new FactsheetAreaDigger();

		FactsheetLanguage factsheetLanguage = FactsheetLanguage.en;
		List<ResourceElementsList> listOfresourceElementList = new ArrayList<ResourceElementsList>();
		ResourceElementsList rel = new ResourceElementsList();
		List<String> elementList = new ArrayList<String>();
		elementList.add(area);
		rel.setElementList(elementList);
		rel.setResource(resource);
		listOfresourceElementList.add(rel);
		CodeAdditionDecission areaDecission = new AreaIntersectionDecision(areaRequested);
		Map<String, List<Long>> map = elementResourceMapIntelligence.create2ElementResourceMap(factsheetLanguage, ad,
				areaDecission, listOfresourceElementList);
		assertNotNull(map.get(areaRequested));
	}

	@Autowired
	public final void setElementResourceMapIntelligence(ElementResourceMapIntelligence elementResourceMapIntelligence) {
		this.elementResourceMapIntelligence = elementResourceMapIntelligence;
	}

}
