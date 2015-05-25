package org.fao.fi.factsheet.marshall;

public class MarshallException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8765667700991322145L;

	public MarshallException(Throwable e) {
		super(e);
	}

	public MarshallException(String newMessage) {
		super(newMessage);
	}

	public MarshallException(String msg, Throwable e) {
		super(msg, e);
	}
}
