package org.fao.fi.factsheetwebservice.domain.util;

import org.fao.fi.factsheetwebservice.FactsheetException;
import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;
import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;

public class FactsheetUrlComposerImpl implements FactsheetUrlComposer {

	private static final String BEGIN_PART_OF_URL = "http://www.fao.org/fishery/xml/";
	private static final String BEGIN_PART_OF_FIRMS_URL = "http://firms.fao.org/firms/xml/";
	private static final String END_PART_OF_URL = "/";;

	public final String composeStatic(String domain, String url, FactsheetLanguage lang) {
		FactsheetDiscriminator vo = composeFactsheetDiscriminator(url);
		String factsheetUrl = BEGIN_PART_OF_URL + vo.getDomain() + "/" + vo.getFactsheet() + END_PART_OF_URL
				+ lang.toString().toLowerCase();

		// post condition
		if (!domain.contains(vo.getDomain().toString())) {
			throw new FactsheetException(
					"StaticDomain found is not congruent with the domain composed. StaticDomain found is:" + domain
							+ ". StaticDomain composed is: " + vo.getDomain());
		}
		return factsheetUrl;
	}

	public final String composeFromDomainAndFactsheet(FactsheetDiscriminator d) {
		// precondition
		if (d.getDomain() == null) {
			throw new FactsheetException("StaticDomain is null");
		}
		if (d.getFactsheet() == null) {
			throw new FactsheetException("Factsheet is null");
		}
		if (d.getLanguage() == null) {
			throw new FactsheetException("Lang is null");
		}

		// logic

		String factsheetUrl;
		if (FactsheetDomain.isFirms(d.getDomain())) {
			factsheetUrl = BEGIN_PART_OF_FIRMS_URL + d.getDomain() + "/" + d.getFactsheet() + END_PART_OF_URL
					+ d.getLanguage().toString().toLowerCase();
		} else {
			factsheetUrl = BEGIN_PART_OF_URL + d.getDomain() + "/" + d.getFactsheet() + END_PART_OF_URL
					+ d.getLanguage().toString().toLowerCase();
		}
		return factsheetUrl;
	}

	public final FactsheetDiscriminator composeDiscriminator(String domain, String url) {
		String realDomain;
		if (domain.startsWith("staticxml.")) {
			realDomain = domain.substring(10, domain.length());
		} else {
			realDomain = domain;
		}

		FactsheetDiscriminator vo;
		if (FactsheetDomain.isFirms(FactsheetDomain.parseDomain(realDomain))) {
			vo = composeFirmsFactsheetDiscriminator(url);
		} else {
			if (url.contains("&fid=")) {
				vo = composeFirmsFactsheetDiscriminator(url);
			} else {
				vo = composeFactsheetDiscriminator(url);
			}
		}
		if (!vo.getDomain().name().equals(realDomain)) {
			throw new FactsheetException(
					"StaticDomain provided is not consistent with the domain found in the Url. StaticDomain provided: "
							+ domain + ". StaticDomain found in Url: " + vo.getDomain() + ". Url is " + url);
		}
		return vo;
	}

	private FactsheetDiscriminator composeFactsheetDiscriminator(String url) {
		int positionDomain = url.indexOf("dom=");
		int positionXml = url.indexOf("&xml=");
		int positionLang = url.indexOf("&lang");
		String domainFound = url.substring(positionDomain + 4, positionXml);
		String name = url.substring(positionXml + 5, positionLang - 4);
		String lang = url.substring(positionLang + 6, positionLang + 8);
		FactsheetDiscriminator vo = new FactsheetDiscriminator(FactsheetLanguage.parseLanguage(lang),
				FactsheetDomain.parseDomain(domainFound), name);
		return vo;
	}

	/**
	 * "/figis/website/FIRMSRetrieveAction.do?dom=resource&fid=2&lang=en";
	 * 
	 * @param domain
	 * @param url
	 * @return
	 */
	private FactsheetDiscriminator composeFirmsFactsheetDiscriminator(String url) {
		int positionDomain = url.indexOf("dom=");
		int positionFid = url.indexOf("&fid=");
		int positionLang = url.indexOf("&lang");
		String lang = url.substring(positionLang + 6, positionLang + 8);
		String domainFound = url.substring(positionDomain + 4, positionFid);
		String name = url.substring(positionFid + 5, positionLang);
		FactsheetDiscriminator vo = new FactsheetDiscriminator(FactsheetLanguage.parseLanguage(lang),
				FactsheetDomain.parseDomain(domainFound), name);
		return vo;
	}

}
