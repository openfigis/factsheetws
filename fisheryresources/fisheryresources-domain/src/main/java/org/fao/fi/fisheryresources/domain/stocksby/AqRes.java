package org.fao.fi.fisheryresources.domain.stocksby;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "AqResType", propOrder = { "owner", "figisIdList", "titleList", "speciesListList",
		"waterAreaRefListList", "identifierList" })
public class AqRes {

	@XmlElements(@XmlElement(name = "FigisID"))
	private List<FigisID> figisIdList;

	@XmlElements(@XmlElement(name = "Title", namespace = "http://purl.org/dc/elements/1.1/"))
	private List<Title> titleList;

	@XmlElements(@XmlElement(name = "SpeciesList"))
	private List<SpeciesList> speciesListList;

	@XmlElements(@XmlElement(name = "WaterAreaList"))
	private List<WaterAreaList> waterAreaRefListList;

	@XmlElements(@XmlElement(name = "Identifier", namespace = "http://purl.org/dc/elements/1.1/"))
	private List<Identifier> identifierList;

	@XmlElement(name = "Owner", required = true)
	private Owner owner;

	public final Owner getOwner() {
		return owner;
	}

	public final void setOwner(Owner owner) {
		this.owner = owner;
	}

	public final List<FigisID> getFigisIdList() {
		if (figisIdList == null) {
			figisIdList = new ArrayList<FigisID>();
		}
		return figisIdList;
	}

	public final void setFigisIdList(List<FigisID> figisIdList) {
		this.figisIdList = figisIdList;
	}

	public final List<SpeciesList> getSpeciesListList() {
		if (speciesListList == null) {
			speciesListList = new ArrayList<SpeciesList>();
		}
		return speciesListList;
	}

	public final void setSpeciesListList(List<SpeciesList> speciesListList) {
		this.speciesListList = speciesListList;
	}

	public final List<WaterAreaList> getWaterAreaRefListList() {
		if (waterAreaRefListList == null) {
			waterAreaRefListList = new ArrayList<WaterAreaList>();
		}
		return waterAreaRefListList;

	}

	public final void setWaterAreaRefListList(List<WaterAreaList> waterAreaRefListList) {
		this.waterAreaRefListList = waterAreaRefListList;
	}

	public final List<Title> getTitleList() {
		if (titleList == null) {
			titleList = new ArrayList<Title>();
		}
		return titleList;
	}

	public final void addTitle(Title title) {
		getTitleList().add(title);
	}

	public final void setTitleList(List<Title> titleList) {
		this.titleList = titleList;
	}

	public final List<Identifier> getIdentifierList() {
		if (identifierList == null) {
			identifierList = new ArrayList<Identifier>();
		}
		return identifierList;
	}

	public final void setIdentifierList(List<Identifier> identifierList) {
		this.identifierList = identifierList;
	}

	public final void addIdentifier(Identifier identifier) {
		getIdentifierList().add(identifier);
	}
}
