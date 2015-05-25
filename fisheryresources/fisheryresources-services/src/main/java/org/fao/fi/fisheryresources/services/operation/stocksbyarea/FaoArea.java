package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

public class FaoArea {

	private String value;
	private FaoAreaType faoAreaType;

	public final String getValue() {
		return value;
	}

	public final void setValue(String value) {
		this.value = value;
	}

	public final FaoAreaType getFaoAreaType() {
		return faoAreaType;
	}

	public final void setFaoAreaType(FaoAreaType faoAreaType) {
		this.faoAreaType = faoAreaType;
	}

}
