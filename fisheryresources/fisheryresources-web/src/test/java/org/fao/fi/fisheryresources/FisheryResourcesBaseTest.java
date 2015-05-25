package org.fao.fi.fisheryresources;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base class for all DI related tests.
 * 
 * 
 * 
 * @author Erik van Ingen
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-fisheryresources.xml" })
public abstract class FisheryResourcesBaseTest {

}
