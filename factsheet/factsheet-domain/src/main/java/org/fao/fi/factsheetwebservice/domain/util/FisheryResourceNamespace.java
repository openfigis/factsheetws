package org.fao.fi.factsheetwebservice.domain.util;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

public class FisheryResourceNamespace implements NamespaceContext {

	public String getNamespaceURI(String prefix) {
		if (prefix == null)
			throw new NullPointerException("Null prefix");
		else if ("fi".equals(prefix))
			return "http://www.fao.org/fi/figis/devcon/";
		else if ("dc".equals(prefix))
			return "http://purl.org/dc/elements/1.1/";
		else if ("dcterms".equals(prefix))
			return "http://purl.org/dc/terms/";
		else if ("ags".equals(prefix))
			return "http://www.purl.org/agmes/1.1/";
		else if ("xml".equals(prefix))
			return XMLConstants.XML_NS_URI;
		return XMLConstants.DEFAULT_NS_PREFIX;
	}

	@Override
	public String getPrefix(String namespaceURI) {
		throw new UnsupportedOperationException();

	}

	@Override
	public Iterator<?> getPrefixes(String namespaceURI) {
		throw new UnsupportedOperationException();
	}

}