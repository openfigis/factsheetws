package org.fao.fi.fisheryresources.services.operation.stocksbyelement;

import java.util.List;

import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;

public interface ResourcePerElement {

	/**
	 * Return the list of resources, given a 3 alpha element code.
	 * 
	 * @param element3AlphaCode
	 * @param factsheetLanguage
	 * @return
	 */

	public abstract List<Long> getResourceListOf(ElementResourceMapIntelligence elementResourceMapIntelligence,
			String element3AlphaCode, FactsheetLanguage factsheetLanguage, FactsheetDigger factsheetDigger,
			CodeAdditionDecission cad);

}