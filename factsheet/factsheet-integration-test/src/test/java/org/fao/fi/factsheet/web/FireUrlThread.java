package org.fao.fi.factsheet.web;


public class FireUrlThread extends Thread {

	private String restUrl;
	private Class<?> clazz;
	private Object object;

	FireUrlThread(String restUrl, Class<?> clazz) {
		this.restUrl = restUrl;
		this.clazz = clazz;

	}

	public void run() {
		this.object = FactsheetIntegrationTest.test.unMarshall(restUrl, clazz);
	}

	public final Object getObject() {
		return object;
	}

}
