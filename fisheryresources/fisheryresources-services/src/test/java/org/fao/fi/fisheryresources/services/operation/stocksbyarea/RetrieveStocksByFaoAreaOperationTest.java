package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.fao.fi.fisheryresources.FisheryResourcesBaseTest;
import org.fao.fi.fisheryresources.domain.stocksby.StocksByFaoArea;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RetrieveStocksByFaoAreaOperationTest extends FisheryResourcesBaseTest {

	private RetrieveStocksByFaoAreaOperation retrieveStocksByFaoAreaOperation;

	/**
	 * Test area are
	 * 
	 * 57 47 18 34 27 37 21 41 31 51 71 81 61 87 88
	 */
	@Test
	@Ignore
	public void testExecute1() {
		String faoArea = "57";
		StocksByFaoArea response = (StocksByFaoArea) retrieveStocksByFaoAreaOperation.execute(faoArea);
		assertNotNull(response);
		assertNotNull(response.getInclusion().getAqResList());
		assertNotNull(response.getIntersection().getAqResList());
	}

	@Test
	@Ignore
	public void testExecute2() {
		String faoArea = "27.2.4";
		StocksByFaoArea response = (StocksByFaoArea) retrieveStocksByFaoAreaOperation.execute(faoArea);
		assertNotNull(response);
	}

	@Test
	@Ignore
	public void testExecute3() {
		String faoArea = "27.1";
		StocksByFaoArea response = (StocksByFaoArea) retrieveStocksByFaoAreaOperation.execute(faoArea);
		assertNotNull(response);
		// assertNull(response.getInclusion());
		// assertNull(response.getIntersection());
	}

	// @Test
	public void testExecute4() {
		String faoArea = "27";
		StocksByFaoArea response = (StocksByFaoArea) retrieveStocksByFaoAreaOperation.execute(faoArea);
		assertNotNull(response);
		assertTrue(response.getInclusion().getAqResList().size() > 0);
		assertTrue(response.getIntersection().getAqResList().size() > 0);
	}

	@Autowired
	public final void setRetrieveStocksByFaoAreaOperation(
			RetrieveStocksByFaoAreaOperation retrieveStocksByFaoAreaOperation) {
		this.retrieveStocksByFaoAreaOperation = retrieveStocksByFaoAreaOperation;
	}

}
