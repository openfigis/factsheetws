package org.fao.fi.fisheryresources.services.operation.stocksbyelement.domain;

import java.util.List;

public class SubDivision extends AbstractArea {

	private List<SubUnit> subUnitList;

	public final List<SubUnit> getSubUnitList() {
		return subUnitList;
	}

	public final void setSubUnitList(List<SubUnit> subUnitList) {
		this.subUnitList = subUnitList;
	}

}
