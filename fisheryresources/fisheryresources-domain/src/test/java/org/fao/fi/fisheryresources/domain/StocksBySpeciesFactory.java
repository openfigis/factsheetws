package org.fao.fi.fisheryresources.domain;

import java.util.ArrayList;
import java.util.List;

import org.fao.fi.fisheryresources.domain.stocksby.AqRes;
import org.fao.fi.fisheryresources.domain.stocksby.FigisID;
import org.fao.fi.fisheryresources.domain.stocksby.ForeignID;
import org.fao.fi.fisheryresources.domain.stocksby.Identifier;
import org.fao.fi.fisheryresources.domain.stocksby.Owner;
import org.fao.fi.fisheryresources.domain.stocksby.SpeciesList;
import org.fao.fi.fisheryresources.domain.stocksby.SpeciesRef;
import org.fao.fi.fisheryresources.domain.stocksby.StocksBySpecies;
import org.fao.fi.fisheryresources.domain.stocksby.Title;
import org.fao.fi.fisheryresources.domain.stocksby.Type;
import org.fao.fi.fisheryresources.domain.stocksby.WaterAreaList;
import org.fao.fi.fisheryresources.domain.stocksby.WaterAreaRef;

public class StocksBySpeciesFactory {

	static StocksBySpecies create() {
		FigisID figisID1 = new FigisID();
		figisID1.setId("10315");
		figisID1.setType(Type.Object);
		FigisID figisID2 = new FigisID();
		figisID2.setId("155383");
		figisID2.setType(Type.Observation);

		List<FigisID> figisIdList = new ArrayList<FigisID>();
		figisIdList.add(figisID1);
		figisIdList.add(figisID2);

		ForeignID foreignID1 = new ForeignID();
		foreignID1.setCode("cod");
		foreignID1.setCodeSystem("fao3alpha");

		SpeciesRef speciesRef = new SpeciesRef();
		speciesRef.setForeignID(foreignID1);

		ForeignID foreignID2 = new ForeignID();
		foreignID2.setCode("21.3.O");
		foreignID2.setCodeSystem("fao_div");
		WaterAreaRef waterAreaRef = new WaterAreaRef();
		waterAreaRef.setForeignID(foreignID2);

		List<SpeciesRef> speciesRefList = new ArrayList<SpeciesRef>();
		speciesRefList.add(speciesRef);

		List<WaterAreaRef> waterAreaRefList = new ArrayList<WaterAreaRef>();
		waterAreaRefList.add(waterAreaRef);

		SpeciesList speciesList = new SpeciesList();
		speciesList.setSpeciesRefList(speciesRefList);

		WaterAreaList waterAreaList = new WaterAreaList();
		waterAreaList.setWaterAreaRefList(waterAreaRefList);

		List<SpeciesList> speciesListList = new ArrayList<SpeciesList>();
		speciesListList.add(speciesList);

		List<WaterAreaList> waterAreaRefListList = new ArrayList<WaterAreaList>();
		waterAreaRefListList.add(waterAreaList);

		Identifier id = new Identifier();
		id.setContent("ftp://ftp.fao.org/docrep/fao/010/a1291e/a1291e.pdf");
		id.setType("URI");

		Title titleEn = new Title();
		titleEn.setContent("Skipjack tuna - Eastern Pacific");

		Title titleEnOwner = new Title();
		titleEnOwner.setLang("en");
		titleEnOwner.setContent("Northwest Atlantic Fisheries Organization (NAFO)");
		Title titleESOwner = new Title();
		titleESOwner.setLang("es");
		titleESOwner.setContent("Organización de Pesquerías del Atlántico Noroeste (NAFO)");
		Title titleFROwner = new Title();
		titleFROwner.setLang("fr");
		titleFROwner.setContent("L'Atlantique du Nord-Ouest Organisation des pêches (OPANO)");
		ForeignID foreignIDOwner = new ForeignID();
		foreignIDOwner.setCode("NAFO_SS");
		foreignIDOwner.setCodeSystem("acronym");

		Owner owner = new Owner();
		owner.setForeignID(foreignIDOwner);
		owner.addTitle(titleEnOwner);
		owner.addTitle(titleESOwner);
		owner.addTitle(titleFROwner);

		AqRes aqRes = new AqRes();
		aqRes.setFigisIdList(figisIdList);
		aqRes.addTitle(titleEn);
		aqRes.setSpeciesListList(speciesListList);
		aqRes.setWaterAreaRefListList(waterAreaRefListList);
		aqRes.addIdentifier(id);
		aqRes.setOwner(owner);

		StocksBySpecies response = new StocksBySpecies();
		response.addAqRes(aqRes);

		return response;
	}
}
