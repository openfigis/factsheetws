package org.fao.fi.factsheet.marshall;

import static org.junit.Assert.assertNotNull;

import org.fao.fi.factsheet.domain.jaxb.FIGISDoc;
import org.junit.Test;

public class FactsheetXmlMarshallTest {

	@Test
	public void testUnmarshal() {
		FactsheetXmlMarshall m = new FactsheetXmlMarshall();
		String url = "http://firms.fao.org/firms/xml/resource/10315/155383/en";
		// http://firms.fao.org/firms/xml/resource/2218/en
		
		FIGISDoc fIGISDoc = m.unmarshal(url);
		assertNotNull(fIGISDoc);
		assertNotNull(fIGISDoc.getAqRes());
		assertNotNull(fIGISDoc.getDataEntry());
		assertNotNull(fIGISDoc.getLang());
		assertNotNull(fIGISDoc.getObjectSource());
	}

}
