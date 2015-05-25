package org.fao.fi.commons;

import org.junit.Test;

public class FigisExceptionTest {

	@Test
	public void testFigisExceptionThrowable() {
		try {
			throw new FigisException(new Throwable());
		} catch (FigisException e) {
		}
	}

	@Test
	public void testFigisExceptionString() {
		try {
			throw new FigisException("This wants to tell you something");
		} catch (FigisException e) {
		}
	}

	@Test
	public void testFigisExceptionStringThrowable() {
		try {
			throw new FigisException("This wants to tell you something", new Throwable());
		} catch (FigisException e) {
		}
	}

}
