package org.fao.fi.services.factsheet;

import org.fao.fi.commons.FigisException;
import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;

public class FactsheetServiceThread extends Thread {

	private FactsheetService factsheetService;

	FactsheetServiceThread(FactsheetService factsheetService) {
		this.factsheetService = factsheetService;

	}

	public void run() {
		int domains = factsheetService.retrieveDomainList().getDomainList().size();
		if (domains != 19) {
			throw new FigisException("getDomainList != 19 " + domains);
		}
		int factsheets = factsheetService.retrieveFactsheetListPerDomain(FactsheetDomain.countrysector)
				.getFactsheetList().size();
		if (factsheets != 392) {
			throw new FigisException("getFactsheetList != 392 " + factsheets);
		}

		int factsheetsLan = factsheetService.retrieveFactsheetList(FactsheetLanguage.en)
				.getFactsheetDiscriminatorList().size();

		if (factsheetsLan != 392) {
			throw new FigisException("getFactsheetList != 392 " + factsheetsLan);
		}
	}

}
