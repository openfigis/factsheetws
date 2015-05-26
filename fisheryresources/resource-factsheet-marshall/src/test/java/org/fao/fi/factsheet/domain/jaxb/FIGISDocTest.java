package org.fao.fi.factsheet.domain.jaxb;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.custommonkey.xmlunit.XMLTestCase;
import org.fao.fi.commons.JaxbValidationEventHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-fisheryresources-test.xml" })
public class FIGISDocTest extends XMLTestCase {

	String fileName = "src/test/resources/firmsresource10315en.xml";

	@Test
	public void testUnmarshall() throws FileNotFoundException, JAXBException {

		// get input XML from file system
		File file = new File(fileName);

		JAXBContext context = JAXBContext.newInstance(FIGISDoc.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		JaxbValidationEventHandler handler = new JaxbValidationEventHandler();
		unmarshaller.setEventHandler(handler);

		FIGISDoc object = (FIGISDoc) unmarshaller.unmarshal(file);
		if (!handler.getMessages().isEmpty()) {
			String errorMessage = "XML parse errors:";
			for (Object message : handler.getMessages()) {
				errorMessage += "\n" + message;
			}
			throw new JAXBException(errorMessage);
		}

		assertNotNull(object);
		// get expected result from test factory
		// FIGISDoc expectedResult = FIGISDocFactory.create();

		// test equality
		// assertEquals("Unmarshalling FIGISDoc", expectedResult, object);
	}

}
