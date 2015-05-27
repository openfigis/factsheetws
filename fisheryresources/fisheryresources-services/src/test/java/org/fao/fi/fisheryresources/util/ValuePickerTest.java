package org.fao.fi.fisheryresources.util;

import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

public class ValuePickerTest {

	@Test
	public void pickNode1() throws MalformedURLException {
		String xmlUrl = "http://firms.fao.org/firms/xml/resource/789789/fr";
		URL url = new URL(xmlUrl);
		String xPathExpresssion = "fi:FIGISDoc/fi:AqRes/fi:AqResIdent";
		try {
			ValuePicker.pickNode(url, xPathExpresssion);
			fail();
		} catch (Exception e) {
		}

	}

	@Test
	public void pickNode2() throws MalformedURLException {
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
