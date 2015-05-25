package org.fao.fi.logical.domain.factsheet.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "url", "type", "fid", "mid", "rating", "status", "ds", "name" })
public class ResultItem {

	@XmlAttribute
	private int fid;
	@XmlAttribute
	private int mid;
	@XmlAttribute
	private int rating;
	@XmlAttribute
	private int status;
	@XmlAttribute
	private String ds;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String type;
	@XmlAttribute
	private String url;

	public final int getFid() {
		return fid;
	}

	public final void setFid(int fid) {
		this.fid = fid;
	}

	public final int getMid() {
		return mid;
	}

	public final void setMid(int mid) {
		this.mid = mid;
	}

	public final int getRating() {
		return rating;
	}

	public final void setRating(int rating) {
		this.rating = rating;
	}

	public final int getStatus() {
		return status;
	}

	public final void setStatus(int status) {
		this.status = status;
	}

	public final String getDs() {
		return ds;
	}

	public final void setDs(String ds) {
		this.ds = ds;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getType() {
		return type;
	}

	public final void setType(String type) {
		this.type = type;
	}

	public final String getUrl() {
		return url;
	}

	public final void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ds == null) ? 0 : ds.hashCode());
		result = prime * result + fid;
		result = prime * result + mid;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + rating;
		result = prime * result + status;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ResultItem other = (ResultItem) obj;
		if (ds == null) {
			if (other.ds != null) {
				return false;
			}
		} else if (!ds.equals(other.ds)) {
			return false;
		}
		if (fid != other.fid) {
			return false;
		}
		if (mid != other.mid) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (rating != other.rating) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (url == null) {
			if (other.url != null) {
				return false;
			}
		} else if (!url.equals(other.url)) {
			return false;
		}
		return true;
	}

	

}
