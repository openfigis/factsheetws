package org.fao.fi.commons.integrationtest.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.fao.fi.commons.FigisException;

public class RestWebserviceIntegrationTest {

	WindosLinuxTrick trick;
	RestServerTester restServerTester = new RestServerTester();

	/**
	 * Construct object with the url for the linux and the local (windows)
	 * server.
	 * 
	 * @param linuxUrl
	 * @param windowsUrl
	 */
	public RestWebserviceIntegrationTest(String linuxUrl, String windowsUrl) {
		trick = new WindosLinuxTrick(windowsUrl, linuxUrl);
	}

	public String doGetOn(String url) {
		try {
			return restServerTester.testResourceAtUrl(new URL(trick.getOsUrl() + url));
		} catch (MalformedURLException e) {
			throw new FigisException(e);
		} catch (Exception e) {
			throw new FigisException(e);
		}
	}

	public Object unMarshall(String url, Class<?> clazz) {
		url = trick.getOsUrl() + url;
		return restServerTester.unMarshall(url, clazz);
	}

	public Object unMarshall(String url, Class<?> clazz, String acceptHttpHeader) {
		try {
			URL formalUrl = new URL(trick.getOsUrl() + url);
			HttpURLConnection connection = (HttpURLConnection) formalUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("ACCEPT", acceptHttpHeader);
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller u = jc.createUnmarshaller();
			return u.unmarshal(inputStream);
		} catch (MalformedURLException e) {
			throw new FigisException(e);
		} catch (ProtocolException e) {
			throw new FigisException(e);
		} catch (IOException e) {
			throw new FigisException(e);
		} catch (JAXBException e) {
			throw new FigisException(e);
		}
	}
}
