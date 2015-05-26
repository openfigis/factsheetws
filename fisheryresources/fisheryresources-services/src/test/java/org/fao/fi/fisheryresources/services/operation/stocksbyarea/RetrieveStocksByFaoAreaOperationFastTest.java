package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.fao.fi.fisheryresources.domain.stocksby.StocksByFaoArea;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-fisheryresources-StocksByFaoArea.xml" })
public class RetrieveStocksByFaoAreaOperationFastTest {

	private RetrieveStocksByFaoAreaOperation retrieveStocksByFaoAreaOperation;

	/**
	 * Test area are
	 * 
	 * 57 47 18 34 27 37 21 41 31 51 71 81 61 87 88
	 * 
	 * 
	 * A request with as input parameter Area results in:
	 * 
	 * an inclusion element with stocks comprised by input parameter Area and its sublevels.
	 * 
	 * 
	 */
	@Test
	@Ignore
	public void testExecute1() {
		String faoArea = "41";
		StocksByFaoArea response = (StocksByFaoArea) retrieveStocksByFaoAreaOperation.execute(faoArea);
		assertNotNull(response);
		assertEquals(1, response.getInclusion().getAqResList().size());
		assertEquals(1, response.getIntersection().getAqResList().size());
	}

	@Test
	@Ignore
	public void testExecute2() {
		String faoArea = "27.2.4";
		StocksByFaoArea response = (StocksByFaoArea) retrieveStocksByFaoAreaOperation.execute(faoArea);
		assertNotNull(response);
		// assertEquals(4, response.getInclusion().getAqResList().size());
		// assertEquals(2, response.getIntersection().getAqResList().size());
	}

	@Autowired
	public final void setRetrieveStocksByFaoAreaOperation(
			RetrieveStocksByFaoAreaOperation retrieveStocksByFaoAreaOperation) {
		this.retrieveStocksByFaoAreaOperation = retrieveStocksByFaoAreaOperation;
	}

}
