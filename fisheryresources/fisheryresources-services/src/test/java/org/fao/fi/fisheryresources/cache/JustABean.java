package org.fao.fi.fisheryresources.cache;

import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.FactsheetDigger;
import org.springframework.stereotype.Component;

import com.googlecode.ehcache.annotations.Cacheable;

@Component
public class JustABean {

	static int index = 0;

	@Cacheable(cacheName = "FisheryResourcesCache")
	public void doSomething(FactsheetLanguage factsheetLanguage, FactsheetDigger factsheetDigger) {
		index++;
		System.out.println("JustABean.doSomething hit!!!");
	}

	public final int getIndex() {
		return index;
	}

}
