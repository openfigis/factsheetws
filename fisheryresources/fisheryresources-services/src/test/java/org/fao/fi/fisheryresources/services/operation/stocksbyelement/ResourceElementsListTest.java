package org.fao.fi.fisheryresources.services.operation.stocksbyelement;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ResourceElementsListTest {

	@Test
	public void testGetResource() {
		ResourceElementsList rsl = new ResourceElementsList();
		Long l = new Long(7l);
		rsl.setResource(l);
		assertEquals(l, rsl.getResource());
	}

	@Test
	public void testGetElementList() {
		ResourceElementsList rsl = new ResourceElementsList();
		List<String> elementList = new ArrayList<String>();
		String value = "HUR";
		elementList.add(value);
		rsl.setElementList(elementList);
		assertEquals(1, rsl.getElementList().size());
		assertEquals(value, rsl.getElementList().get(0));
	}

}
