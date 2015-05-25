package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import org.fao.fi.fisheryresources.domain.stocksby.StocksByFaoArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetrieveStocksByFaoAreaOperation {

	private InclusionPartOfOperation inclusionPartOfOperation;
	private IntersectionPartOfOperation intersectionPartOfOperation;

	public StocksByFaoArea execute(String code) {
		StocksByFaoArea response = new StocksByFaoArea();
		response.setInclusion(inclusionPartOfOperation.execute(code));
		response.setIntersection(intersectionPartOfOperation.execute(code));
		return response;
	}

	@Autowired
	public final void setInclusionPartOfOperation(InclusionPartOfOperation inclusionPartOfOperation) {
		this.inclusionPartOfOperation = inclusionPartOfOperation;
	}

	@Autowired
	public final void setIntersectionPartOfOperation(IntersectionPartOfOperation intersectionPartOfOperation) {
		this.intersectionPartOfOperation = intersectionPartOfOperation;
	}

}
