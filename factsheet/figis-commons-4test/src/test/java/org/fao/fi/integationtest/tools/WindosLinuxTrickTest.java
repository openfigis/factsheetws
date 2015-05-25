package org.fao.fi.integationtest.tools;

import static org.junit.Assert.assertEquals;

import org.fao.fi.commons.integrationtest.tools.WindosLinuxTrick;
import org.junit.Test;

public class WindosLinuxTrickTest {

	@Test
	public void testGetOsUrl() {
		String linuxUrl = "http://figis02:8888/fisheryresources-web-0.0.1/";
		String windowsUrl = "http://localhost:8080/fisheryresources-web/";
		WindosLinuxTrick trick = new WindosLinuxTrick(windowsUrl, linuxUrl);
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().startsWith("windows")) {
			assertEquals(windowsUrl, trick.getOsUrl());
		} else {
			assertEquals(linuxUrl, trick.getOsUrl());
		}
	}

}
