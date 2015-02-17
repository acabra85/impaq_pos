/**
 * 
 */
package pl.com.impaq.test.model.core;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.enums.DeviceType;

/**
 * @author acs
 *
 */
public class DeviceTypeTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link pl.com.impaq.main.enums.DeviceType#getType(java.lang.String)}.
	 * PRINTER
	 */
	@Test
	public void testGetTypePrinter() {		
		assertEquals(DeviceType.getType("PRINTER"), DeviceType.PRINTER);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.enums.DeviceType#getType(java.lang.String)}.
	 * SCANNER
	 */
	@Test
	public void testGetTypeScanner() {
		assertEquals(DeviceType.getType("SCANNER"), DeviceType.SCANNER);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.enums.DeviceType#getType(java.lang.String)}.
	 * DISPLAY
	 */
	@Test
	public void testGetTypeDisplay() {
		assertEquals(DeviceType.getType("DISPLAY"), DeviceType.DISPLAY);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.enums.DeviceType#getType(java.lang.String)}.
	 * OTHERS
	 */
	@Test
	public void testGetTypeOthers() {
		assertEquals(DeviceType.getType("XXXX"), DeviceType.UNKNOWN_DEVICE_TYPE);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.enums.DeviceType#getType(java.lang.String)}.
	 * OTHERS
	 */
	@Test
	public void testGetTypeUnknown() {
		assertEquals(DeviceType.UNKNOWN_DEVICE_TYPE+"", "UNKNOWN_DEVICE_TYPE");
	}

}
