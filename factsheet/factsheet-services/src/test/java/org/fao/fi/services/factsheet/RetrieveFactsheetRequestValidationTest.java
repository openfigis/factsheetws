package org.fao.fi.services.factsheet;

import static org.junit.Assert.fail;

import org.fao.fi.logical.domain.RetrieveFactsheetRequest;
import org.junit.Test;

public class RetrieveFactsheetRequestValidationTest {

	@Test
	public void testRetrieveFactsheetRequestValidation() {

		RetrieveFactsheetRequest request = new RetrieveFactsheetRequest();
		try {
			new RetrieveFactsheetRequestValidation(request);
			fail();
		} catch (Exception e) {
		}

		try {
			new RetrieveFactsheetRequestValidation(request);
			fail();
		} catch (Exception e) {
		}
	}

}
