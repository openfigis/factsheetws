package org.fao.fi.fisheryresources.services;

import java.io.File;

import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.factsheetwebservice.domain.util.FactsheetUrlComposer;

public class FactsheetUrlComposerDummy4Testing implements FactsheetUrlComposer {

	@Override
	public FactsheetDiscriminator composeDiscriminator(String domain, String url) {
		// no discriminator needed for dummy testing
		return null;
	}

	@Override
	public String composeFromDomainAndFactsheet(FactsheetDiscriminator d) {
		String url = "";
		if (d.getLanguage().equals(FactsheetLanguage.en)) {
			File file = new File("src/test/resources/ResourcePerSpeciesDummy4Testing/10123en.xml");
			url = file.getAbsolutePath();
		}
		if (d.getLanguage().equals(FactsheetLanguage.fr)) {
			File file = new File("src/test/resources/ResourcePerSpeciesDummy4Testing/10123fr.xml");
			url = file.getAbsolutePath();
		}
		url = "file:///" + url;

		return url;
	}

	@Override
	public String composeStatic(String domain, String url, FactsheetLanguage lang) {
		// no static needed for dummy testing
		return null;
	}

}
