package org.fao.fi.fisheryresources.web.it;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.fao.fi.commons.FigisException;
import org.fao.fi.commons.JaxbValidationEventHandler;
import org.fao.fi.fisheryresources.domain.stocksby.StocksBySpecies;

public class DomainMarshaller {

	public StocksBySpecies xml2Java(String inputXml) {
		StocksBySpecies object = null;
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(StocksBySpecies.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			JaxbValidationEventHandler handler = new JaxbValidationEventHandler();
			unmarshaller.setEventHandler(handler);
			object = (StocksBySpecies) unmarshaller.unmarshal(new StringReader(inputXml));
			if (!handler.getMessages().isEmpty()) {
				String errorMessage = "XML parse errors:";
				for (Object message : handler.getMessages()) {
					errorMessage += "\n" + message;
				}
				throw new FigisException(errorMessage);
			}
		} catch (JAXBException e) {
			throw new FigisException(e);
		}

		return object;
	}

}
