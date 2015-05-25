package org.fao.fi.factsheet.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-factsheet.xml" })
public class FactsheetControllerMultiThreadingTest {

	private FactsheetController factsheetController;

	@Test
	public void testMultiThreads() {

		FactsheetControllerThread fut1 = new FactsheetControllerThread(factsheetController);
		FactsheetControllerThread fut2 = new FactsheetControllerThread(factsheetController);
		FactsheetControllerThread fut3 = new FactsheetControllerThread(factsheetController);
		fut1.start();
		fut2.start();
		fut3.start();

		while (!fut1.getState().equals(Thread.State.TERMINATED) || !fut2.getState().equals(Thread.State.TERMINATED)
				|| !fut3.getState().equals(Thread.State.TERMINATED)) {
			// System.out.println(fut1.getState());
			// System.out.println(fut2.getState());
			// System.out.println(fut3.getState());
			// System.out.println("------------------------");
		}

	}

	@Autowired
	public final void setFactsheetController(FactsheetController factsheetController) {
		this.factsheetController = factsheetController;
	}

}
