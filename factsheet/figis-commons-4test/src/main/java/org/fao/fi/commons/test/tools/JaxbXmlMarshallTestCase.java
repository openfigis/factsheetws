package org.fao.fi.commons.test.tools;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.IgnoreTextAndAttributeValuesDifferenceListener;
import org.custommonkey.xmlunit.XMLTestCase;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class JaxbXmlMarshallTestCase extends XMLTestCase {

	// get input XML from file system
	protected File file;
	protected Class<?> domainClass;
	protected Object expectedResult;
	protected Object namespacePrefixMapper;

	public void testMarshall() throws SAXException, IOException, JAXBException {

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Writer out = new BufferedWriter(new OutputStreamWriter(outputStream));

		// set up JAXB marshalling context
		JAXBContext context = JAXBContext.newInstance(domainClass);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		if (namespacePrefixMapper != null) {
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", namespacePrefixMapper);
		}
		marshaller.marshal(expectedResult, out);

		// marshal to console - nice if you're looking
		Marshaller marshaller1 = context.createMarshaller();
		marshaller1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		if (namespacePrefixMapper != null) {
			marshaller1.setProperty("com.sun.xml.bind.namespacePrefixMapper", namespacePrefixMapper);
		}
		marshaller1.marshal(expectedResult, new BufferedWriter(new OutputStreamWriter(System.out)));

		// convert to InputSource for the comparison test
		InputSource marshallingResult = new InputSource(new ByteArrayInputStream(outputStream.toByteArray()));

		// get expected result from filesystem
		InputSource expectedResult = new InputSource(new FileInputStream(file));

		// comparison test
		Diff diff = new Diff(expectedResult, marshallingResult);
		diff.overrideDifferenceListener(new IgnoreTextAndAttributeValuesDifferenceListener());
		assertTrue("Marshalled Object matches expected XML " + diff, diff.similar());
	}
}
