package org.fao.fi.fisheryresources.services.operation.stocksbyelement;

import java.util.List;
import java.util.Map;

public interface CodeAdditionDecission {

	void decideAndAdd(Long resource, String elementCode, Map<String, List<Long>> elementResourceMap);

}
