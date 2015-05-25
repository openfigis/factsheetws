package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import static org.junit.Assert.assertTrue;

import org.fao.fi.fisheryresources.FisheryResourcesBaseTest;
import org.fao.fi.fisheryresources.domain.stocksby.Inclusion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class InclusionPartOfOperationTest extends FisheryResourcesBaseTest {

	InclusionPartOfOperation inclusionPartOfOperation;

	@Test
	public void testExecute() {
		String code = "27.1";
		Inclusion inclusion = inclusionPartOfOperation.execute(code);
		assertTrue(inclusion.getAqResList().size() > 9);

	}

	@Autowired
	public final void setInclusionPartOfOperation(InclusionPartOfOperation inclusionPartOfOperation) {
		this.inclusionPartOfOperation = inclusionPartOfOperation;
	}

}
