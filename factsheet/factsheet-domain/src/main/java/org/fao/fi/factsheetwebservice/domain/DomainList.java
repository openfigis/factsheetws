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
@XmlType(name = "DomainListType", propOrder = { "domainList" })
@XmlRootElement(name = "DomainList")
public class DomainList extends RowCount {

	@XmlElements(@XmlElement(name = "Domain"))
	private List<FactsheetDomain> domainList;

	public final List<FactsheetDomain> getDomainList() {
		if (domainList == null) {
			domainList = new ArrayList<FactsheetDomain>();
		}
		return domainList;
	}

	public final void setDomainList(List<FactsheetDomain> domainList) {
		this.domainList = domainList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domainList == null) ? 0 : domainList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		DomainList other = (DomainList) obj;
		if (domainList == null) {
			if (other.domainList != null) {
				return false;
			}
		} else {
			if (domainList.size() != (other.domainList.size())) {
				return false;
			}
		}
		return true;
	}

}
