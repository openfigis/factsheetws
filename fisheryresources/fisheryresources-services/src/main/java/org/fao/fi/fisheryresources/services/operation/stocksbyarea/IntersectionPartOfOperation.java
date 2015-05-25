package org.fao.fi.fisheryresources.services.operation.stocksbyarea;

import java.util.List;

import org.fao.fi.factsheet.domain.jaxb.IntersectingAreas;
import org.fao.fi.factsheet.domain.jaxb.WaterAreaList;
import org.fao.fi.factsheet.domain.jaxb.WaterAreaRef;
import org.fao.fi.fisheryresources.domain.stocksby.Intersection;
import org.fao.fi.fisheryresources.domain.stocksby.StocksByElement;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.CodeAdditionDecission;
import org.fao.fi.fisheryresources.services.operation.stocksbyelement.RetrieveStocksByElementOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntersectionPartOfOperation extends RetrieveStocksByElementOperation {

	public Intersection execute(String code) {
		CodeAdditionDecission cad = new AreaIntersectionDecision(code);
		StocksByElement r = this.preExecute(code, cad);
		Intersection intersection = new Intersection();
		intersection.setAqResList(r.getAqResList());
		return intersection;
	}

	@Override
	protected void enrichWithWater(org.fao.fi.fisheryresources.domain.stocksby.AqRes aqRes, List<Object> list) {
		for (Object object : list) {
			if (object instanceof WaterAreaList) {
				WaterAreaList waterAreaList = (WaterAreaList) object;
				List<Object> titleOrWaterAreaRefList = waterAreaList.getTitleOrWaterAreaRef();
				org.fao.fi.fisheryresources.domain.stocksby.WaterAreaList waterAreaListNew = new org.fao.fi.fisheryresources.domain.stocksby.WaterAreaList();

				for (Object titleOrWaterAreaRef : titleOrWaterAreaRefList) {
					if (titleOrWaterAreaRef instanceof WaterAreaRef) {
						WaterAreaRef waterAreaRef = (WaterAreaRef) titleOrWaterAreaRef;
						IntersectingAreas ias = waterAreaRef.getIntersectingAreas();
						if (ias != null) {
							List<WaterAreaRef> intersectingWaterAreaRefs = ias.getWaterAreaRef();
							for (WaterAreaRef intersectingWaterAreaRef : intersectingWaterAreaRefs) {
								addWaterAreaRef2WaterAreaList(intersectingWaterAreaRef, waterAreaListNew);
							}
						}
					}
				}
				aqRes.getWaterAreaRefListList().add(waterAreaListNew);
			}

		}
	}

	@Autowired
	public final void setFactsheetIntersectionAreaDigger(FactsheetIntersectionAreaDigger factsheetIntersectionAreaDigger) {
		this.setFactsheetDigger(factsheetIntersectionAreaDigger);
	}

}
