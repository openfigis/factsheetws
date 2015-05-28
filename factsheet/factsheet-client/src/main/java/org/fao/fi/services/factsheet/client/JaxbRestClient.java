package org.fao.fi.services.factsheet.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Helper client for accessing xml resources which be parsed by Jaxb.
 * 
 * @author Erik van Ingen
 * 
 */
class JaxbRestClient {

	private String restWebserverUrl;

	public JaxbRestClient(String restWebserverUrl) {
		this.restWebserverUrl = restWebserverUrl;
	}

	public Object doGet(String url, Class<?> clazz) {
		url = restWebserverUrl + url;
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(clazz);
			Unmarshaller u = jc.createUnmarshaller();
			URL formalUrl = new URL(url);
			return u.unmarshal(formalUrl);
		} catch (JAXBException e) {
			throw new FactsheetClientException(e);
		} catch (MalformedURLException e) {
			throw new FactsheetClientException(e);
		}
	}

}
