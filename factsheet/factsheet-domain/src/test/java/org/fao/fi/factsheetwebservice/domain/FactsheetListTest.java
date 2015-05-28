package org.fao.fi.factsheetwebservice.domain;

import java.io.File;

import org.fao.fi.commons.test.tools.JaxbXmlTestCase;

public class FactsheetListTest extends JaxbXmlTestCase {

	public FactsheetListTest() {
		this.file = new File("src/test/resources/webservice/FactsheetList.xml");

		FactsheetList o = new FactsheetList();
		FactsheetDiscriminator a = new FactsheetDiscriminator();
		a.setLanguage(FactsheetLanguage.en);
		a.setDomain(FactsheetDomain.resource);
		a.setFactsheet("4");

		FactsheetDiscriminator b = new FactsheetDiscriminator();
		b.setLanguage(FactsheetLanguage.en);
		b.setDomain(FactsheetDomain.resource);
		b.setFactsheet("5");

		o.getFactsheetList().add(a);
		o.getFactsheetList().add(b);
		o.setRowCount(o.getFactsheetList().size());
		this.expectedResult = o;
		this.domainClass = FactsheetList.class;
	}

}
