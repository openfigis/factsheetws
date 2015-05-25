package org.fao.fi.factsheetwebservice.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "FactsheetDiscriminatorType", propOrder = { "domain", "factsheet", "language" })
public class FactsheetDiscriminator {

	@XmlAttribute(name = "domain")
	private FactsheetDomain domain;
	@XmlAttribute(name = "lang")
	private FactsheetLanguage language;
	@XmlAttribute(name = "factsheet")
	private String factsheet;

	public FactsheetDiscriminator(FactsheetLanguage language, FactsheetDomain domain, String factsheet) {
		this.domain = domain;
		this.factsheet = factsheet;
		this.language = language;
	}

	public FactsheetDiscriminator() {
	}

	public final FactsheetDomain getDomain() {
		return domain;
	}

	public final void setDomain(FactsheetDomain domain) {
		this.domain = domain;
	}

	public final FactsheetLanguage getLanguage() {
		return language;
	}

	public final void setLanguage(FactsheetLanguage language) {
		this.language = language;
	}

	public final String getFactsheet() {
		return factsheet;
	}

	public final void setFactsheet(String factsheet) {
		this.factsheet = factsheet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.ordinal());
		result = prime * result + ((factsheet == null) ? 0 : factsheet.hashCode());
		result = prime * result + ((language == null) ? 0 : language.ordinal());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FactsheetDiscriminator other = (FactsheetDiscriminator) obj;
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		if (factsheet == null) {
			if (other.factsheet != null)
				return false;
		} else if (!factsheet.equals(other.factsheet))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		return true;
	}

}
