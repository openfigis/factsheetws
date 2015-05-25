package org.fao.fi.fisheryresources.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class FisheryResourceNamespaceTest {
	FisheryResourceNamespace n = new FisheryResourceNamespace();

	String namespaceURI = "http://www.fao.org/fi/figis/devcon/";
	String namespacePrefix = "fi";

	@Test
	public void testGetNamespaceURI() {
		assertEquals(namespaceURI, n.getNamespaceURI(namespacePrefix));
	}

	@Test
	public void testGetPrefix() {
		try {
			n.getPrefix(namespaceURI);
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testGetPrefixes() {
		try {
			n.getPrefixes(namespaceURI);
			fail();
		} catch (Exception e) {
		}
	}

}
