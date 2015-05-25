package org.fao.fi.logical.domain.factsheet.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.fao.fi.commons.test.tools.JaxbXmlTestCase;

public class FsSearchResultJaxbTest extends JaxbXmlTestCase {

	public FsSearchResultJaxbTest() {
		this.file = new File("src/test/resources/FsSearchResult.xml");
		ResultItem resultItem = new ResultItem();
		resultItem.setFid(1625313278);
		resultItem.setMid(1015);
		resultItem.setRating(100);
		resultItem.setStatus((short) 3);
		resultItem.setDs("staticxml.vmstopic");
		resultItem.setName("Security of VMS information");
		resultItem.setType("weblink");
		resultItem.setUrl("/fi/website/FIRetrieveAction.do?dom=vmstopic&amp;xml=14_Security_Of_VMS_Information.xml&amp;lang=en");
		
		FsSearchResult result = new FsSearchResult();
		result.setRowcount(1);
		result.setTime(10);
		List<ResultItem> list = new ArrayList<ResultItem>();
		list.add(resultItem);
		result.setResultItemList(list);
		
		this.expectedResult = result;
		this.domainClass = FsSearchResult.class;
	}
}
