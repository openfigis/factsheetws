package org.fao.fi.services.factsheet.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.fao.fi.commons.FigisException;
import org.fao.fi.factsheet.search.binding.FactsheetSearchXmlBinder;
import org.fao.fi.factsheetwebservice.domain.DynamicDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;
import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.factsheetwebservice.domain.StaticDomain;
import org.fao.fi.factsheetwebservice.domain.util.FactsheetUrlComposer;
import org.fao.fi.factsheetwebservice.domain.util.FactsheetUrlComposerImpl;
import org.fao.fi.logical.domain.factsheet.search.ResultItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FactsheetAggregator aggregates over all domains and composes a list of all the published factsheets.
 * 
 * It uses the class DomainServer in order to retrieve the list of domains.
 * 
 * 
 * @author Erik van Ingen
 * 
 */
@Component
public class FactsheetAggregator {
	private FactsheetSearchXmlBinder factsheetSearchXmlBinder;
	private FactsheetUrlComposer factsheetUrlComposer = new FactsheetUrlComposerImpl();

	private static final String BEGIN_PART_OF_URL = "http://www.fao.org/fishery/factsheets/search/xml/";
	private static final String END_PART_OF_URL = "/";
	private static final Set<String> blackList = new HashSet<String>();

	static {
		blackList.add("species3302");
	}

	/**
	 * This method has a static cache. This should be replaced by a dynamic cache.
	 * 
	 * 
	 * @param lang
	 * @return
	 */

	public final List<FactsheetDiscriminator> getFactsheetsOfAllDomains(FactsheetLanguage lang) {
		List<FactsheetDiscriminator> factsheetDiscriminatorList = new ArrayList<FactsheetDiscriminator>();
		StaticDomain sds[] = StaticDomain.values();
		for (StaticDomain staticDomain : sds) {
			factsheetDiscriminatorList.addAll(getFactsheetsPerDomainAndLanguage(staticDomain.name(), lang));
		}
		DynamicDomain dds[] = DynamicDomain.values();
		for (DynamicDomain dynamicDomain : dds) {
			System.out.println(dynamicDomain.name());
			factsheetDiscriminatorList.addAll(getFactsheetsPerDomainAndLanguage(dynamicDomain.name(), lang));

		}
		return factsheetDiscriminatorList;
	}

	public List<FactsheetDiscriminator> getFactsheetsPerDomain(FactsheetDomain domain) {
		return getFactsheetsPerDomainAndLanguage(domain.toString(), FactsheetLanguage.values());
	}

	/**
	 * 
	 * 
	 * @param lang
	 * @return
	 */

	public final List<FactsheetDiscriminator> getFactsheetsPerDomainAndLanguage(String domain, FactsheetLanguage lang) {
		FactsheetLanguage[] langs = { lang };
		return getFactsheetsPerDomainAndLanguage(domain, langs);
	}

	/**
	 * 
	 * 
	 * @param lang
	 * @return
	 */

	private final List<FactsheetDiscriminator> getFactsheetsPerDomainAndLanguage(String domain,
			FactsheetLanguage[] langs) {
		List<FactsheetDiscriminator> factsheetDiscriminatorList = new ArrayList<FactsheetDiscriminator>();

		// The English list is the reference list. The Fench and Spanish lists
		// are useless.
		String url = BEGIN_PART_OF_URL + domain + END_PART_OF_URL + FactsheetLanguage.en.toString().toLowerCase();
		List<ResultItem> resultList = factsheetSearchXmlBinder.unmarshal(url).getResultItemList();
		if (resultList == null || resultList.size() == 0) {
			throw new FigisException("There is a problem with this resource: " + url);
		}

		for (ResultItem r : resultList) {
			// add only to the list when the factsheet is published
			if (entitled(r)) {

				String domainUnderstood = retrieveDomainWithException(r.getDs());

				FactsheetDiscriminator d = factsheetUrlComposer.composeDiscriminator(domainUnderstood, r.getUrl());
				// do not retrieve the language from the url but assume
				// that is the same language as it has been requested.
				String composedId = domainUnderstood + d.getFactsheet();
				boolean languageDesired = false;
				for (FactsheetLanguage lang : langs) {
					if (lang.equals(d.getLanguage())) {
						languageDesired = true;
					}
				}
				if (!blackList.contains(composedId) && languageDesired) {
					factsheetDiscriminatorList.add(d);
				}
			}
		}

		// factsheetDiscriminatorList
		return factsheetDiscriminatorList;
	}

	private String retrieveDomainWithException(String ds) {
		if (ds.equals("institute")) {
			ds = DynamicDomain.organization.name();
		}
		return ds;
	}

	/**
	 * The following rules should apply before an object is valid: (1) The status should be 2 or 3. (2) The url should
	 * end with en. (3) The id should not already have been used.
	 * 
	 * And this url is excluded for now: /fi/website/FIRetrieveAction.do?dom=speciesgroup&xml=tunalike.xml&lang=en
	 * 
	 * Cases 2 and 3 are errors in the feed.
	 * 
	 * @param r
	 * @return
	 */
	final boolean entitled(ResultItem r) {
		boolean entitled = false;
		if (r.getStatus() == FactsheetStatus.PUBLISHED_DYNAMIC || r.getStatus() == FactsheetStatus.PUBLISHED_STATIC) {
			entitled = true;
		}
		if (r.getUrl().equals("/fi/website/FIRetrieveAction.do?dom=speciesgroup&xml=tunalike.xml&lang=en")) {
			entitled = false;
		}
		return entitled;
	}

	@Autowired
	public final void setFactsheetSearchXmlBinder(FactsheetSearchXmlBinder factsheetSearchXmlBinder) {
		this.factsheetSearchXmlBinder = factsheetSearchXmlBinder;
	}

}
