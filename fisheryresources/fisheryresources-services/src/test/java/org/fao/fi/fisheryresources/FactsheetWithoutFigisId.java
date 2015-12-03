package org.fao.fi.fisheryresources;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.fao.fi.factsheet.marshall.FactsheetXmlMarshall;
import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.factsheetwebservice.domain.util.FactsheetUrlComposer;
import org.fao.fi.factsheetwebservice.domain.util.FactsheetUrlComposerImpl;
import org.fao.fi.figis.devcon.FIGISDoc;
import org.fao.fi.figis.devcon.FigisID;
import org.fao.fi.logical.domain.RetrieveFactsheetListResponse;
import org.fao.fi.logical.domain.RetrieveFactsheetPerDomainListRequest;
import org.fao.fi.services.factsheet.FactsheetService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FactsheetWithoutFigisId extends FisheryResourcesBaseTest {

	FactsheetService factsheetService;

	@Test
	@Ignore
	public void calculateNumberOfResourceFactsheetWithoutFigisId() {
		RetrieveFactsheetPerDomainListRequest request = new RetrieveFactsheetPerDomainListRequest();
		request.setDomain("resource");
		request.setLanguage(FactsheetLanguage.en);
		RetrieveFactsheetListResponse response = factsheetService.retrieveFactsheetListPerDomainAndLanguage(request);
		List<FactsheetDiscriminator> list = response.getFactsheetDiscriminatorList();
		Set<String> withoutFigisID = new HashSet<String>();
		for (FactsheetDiscriminator factsheetDiscriminator : list) {
			FactsheetUrlComposer c = new FactsheetUrlComposerImpl();
			String url = c.composeFromDomainAndFactsheet(factsheetDiscriminator);
			FactsheetXmlMarshall m = new FactsheetXmlMarshall();
			FIGISDoc doc = m.unmarshal(url);
			List<Serializable> olist = doc.getAqRes().getAqResIdent().getFigisIDsAndTitlesAndReferenceYears();
			FigisID id = null;
			for (Object object : olist) {
				if (object instanceof FigisID) {
					FigisID foundID = (FigisID) object;
					if (foundID.getType() == null || foundID.getType().equals("Object")) {
						id = foundID;
					}
					// if (foundID.getType() != null &&
					// foundID.getType().equals("Observation")) {
					// oid = foundID;
					// }
				}
			}

			if (id == null) {
				System.out.println(url);
				withoutFigisID.add(url);

			}
		}
		System.out.println(withoutFigisID.size());

	}

	@Autowired
	public final void setFishStatFactsheetService(FactsheetService factsheetService) {
		this.factsheetService = factsheetService;
	}

}
