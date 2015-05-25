package org.fao.fi.fisheryresources.services;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;
import org.fao.fi.commons.FigisException;
import org.fao.fi.fisheryresources.services.vo.ThreeAlphaCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * This beautiful class is a bit of a hack. It works only for
 * fisheryResourcesService.retrieveStocksBySpeciesResponse(threeAlphaCode)
 * 
 * It takes care of firing the service with a max of 5007 times of parameter
 * objects which have been used lately.
 * 
 * A more advanced method for this is proposed in
 * http://groups.google.com/group/
 * ehcache-spring-annotations/browse_thread/thread/8e8459e1a18f1998
 * 
 * @author Erik van Ingen
 * 
 */

@Scope("singleton")
@Component
public class CacheUpdater {
	private Logger logger = Logger.getLogger(CacheUpdater.class);
	/**
	 * The lists are synchronised because the application and the schedular can
	 * update then concurrently.
	 */
	private List<ThreeAlphaCode> threeAlphaCodeUsageList = Collections
			.synchronizedList(new CopyOnWriteArrayList<ThreeAlphaCode>());
	private List<String> areaUsageList = Collections.synchronizedList(new CopyOnWriteArrayList<String>());

	private static int maxUpdates = 500;

	private FisheryResourcesService fisheryResourcesService;

	/**
	 * Adding the some popular codes in any case always to the usage list.
	 */
	public CacheUpdater() {
		ThreeAlphaCode alb = new ThreeAlphaCode();
		alb.setValue("ALB");
		add2UsageStore(alb);

		ThreeAlphaCode bft = new ThreeAlphaCode();
		bft.setValue("BFT");
		add2UsageStore(bft);

		ThreeAlphaCode skj = new ThreeAlphaCode();
		skj.setValue("SKJ");
		add2UsageStore(skj);

		add2AreaUsageStore("27");
		add2AreaUsageStore("21.3");

	}

	/**
	 * 
	 * Run this method every 606600000 milliseconds (1 week and 30 minutes).
	 * 
	 * 60 seconds * 60 minutes * 24 hours * 7 days = 604800
	 * 
	 * 60 seconds * 30 minutes = 1800
	 * 
	 * sum of above = 606600
	 * 
	 * multiplied with 1000 because of milliseconds 606600000
	 * 
	 * Objects in the cache live for exactly one week. Update of the cache may
	 * take up to 30 minutes, probably less. Therefore this update runs every
	 * week plus 30 minutes. This means that a fast response is guaranteed for
	 * 99.99702381%. Calculation: 100%-30minutes/(60minutes*24hours*7days)
	 * 
	 * 
	 */
	@Scheduled(fixedDelay = 606600000)
	final void updateCache() {
		logger.info("start CacheUpdater.updateCache. Runs every week.");
		StopWatch sw = new StopWatch("CacheUpdater.updateCache. Number of threeAlphaCodes is "
				+ threeAlphaCodeUsageList.size() + ". Number of fao areas is " + areaUsageList.size());
		sw.start();
		for (ThreeAlphaCode threeAlphaCode : threeAlphaCodeUsageList) {
			fisheryResourcesService.stocksBySpecies(threeAlphaCode);
		}
		for (String faoArea : areaUsageList) {
			fisheryResourcesService.retrieveStocksByFaoArea(faoArea);
		}
		sw.stop();
		logger.info(sw.shortSummary());
	}

	/**
	 * Add a parameter to the threeAlphaCode usage List. This list will be used
	 * later to update the cache.
	 * 
	 * 
	 * 
	 * @param threeAlphaCode
	 */

	public final void add2UsageStore(ThreeAlphaCode threeAlphaCode) {
		if (threeAlphaCode == null) {
			throw new FigisException("threeAlphaCodeList2UsageMap should not be null at this point");
		}

		// add a new element to the cache
		threeAlphaCodeUsageList.add(threeAlphaCode);

		adjustList(threeAlphaCodeUsageList);
	}

	/**
	 * Delete all element at the beginning of the list if the lists exceeds
	 * MAX_UPDATES number of elements.
	 * 
	 * @param anyList
	 */
	private void adjustList(List<?> anyList) {
		// delete the latest added more that MAX_UPDATES
		if (anyList.size() > maxUpdates) {
			int tooManyElemets = anyList.size() - maxUpdates;
			for (int i = 0; i <= tooManyElemets; i++) {
				anyList.remove(0);
			}
		}
	}

	/**
	 * Add the requested area to the Area Usage Store
	 * 
	 * 
	 * @param faoArea
	 */
	public void add2AreaUsageStore(String faoArea) {
		if (faoArea == null) {
			throw new FigisException("threeAlphaCodeList2UsageMap should not be null at this point");
		}

		// add a new element to the cache
		if (!areaUsageList.contains(faoArea)) {
			areaUsageList.add(faoArea);
		}
		adjustList(areaUsageList);

	}

	@Autowired
	public final void setFisheryResourcesService(FisheryResourcesService fisheryResourcesService) {
		this.fisheryResourcesService = fisheryResourcesService;
	}

}
