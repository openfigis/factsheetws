package org.fao.fi.services.factsheet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-factsheet.xml" })
public class FactsheetServiceMultiThreadingTest {

	private FactsheetService factsheetService;

	@Test
	public void testMultiThreads() {
		FactsheetServiceThread fut1 = new FactsheetServiceThread(factsheetService);
		FactsheetServiceThread fut2 = new FactsheetServiceThread(factsheetService);
		FactsheetServiceThread fut3 = new FactsheetServiceThread(factsheetService);

		fut1.start();
		fut2.start();
		fut3.start();

		while (!fut1.getState().equals(Thread.State.TERMINATED) || !fut2.getState().equals(Thread.State.TERMINATED)
				|| !fut3.getState().equals(Thread.State.TERMINATED)) {
			System.out.println(fut1.getState());
			System.out.println(fut2.getState());
			System.out.println(fut3.getState());
			System.out.println("------------------------");
		}

	}

	@Autowired
	public final void setFactsheetService(FactsheetService factsheetService) {
		this.factsheetService = factsheetService;
	}
}
