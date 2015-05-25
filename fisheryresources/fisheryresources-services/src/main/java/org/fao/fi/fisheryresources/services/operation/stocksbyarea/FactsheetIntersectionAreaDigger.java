package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import org.fao.fi.fisheryresources.services.operation.stocksbyelement.FactsheetDigger;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.FactsheetDiggerAbstract;
import org.springframework.stereotype.Component;

/**
 * This class extracts the list of species used in a resource factsheet list.
 * 
 * 
 * @author Erik van Ingen
 * 
 */
@Component
public class FactsheetIntersectionAreaDigger extends FactsheetDiggerAbstract implements FactsheetDigger {

	public FactsheetIntersectionAreaDigger() {
		setListName("WaterAreaList");
		setRefName("WaterAreaRef");
		setRefNamePlus("/fi:IntersectingAreas/fi:WaterAreaRef");
		getNodeNameSet().add("fao_major");
		getNodeNameSet().add("fao_area");
		getNodeNameSet().add("fao_sub_area");
		getNodeNameSet().add("fao_div");
		getNodeNameSet().add("fao_sub_div");
		getNodeNameSet().add("fao_sub_unit");
	}

	/**
	 * FactsheetSpeciesDigger starts with 100, FactsheetAreaDigger with 200,
	 * FactsheetIntersectionAreaDigger with 300.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 300;
		result = prime * result + ((getListName() == null) ? 0 : getListName().hashCode());
		result = prime * result + ((getRefName() == null) ? 0 : getRefName().hashCode());
		result = prime * result + ((getRefNamePlus() == null) ? 0 : getRefNamePlus().hashCode());
		return result;

	}

}
