package org.fao.fi.fisheryresources.services.operation.stocksbyelement;

import java.io.Serializable;
import java.util.List;

public class ResourceElementsList implements Serializable {

	private Long resource;
	private List<String> elementList;

	public final Long getResource() {
		return resource;
	}

	public final void setResource(Long resource) {
		this.resource = resource;
	}

	public final List<String> getElementList() {
		return elementList;
	}

	public final void setElementList(List<String> elementList) {
		this.elementList = elementList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7550623067942825421L;

}
