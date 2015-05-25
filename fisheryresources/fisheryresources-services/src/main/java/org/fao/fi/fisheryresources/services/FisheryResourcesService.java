package org.fao.fi.fisheryresources.services;

import org.fao.fi.fisheryresources.domain.stocksby.StocksByElement;
import org.fao.fi.fisheryresources.domain.stocksby.StocksByFaoArea;
import org.fao.fi.fisheryresources.domain.stocksby.StocksBySpecies;
import org.fao.fi.fisheryresources.services.operation.stocksbyarea.RetrieveStocksByFaoAreaOperation;
import org.fao.fi.fisheryresources.services.operation.stocksbyspecies.RetrieveStocksBySpeciesOperation;
import org.fao.fi.fisheryresources.services.vo.ThreeAlphaCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;

/**
 * The principle class for the fishery resource webservice. This class
 * represents the logical implementation for the webservice. This class does not
 * implement an interface because of the YAGNI principle. There would only be 1
 * implementation.
 * 
 * 
 * @author Erik van Ingen
 * 
 */
@Service
public class FisheryResourcesService {

	private RetrieveStocksBySpeciesOperation retrieveStocksBySpeciesOperation;
	private RetrieveStocksByFaoAreaOperation retrieveStocksByFaoAreaOperation;
	private CacheUpdater cacheUpdater;

	/**
	 * This service gives the stocks, given a certain species. Check for more
	 * info http://km.fao.org/FIGISwiki/index.php/Webservice_Fishery_Resources
	 * 
	 */
	@Cacheable(cacheName = "FisheryResourcesCache")
	public StocksBySpecies stocksBySpecies(ThreeAlphaCode threeAlphaCode) {

		// adding this use of the method to the cache.
		cacheUpdater.add2UsageStore(threeAlphaCode);

		StocksByElement response = retrieveStocksBySpeciesOperation.execute(threeAlphaCode.getValue());
		return (StocksBySpecies) response;

	}

	/**
	 * This service gives the stocks, given a certain species
	 * 
	 */
	@Cacheable(cacheName = "FisheryResourcesCache")
	public StocksByFaoArea retrieveStocksByFaoArea(String faoArea) {
		// adding this use of the method to the cache.
		cacheUpdater.add2AreaUsageStore(faoArea);

		return retrieveStocksByFaoAreaOperation.execute(faoArea);
	}

	@Autowired
	public final void setCacheUpdater(CacheUpdater cacheUpdater) {
		this.cacheUpdater = cacheUpdater;
	}

	@Autowired
	public final void setRetrieveStocksBySpeciesOperation(
			RetrieveStocksBySpeciesOperation retrieveStocksBySpeciesOperation) {
		this.retrieveStocksBySpeciesOperation = retrieveStocksBySpeciesOperation;
	}

	@Autowired
	public final void setRetrieveStocksByFaoAreaOperation(
			RetrieveStocksByFaoAreaOperation retrieveStocksByFaoAreaOperation) {
		this.retrieveStocksByFaoAreaOperation = retrieveStocksByFaoAreaOperation;
	}

}
