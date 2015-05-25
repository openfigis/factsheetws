package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.fao.fi.commons.FigisException;
import org.junit.Test;

public class FaoAreaInterpreterTest {

	// @Test
	public void testCiviliseRawCode() {
		FaoAreaInterpreter i = new FaoAreaInterpreter();
		String rawFaoAreaCode;
		FaoArea faoArea;

		rawFaoAreaCode = "27.3.b.23.x";
		faoArea = i.civiliseRawCode(rawFaoAreaCode);
		assertEquals(FaoAreaType.subunit, faoArea.getFaoAreaType());
		assertEquals(rawFaoAreaCode, faoArea.getValue());

		rawFaoAreaCode = "27.3.b.23";
		faoArea = i.civiliseRawCode(rawFaoAreaCode);
		assertEquals(FaoAreaType.subdivision, faoArea.getFaoAreaType());
		assertEquals(rawFaoAreaCode, faoArea.getValue());

		rawFaoAreaCode = "27.3.b";
		faoArea = i.civiliseRawCode(rawFaoAreaCode);
		assertEquals(FaoAreaType.division, faoArea.getFaoAreaType());
		assertEquals(rawFaoAreaCode, faoArea.getValue());

		rawFaoAreaCode = "27.3";
		faoArea = i.civiliseRawCode(rawFaoAreaCode);
		assertEquals(FaoAreaType.subarea, faoArea.getFaoAreaType());
		assertEquals(rawFaoAreaCode, faoArea.getValue());

		rawFaoAreaCode = "27";
		faoArea = i.civiliseRawCode(rawFaoAreaCode);
		assertEquals(FaoAreaType.area, faoArea.getFaoAreaType());
		assertEquals(rawFaoAreaCode, faoArea.getValue());

	}

	// @Test
	public void testCiviliseRawCodeMonkey() {
		try {
			FaoAreaInterpreter i = new FaoAreaInterpreter();
			i.civiliseRawCode("22/2/2/;");
			fail();
		} catch (FigisException e) {
		}
		try {
			FaoAreaInterpreter i = new FaoAreaInterpreter();
			i.civiliseRawCode("2");
		} catch (FigisException e) {
			fail();
		}
		try {
			FaoAreaInterpreter i = new FaoAreaInterpreter();
			i.civiliseRawCode("2.2");
		} catch (FigisException e) {
			e.printStackTrace();
			fail();
		}
	}

	// @Test
	public void testIncludes1() {
		FaoAreaInterpreter i = new FaoAreaInterpreter();
		String big;
		String small;
		big = "27.3.b";
		small = "27.3.b.23";
		assertTrue(i.includes(big, small));
		big = "27.3.b";
		small = "27.3.b";
		assertTrue(i.includes(big, small));
		big = "27.3.b";
		small = "27.3";
		assertFalse(i.includes(big, small));
		try {
			i.includes("@", small);
			fail();
		} catch (FigisException e) {
		}

	}

	// @Test
	public void testIncludes2() {
		FaoAreaInterpreter i = new FaoAreaInterpreter();
		String big;
		String small;
		big = "27.3.b";
		small = "26.3.b.23";
		assertFalse(i.includes(big, small));
		big = "27.3";
		small = "27.3.b.23";
		assertTrue(i.includes(big, small));

	}

	@Test
	public void testIntersects() {
		FaoAreaInterpreter i = new FaoAreaInterpreter();
		String first;
		String second;
		first = "27.3.b";
		second = "26.3.b.23";
		assertFalse(i.intersects(first, second));
		first = "27.3";
		second = "27.3.b.23";
		assertTrue(i.intersects(first, second));
		first = "27.3.b";
		second = "27.3";
		assertTrue(i.intersects(first, second));
		first = "27";
		second = "28";
		assertFalse(i.intersects(first, second));
		first = "27.1.1.1.1";
		second = "27";
		assertTrue(i.intersects(first, second));

	}

}
