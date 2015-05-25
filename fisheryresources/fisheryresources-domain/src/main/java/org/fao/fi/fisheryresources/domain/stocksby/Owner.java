package org.fao.fi.fisheryresources.domain.stocksby;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "OwnerType", propOrder = { "foreignID", "titleList" })
public class Owner {

	@XmlElement(name="ForeignID") 
	private ForeignID foreignID;

	@XmlElements(@XmlElement(name = "Title", namespace = "http://purl.org/dc/elements/1.1/"))
	private List<Title> titleList;

	public final List<Title> getTitleList() {
		return titleList;
	}

	public final void setTitleList(List<Title> titleList) {
		this.titleList = titleList;
	}

	public final void addTitle(Title title) {
		if (titleList == null) {
			titleList = new ArrayList<Title>();
		}
		getTitleList().add(title);
	}
	public final ForeignID getForeignID() {
		return foreignID;
	}

	public final void setForeignID(ForeignID foreignID) {
		this.foreignID = foreignID;
	}

}
