package org.fao.fi.fisheryresources.services.operation.stocksbyelement.domain;

import java.util.List;

public class Division extends AbstractArea {

	private List<SubDivision> subDivisionList;

	public final List<SubDivision> getSubDivisionList() {
		return subDivisionList;
	}

	public final void setSubDivisionList(List<SubDivision> subDivisionList) {
		this.subDivisionList = subDivisionList;
	}
}
