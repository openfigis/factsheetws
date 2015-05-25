package org.fao.fi.fisheryresources.services.operation.stocksbyspecies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A decission determines whether to add a
 * 
 * @author Erik van Ingen
 * 
 */
public abstract class Decision {

	public void decideAndAdd(Long resource, String elementCode, Map<String, List<Long>> elementResourceMap) {
		if (elementResourceMap.containsKey(elementCode)) {
			// if the element does already occur, just add it to the
			// existing list
			if (!elementResourceMap.get(elementCode).contains(resource)) {
				// if this resource is not yet in the list, add it
				// to the list
				elementResourceMap.get(elementCode).add(resource);
			}
		} else {
			// if the element does not occur, create a new one and
			// add the first one to it.
			List<Long> newList = new ArrayList<Long>();
			newList.add(resource);
			elementResourceMap.put(elementCode, newList);
		}
	}

}
