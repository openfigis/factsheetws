package org.fao.fi.fisheryresources.services.operation.stocksbyelement;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.logical.domain.DynamicDomain;
import org.fao.fi.logical.domain.RetrieveFactsheetListResponse;
import org.fao.fi.logical.domain.RetrieveFactsheetPerDomainListRequest;
import org.fao.fi.logical.domain.RetrieveFactsheetRequest;
import org.fao.fi.services.factsheet.FactsheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.ehcache.annotations.Cacheable;

/**
 * 
 * An element can be a fao3alpha element code or a FAO Major Area
 * 
 * @author Erik van Ingen
 * 
 */

@Component
public class ElementResourceMapIntelligence {

	private FactsheetService factsheetService;
	private Logger logger = Logger.getLogger(ElementResourceMapIntelligence.class);

	@Cacheable(cacheName = "FisheryResourcesCacheDisk")
	public List<ResourceElementsList> create1ResourceElementRelation(FactsheetLanguage factsheetLanguage,
			FactsheetDigger factsheetDigger) {
		logger.debug("-- touched create1ResourceElementRelation");

		List<ResourceElementsList> listOfresourceElementList = new ArrayList<ResourceElementsList>();
		RetrieveFactsheetPerDomainListRequest request = new RetrieveFactsheetPerDomainListRequest();
		request.setDomain(DynamicDomain.resource.name());
		request.setLanguage(factsheetLanguage);

		// get the list of resources from the factsheet service
		RetrieveFactsheetListResponse response = factsheetService.retrieveFactsheetListPerDomainAndLanguage(request);
		List<FactsheetDiscriminator> resourceList = response.getFactsheetDiscriminatorList();

		// loop through the list of resources.
		for (FactsheetDiscriminator factsheetDiscriminator : resourceList) {
			if (factsheetDiscriminator.getLanguage().equals(factsheetLanguage)) {
				ResourceElementsList resourceElementList = new ResourceElementsList();
				List<String> reourceList = extractElementListFromFactsheet(factsheetDiscriminator, factsheetDigger);
				Long factsheetLong = new Long(factsheetDiscriminator.getFactsheet());
				resourceElementList.setResource(factsheetLong);
				resourceElementList.setElementList(reourceList);
				listOfresourceElementList.add(resourceElementList);
			}
		}
		// post condition
		return listOfresourceElementList;
	}

	@Cacheable(cacheName = "FisheryResourcesCache")
	public Map<String, List<Long>> create2ElementResourceMap(FactsheetLanguage factsheetLanguage,
			FactsheetDigger factsheetDigger, CodeAdditionDecission cad,
			List<ResourceElementsList> listOfresourceElementList) {
		logger.debug("-- touched ElementResourceMapIntelligence.create2ElementResourceMap");
		return generateElementResourceMapFrom(listOfresourceElementList, factsheetLanguage, cad);

	}

	private Map<String, List<Long>> generateElementResourceMapFrom(
			List<ResourceElementsList> listOfresourceElementList, FactsheetLanguage factsheetLanguage,
			CodeAdditionDecission cad) {
		Map<String, List<Long>> elementResourceMap = new HashMap<String, List<Long>>();
		for (ResourceElementsList resourceElementList : listOfresourceElementList) {
			List<String> elementCodeList = resourceElementList.getElementList();
			Long resource = resourceElementList.getResource();
			for (String elementCode : elementCodeList) {
				cad.decideAndAdd(resource, elementCode, elementResourceMap);
			}
		}
		return elementResourceMap;
	}

	/**
	 * Because of the cache, sometimes it happens that a url does not exist anymore. This logic checks therefore first
	 * whether the url exists or not. Of course this is not an optimal situation. Optimal situation is that the cache is
	 * updated when a certain url disappears. That would require quite some work to do. This solution is for now
	 * considered accaptable because the lifetime of the cache is 1 week.
	 * 
	 * 
	 * @param factsheetDiscriminator
	 * @param factsheetDigger
	 * @return
	 */
	private List<String> extractElementListFromFactsheet(FactsheetDiscriminator factsheetDiscriminator,
			FactsheetDigger factsheetDigger) {
		URL response = factsheetService.retrieveFactsheet(new RetrieveFactsheetRequest(factsheetDiscriminator));
		try {
			// check whether the url does exist.
			response.openStream();
			// when exists, do the digging.
			return factsheetDigger.dig(response);
		} catch (IOException e) {
			// when the url does not exist, return an emtpy list.
			return new ArrayList<String>();
		}
	}

	@Autowired
	public final void setFishStatFactsheetService(FactsheetService factsheetService) {
		this.factsheetService = factsheetService;
	}

}
