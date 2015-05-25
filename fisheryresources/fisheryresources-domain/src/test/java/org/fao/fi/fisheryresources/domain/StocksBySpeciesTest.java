package org.fao.fi.fisheryresources.domain;

import java.io.File;

import org.fao.fi.commons.test.tools.JaxbXmlTestCase;
import org.fao.fi.fisheryresources.domain.stocksby.Inclusion;
import org.fao.fi.fisheryresources.domain.stocksby.Intersection;
import org.fao.fi.fisheryresources.domain.stocksby.StocksByFaoArea;
import org.fao.fi.fisheryresources.domain.stocksby.StocksBySpecies;

public class StocksBySpeciesTest extends JaxbXmlTestCase {

	public StocksBySpeciesTest() {
		this.file = new File("src/test/resources/fisheryResources/StocksByFaoArea.xml");
		StocksBySpecies o = StocksBySpeciesFactory.create();
		Inclusion in = new Inclusion();
		in.setAqResList(o.getAqResList());
		Intersection is = new Intersection();
		is.setAqResList(o.getAqResList());
		StocksByFaoArea response = new StocksByFaoArea();
		response.setInclusion(in);
		response.setIntersection(is);
		this.expectedResult = response;
		this.domainClass = StocksByFaoArea.class;
	}

}
