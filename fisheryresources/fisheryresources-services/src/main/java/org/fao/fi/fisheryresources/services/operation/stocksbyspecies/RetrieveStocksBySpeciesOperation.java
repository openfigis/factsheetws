package org.fao.fi.fisheryresources.services.operation.stocksbyspecies;

import org.fao.fi.fisheryresources.domain.stocksby.StocksByElement;
import org.fao.fi.fisheryresources.domain.stocksby.StocksBySpecies;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.CodeAdditionDecission;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.RetrieveStocksByElementOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetrieveStocksBySpeciesOperation extends RetrieveStocksByElementOperation {

	public StocksBySpecies execute(String code) {
		CodeAdditionDecission cad = new SpeciesDecision();
		StocksByElement r = this.preExecute(code, cad);
		StocksBySpecies response = new StocksBySpecies();
		response.setAqResList(r.getAqResList());
		return response;
	}

	@Autowired
	public final void setFactsheetSpeciesDigger(FactsheetSpeciesDigger factsheetSpeciesDigger) {
		this.setFactsheetDigger(factsheetSpeciesDigger);
	}

}
