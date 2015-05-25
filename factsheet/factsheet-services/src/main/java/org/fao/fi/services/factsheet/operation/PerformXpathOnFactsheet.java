package org.fao.fi.services.factsheet.operation;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.fao.fi.commons.FigisException;
import org.fao.fi.services.factsheet.FisheryResourceNamespace;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Component
public class PerformXpathOnFactsheet {

	public Node pickNode(URL xmlUrl, String xPathExpresssion) {
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
			return (Node) expr.evaluate(doc, XPathConstants.NODE);
		} catch (ParserConfigurationException e) {
			throw new FigisException(e);
		} catch (IOException e) {
			throw new FigisException(e);
		} catch (SAXException e) {
			throw new FigisException(e);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			throw new FigisException(e);
		}

	}

	public NodeList pickNodeList(URL xmlUrl, String xPathExpresssion) {
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
			return (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		} catch (ParserConfigurationException e) {
			throw new FigisException(e);
		} catch (IOException e) {
			throw new FigisException(e);
		} catch (SAXException e) {
			throw new FigisException(e);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			throw new FigisException(e);
		}

	}

}
