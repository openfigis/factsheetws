package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.fao.fi.fisheryresources.services.operation.stocksbyelement.FactsheetDigger;
import org.junit.Test;

public class FactsheetAreaDiggerTest {

	FactsheetDigger factsheetDigger = new FactsheetAreaDigger();

	/**
	 * test with 1 area
	 * 
	 * @throws MalformedURLException
	 */
	@Test
	public void testDig() throws MalformedURLException {
		String factsheetUrl = "src/test/resources/testFactSheet4FactsheetDiggerTest1.xmll";
		File file = new File(factsheetUrl);
		List<String> list = factsheetDigger.dig(file.toURI().toURL());
		assertEquals(1, list.size());
		for (String string : list) {
			System.out.println(string);
			assertTrue(string.length() > 0);
		}
	}

	/**
	 * test with 2 area
	 * 
	 * Real examples are:
	 * 
	 * 
	 * 
	 * @throws IOException
	 */
	@Test
	public void testDigUpSpecies2() throws IOException {
		String factsheetUrl = "src/test/resources/testFactSheet4FactsheetDiggerTest2.xmll";
		File file = new File(factsheetUrl);
		List<String> list = factsheetDigger.dig(file.toURI().toURL());
		assertEquals(2, list.size());
		for (String string : list) {
			assertTrue(string.length() > 0);
		}
	}

	/**
	 * test with 2 area List with each 2 area.
	 * 
	 * 
	 */
	@Test
	public void testDigUpSpecies3() throws IOException {
		String factsheetUrl = "src/test/resources/testFactSheet4FactsheetDiggerTest3.xmll";
		File file = new File(factsheetUrl);

		List<String> list = factsheetDigger.dig(file.toURI().toURL());
		assertEquals(4, list.size());
		for (String string : list) {
			assertTrue(string.length() > 0);
		}
	}

	/**
	 * A factsheet with more than 1 area list.
	 * 
	 * 
	 * 
	 */
	@Test
	public void testDigUpSpecies4() throws IOException {
		String factsheetUrl = "http://firms.fao.org/firms/xml/resource/16001/en";
		URL url = new URL(factsheetUrl);

		List<String> list = factsheetDigger.dig(url);
		assertEquals(17, list.size());
	}

	@Test
	public void testDigUpSpecies10114() throws IOException {
		String factsheetUrl = "http://firms.fao.org/firms/xml/resource/10114/en";
		URL url = new URL(factsheetUrl);

		List<String> list = factsheetDigger.dig(url);
		assertEquals(3, list.size());
	}

}
