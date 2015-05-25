package org.fao.fi.services.factsheet.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;
import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.junit.Test;

public class FactsheetUrlComposerTest {
	FactsheetLanguage lang = FactsheetLanguage.en;
	FactsheetUrlComposer factsheetUrlComposer = new FactsheetUrlComposerImpl();

	@Test
	public void testComposeStatic() {

		String domain = "staticxml.area";
		String url = "/fi/website/FIRetrieveAction.do?dom=area&xml=Area88.xml&lang=en";
		assertEquals("http://www.fao.org/fishery/xml/area/Area88/en", factsheetUrlComposer.composeStatic(domain, url,
				lang));

		try {
			domain = "explode";
			factsheetUrlComposer.composeStatic(domain, url, lang);
			fail();
		} catch (Exception e) {
		}

	}

	@Test
	public void testComposeDiscriminator1() {
		String domain;
		String url;
		FactsheetDiscriminator d;
		domain = "staticxml.area";

		url = "/fi/website/FIRetrieveAction.do?dom=area&xml=Area88.xml&lang=en";
		d = factsheetUrlComposer.composeDiscriminator(domain, url);
		assertEquals(FactsheetDomain.area, d.getDomain());
		assertEquals("Area88", d.getFactsheet());
	}

	@Test
	public void testComposeDiscriminator2() {
		String domain;
		String url;
		FactsheetDiscriminator d;
		domain = "resource";
		url = "/figis/website/FIRMSRetrieveAction.do?dom=resource&fid=2&lang=en";
		d = factsheetUrlComposer.composeDiscriminator(domain, url);
		assertEquals(FactsheetDomain.resource, d.getDomain());
		assertEquals("2", d.getFactsheet());
	}

	@Test
	public void testComposeDiscriminator3() {
		String domain;
		String url;
		FactsheetDiscriminator d;

		domain = "fishtech";
		url = "/fi/website/FIRetrieveAction.do?dom=fishtech&fid=30&lang=en";
		d = factsheetUrlComposer.composeDiscriminator(domain, url);
		assertEquals(FactsheetDomain.fishtech, d.getDomain());
		assertEquals("30", d.getFactsheet());

	}

	@Test
	public void testComposeDiscriminator4() {
		String domain;
		String url;
		FactsheetDiscriminator d;

		domain = "geartype";
		url = "/fi/website/FIRetrieveAction.do?dom=geartype&fid=101&lang=en";
		d = factsheetUrlComposer.composeDiscriminator(domain, url);
		assertEquals(FactsheetDomain.geartype, d.getDomain());
		assertEquals("101", d.getFactsheet());

	}

	@Test
	public void testComposeFromDomainAndFactsheet() {
		FactsheetDomain domain = FactsheetDomain.area;
		String factsheet = "Area88";
		FactsheetDiscriminator d = new FactsheetDiscriminator(lang, domain, factsheet);
		String url = factsheetUrlComposer.composeFromDomainAndFactsheet(d);
		assertEquals("http://www.fao.org/fishery/xml/area/Area88/en", url);
	}

}
