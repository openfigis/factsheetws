package org.fao.fi.fisheryresources.services.operation.stocksbyspecies;

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
public class FactsheetSpeciesDigger extends FactsheetDiggerAbstract implements FactsheetDigger {

	public FactsheetSpeciesDigger() {
		setListName("SpeciesList");
		setRefName("SpeciesRef");
		getNodeNameSet().add("fao3alpha");
	}

	/**
	 * FactsheetSpeciesDigger starts with 100, FactsheetAreaDigger with 200, FactsheetIntersectionAreaDigger with 300.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 100;
		result = prime * result + ((getListName() == null) ? 0 : getListName().hashCode());
		result = prime * result + ((getRefName() == null) ? 0 : getRefName().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

	protected boolean condition4AddingNode(String nodeNameFound) {
		return true;
	}

}
