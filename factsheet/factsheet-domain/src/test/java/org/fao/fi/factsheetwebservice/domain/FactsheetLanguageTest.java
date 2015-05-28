package org.fao.fi.factsheetwebservice.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class FactsheetLanguageTest {

	@Test
	public void testParseLanguage() {
		assertEquals(FactsheetLanguage.en, FactsheetLanguage.parseLanguage("en"));
		assertEquals(FactsheetLanguage.en, FactsheetLanguage.parseLanguage("EN"));

		assertEquals(FactsheetLanguage.fr, FactsheetLanguage.parseLanguage("fr"));
		assertEquals(FactsheetLanguage.fr, FactsheetLanguage.parseLanguage("FR"));

		try {
			FactsheetLanguage.parseLanguage("NL");
			fail();
		} catch (Exception e) {

		}
	}

}
