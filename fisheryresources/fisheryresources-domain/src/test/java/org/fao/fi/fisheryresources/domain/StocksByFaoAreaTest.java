package org.fao.fi.fisheryresources.domain;

import java.io.File;

import org.fao.fi.commons.test.tools.JaxbXmlTestCase;
import org.fao.fi.fisheryresources.domain.stocksby.StocksBySpecies;

public class StocksByFaoAreaTest extends JaxbXmlTestCase {

	public StocksByFaoAreaTest() {
		this.file = new File("src/test/resources/fisheryResources/fisheryresources.xml");
		this.expectedResult = StocksBySpeciesFactory.create();
		this.domainClass = StocksBySpecies.class;
	}

}
