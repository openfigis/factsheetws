package org.fao.fi.factsheetwebservice.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FIGISReferenceType", propOrder = { "itemCount" })
@XmlSeeAlso( { FactsheetList.class, LanguageList.class })
public class RowCount {

	@XmlAttribute
	private int itemCount;

	public final int getRowCount() {
		return itemCount;
	}

	public final void setRowCount(int itemCount) {
		this.itemCount = itemCount;
	}

}
