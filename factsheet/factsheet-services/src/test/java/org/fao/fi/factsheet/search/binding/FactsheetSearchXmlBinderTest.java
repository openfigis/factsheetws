package org.fao.fi.factsheet.search.binding;

import static org.junit.Assert.assertEquals;

import org.fao.fi.logical.domain.factsheet.search.FsSearchResult;
import org.junit.Test;

public class FactsheetSearchXmlBinderTest {
	String url = "http://www.fao.org/fishery/factsheets/search/xml/psm/en";

	@Test
	public void testUnmarshal() {
		FactsheetSearchXmlBinder binder = new FactsheetSearchXmlBinder();

		FsSearchResult o = binder.unmarshal(url);
		assertEquals(1127, o.getResultItemList().size(), 5);
	}

	// @Test
	public void testUnmarshalFigis02() {
		url = "http://figis02:8282/fishery/factsheets/search/xml/resource/en";
		FactsheetSearchXmlBinder binder = new FactsheetSearchXmlBinder();
		FsSearchResult o = binder.unmarshal(url);
		assertEquals(388, o.getResultItemList().size(), 5);
	}

}
