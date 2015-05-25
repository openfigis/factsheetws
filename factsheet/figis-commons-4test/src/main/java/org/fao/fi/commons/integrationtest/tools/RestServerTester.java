package org.fao.fi.commons.integrationtest.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.fao.fi.commons.FigisException;

public class RestServerTester {

	String serverUrl = ""; // initializing from null to an empty String

	public RestServerTester() {
	}

	public RestServerTester(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public final String testResourceAtUrl(URL url) throws Exception {

		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			// connection.setRequestProperty("ACCEPT", "application/xml");
			// connection.setRequestProperty("ACCEPT",
			// "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			// connection.setRequestProperty("ACCEPT", "*/*");
			connection.setRequestProperty("ACCEPT", "application/xml");
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String firstLineOfText = reader.readLine();
			// you can also read the whole thing and then test

			connection.disconnect();

			return firstLineOfText;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		throw new Exception("could not establish connection to " + url.toExternalForm());
	}

	public Object unMarshall(String url, Class<?> clazz) {
		url = serverUrl + url;
		System.out.println(url);
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(clazz);
			Unmarshaller u = jc.createUnmarshaller();
			URL formalUrl = new URL(url);
			return u.unmarshal(formalUrl);
		} catch (JAXBException e) {
			throw new FigisException(e);
		} catch (MalformedURLException e) {
			throw new FigisException(e);
		}
	}

}
