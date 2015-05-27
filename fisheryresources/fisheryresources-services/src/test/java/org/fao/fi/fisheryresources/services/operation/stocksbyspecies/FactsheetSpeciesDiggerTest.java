package org.fao.fi.fisheryresources.services.operation.stocksbyspecies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.fao.fi.fisheryresources.services.operation.stocksbyelement.FactsheetDigger;
import org.junit.Test;

public class FactsheetSpeciesDiggerTest {
	FactsheetDigger factsheetDigger = new FactsheetSpeciesDigger();

	// FactsheetService factsheetService;

	/**
	 * test with 1 species
	 * 
	 * @throws MalformedURLException
	 */
	@Test
	public void testDigUpSpecies1() throws MalformedURLException {
		String factsheetUrl = "src/test/resources/testFactSheet4FactsheetDiggerTest1.xmll";
		File file = new File(factsheetUrl);
		List<String> list = factsheetDigger.dig(file.toURI().toURL());
		assertEquals(1, list.size());
		for (String string : list) {
			assertTrue(string.length() > 0);
		}
	}

	/**
	 * test with 2 species
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
	 * test with 2 SpeciesList with each 2 species.
	 * 
	 * Real examples are:
	 * 
	 * @throws IOException
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

	// http://firms.fao.org/firms/xml/resource/16001/en

	/**
	 * A factsheet with more than 1 species list.
	 * 
	 * 
	 * //http://firms.fao.org/firms/xml/resource/16001/en
	 * 
	 */
	@Test
	public void testDigUpSpecies4() throws IOException {
		String factsheetUrl = "http://firms.fao.org/firms/xml/resource/16001/en";
		URL url = new URL(factsheetUrl);
		List<String> list = factsheetDigger.dig(url);
		assertEquals(20, list.size(), 5);
	}

	@Test
	public void testDigUpSpecies10114() throws IOException {
		String factsheetUrl = "http://firms.fao.org/firms/xml/resource/10114/en";
		URL url = new URL(factsheetUrl);
		List<String> list = factsheetDigger.dig(url);
		assertEquals(1, list.size());
	}

}
