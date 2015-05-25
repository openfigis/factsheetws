package org.fao.fi.logical.domain;

import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;

public class RetrieveFactsheetRequest extends FactsheetDiscriminator {

	public RetrieveFactsheetRequest(FactsheetDiscriminator factsheetDiscriminator) {
		super(factsheetDiscriminator.getLanguage(), factsheetDiscriminator.getDomain(), factsheetDiscriminator
				.getFactsheet());
		this.setDomain(factsheetDiscriminator.getDomain());
		this.setFactsheet(factsheetDiscriminator.getFactsheet());
		this.setLanguage(factsheetDiscriminator.getLanguage());
	}

	public RetrieveFactsheetRequest() {
		super();
	}

}
