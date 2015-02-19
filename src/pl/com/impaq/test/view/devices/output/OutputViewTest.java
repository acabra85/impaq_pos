/**
 * 
 */
package pl.com.impaq.test.view.devices.output;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.enums.DeviceCategory;
import pl.com.impaq.main.view.devices.output.OutputView;

/**
 * @author Agustin
 *
 */
public class OutputViewTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.output.OutputView#getDisplayText()}.
	 */
	@Test
	public void testGetDisplayTest() {
		OutputView ov = new OutputView("0001", "name", DeviceCategory.DISPLAY);
		String msg = "testing method";
		ov.print(msg);
		assertEquals(msg, ov.getDisplayText());
		ov = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.output.OutputView#getDisplayText()}.
	 */
	@Test
	public void testGetDisplayTestOther() {
		OutputView ov = new OutputView("0001", "name", DeviceCategory.UNKNOWN_DEVICE_TYPE);
		String msg = "testing method";
		ov.print(msg);
		assertEquals(msg, ov.getDisplayText());
		ov = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.output.OutputView#getDisplayText()}.
	 */
	@Test
	public void testGetDisplayTestPrinter() {
		OutputView ov = new OutputView("0001", "name", DeviceCategory.PRINTER);
		String msg = "testing method";
		ov.print(msg);
		assertEquals(msg, ov.getDisplayText());
		ov = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.output.OutputView#getCategory()}.
	 */
	@Test
	public void testGetCategory() {
		OutputView ov = new OutputView("0001", "name", DeviceCategory.DISPLAY);
		assertEquals(ov.getCategory(), DeviceCategory.DISPLAY);
		ov = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.output.OutputView#getName()}.
	 */
	@Test
	public void testGetName() {
		OutputView ov = new OutputView("0001", "name", DeviceCategory.DISPLAY);
		String name = ov.getName();
		assertEquals(name, "name");
		ov = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.output.OutputView#getCode()}.
	 */
	@Test
	public void testGetCode() {
		OutputView ov = new OutputView("0001", "name", DeviceCategory.DISPLAY);
		assertEquals(ov.getCode(), "0001");
		ov = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.output.OutputView#print(java.lang.String)}.
	 */
	@Test
	public void testPrintString() {
		OutputView ov = new OutputView("0001", "name", DeviceCategory.DISPLAY);
		String msg = "testing method";
		ov.print(msg);
		assertEquals(ov.getDisplayText(), msg);
		ov = null;
	}

}
