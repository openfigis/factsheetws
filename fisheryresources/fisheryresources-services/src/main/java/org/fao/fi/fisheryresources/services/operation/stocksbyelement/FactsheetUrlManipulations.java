package org.fao.fi.fisheryresources.services.operation.stocksbyelement;

/**
 * 
 * Convert an English xml resource factsheet url into an html url, taking the
 * language into account.
 * 
 * @author Erik van Ingen
 * 
 */
public class FactsheetUrlManipulations {

	public static String xml2HtmlLanguaged(String xmlUrl) {

		String htmlUrl = xmlUrl.replaceFirst("/xml", "");

		return htmlUrl;
	}

}
