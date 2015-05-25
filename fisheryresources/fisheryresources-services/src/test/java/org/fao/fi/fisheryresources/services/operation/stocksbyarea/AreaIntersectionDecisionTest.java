package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fao.fi.fisheryresources.services.operation.stocksbyelement.CodeAdditionDecission;
import org.junit.Test;

public class AreaIntersectionDecisionTest {

	@Test
	public void testDecide() {
		CodeAdditionDecission d = new AreaIntersectionDecision("27");
		String elementCode = "27.3";
		Long resource = new Long(4l);
		Map<String, List<Long>> elementResourceMap = new HashMap<String, List<Long>>();
		d.decideAndAdd(resource, elementCode, elementResourceMap);
		// there should be 2 maps, with for 27 and one for 27.3. Probably in
		// this context the map for 27.3 is not even necessary anymore but it
		// won't hurt.
		assertEquals(2, elementResourceMap.size());

		elementCode = "26.3";
		elementResourceMap = new HashMap<String, List<Long>>();
		d.decideAndAdd(resource, elementCode, elementResourceMap);
		assertEquals(0, elementResourceMap.size());

		d = new AreaIntersectionDecision("25.6");
		elementResourceMap = new HashMap<String, List<Long>>();
		elementCode = "25";
		d.decideAndAdd(resource, elementCode, elementResourceMap);
		assertEquals(2, elementResourceMap.size());

	}

}
