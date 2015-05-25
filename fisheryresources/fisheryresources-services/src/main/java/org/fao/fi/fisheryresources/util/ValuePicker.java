package org.fao.fi.fisheryresources.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.fao.fi.commons.FigisException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Using XPath to pick the value from an XML document.
 * 
 * 
 * @author Erik van Ingen
 * 
 */

public class ValuePicker {

	/**
	 * Utility classes should not have a public or default constructor.
	 */
	protected ValuePicker() {

	}

	/**
	 * returns the node value given a url and xPatch expression
	 * 
	 * @param xmlUrl
	 * @param xPathExpresssion
	 * @return in value
	 */
	public static final Node pickNode(URL xmlUrl, String xPathExpresssion) {
		Node node = (Node) pickStringValue(xmlUrl, xPathExpresssion, XPathConstants.NODE);
		return node;
	}

	/**
	 * returns an object given a url, xPatch and qname
	 * 
	 * 
	 * @param xmlUrl
	 * @param xPathExpresssion
	 * @return String value
	 */
	private static Object pickStringValue(URL xmlUrl, String xPathExpresssion, QName qname) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		docFactory.setNamespaceAware(true); // never forget this!
		DocumentBuilder builder;
		try {
			builder = docFactory.newDocumentBuilder();
			URLConnection urlConnection = (URLConnection) xmlUrl.openConnection();
			urlConnection.connect();
			Document doc = builder.parse(urlConnection.getInputStream());
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			xpath.setNamespaceContext(new FisheryResourceNamespace());
			XPathExpression expr = xpath.compile(xPathExpresssion);
			return expr.evaluate(doc, qname);
		} catch (ParserConfigurationException e) {
			throw new FigisException(e);
		} catch (IOException e) {
			throw new FigisException(e);
		} catch (SAXException e) {
			throw new FigisException(e);
		} catch (XPathExpressionException e) {
			throw new FigisException(e);
		}

	}

	public static final String pickString(Node node, String xPathExpresssion) {
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			xpath.setNamespaceContext(new FisheryResourceNamespace());
			XPathExpression expr = xpath.compile(xPathExpresssion);
			return (String) expr.evaluate(node, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			throw new FigisException(e);
		}

	}

	public static final Node pickNode(Node node, String xPathExpresssion) {
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			xpath.setNamespaceContext(new FisheryResourceNamespace());
			XPathExpression expr = xpath.compile(xPathExpresssion);
			return (Node) expr.evaluate(node, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			throw new FigisException(e);
		}

	}

}
