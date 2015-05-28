package org.fao.fi.commons.integrationtest.tools;

/**
 * Assumed is that developers work on Windows and the integration test are running on Linux.
 * 
 * 
 * @author Erik van Ingen
 * 
 */

public class WindosLinuxTrick {

	private String windowsUrl;
	private String linuxUrl;

	public WindosLinuxTrick(String windowsUrl, String linuxUrl) {
		this.windowsUrl = windowsUrl;
		this.linuxUrl = linuxUrl;
	}

	public String getOsUrl() {
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().startsWith("windows")) {
			return windowsUrl;
		} else {
			return linuxUrl;
		}
	}

}
