package org.fao.fi.fisheryresources.services.operation.stocksbyelement;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.fao.fi.fisheryresources.util.ValuePicker;
import org.w3c.dom.Node;

/**
 * This class extracts the list of element used in a resource factsheet list.
 * 
 * 
 * @author Erik van Ingen
 * 
 */

public abstract class FactsheetDiggerAbstract {

	private String listName;
	private String refName;
	private String refNamePlus = "";
	private Set<String> nodeNameSet = new HashSet<String>();

	/**
	 * get all the element listed in the aquatic resource identifier element. Assume that there is only 1 fi:AqRes and 1
	 * fi:AqResIdent in the factsheet. Assume that there can be more elementLists in 1 FIGISDoc/fi:AqRes/fi:AqResIdent
	 * 
	 * 
	 * fi:FIGISDoc/fi:AqRes/fi:AqResIdent/fi:ElementList
	 * 
	 * * Area listName = "WaterAreaList"
	 * 
	 * @param factsheetUrl
	 * @return
	 */
	public List<String> dig(URL factsheetUrl) {
		List<String> listOfElement = new ArrayList<String>();

		String xPathExpresssionAqResIdent = "fi:FIGISDoc/fi:AqRes/fi:AqResIdent";
		Node aqResIdentNode = ValuePicker.pickNode(factsheetUrl, xPathExpresssionAqResIdent);

		int elementListIndex = 1;
		Node elementList = null;
		// loop through the elementLists
		do {
			String xPathExpresssionElementList = "fi:" + listName + "[" + elementListIndex++ + "]";
			elementList = ValuePicker.pickNode(aqResIdentNode, xPathExpresssionElementList);
			if (elementList != null) {
				digUpElementRefs(elementList, listOfElement);
			}
		} while (elementList != null);

		return listOfElement;
	}

	/**
	 * loop trough the ElementRef
	 * 
	 * @param elementList
	 * @param listOfElement
	 * @return
	 */
	private List<String> digUpElementRefs(Node elementList, List<String> listOfElement) {
		int elementRefIndex = 1;
		Node elementRef = null;
		do {
			String xPathExpresssionX = "fi:" + refName + "[" + elementRefIndex++ + "]" + refNamePlus;
			elementRef = ValuePicker.pickNode(elementList, xPathExpresssionX);
			if (elementRef != null) {
				addElement2List(elementRef, listOfElement);
			}
		} while (elementRef != null);

		return listOfElement;
	}

	/**
	 * Add the element found in this ElementList to the node. Loop through the Foreign Id's
	 * 
	 * Area listName = "WaterAreaList"
	 * 
	 * refName = "WaterAreaRef"
	 * 
	 * nodeName = "fao_major"
	 * 
	 * Element
	 * 
	 * listName = "SpeciesList"
	 * 
	 * refName = "SpeciesRef"
	 * 
	 * nodeName = "fao3alpha"
	 * 
	 * 
	 * 
	 * 
	 * @param elementList1
	 * @param listOfElement
	 */
	private void addElement2List(Node elementList, List<String> listOfElement) {
		boolean elementRefCondition = true;
		int foreignIDIndex = 1;
		do {
			String xPathExpresssionCodeSystem = "fi:ForeignID[" + foreignIDIndex + "]/@CodeSystem";
			String node = (String) ValuePicker.pickString(elementList, xPathExpresssionCodeSystem);

			if (nodeNameSet.contains(node)) {
				String xPathExpresssionCode = "fi:ForeignID[" + foreignIDIndex + "]/@Code";
				String code = (String) ValuePicker.pickString(elementList, xPathExpresssionCode);
				listOfElement.add(code);
				elementRefCondition = false;
			}
			foreignIDIndex++;
		} while (elementRefCondition && foreignIDIndex < 10);
	}

	public final String getListName() {
		return listName;
	}

	public final void setListName(String listName) {
		this.listName = listName;
	}

	public final String getRefName() {
		return refName;
	}

	public final void setRefName(String refName) {
		this.refName = refName;
	}

	public final String getRefNamePlus() {
		return refNamePlus;
	}

	public final void setRefNamePlus(String refNamePlus) {
		this.refNamePlus = refNamePlus;
	}

	public final Set<String> getNodeNameSet() {
		return nodeNameSet;
	}

	public final void setNodeNameSet(Set<String> nodeNameSet) {
		this.nodeNameSet = nodeNameSet;
	}

}
