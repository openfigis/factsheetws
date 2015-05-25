package org.fao.fi.fisheryresources.domain.stocksby;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "content" })
@XmlRootElement(name = "Title")
public class Title {

	@XmlValue
	private String content;
	@XmlAttribute(   namespace = "http://www.w3.org/XML/1998/namespace")
	private String lang;

	public final String getContent() {
		return content;
	}

	public final void setContent(String content) {
		this.content = content;
	}

	public final String getLanguage() {
		return lang;
	}

	public final void setLang(String lang) {
		this.lang = lang;
	}

}
