package org.fao.fi.factsheetwebservice.domain;

import org.fao.fi.commons.FigisException;

public enum FactsheetDomain {

	// dynamic domains
	facp, fishery, fishtech, geartype, introsp, organization, resource, species, topic, vesseltype, vme,

	// static domains
	area, collection, countrysector, culturespecies, eaftool, equipment, legalframework, ontology, org, psm;

	public static FactsheetDomain parseDomain(String factsheetDomain) {
		if (factsheetDomain == null) {
			throw new FigisException("factsheetDomain is empty");
		}
		FactsheetDomain list[] = FactsheetDomain.values();
		FactsheetDomain factsheetDomainFound = null;
		for (FactsheetDomain fl : list) {
			if (fl.toString().equals(factsheetDomain.toLowerCase())) {
				factsheetDomainFound = fl;
			}
		}
		if (factsheetDomainFound == null) {
			throw new FigisException(factsheetDomain + " is not a valid factsheet domain.");
		}
		return factsheetDomainFound;
	}

	public static final boolean isFirms(FactsheetDomain domain) {
		return (domain.equals(resource) || domain.equals(fishery));
	}

}
