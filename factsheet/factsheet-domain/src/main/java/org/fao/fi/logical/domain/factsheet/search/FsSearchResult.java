package org.fao.fi.logical.domain.factsheet.search;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Logical classes for
 * http://www.fao.org/fishery/factsheets/search/xml/vmstopic/en
 * 
 * @author Erik van Ingen
 * 
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = { "rowcount", "time", "resultItemList" })
@XmlRootElement(name = "FsSearchResult")
public class FsSearchResult {

	@XmlAttribute
	private int rowcount;
	@XmlAttribute
	private int time;

	@XmlElements(@XmlElement(name = "ResultItem"))
	private List<ResultItem> resultItemList;

	public final int getRowcount() {
		return rowcount;
	}

	public final void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}

	public final int getTime() {
		return time;
	}

	public final void setTime(int time) {
		this.time = time;
	}

	public final List<ResultItem> getResultItemList() {
		return resultItemList;
	}

	public final void setResultItemList(List<ResultItem> resultItemList) {
		this.resultItemList = resultItemList;
	}

	/**
	 * For JiBX binding.
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private static List<ResultItem> resultItemSetFactory() {
		return new ArrayList<ResultItem>();
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resultItemList == null) ? 0 : resultItemList.hashCode());
		result = prime * result + rowcount;
		result = prime * result + time;
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj || obj == null) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FsSearchResult other = (FsSearchResult) obj;
		if (resultItemList == null & other.resultItemList != null) {
			return false;
		}
		if (resultItemList != null & other.resultItemList.size() != resultItemList.size()) {
			return false;
		}
		if (rowcount != other.rowcount || time != other.time) {
			return false;
		}
		return true;
	}

}
