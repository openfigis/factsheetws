package org.fao.fi.factsheet.search.binding;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.fao.fi.commons.FigisException;
import org.fao.fi.commons.JaxbValidationEventHandler;
import org.fao.fi.logical.domain.factsheet.search.FsSearchResult;
import org.springframework.stereotype.Component;

import com.googlecode.ehcache.annotations.Cacheable;

@Component
public class FactsheetSearchXmlBinder {

	@Cacheable(cacheName = "FactsheetServicesCache")
	public FsSearchResult unmarshal(String url) {
		try {
			URL uRL = new URL(url);
			JAXBContext context = JAXBContext.newInstance(FsSearchResult.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JaxbValidationEventHandler handler = new JaxbValidationEventHandler();
			unmarshaller.setEventHandler(handler);
			return (FsSearchResult) unmarshaller.unmarshal(uRL);
		} catch (JAXBException e) {
			throw new FigisException(e);
		} catch (MalformedURLException e) {
			throw new FigisException(e);
		}
	}

}
