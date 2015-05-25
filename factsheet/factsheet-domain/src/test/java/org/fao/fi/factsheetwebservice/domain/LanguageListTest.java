package org.fao.fi.factsheetwebservice.domain;

import java.io.File;

import org.fao.fi.commons.test.tools.JaxbXmlTestCase;

public class LanguageListTest extends JaxbXmlTestCase {

	public LanguageListTest() {
		this.file = new File("src/test/resources/webservice/LanguageList.xml");

		LanguageList o = new LanguageList();
		o.getLanguageList().add(FactsheetLanguage.en);
		o.getLanguageList().add(FactsheetLanguage.fr);
		o.setRowCount(o.getLanguageList().size());
		this.expectedResult = o;

		this.domainClass = LanguageList.class;
	}

}
