/**
 * 
 */
package pl.com.impaq.test.model.util;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.model.util.PropertyReader;

/**
 * @author Agustin Cabra
 * @version 1.0
 *
 */
public class PropertyReaderTest {
	
	private static final String CONFIG_FILE_NAME = "data/config/config.properties";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.util.PropertyReader#retrievePropertyFromConfigFile(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRetrievePropertyFromConfigFileExistent() {
		PropertyReader pr = PropertyReader.getInstance();
		assertNotNull(pr.retrievePropertyFromConfigFile(CONFIG_FILE_NAME, "tax"));
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.util.PropertyReader#retrievePropertyFromConfigFile(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRetrievePropertyFromConfigInexistentProperty() {
		PropertyReader pr = PropertyReader.getInstance();
		assertNull(pr.retrievePropertyFromConfigFile(CONFIG_FILE_NAME, "taxxx"));
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.util.PropertyReader#retrievePropertyFromConfigFile(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRetrievePropertyFromConfigFileInExistent() {
		PropertyReader pr = PropertyReader.getInstance();
		assertEquals(pr.retrievePropertyFromConfigFile(CONFIG_FILE_NAME+"44", "tax").length(), 0);
		
	}

}
