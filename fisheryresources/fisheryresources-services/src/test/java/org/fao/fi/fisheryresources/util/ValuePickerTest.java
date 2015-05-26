package org.fao.fi.fisheryresources.util;

import static org.junit.Assert.*;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class ValuePickerTest {

	// @Test
	public void testPickNodeURLString() {

	}

	// @Test
	public void testPickString() {

	}

	@Test
	public void testPickNode() throws MalformedURLException {
		String xmlUrl = "http://firms.fao.org/firms/xml/resource/11/fr";
		URL url = new URL(xmlUrl);
		String xPathExpresssion = "fi:FIGISDoc/fi:AqRes/fi:AqResIdent";

		try {
			ValuePicker.pickNode(url, xPathExpresssion);
			fail();
		} catch (Exception e) {
		}
	}

}
