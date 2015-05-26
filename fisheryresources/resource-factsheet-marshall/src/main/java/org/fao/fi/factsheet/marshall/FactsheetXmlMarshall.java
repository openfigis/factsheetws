package org.fao.fi.factsheet.marshall;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.fao.fi.commons.JaxbValidationEventHandler;
import org.fao.fi.figis.devcon.FIGISDoc;
import org.springframework.stereotype.Component;

import com.googlecode.ehcache.annotations.Cacheable;

@Component
public class FactsheetXmlMarshall {

	@Cacheable(cacheName = "FisheryResourcesCacheDisk")
	public FIGISDoc unmarshal(String url) {
		try {
			URL uRL = new URL(url);
			JAXBContext context = JAXBContext.newInstance(FIGISDoc.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JaxbValidationEventHandler handler = new JaxbValidationEventHandler();
			unmarshaller.setEventHandler(handler);
			return (FIGISDoc) unmarshaller.unmarshal(uRL);
		} catch (JAXBException e) {
			throw new MarshallException(e);
		} catch (MalformedURLException e) {
			throw new MarshallException(e);
		}
	}

}
