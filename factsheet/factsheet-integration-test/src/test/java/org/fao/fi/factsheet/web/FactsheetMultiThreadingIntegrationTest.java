package org.fao.fi.factsheet.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.fao.fi.commons.integrationtest.tools.RestWebserviceIntegrationTest;
import org.fao.fi.factsheetwebservice.domain.DomainList;
import org.fao.fi.factsheetwebservice.domain.FactsheetList;
import org.junit.Test;

public class FactsheetMultiThreadingIntegrationTest {
	RestWebserviceIntegrationTest test = new RestWebserviceIntegrationTest(
			"http://ldvapp08.fao.org:9999/factsheet-web-0.0.4/", "http://localhost:8080/factsheet-web/");

	String response;

	@Test
	public void testMultiThreads() {

		FireUrlThread fut1 = new FireUrlThread("domain/", DomainList.class);
		FireUrlThread fut2 = new FireUrlThread("domain/resource", DomainList.class);
		FireUrlThread fut3 = new FireUrlThread("domain/resource/language", DomainList.class);
		fut1.start();
		fut2.start();
		fut3.start();

		do {
		} while (!fut1.getState().equals(Thread.State.TERMINATED) || !fut2.getState().equals(Thread.State.TERMINATED)
				|| !fut3.getState().equals(Thread.State.TERMINATED));

		DomainList domainList1 = (DomainList) fut1.getObject();
		FactsheetList factsheetList1 = (FactsheetList) fut2.getObject();
		FactsheetList factsheetList2 = (FactsheetList) fut3.getObject();
		assertEquals(17, domainList1.getRowCount());
		assertTrue(factsheetList1.getRowCount() > 350);
		assertTrue(factsheetList1.getRowCount() < 700);
		assertEquals(factsheetList1.getRowCount(), factsheetList2.getRowCount());

	}
}
