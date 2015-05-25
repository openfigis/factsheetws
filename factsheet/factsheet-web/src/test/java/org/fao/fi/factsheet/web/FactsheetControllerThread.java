package org.fao.fi.factsheet.web;

import org.fao.fi.commons.FigisException;
import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;

public class FactsheetControllerThread extends Thread {

	private FactsheetController factsheetController;

	FactsheetControllerThread(FactsheetController factsheetController) {
		this.factsheetController = factsheetController;
	}

	public void run() {

		int domains = factsheetController.retrieveDomains1().getDomainList().size();
		if (domains != 19) {
			throw new FigisException("getDomainList != 19 " + domains);
		}

		int factsheets = factsheetController.retrieveFactsheets4Domain1(FactsheetDomain.countrysector.name())
				.getFactsheetList().size();
		if (factsheets != 392) {
			throw new FigisException("getFactsheetList != 392 " + factsheets);
		}

		int factsheetsLan = factsheetController.retrieveFactsheets4Domain1(FactsheetDomain.countrysector.name())
				.getFactsheetList().size();

		if (factsheetsLan != 392) {
			throw new FigisException("getFactsheetList != 392 " + factsheetsLan);
		}
	}

}
