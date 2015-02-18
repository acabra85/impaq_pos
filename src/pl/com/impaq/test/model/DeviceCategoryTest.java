/**
 * 
 */
package pl.com.impaq.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.enums.DeviceCategory;

/**
 * @author acs
 *
 */
public class DeviceCategoryTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link pl.com.impaq.main.enums.DeviceCategory#getCategory(java.lang.String)}.
	 * PRINTER
	 */
	@Test
	public void testGetTypePrinter() {		
		assertEquals(DeviceCategory.getCategory("PRINTER"), DeviceCategory.PRINTER);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.enums.DeviceCategory#getCategory(java.lang.String)}.
	 * SCANNER
	 */
	@Test
	public void testGetTypeScanner() {
		assertEquals(DeviceCategory.getCategory("SCANNER"), DeviceCategory.SCANNER);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.enums.DeviceCategory#getCategory(java.lang.String)}.
	 * DISPLAY
	 */
	@Test
	public void testGetTypeDisplay() {
		assertEquals(DeviceCategory.getCategory("DISPLAY"), DeviceCategory.DISPLAY);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.enums.DeviceCategory#getCategory(java.lang.String)}.
	 * OTHERS
	 */
	@Test
	public void testGetTypeOthers() {
		assertEquals(DeviceCategory.getCategory("XXXX"), DeviceCategory.UNKNOWN_DEVICE_TYPE);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.enums.DeviceCategory#getCategory(java.lang.String)}.
	 * OTHERS
	 */
	@Test
	public void testGetTypeUnknown() {
		assertEquals(DeviceCategory.UNKNOWN_DEVICE_TYPE+"", "UNKNOWN_DEVICE_TYPE");
	}

}
