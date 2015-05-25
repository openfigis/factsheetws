package org.fao.fi.fisheryresources.web;

import org.fao.fi.fisheryresources.domain.stocksby.StocksByFaoArea;
import org.fao.fi.fisheryresources.domain.stocksby.StocksBySpecies;
import org.fao.fi.fisheryresources.services.FisheryResourcesService;
import org.fao.fi.fisheryresources.services.vo.ThreeAlphaCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class FisheryResourceController {

	private FisheryResourcesService fisheryResourcesService;

	@RequestMapping(value = "/fao3alphacode/{fao3alphacode}", method = RequestMethod.GET)
	@ResponseBody
	public final StocksBySpecies retrieveStocksBySpeciesResponseBy3alphacode(
			@PathVariable("fao3alphacode") String fao3alphacode) {
		ThreeAlphaCode threeAlphaCode = new ThreeAlphaCode();
		threeAlphaCode.setValue(fao3alphacode);
		return fisheryResourcesService.stocksBySpecies(threeAlphaCode);
	}

	@RequestMapping(value = "/faoarea/{faoarea}", method = RequestMethod.GET)
	@ResponseBody
	public final StocksByFaoArea retrieveStocksByFaoArea(@PathVariable("faoarea") String faoarea) {
		return fisheryResourcesService.retrieveStocksByFaoArea(faoarea);
	}

	@Autowired
	public final void setFisheryResourcesService(FisheryResourcesService fisheryResourcesService) {
		this.fisheryResourcesService = fisheryResourcesService;
	}

}
