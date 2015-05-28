package org.fao.fi.factsheetwebservice.domain;

import java.io.File;

import org.fao.fi.commons.test.tools.JaxbXmlTestCase;

public class DomainListTest extends JaxbXmlTestCase {

	public DomainListTest() {
		this.file = new File("src/test/resources/webservice/DomainList.xml");

		DomainList o = new DomainList();
		o.getDomainList().add(FactsheetDomain.resource);
		o.getDomainList().add(FactsheetDomain.culturespecies);
		o.setRowCount(o.getDomainList().size());
		this.expectedResult = o;

		this.domainClass = DomainList.class;
	}

}
