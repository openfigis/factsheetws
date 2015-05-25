package org.fao.fi.fisheryresources.services.operation.stocksbyelement.domain;

import java.util.List;

public class MajorArea extends AbstractArea {

	private List<SubArea> subAreaList;

	public final List<SubArea> getSubAreaList() {
		return subAreaList;
	}

	public final void setSubAreaList(List<SubArea> subAreaList) {
		this.subAreaList = subAreaList;
	}

}
