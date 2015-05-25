package org.fao.fi.fisheryresources.services.operation.stocksbyelement.domain;

import java.util.List;

public class SubArea extends AbstractArea {

	private List<Division> divisionList;

	public final List<Division> getDivisionList() {
		return divisionList;
	}

	public final void setDivisionList(List<Division> divisionList) {
		this.divisionList = divisionList;
	}
}
