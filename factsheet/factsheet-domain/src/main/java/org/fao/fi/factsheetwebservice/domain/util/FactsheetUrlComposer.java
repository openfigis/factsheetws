package org.fao.fi.factsheetwebservice.domain.util;

import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;

public interface FactsheetUrlComposer {
	public abstract String composeStatic(String domain, String url, FactsheetLanguage lang);

	public abstract String composeFromDomainAndFactsheet(FactsheetDiscriminator d);

	public abstract FactsheetDiscriminator composeDiscriminator(String domain, String url);
}