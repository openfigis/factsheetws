package org.fao.fi.fisheryresources.services;

import java.util.ArrayList;
import java.util.List;

import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.CodeAdditionDecission;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.ElementResourceMapIntelligence;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.FactsheetDigger;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.ResourcePerElement;

public class ResourcePerSpeciesDummy4Testing implements ResourcePerElement {

	public final void setElementResourceMap(ElementResourceMapIntelligence elementResourceMapIntelligence) {
	}

	@Override
	public List<Long> getResourceListOf(ElementResourceMapIntelligence elementResourceMapIntelligence,
			String element3AlphaCode, FactsheetLanguage factsheetLanguage, FactsheetDigger factsheetDigger,
			CodeAdditionDecission cad) {
		List<Long> longList = null;
		if (factsheetLanguage.equals(FactsheetLanguage.en) || factsheetLanguage.equals(FactsheetLanguage.fr)) {
			longList = new ArrayList<Long>();
			longList.add(new Long(2l));
		}
		return longList;
	}

}
