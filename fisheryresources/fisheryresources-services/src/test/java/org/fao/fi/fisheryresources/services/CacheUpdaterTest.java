package org.fao.fi.fisheryresources.services;

import org.fao.fi.fisheryresources.FisheryResourcesBaseTest;
import org.fao.fi.fisheryresources.services.vo.ThreeAlphaCode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CacheUpdaterTest extends FisheryResourcesBaseTest {

	CacheUpdater cacheUpdater;

	@Test
	public void testUpdateCache() {
		cacheUpdater.updateCache();
	}

	@Test
	public void testAdd2UsageStore() {
		ThreeAlphaCode threeAlphaCode = new ThreeAlphaCode();
		cacheUpdater.add2UsageStore(threeAlphaCode);
		cacheUpdater.updateCache();
	}

	@Test
	public void testAdd2UsageStore500() {
		ThreeAlphaCode threeAlphaCode = new ThreeAlphaCode();
		for (int i = 0; i < 600; i++) {
			threeAlphaCode.setValue(i + "");
			cacheUpdater.add2UsageStore(threeAlphaCode);

		}

		cacheUpdater.add2UsageStore(threeAlphaCode);
		cacheUpdater.updateCache();
	}

	@Test
	public void testAdd2AreaCache() {
		for (int i = 0; i < 600; i++) {
			cacheUpdater.add2AreaUsageStore(i + "area");
		}
	}

	@Autowired
	public final void setCacheUpdater(CacheUpdater cacheUpdater) {
		this.cacheUpdater = cacheUpdater;
	}

}
