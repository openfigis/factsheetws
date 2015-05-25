package org.fao.fi.factsheetwebservice.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "LanguageListType", propOrder = { "languageList" })
@XmlRootElement(name = "LanguageList")
public class LanguageList extends RowCount {

	@XmlElements(@XmlElement(name = "Language"))
	private List<FactsheetLanguage> languageList;

	public final List<FactsheetLanguage> getLanguageList() {
		if (languageList == null) {
			languageList = new ArrayList<FactsheetLanguage>();
		}
		return languageList;
	}

	public final void setLanguageList(List<FactsheetLanguage> languageList) {
		this.languageList = languageList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((languageList == null) ? 0 : languageList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		LanguageList other = (LanguageList) obj;
		if (languageList == null) {
			if (other.languageList != null) {
				return false;
			}
		} else if (languageList.size() != other.languageList.size()) {
			return false;
		}
		return true;
	}

}
