package org.fao.fi.fisheryresources.services.operation.stocksbyelement;

import java.util.List;
import java.util.Map;

import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.springframework.stereotype.Component;

/**
 * The relation between element and resources within FIGIS is only reflected in the factsheets. This class reads all the
 * factsheets and holds a list of all the relations.
 * 
 * 
 * @author Erik van Ingen
 * 
 */
@Component
public class ResourcePerElementImpl implements ResourcePerElement {

	public List<Long> getResourceListOf(ElementResourceMapIntelligence elementResourceMapIntelligence, String code,
			FactsheetLanguage factsheetLanguage, FactsheetDigger factsheetDigger, CodeAdditionDecission cad) {

		List<ResourceElementsList> listOfresourceElementList = elementResourceMapIntelligence
				.create1ResourceElementRelation(factsheetLanguage, factsheetDigger);
		Map<String, List<Long>> map = elementResourceMapIntelligence.create2ElementResourceMap(factsheetLanguage,
				factsheetDigger, cad, listOfresourceElementList);
		return map.get(code);
	}

}
