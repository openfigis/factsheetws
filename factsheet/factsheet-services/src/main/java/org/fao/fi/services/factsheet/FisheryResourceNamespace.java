package org.fao.fi.services.factsheet;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

public class FisheryResourceNamespace implements NamespaceContext {

	public String getNamespaceURI(String prefix) {
		if (prefix == null)
			throw new NullPointerException("Null prefix");
		else if ("fi".equals(prefix))
			return "http://www.fao.org/fi/figis/devcon/";
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