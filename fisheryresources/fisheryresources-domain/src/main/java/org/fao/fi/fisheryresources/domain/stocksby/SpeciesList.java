package org.fao.fi.fisheryresources.domain.stocksby;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "SpeciesListType", propOrder = { "speciesRefList" })
public class SpeciesList {

	@XmlElements(@XmlElement(name = "SpeciesRef"))
	private List<SpeciesRef> speciesRefList;

	public final List<SpeciesRef> getSpeciesRefList() {
		if (speciesRefList == null) {
			speciesRefList = new ArrayList<SpeciesRef>();
		}
		return speciesRefList;
	}

	public final void setSpeciesRefList(List<SpeciesRef> speciesRefList) {
		this.speciesRefList = speciesRefList;
	}

}
