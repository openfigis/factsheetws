package org.fao.fi.fisheryresources.services;

import org.fao.fi.fisheryresources.FisheryResourcesBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CacheFillerTest extends FisheryResourcesBaseTest {

	CacheFiller cacheFiller;

	@Test
	public void testFillCache() {

		System.out.println("erikerikerik" + System.getProperty("java.io.tmpdir"));

		cacheFiller.fillCache();
	}

	@Autowired
	public final void setCacheFiller(CacheFiller cacheFiller) {
		this.cacheFiller = cacheFiller;
	}

}
