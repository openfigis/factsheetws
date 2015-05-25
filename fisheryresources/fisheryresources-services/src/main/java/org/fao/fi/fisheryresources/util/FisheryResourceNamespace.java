package org.fao.fi.fisheryresources.util;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

import org.fao.fi.commons.FigisException;

/**
 * The namespace generator used for Xpath operations.
 * 
 * @author Erik van Ingen
 * 
 */
public class FisheryResourceNamespace implements NamespaceContext {

	public String getNamespaceURI(String prefix) {
		if (prefix == null) {
			throw new FigisException("Null prefix");
		} else if ("fi".equals(prefix)) {
			return "http://www.fao.org/fi/figis/devcon/";
		} else if ("xml".equals(prefix)) {
			return XMLConstants.XML_NS_URI;
		}
		return XMLConstants.DEFAULT_NS_PREFIX;
	}

	@Override
	public final String getPrefix(String namespaceURI) {
		throw new UnsupportedOperationException();

	}

	@Override
	public final Iterator<?> getPrefixes(String namespaceURI) {
		throw new UnsupportedOperationException();
	}

}