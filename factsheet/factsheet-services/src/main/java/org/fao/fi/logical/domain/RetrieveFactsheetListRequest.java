package org.fao.fi.logical.domain;

import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;

public class RetrieveFactsheetListRequest {

	private FactsheetLanguage lang;

	public final FactsheetLanguage getLanguage() {
		return lang;
	}

	public final void setLanguage(FactsheetLanguage lang) {
		this.lang = lang;
	}

}
