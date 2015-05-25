package org.fao.fi.factsheetwebservice.domain;

import org.fao.fi.commons.FigisException;

public enum FactsheetLanguage {

	en, fr, zh, es, ar;

	public static FactsheetLanguage parseLanguage(String factsheetLanguage) {
		FactsheetLanguage list[] = FactsheetLanguage.values();
		FactsheetLanguage factsheetLanguageFound = null;
		for (FactsheetLanguage fl : list) {
			if (fl.toString().equals(factsheetLanguage.toLowerCase())) {
				factsheetLanguageFound = fl;
			}
		}
		if (factsheetLanguageFound == null) {
			throw new FigisException(factsheetLanguage + " is not a valid factsheet language.");
		}
		return factsheetLanguageFound;
	}

}
