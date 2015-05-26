package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import static org.junit.Assert.assertTrue;

import org.fao.fi.fisheryresources.FisheryResourcesBaseTest;
import org.fao.fi.fisheryresources.domain.stocksby.Intersection;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IntersectionPartOfOperationTest extends FisheryResourcesBaseTest {

	IntersectionPartOfOperation intersectionPartOfOperation;

	/**
	 * In the case of area 27 there are 133 factsheets with intersecting areas of 27. These are the factsheets: [10014,
	 * 10017, 10327, 10328, 10329, 10330, 10332, 10333, 10334, 10335, 10336, 10337, 10338, 10341, 10342, 10343, 10344,
	 * 10345, 10348, 10349, 10350, 10351, 10352, 10354, 10355, 10358, 10359, 10360, 10361, 10362, 10365, 10366, 10367,
	 * 10368, 10369, 10370, 10371, 10372, 10373, 10374, 10375, 10376, 10377, 10378, 10379, 10380, 10381, 10382, 10383,
	 * 10384, 10385, 10386, 10387, 10389, 10390, 10392, 10393, 10394, 10397, 10398, 10400, 10401, 10403, 10404, 10405,
	 * 10406, 10407, 10408, 10409, 10411, 10412, 10413, 10414, 10415, 10417, 10418, 10419, 10420, 10421, 10422, 10423,
	 * 10424, 10425, 10426, 10427, 10428, 10429, 10430, 10431, 10432, 10433, 10434, 10435, 10436, 10437, 10438, 10439,
	 * 10440, 10441, 10442, 10443, 10444, 10445, 10448, 10449, 10450, 10451, 10452, 10453, 10454, 10455, 10457, 10458,
	 * 10459, 10460, 10461, 10462, 10465, 10468, 10469, 10470, 10471, 10472, 10473, 10474, 10475, 10476, 10477, 10479,
	 * 10480, 10481, 10483, 10485]
	 * 
	 * 
	 */
	@Test
	public void testExecute() {
		String code = "27.1";
		Intersection intersection = intersectionPartOfOperation.execute(code);
		System.out.println(intersection.getAqResList());
		assertTrue(intersection.getAqResList().size() > 130);

	}

	// @Test
	public void testEnrichWithWater() {
		// covered by testExecute
	}

	@Autowired
	public final void setIntersectionPartOfOperation(IntersectionPartOfOperation intersectionPartOfOperation) {
		this.intersectionPartOfOperation = intersectionPartOfOperation;
	}

}
