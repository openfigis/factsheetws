package org.fao.fi.logical.domain;

import java.util.List;

import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;

public class RetrieveFactsheetListResponse {

	private List<FactsheetDiscriminator> factsheetDiscriminatorList;

	public List<FactsheetDiscriminator> getFactsheetDiscriminatorList() {
		return factsheetDiscriminatorList;
	}

	public void setFactsheetDiscriminatorList(List<FactsheetDiscriminator> factsheetDiscriminatorList) {
		this.factsheetDiscriminatorList = factsheetDiscriminatorList;
	}

}
