package org.fao.fi.services.factsheet;

import org.fao.fi.logical.domain.RetrieveFactsheetRequest;

public class RetrieveFactsheetRequestValidation {

	public RetrieveFactsheetRequestValidation(RetrieveFactsheetRequest request) {
		if (request.getDomain() == null) {
			throw new FactsheetException("Domain in the request is not filled(null)");
		}
		validateNotNullAndFilled("domain", request.getDomain().name());
		validateNotNullAndFilled("factsheet", request.getFactsheet());
	}

	private void validateNotNullAndFilled(String fieldName, String field) {
		if (field == null) {
			throw new FactsheetException("This field is null: " + fieldName);
		}
		if (field.length() == 0) {
			throw new FactsheetException("This field is not filled: " + fieldName);
		}
	}

}
