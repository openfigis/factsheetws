package org.fao.fi.fisheryresources.services;

import org.apache.log4j.Logger;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.ElementResourceMapIntelligence;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.FactsheetDigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * This class optimises the performance by hitting the operations which take a very
 * long time and which are loaded into a cache on the disk.
 * 
 * 
 * @author Erik van Ingen
 */

@Component
public class CacheFiller {
	private Logger logger = Logger.getLogger(CacheFiller.class);
	private ElementResourceMapIntelligence elementResourceMapIntelligence;
	private FactsheetDigger factsheetSpeciesDigger;
	private FactsheetDigger factsheetAreaDigger;
	private FactsheetDigger factsheetIntersectionAreaDigger;

	/**
	 * This cachefiller is here to fill in advance some lists which are used
	 * intensively in the application. Check for the explanation of the number
	 * 606600000 the docs in CacheUpdater.updateCache
	 * 
	 * 
	 */

	@Scheduled(fixedDelay = 606600000)
	public void fillCache() {
		StopWatch sw = new StopWatch("CacheFiller.fillCache");
		sw.start();
		logger.info("start CacheFiller.fillCache"); 

		elementResourceMapIntelligence.create1ResourceElementRelation(FactsheetLanguage.en, factsheetSpeciesDigger);
		elementResourceMapIntelligence.create1ResourceElementRelation(FactsheetLanguage.fr, factsheetSpeciesDigger);

		elementResourceMapIntelligence.create1ResourceElementRelation(FactsheetLanguage.en, factsheetAreaDigger);
		elementResourceMapIntelligence.create1ResourceElementRelation(FactsheetLanguage.fr, factsheetAreaDigger);

		elementResourceMapIntelligence.create1ResourceElementRelation(FactsheetLanguage.en,
				factsheetIntersectionAreaDigger);
		elementResourceMapIntelligence.create1ResourceElementRelation(FactsheetLanguage.fr,
				factsheetIntersectionAreaDigger);

		sw.stop();
		logger.info(sw.shortSummary()); 
	}

	@Autowired
	public final void setElementResourceMapIntelligence(ElementResourceMapIntelligence elementResourceMapIntelligence) {
		this.elementResourceMapIntelligence = elementResourceMapIntelligence;
	}

	@Autowired
	public final void setFactsheetSpeciesDigger(FactsheetDigger factsheetSpeciesDigger) {
		this.factsheetSpeciesDigger = factsheetSpeciesDigger;
	}

	@Autowired
	public final void setFactsheetAreaDigger(FactsheetDigger factsheetAreaDigger) {
		this.factsheetAreaDigger = factsheetAreaDigger;
	}

	@Autowired
	public final void setFactsheetIntersectionDigger(FactsheetDigger factsheetIntersectionAreaDigger) {
		this.factsheetIntersectionAreaDigger = factsheetIntersectionAreaDigger;
	}

}
