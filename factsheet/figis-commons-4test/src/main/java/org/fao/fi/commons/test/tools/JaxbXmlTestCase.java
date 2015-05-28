package org.fao.fi.commons.test.tools;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.fao.fi.commons.JaxbValidationEventHandler;

/**
 * Making a seperate one for marshalling because that one will be used also separately
 * 
 * @author Erik van Ingen
 * 
 */
public abstract class JaxbXmlTestCase extends JaxbXmlMarshallTestCase {

	public void testUnmarshall() throws FileNotFoundException, JAXBException {
		JAXBContext context = JAXBContext.newInstance(domainClass);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		JaxbValidationEventHandler handler = new JaxbValidationEventHandler();
		unmarshaller.setEventHandler(handler);

		Object object = unmarshaller.unmarshal(file);
		if (!handler.getMessages().isEmpty()) {
			String errorMessage = "XML parse errors:";
			for (Object message : handler.getMessages()) {
				errorMessage += "\n" + message;
			}
			throw new JAXBException(errorMessage);
		}

		// test equality
		assertEquals("Unmarshalling object", expectedResult, object);
	}

}
