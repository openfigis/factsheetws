package org.fao.fi.fisheryresources.domain.stocksby;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "aqResList", "itemCount" })
public class StocksByElement {

	@XmlElements(@XmlElement(name = "AqRes"))
	private List<AqRes> aqResList;

	@XmlAttribute
	private int itemCount;

	public final List<AqRes> getAqResList() {
		return aqResList;
	}

	public final void setAqResList(List<AqRes> aqResList) {
		this.aqResList = aqResList;
		if (aqResList != null) {
			this.itemCount = aqResList.size();
		}
	}

	public final void addAqRes(AqRes aqRes) {
		if (aqResList == null) {
			aqResList = new ArrayList<AqRes>();
		}
		aqResList.add(aqRes);
		this.itemCount = aqResList.size();
	}

	public final int getItemCount() {
		return itemCount;
	}

	public final void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aqResList == null) ? 0 : aqResList.hashCode());
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
		StocksByElement other = (StocksByElement) obj;
		if (getAqResList() == null) {
			if (other.getAqResList() != null) {
				return false;
			}
		} else if (getAqResList().size() != other.getAqResList().size()) {
			return false;
		}
		if (getAqResList().size() > 0) {
			if (getAqResList().get(0).getSpeciesListList().size() != other.getAqResList().get(0).getSpeciesListList()
					.size()) {
				return false;
			}
			if (getAqResList().get(0).getWaterAreaRefListList().size() != other.getAqResList().get(0)
					.getWaterAreaRefListList().size()) {
				return false;
			}
			if (getAqResList().get(0).getOwner().getTitleList().size() != other.getAqResList().get(0).getOwner()
					.getTitleList().size()) {
				return false;
			}
		}

		return true;
	}

}
