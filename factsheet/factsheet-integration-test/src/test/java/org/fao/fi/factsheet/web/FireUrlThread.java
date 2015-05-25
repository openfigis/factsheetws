package org.fao.fi.factsheet.web;

import org.fao.fi.commons.integationtest.tools.RestWebserviceIntegrationTest;

public class FireUrlThread extends Thread {
	RestWebserviceIntegrationTest test = new RestWebserviceIntegrationTest("http://figis02:8888/factsheet-web/",
			"http://localhost:8080/factsheet-web/");

	private String restUrl;
	private Class<?> clazz;
	private Object object;

	FireUrlThread(String restUrl, Class<?> clazz) {
		this.restUrl = restUrl;
		this.clazz = clazz;

	}

	public void run() {
		this.object = test.unMarshall(restUrl, clazz);
	}

	public final Object getObject() {
		return object;
	}

}
