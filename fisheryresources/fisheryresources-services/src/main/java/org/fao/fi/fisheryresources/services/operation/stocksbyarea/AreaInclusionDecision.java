package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import java.util.List;
import java.util.Map;

import org.fao.fi.fisheryresources.services.operation.stocksbyelement.CodeAdditionDecission;

public class AreaInclusionDecision extends AreaDecision implements CodeAdditionDecission {

	public AreaInclusionDecision(String requestedCode) {
		this.requestedCode = requestedCode;
	}

	/**
	 * AreaInclusionDecision starts with result 100, AreaIntersectionDecision with 200
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 100;
		result = prime * result + ((requestedCode == null) ? 0 : requestedCode.hashCode());
		return result;
	}

	@Override
	public void decideAndAdd(Long resource, String elementCode, Map<String, List<Long>> elementResourceMap) {
		FaoAreaInterpreter i = new FaoAreaInterpreter();
		if (i.includes(requestedCode, elementCode)) {
			super.decideAndAdd(resource, requestedCode, elementResourceMap);
			super.decideAndAdd(resource, elementCode, elementResourceMap);
		}

	}

}
