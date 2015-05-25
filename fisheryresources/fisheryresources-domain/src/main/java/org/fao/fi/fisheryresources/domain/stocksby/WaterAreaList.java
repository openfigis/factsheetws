package org.fao.fi.fisheryresources.domain.stocksby;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "WaterAreaListType", propOrder = { "waterAreaList" })
public class WaterAreaList {

	@XmlElements(@XmlElement(name = "WaterAreaRef"))
	private List<WaterAreaRef> waterAreaList;

	public final List<WaterAreaRef> getWaterAreaRefList() {
		if (waterAreaList == null) {
			waterAreaList = new ArrayList<WaterAreaRef>();
		}
		return waterAreaList;
	}

	public final void setWaterAreaRefList(List<WaterAreaRef> waterAreaRefList) {
		this.waterAreaList = waterAreaRefList;
	}
}