package org.fao.fi.logical.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.junit.Test;

public class RetrieveFactsheetPerDomainListRequestTest {

	/**
	 * this test is not exhaustive because it does not simulate more than 1 execution of Java.
	 * 
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testHashCode() throws InterruptedException {

		RetrieveFactsheetPerDomainListRequest r1 = new RetrieveFactsheetPerDomainListRequest();
		r1.setDomain("resource");
		r1.setLanguage(FactsheetLanguage.en);

		RetrieveFactsheetPerDomainListRequest r2 = new RetrieveFactsheetPerDomainListRequest();
		r2.setDomain("resource");
		r2.setLanguage(FactsheetLanguage.en);
		assertEquals(r1.hashCode(), r2.hashCode());
		r2.setLanguage(FactsheetLanguage.fr);
		assertFalse(r1.hashCode() == r2.hashCode());

		assertEquals(FactsheetLanguage.en.hashCode(), FactsheetLanguage.en.hashCode());
		assertEquals(FactsheetLanguage.en.hashCode(), FactsheetLanguage.en.hashCode());

	}
}
