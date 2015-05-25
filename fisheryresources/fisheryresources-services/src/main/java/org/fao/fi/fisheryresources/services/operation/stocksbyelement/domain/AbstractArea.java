package org.fao.fi.fisheryresources.services.operation.stocksbyelement.domain;

public abstract class AbstractArea {

	String fullValue;
	String partialvalue;

	public final String getFullValue() {
		return fullValue;
	}

	public final void setFullValue(String fullValue) {
		this.fullValue = fullValue;
	}

	public final String getPartialvalue() {
		return partialvalue;
	}

	public final void setPartialvalue(String partialvalue) {
		this.partialvalue = partialvalue;
	}

}
