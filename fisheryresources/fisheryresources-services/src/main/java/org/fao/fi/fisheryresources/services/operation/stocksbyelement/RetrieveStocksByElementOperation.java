package org.fao.fi.fisheryresources.services.operation.stocksbyelement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.fao.fi.factsheet.marshall.FactsheetXmlMarshall;
import org.fao.fi.factsheetwebservice.domain.FactsheetDiscriminator;
import org.fao.fi.factsheetwebservice.domain.FactsheetDomain;
import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;
import org.fao.fi.figis.devcon.FIGISDoc;
import org.fao.fi.figis.devcon.FigisID;
import org.fao.fi.figis.devcon.ForeignID;
import org.fao.fi.figis.devcon.OrgRef;
import org.fao.fi.figis.devcon.Owner;
import org.fao.fi.figis.devcon.SpeciesList;
import org.fao.fi.figis.devcon.SpeciesRef;
import org.fao.fi.figis.devcon.WaterAreaList;
import org.fao.fi.figis.devcon.WaterAreaRef;
import org.fao.fi.fisheryresources.domain.stocksby.AqRes;
import org.fao.fi.fisheryresources.domain.stocksby.Identifier;
import org.fao.fi.fisheryresources.domain.stocksby.StocksByElement;
import org.fao.fi.fisheryresources.domain.stocksby.Type;
import org.fao.fi.services.factsheet.logic.FactsheetUrlComposer;
import org.fao.fi.services.factsheet.logic.FactsheetUrlComposerImpl;
import org.purl.dc.elements._1.Title;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class RetrieveStocksByElementOperation {

	private ResourcePerElement resourcePerElement;
	private ElementResourceMapIntelligence elementResourceMapIntelligence;
	private FactsheetUrlComposer factsheetUrlComposer = new FactsheetUrlComposerImpl();
	private FactsheetDigger factsheetDigger;
	private FactsheetXmlMarshall factsheetXmlMarshall = new FactsheetXmlMarshall();
	private static FactsheetDomain DOMAIN = FactsheetDomain.resource;

	private static Set<FactsheetLanguage> languageSet = new HashSet<FactsheetLanguage>();;
	static {
		languageSet.add(FactsheetLanguage.en);
		languageSet.add(FactsheetLanguage.fr);
		languageSet.add(FactsheetLanguage.fr);
	}

	protected StocksByElement preExecute(String code, CodeAdditionDecission cad) {

		// go through all the languages and find at least one to use to build up
		// the response. This logic does not consider a certain language above
		// another, it just takes the first.
		List<Long> resourceList = null;
		FactsheetLanguage atLeastFoundfactsheetLanguage = null;
		for (FactsheetLanguage factsheetLanguage : languageSet) {
			if (resourceList == null) {
				atLeastFoundfactsheetLanguage = factsheetLanguage;
				resourceList = elaborateResourceList(code, factsheetLanguage, cad);
			}
		}

		// building up the response
		StocksByElement response = new StocksByElement();
		if (resourceList != null) {
			for (Long resource : resourceList) {
				String url = factsheetUrlComposer.composeFromDomainAndFactsheet(new FactsheetDiscriminator(
						atLeastFoundfactsheetLanguage, DOMAIN, resource.toString()));
				add2response(response, parseFactsheetFrom(atLeastFoundfactsheetLanguage, resource.toString()), url);
			}
		}

		// go through all factsheet from the other languages and take from there
		// the title and the URL
		for (FactsheetLanguage factsheetLanguage : languageSet) {
			resourceList = resourcePerElement.getResourceListOf(elementResourceMapIntelligence, code,
					factsheetLanguage, factsheetDigger, cad);
			if (factsheetLanguage != atLeastFoundfactsheetLanguage && resourceList != null) {
				for (Long resource : resourceList) {
					String url = factsheetUrlComposer.composeFromDomainAndFactsheet(new FactsheetDiscriminator(
							factsheetLanguage, DOMAIN, resource.toString()));
					addOtherLanguageUrlAndTitle2response(response,
							parseFactsheetFrom(factsheetLanguage, resource.toString()), url);
				}
			}
		}
		return response;

	}

	private List<Long> elaborateResourceList(String value, FactsheetLanguage factsheetLanguage,
			CodeAdditionDecission cad) {
		return resourcePerElement.getResourceListOf(elementResourceMapIntelligence, value, factsheetLanguage,
				factsheetDigger, cad);
	}

	/**
	 * This method could be put in a cache. For instance in the case of Area 27, 133 factsheets are found where the
	 * areas from need to be extracted.
	 * 
	 * 
	 * @param atLeastFoundfactsheetLanguage
	 * @param resource
	 * @return
	 */
	private FIGISDoc parseFactsheetFrom(FactsheetLanguage atLeastFoundfactsheetLanguage, String resource) {
		FactsheetDiscriminator d = new FactsheetDiscriminator(atLeastFoundfactsheetLanguage, DOMAIN, resource);
		String url = factsheetUrlComposer.composeFromDomainAndFactsheet(d);
		return factsheetXmlMarshall.unmarshal(url);
	}

	private void addOtherLanguageUrlAndTitle2response(StocksByElement response, FIGISDoc figisDoc, String url) {
		List<AqRes> aqResList = response.getAqResList();
		if (aqResList != null) {
			for (AqRes aqRes : aqResList) {
				List<org.fao.fi.fisheryresources.domain.stocksby.FigisID> figisIdLIst = aqRes.getFigisIdList();
				for (org.fao.fi.fisheryresources.domain.stocksby.FigisID figisID : figisIdLIst) {
					if (figisID.getType().equals(Type.Object)) {
						List<Object> list = figisDoc.getAqRes().getAqResIdent().getFigisIDsAndTitlesAndReferenceYears();
						for (Object object : list) {
							if (object instanceof FigisID) {
								FigisID figisIDFactsheet = (FigisID) object;
								if (figisIDFactsheet.getType().equals("Object")
										&& figisID.getId().equals(figisIDFactsheet.getContent())) {
									addUrlAndTitle2ThisAqRes(url, figisDoc, aqRes);
								}
							}
						}
					}
				}
			}
		}
	}

	private void addUrlAndTitle2ThisAqRes(String url, FIGISDoc figisDoc, AqRes aqRes) {
		List<Object> list = figisDoc.getAqRes().getAqResIdent().getFigisIDsAndTitlesAndReferenceYears();
		for (Object object : list) {
			if (object instanceof Title) {
				Title title = (Title) object;
				org.fao.fi.fisheryresources.domain.stocksby.Title titleNew = new org.fao.fi.fisheryresources.domain.stocksby.Title();
				titleNew.setContent(title.getContent());
				titleNew.setLang(title.getLang());
				aqRes.addTitle(titleNew);
			}
		}
		addUrl(url, aqRes);
	}

	private void addUrl(String url, AqRes aqRes) {
		Identifier identifier = new Identifier();
		identifier.setContent(FactsheetUrlManipulations.xml2HtmlLanguaged(url));
		identifier.setType("URL");
		aqRes.addIdentifier(identifier);
	}

	private void add2response(StocksByElement response, FIGISDoc figisDoc, String url) {
		org.fao.fi.fisheryresources.domain.stocksby.AqRes aqRes = new org.fao.fi.fisheryresources.domain.stocksby.AqRes();
		List<Object> list = figisDoc.getAqRes().getAqResIdent().getFigisIDsAndTitlesAndReferenceYears();
		enrichWithSpecies(aqRes, list);
		enrichWithWater(aqRes, list);
		enrichWithTitleAndId(aqRes, list);
		enrichWithOwner(aqRes, figisDoc.getObjectSource().getOwner());
		addUrl(url, aqRes);
		response.addAqRes(aqRes);
	}

	/**
	 * This logic takes as the owner the organisational reference found in the last factsheet.
	 * 
	 * A factsheet has a list of organisation references. This logic takes the first organisational reference.
	 * 
	 * A factsheet has a list of references. This logic takes the last ForeignID reference which has both a code and
	 * codesystem.
	 * 
	 * 
	 * Both titles and ForeignID are part of the OrgRef.
	 * 
	 * @param aqRes
	 * @param owner
	 */
	private void enrichWithOwner(AqRes aqRes, Owner owner) {
		org.fao.fi.fisheryresources.domain.stocksby.Owner newOwner = new org.fao.fi.fisheryresources.domain.stocksby.Owner();
		org.fao.fi.fisheryresources.domain.stocksby.ForeignID newForeignID = new org.fao.fi.fisheryresources.domain.stocksby.ForeignID();
		OrgRef orgRef = owner.getCollectionRef().getOrgReves().get(0);
		List<Object> list = orgRef.getForeignIDsAndFigisIDsAndTitles();
		for (Object figisIDOrForeignID : list) {
			if (figisIDOrForeignID instanceof ForeignID) {
				ForeignID foreignIDFound = (ForeignID) figisIDOrForeignID;
				if (foreignIDFound.getCode() != null && foreignIDFound.getCodeSystem() != null) {
					newForeignID.setCode(foreignIDFound.getCode());
					newForeignID.setCodeSystem(foreignIDFound.getCodeSystem());
				}
			}
		}
		List<Object> titleList = orgRef.getForeignIDsAndFigisIDsAndTitles();
		for (Object title : titleList) {
			if (title instanceof Title) {
				org.fao.fi.fisheryresources.domain.stocksby.Title titleNew = new org.fao.fi.fisheryresources.domain.stocksby.Title();
				titleNew.setContent(((Title) title).getContent());
				titleNew.setLang(((Title) title).getLang());
				newOwner.addTitle(titleNew);
			}
		}
		newOwner.setForeignID(newForeignID);
		aqRes.setOwner(newOwner);
	}

	private void enrichWithTitleAndId(org.fao.fi.fisheryresources.domain.stocksby.AqRes aqRes, List<Object> list) {
		for (Object object : list) {
			if (object instanceof Title) {
				Title title = (Title) object;
				org.fao.fi.fisheryresources.domain.stocksby.Title titleNew = new org.fao.fi.fisheryresources.domain.stocksby.Title();
				titleNew.setContent(title.getContent());
				titleNew.setLang(title.getLang());
				aqRes.addTitle(titleNew);
			}
		}
		List<org.fao.fi.fisheryresources.domain.stocksby.FigisID> figisIDList = aqRes.getFigisIdList();
		for (Object object : list) {
			if (object instanceof FigisID) {
				FigisID figisID = (FigisID) object;
				org.fao.fi.fisheryresources.domain.stocksby.FigisID figisId = new org.fao.fi.fisheryresources.domain.stocksby.FigisID();
				figisId.setId(figisID.getContent());
				figisId.setType(org.fao.fi.fisheryresources.domain.stocksby.Type.string2Type(figisID.getType()));
				figisIDList.add(figisId);
			}
		}
	}

	private void enrichWithSpecies(org.fao.fi.fisheryresources.domain.stocksby.AqRes aqRes, List<Object> list) {
		for (Object object : list) {
			if (object instanceof SpeciesList) {
				SpeciesList speciesList = (SpeciesList) object;
				org.fao.fi.fisheryresources.domain.stocksby.SpeciesList speciesListNew = new org.fao.fi.fisheryresources.domain.stocksby.SpeciesList();
				List<Object> titleOrSpeciesRefList = speciesList.getTitlesAndSpeciesReves();
				for (Object titleOrSpeciesRef : titleOrSpeciesRefList) {
					if (titleOrSpeciesRef instanceof SpeciesRef) {
						SpeciesRef speciesRef = (SpeciesRef) titleOrSpeciesRef;
						addSpeciesRef2SpeciesList(speciesRef, speciesListNew);
					}
				}
				aqRes.getSpeciesListList().add(speciesListNew);
			}
		}
	}

	protected void enrichWithWater(org.fao.fi.fisheryresources.domain.stocksby.AqRes aqRes, List<Object> list) {
		for (Object object : list) {
			if (object instanceof WaterAreaList) {
				WaterAreaList waterAreaList = (WaterAreaList) object;
				List<Object> titleOrWaterAreaRefList = waterAreaList.getTitlesAndWaterAreaReves();
				org.fao.fi.fisheryresources.domain.stocksby.WaterAreaList waterAreaListNew = new org.fao.fi.fisheryresources.domain.stocksby.WaterAreaList();

				for (Object titleOrWaterAreaRef : titleOrWaterAreaRefList) {
					if (titleOrWaterAreaRef instanceof WaterAreaRef) {
						WaterAreaRef waterAreaRef = (WaterAreaRef) titleOrWaterAreaRef;
						List<Object> figisIDOrForeignIDList = waterAreaRef.getFigisIDsAndForeignIDs();
						for (Object figisIDOrForeignID : figisIDOrForeignIDList) {
							if (figisIDOrForeignID instanceof ForeignID) {
								addWaterAreaRef2WaterAreaList(waterAreaRef, waterAreaListNew);
							}
						}
					}

				}
				aqRes.getWaterAreaRefListList().add(waterAreaListNew);
			}

		}
	}

	protected final void addWaterAreaRef2WaterAreaList(WaterAreaRef waterAreaRef,
			org.fao.fi.fisheryresources.domain.stocksby.WaterAreaList waterAreaListNew) {
		org.fao.fi.fisheryresources.domain.stocksby.WaterAreaRef waterAreaRefNew = new org.fao.fi.fisheryresources.domain.stocksby.WaterAreaRef();
		waterAreaRefNew.setForeignID(foreinID2New(waterAreaRef.getFigisIDsAndForeignIDs()));
		waterAreaListNew.getWaterAreaRefList().add(waterAreaRefNew);

	}

	private void addSpeciesRef2SpeciesList(SpeciesRef speciesRef,
			org.fao.fi.fisheryresources.domain.stocksby.SpeciesList speciesListNew) {
		org.fao.fi.fisheryresources.domain.stocksby.SpeciesRef speciesRefNew = new org.fao.fi.fisheryresources.domain.stocksby.SpeciesRef();
		speciesRefNew.setForeignID(foreinID2New(speciesRef.getFigisIDsAndForeignIDsAndTitles()));
		speciesListNew.getSpeciesRefList().add(speciesRefNew);
	}

	/**
	 * This one takes always the last foreign ID. Is that correct?
	 * 
	 * 
	 * @param figisIDOrForeignID
	 * @return
	 */
	private org.fao.fi.fisheryresources.domain.stocksby.ForeignID foreinID2New(List<Object> figisIDOrForeignID) {
		org.fao.fi.fisheryresources.domain.stocksby.ForeignID foreignIDNew = new org.fao.fi.fisheryresources.domain.stocksby.ForeignID();
		for (Object object : figisIDOrForeignID) {
			if (object instanceof ForeignID) {
				ForeignID foreignID = (ForeignID) object;
				foreignIDNew.setCode(foreignID.getCode());
				foreignIDNew.setCodeSystem(foreignID.getCodeSystem());
			}
		}
		return foreignIDNew;
	}

	@Autowired
	public final void setResourcePerSpecies(ResourcePerElement resourcePerElement) {
		this.resourcePerElement = resourcePerElement;
	}

	@Autowired
	public final void setFactsheetUrlComposer(FactsheetUrlComposer factsheetUrlComposer) {
		this.factsheetUrlComposer = factsheetUrlComposer;
	}

	@Autowired
	public final void setElementResourceMapIntelligence(ElementResourceMapIntelligence elementResourceMapIntelligence) {
		this.elementResourceMapIntelligence = elementResourceMapIntelligence;
	}

	@Autowired
	public final void setFactsheetXmlMarshall(FactsheetXmlMarshall factsheetXmlMarshall) {
		this.factsheetXmlMarshall = factsheetXmlMarshall;
	}

	public final void setFactsheetDigger(FactsheetDigger factsheetDigger) {
		this.factsheetDigger = factsheetDigger;
	}

}
