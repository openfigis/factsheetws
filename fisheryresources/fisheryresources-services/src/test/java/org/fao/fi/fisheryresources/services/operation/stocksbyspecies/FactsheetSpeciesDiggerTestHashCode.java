package org.fao.fi.fisheryresources.services.operation.stocksbyspecies;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FactsheetSpeciesDiggerTestHashCode {

	@Test
	public void testHashCode() throws InterruptedException {
		FactsheetSpeciesDigger f1 = new FactsheetSpeciesDigger();
		FactsheetSpeciesDigger f2 = new FactsheetSpeciesDigger();
		assertEquals(f1.hashCode(), f2.hashCode());
	}

}
