package org.fao.fi.factsheetwebservice.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class FactsheetDomainTest {

	@Test
	public void testParseDomain() {
		try {
			FactsheetDomain.parseDomain("");
			fail();
		} catch (Exception e) {
		}
		try {
			FactsheetDomain.parseDomain("xx");
			fail();
		} catch (Exception e) {
		}
		
		assertEquals(FactsheetDomain.equipment.name(), "equipment");
	}

}
