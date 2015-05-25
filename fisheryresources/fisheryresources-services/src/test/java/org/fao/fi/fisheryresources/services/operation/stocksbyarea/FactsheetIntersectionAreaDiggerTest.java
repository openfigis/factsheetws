package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import org.fao.fi.fisheryresources.services.operation.stocksbyelement.FactsheetDigger;
import org.junit.Test;

public class FactsheetIntersectionAreaDiggerTest {
	FactsheetDigger factsheetDigger = new FactsheetIntersectionAreaDigger();

	/**
	 * test with 1 area
	 * 
	 * @throws MalformedURLException
	 */
	@Test
	public void testDig() throws MalformedURLException {
		String factsheetUrl = "src/test/resources/testFactSheet4FactsheetDiggerTest4.xmll";
		File file = new File(factsheetUrl);
		List<String> list = factsheetDigger.dig(file.toURI().toURL());
		assertEquals(2, list.size());
		for (String string : list) {
			System.out.println(string);
			assertTrue(string.length() > 0);
		}
	}

}
