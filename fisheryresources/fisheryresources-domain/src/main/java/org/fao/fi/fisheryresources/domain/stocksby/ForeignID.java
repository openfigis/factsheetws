package org.fao.fi.fisheryresources.domain.stocksby;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "ForeignID")
public class ForeignID {

	@XmlAttribute(name = "CodeSystem", required = true)
	private String codeSystem;
	@XmlAttribute(name = "Code")
	private String code;

	/**
	 * Gets the value of the codeSystem property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCodeSystem() {
		return codeSystem;
	}

	/**
	 * Sets the value of the codeSystem property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCodeSystem(String value) {
		this.codeSystem = value;
	}

	/**
	 * Gets the value of the code property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the value of the code property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCode(String value) {
		this.code = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((codeSystem == null) ? 0 : codeSystem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		ForeignID other = (ForeignID) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (codeSystem == null) {
			if (other.codeSystem != null) {
				return false;
			}
		} else if (!codeSystem.equals(other.codeSystem)) {
			return false;
		}
		return true;
	}

}
