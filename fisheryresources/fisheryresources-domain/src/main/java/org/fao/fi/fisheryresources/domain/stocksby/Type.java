package org.fao.fi.fisheryresources.domain.stocksby;

import javax.xml.bind.annotation.XmlEnum;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * A FigisId can be an Object ID or an Observation ID.
 * 
 * When Observation is specified, it is an Observation ID.
 * 
 * When nothing is specified, it is an Object ID.
 * 
 * See for more analyses http://figisapps.fao.org/FIGISwiki/index.php/Factsheet_Webservice_Usecases
 * 
 * 
 * @author Erik van Ingen
 * 
 */

@XmlEnum
public enum Type {

	Object, Observation;

	public static Type string2Type(String typeString) {
		Type type = Object;
		if (typeString.equals(Type.Object.name())) {
			type = Type.Object;
		}
		if (typeString.equals(Type.Observation.name())) {
			type = Type.Observation;
		}
		if (StringUtils.isBlank(typeString)) {
			type = Type.Object;
		}
		return type;
	}

}
