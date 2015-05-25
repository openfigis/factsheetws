package org.fao.fi.fisheryresources.cache;

import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.CodeAdditionDecission;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.FactsheetDigger;
import org.springframework.stereotype.Component;

import com.googlecode.ehcache.annotations.Cacheable;

@Component
public class CacheTestObject {

	private static int index = 0;

	@Cacheable(cacheName = "FisheryResourcesCache")
	public void getElementResourceMap(FactsheetLanguage factsheetLanguage, FactsheetDigger factsheetDigger,
			CodeAdditionDecission cad) {
		index++;
	}

	public final int getIndex() {
		return index;
	}

}
