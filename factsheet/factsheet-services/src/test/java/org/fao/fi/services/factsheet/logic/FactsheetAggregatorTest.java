package org.fao.fi.services.factsheet.logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.logical.domain.factsheet.search.ResultItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-factsheet.xml" })
public class FactsheetAggregatorTest {

	FactsheetAggregator factsheetAggregator;
	FactsheetLanguage lang = FactsheetLanguage.en;

	@Test
	public void testGetFactsheetsPerDomain() {
		// assertTrue(factsheetAggregator.getFactsheetsPerDomain("resource",
		// FactsheetLanguage.en).size() > 0);
		// assertTrue(factsheetAggregator.getFactsheetsPerDomain("resource",
		// FactsheetLanguage.fr).size() > 0);
		// assertTrue(factsheetAggregator.getFactsheetsPerDomain("resource",
		// FactsheetLanguage.fr).size() >= 0);

		// list the french
		List<FactsheetDiscriminator> list = factsheetAggregator.getFactsheetsPerDomainAndLanguage("resource",
				FactsheetLanguage.fr);
		for (FactsheetDiscriminator factsheetDiscriminator : list) {
			System.out.println(factsheetDiscriminator.getFactsheet());
		}

	}

	/**
	 * Doubles found :
	 * 
	 * Double idcountrysectorFI-CP_JM
	 * 
	 * Double idorgfishcode_prog
	 */
	@Test
	public void testGetFactsheetsOfAllDomains1() {

		List<FactsheetDiscriminator> list = factsheetAggregator.getFactsheetsOfAllDomains(lang);
		Set<String> idSet = new HashSet<String>();
		for (FactsheetDiscriminator d : list) {
			String id = d.getDomain() + " " + d.getFactsheet();
			if (idSet.contains(id)) {
				System.out.println("Double id" + id);
			}
			idSet.add(id);
		}
		assertTrue(list.size() > 2000);
		System.out.println(list.size());

		if (list.size() != idSet.size()) {
			System.out.println("Size of the list: " + list.size() + ". Number of doubles detected : "
					+ (list.size() - idSet.size()));
		}

		assertTrue((list.size() * 0.98) < idSet.size());
	}

	@Test
	public void testEntitled() {
		ResultItem r = new ResultItem();
		r.setFid(3);
		r.setUrl("ar");
		assertFalse(factsheetAggregator.entitled(r));
		r.setStatus(FactsheetStatus.PUBLISHED_DYNAMIC);
		r.setFid(123456789);
		r.setUrl("en");
		assertTrue(factsheetAggregator.entitled(r));
	}

	@Autowired
	public final void setFactsheetAggregator(FactsheetAggregator factsheetAggregator) {
		this.factsheetAggregator = factsheetAggregator;
	}

}
