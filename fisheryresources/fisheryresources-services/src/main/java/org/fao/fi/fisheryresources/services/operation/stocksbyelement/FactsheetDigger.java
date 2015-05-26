package org.fao.fi.fisheryresources.services.operation.stocksbyelement;

import java.net.URL;
import java.util.List;

public interface FactsheetDigger {

	/**
	 * get all the element listed in the aquatic resource identifier element. Assume that there is only 1 fi:AqRes and 1
	 * fi:AqResIdent in the factsheet. Assume that there can be more elementLists in 1 FIGISDoc/fi:AqRes/fi:AqResIdent
	 * 
	 * 
	 * fi:FIGISDoc/fi:AqRes/fi:AqResIdent/fi:ElementList
	 * 
	 * 
	 * @param factsheetUrl
	 * @return
	 */
	public abstract List<String> dig(URL factsheetUrl);

	int hashCode();

}