package org.fao.fi.services.factsheet.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.junit.Test;
import org.w3c.dom.Document;

public class FactsheetWebServiceClientTest {

	FactsheetWebServiceClient c = new FactsheetWebServiceClient();

	@Test
	public void testFactsheetWebServiceClient() {
		FactsheetWebServiceClient c = new FactsheetWebServiceClient("http://www.fao.org/figis/ws/factsheets/");
		assertEquals(19, c.retrieveDomainList().getDomainList().size(), 1);
	}

	@Test
	public void testRetrieveDomainList() {
		assertEquals(19, c.retrieveDomainList().getDomainList().size(), 1);
	}

	@Test
	public void testRetrieveFactsheetListPerDomain() {
		assertEquals(19, c.retrieveFactsheetListPerDomain(FactsheetDomain.area).getFactsheetList().size(), 1);

	}

	@Test
	public void testRetrieveFactsheetListPerDomainAndLanguage() {
		assertEquals(557, c.retrieveFactsheetListPerDomainAndLanguage(FactsheetDomain.resource, FactsheetLanguage.en)
				.getRowCount(), 30);
	}

	@Test
	public void testRetrieveLanguageListInDomain4ThisFactsheet() {
		assertEquals(1, c.retrieveLanguageListInDomain4ThisFactsheet(FactsheetDomain.resource, "10315").getRowCount(),
				1);
	}

	@Test
	public void testRetrieveFactsheet() {
		Document document = c.retrieveFactsheet("13384", FactsheetDomain.resource, FactsheetLanguage.en);
		assertNotNull(document);
		parseAndPrint(document);
	}

	@Test
	public void testPickNodeWithXpath() {
		Document document = c
				.pickNodeWithXpath("domain/resource/factsheet/10314/language/en/xpath/FIGISDoc/AqRes/AqResIdent");
		parseAndPrint(document);
	}

	@Test
	public void testPickNodeListWithXpath() {
		Document document = c
				.pickNodeListWithXpath("domain/resource/factsheet/10102/language/en/xpathdescendant/StandardExploitRate");
		parseAndPrint(document);
	}

	private void parseAndPrint(Document document) {
		try {
			Source source = new DOMSource(document);
			StringWriter stringWriter = new StringWriter();
			Result result = new StreamResult(stringWriter);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.transform(source, result);
			System.out.println(stringWriter.getBuffer().toString());
		} catch (TransformerConfigurationException e) {
		} catch (TransformerException e) {
		}
	}

}
