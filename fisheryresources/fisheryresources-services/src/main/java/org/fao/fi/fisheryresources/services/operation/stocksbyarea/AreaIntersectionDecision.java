package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import java.util.List;
import java.util.Map;

import org.fao.fi.fisheryresources.services.operation.stocksbyelement.CodeAdditionDecission;
import org.fao.fi.fisheryresources.services.operation.stocksbyspecies.Decision;

public class AreaIntersectionDecision extends Decision implements CodeAdditionDecission {

	private String requestedCode;

	public AreaIntersectionDecision(String requestedCode) {
		this.requestedCode = requestedCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 200;
		result = prime * result + ((requestedCode == null) ? 0 : requestedCode.hashCode());
		return result;
	}

	@Override
	public void decideAndAdd(Long resource, String elementCode, Map<String, List<Long>> elementResourceMap) {
		FaoAreaInterpreter i = new FaoAreaInterpreter();
		if (i.intersects(requestedCode, elementCode)) {
			super.decideAndAdd(resource, requestedCode, elementResourceMap);
			super.decideAndAdd(resource, elementCode, elementResourceMap);
		}

	}

}
