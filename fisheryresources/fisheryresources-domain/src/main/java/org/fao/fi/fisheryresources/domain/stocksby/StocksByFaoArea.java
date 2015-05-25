package org.fao.fi.fisheryresources.domain.stocksby;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author Erik van Ingen
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "StocksByFaoArea")
@XmlType(propOrder = { "inclusion", "intersection" })
public class StocksByFaoArea {

	@XmlElement(name = "Inclusion")
	private Inclusion inclusion;

	@XmlElement(name = "Intersection")
	private Intersection intersection;

	public final Intersection getIntersection() {
		return intersection;
	}

	public final void setIntersection(Intersection intersection) {
		this.intersection = intersection;
	}

	public final Inclusion getInclusion() {
		return inclusion;
	}

	public final void setInclusion(Inclusion inclusion) {
		this.inclusion = inclusion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inclusion == null) ? 0 : inclusion.hashCode());
		result = prime * result + ((intersection == null) ? 0 : intersection.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StocksByFaoArea other = (StocksByFaoArea) obj;
		if (inclusion == null) {
			if (other.inclusion != null)
				return false;
		} else if (!inclusion.equals(other.inclusion))
			return false;
		if (intersection == null) {
			if (other.intersection != null)
				return false;
		} else if (!intersection.equals(other.intersection))
			return false;
		return true;
	}

}
