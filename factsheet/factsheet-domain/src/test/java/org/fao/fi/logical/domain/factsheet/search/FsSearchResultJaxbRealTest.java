package org.fao.fi.logical.domain.factsheet.search;

import java.util.List;

import org.fao.fi.commons.integrationtest.tools.RestServerTester;
import org.junit.Test;
import static org.junit.Assert.*;

public class FsSearchResultJaxbRealTest {

	RestServerTester restServerTester = new RestServerTester();

	//@Test
	public void testFsSearchResultJaxbTestResource() {

		String url = "http://www.fao.org/fishery/factsheets/search/xml/resource/en";

		FsSearchResult result = (FsSearchResult) restServerTester.unMarshall(url, FsSearchResult.class);

		assertEquals(387, result.getRowcount(), 30);
		assertEquals(result.getResultItemList().size(), result.getRowcount(), 1);

	}

	@Test
	public void testFsSearchResultJaxbTestArea() {
		String url = "http://www.fao.org/fishery/factsheets/search/xml/area/en";
		FsSearchResult result = (FsSearchResult) restServerTester.unMarshall(url, FsSearchResult.class);

		assertEquals(18, result.getRowcount(), 30);
		assertEquals(result.getResultItemList().size(), result.getRowcount(), 1);
		List<ResultItem> list = result.getResultItemList();
		int i = 0;
		for (ResultItem resultItem : list) {
			System.out.println(resultItem.getStatus());
			if (resultItem.getStatus() > 1 && resultItem.getStatus() < 4) {
				i++;
			}
		}
		assertEquals(result.getRowcount(), i, 1);
	}

}
