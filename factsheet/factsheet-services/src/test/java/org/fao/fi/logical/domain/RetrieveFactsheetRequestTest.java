package org.fao.fi.logical.domain;

import static org.junit.Assert.assertEquals;

import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.junit.Test;

public class RetrieveFactsheetRequestTest {

	@Test
	public void testHashCode() {
		RetrieveFactsheetRequest r1 = new RetrieveFactsheetRequest();
		r1.setDomain(FactsheetDomain.resource);
		r1.setFactsheet("2");
		r1.setLanguage(FactsheetLanguage.en);
		RetrieveFactsheetRequest r2 = new RetrieveFactsheetRequest();
		r2.setDomain(FactsheetDomain.resource);
		r2.setFactsheet("2");
		r2.setLanguage(FactsheetLanguage.en);
		assertEquals(r1.hashCode(), r2.hashCode());

	}

}
