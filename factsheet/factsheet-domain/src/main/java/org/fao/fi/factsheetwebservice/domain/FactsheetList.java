package org.fao.fi.factsheetwebservice.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "FactsheetListType", propOrder = { "factsheetList" })
@XmlRootElement(name = "FactsheetList")
public class FactsheetList extends RowCount {

	@XmlElements(@XmlElement(name = "FactsheetDiscriminator"))
	private List<FactsheetDiscriminator> factsheetList;

	public final List<FactsheetDiscriminator> getFactsheetList() {
		if (factsheetList == null) {
			factsheetList = new ArrayList<FactsheetDiscriminator>();
		}
		return factsheetList;
	}

	public final void setFactsheetList(List<FactsheetDiscriminator> factsheetList) {
		this.factsheetList = factsheetList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((factsheetList == null) ? 0 : factsheetList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FactsheetList other = (FactsheetList) obj;
		if (factsheetList == null) {
			if (other.factsheetList != null) {
				return false;
			}
		} else if (factsheetList.size() != other.factsheetList.size()) {
			return false;
		}
		return true;
	}

}
