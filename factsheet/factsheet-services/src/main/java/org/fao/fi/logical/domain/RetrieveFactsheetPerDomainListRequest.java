package org.fao.fi.logical.domain;

import org.fao.fi.factsheetwebservice.domain.FactsheetLanguage;

public class RetrieveFactsheetPerDomainListRequest extends RetrieveFactsheetListRequest {

	private String domain;

	public final String getDomain() {
		return domain;
	}

	public final void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * In order to have the cache (@Cacheable(cacheName = "FactsheetServicesCache") working correctly, calculate a
	 * correct hash code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		int f = 0;
		if (getLanguage() != null) {
			FactsheetLanguage v[] = FactsheetLanguage.values();
			for (int i = 0; i < v.length; i++) {
				FactsheetLanguage factsheetLanguage = v[i];
				if (getLanguage() == factsheetLanguage) {
					f = i;
				}
			}
		}
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + f;
		return result;
	}
}
