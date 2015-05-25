package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import org.fao.fi.fisheryresources.domain.stocksby.Inclusion;
import org.fao.fi.fisheryresources.domain.stocksby.StocksByElement;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.CodeAdditionDecission;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.RetrieveStocksByElementOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InclusionPartOfOperation extends RetrieveStocksByElementOperation {

	public Inclusion execute(String code) {
		CodeAdditionDecission cad = new AreaInclusionDecision(code);
		StocksByElement r = this.preExecute(code, cad);
		Inclusion inclusion = new Inclusion();
		inclusion.setAqResList(r.getAqResList());
		return inclusion;
	}

	@Autowired
	public final void setFactsheetAreaDigger(FactsheetAreaDigger factsheetAreaDigger) {
		this.setFactsheetDigger(factsheetAreaDigger);
	}

}
