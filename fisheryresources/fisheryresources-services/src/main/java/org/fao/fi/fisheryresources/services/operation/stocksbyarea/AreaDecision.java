package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import org.fao.fi.fisheryresources.services.operation.stocksbyelement.CodeAdditionDecission;
import org.fao.fi.fisheryresources.services.operation.stocksbyspecies.Decision;

/**
 * Decision related to Are does need to know the area code requested by the client. 
 * 
 * 
 * @author Erik van Ingen
 * 
 */
public abstract class AreaDecision extends Decision implements CodeAdditionDecission {

	protected String requestedCode;

}
