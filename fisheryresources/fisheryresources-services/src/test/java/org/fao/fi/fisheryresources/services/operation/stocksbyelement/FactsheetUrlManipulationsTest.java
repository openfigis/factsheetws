package org.fao.fi.fisheryresources.services.operation.stocksbyelement;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FactsheetUrlManipulationsTest {

	@Test
	public void testXml2HtmlLanguaged() {
		String url = "http://firms.fao.org/firms/xml/resource/16001/en";
		String urlCorrected = "http://firms.fao.org/firms/resource/16001/en";
		assertEquals(urlCorrected, FactsheetUrlManipulations.xml2HtmlLanguaged(url));
	}

}
