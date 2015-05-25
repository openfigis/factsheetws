package org.fao.fi.fisheryresources.web.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.fao.fi.fisheryresources.domain.stocksby.StocksBySpecies;
import org.junit.Test;

public class DomainMarshallerTest {

	@Test
	public void testXml2Java() {
		DomainMarshaller d = new DomainMarshaller();

		String xml = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><StocksBySpecies xmlns='http://www.fao.org/fi/figis/devcon/webservice' xmlns:ns2='http://purl.org/dc/elements/1.1/'><AqRes><FigisID Type='Observation'>156422</FigisID><FigisID Type='Object'>1</FigisID><ns2:Title>Albacore - Indian Ocean</ns2:Title><SpeciesList><SpeciesRef><ForeignID Code='ALB' CodeSystem='fao3alpha'/></SpeciesRef><SpeciesRef><ForeignID Code='ALB' CodeSystem='fao3alpha'/></SpeciesRef></SpeciesList><WaterAreaList><WaterAreaRef><ForeignID Code='IOTC' CodeSystem='iotc_comp'/></WaterAreaRef></WaterAreaList></AqRes><AqRes><FigisID Type='Observation'>311</FigisID><ns2:Title>Albacore - North Atlantic</ns2:Title><SpeciesList><SpeciesRef><ForeignID Code='ALB' CodeSystem='fao3alpha'/></SpeciesRef><SpeciesRef><ForeignID Code='ALB' CodeSystem='fao3alpha'/></SpeciesRef></SpeciesList><WaterAreaList><WaterAreaRef><ForeignID Code='ALB_N' CodeSystem='iccat_smu_alb'/></WaterAreaRef></WaterAreaList></AqRes></StocksBySpecies>";
		StocksBySpecies response = d.xml2Java(xml);
		assertNotNull(response);
		assertEquals(2, response.getAqResList().size());
	}

}
