package org.fao.fi.factsheet.web;

import java.io.CharArrayReader;
import java.io.Reader;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.fao.fi.services.factsheet.FactsheetService;

/**
 * Generic exception handler for the weblayer for Source related messages.
 * 
 * @author Erik van Ingen
 *
 */
public class ExceptionHandler {

	public final Source composeSource(Exception e) {
		String errorMessage = FactsheetService.FIGISDOC_START + e.getMessage() + FactsheetService.FIGISDOC_END;
		Reader nodeListReader = new CharArrayReader(errorMessage.toCharArray());
		return new StreamSource(nodeListReader);
	}

}
