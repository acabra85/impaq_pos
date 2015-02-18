/**
 * 
 */
package pl.com.impaq.test.view;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.controller.PointOfSale;
import pl.com.impaq.main.enums.DeviceCategory;
import pl.com.impaq.main.view.devices.View;
import pl.com.impaq.main.view.devices.input.InputView;

/**
 * @author Agustin Cabra
 * @version 1.0
 *
 */
public class InputViewTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.input.InputView#getTextInputField()}.
	 */
	@Test
	public void testGetTextInputField() {
		PointOfSale pos = PointOfSale.getInstance();
		InputView iv = new InputView("001", "view name", DeviceCategory.DISPLAY, View.getInstance(pos));
		assertTrue(iv.capture().length() == 0);
		PointOfSale.dispose();
		View.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.input.InputView#clearTextInputField()}.
	 */
	@Test
	public void testClearTextInputField() {
		PointOfSale pos = PointOfSale.getInstance();
		InputView iv = new InputView("001", "view name", DeviceCategory.DISPLAY, View.getInstance(pos));
		String text = "001231301";
		iv.setTextInputField(text);
		assertEquals(iv.capture().length(), text.length());
		iv.clearTextInputField();
		assertEquals(iv.capture().length(), 0);
		PointOfSale.dispose();
		View.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.input.InputView#setTextInputField()}.
	 */
	@Test
	public void testSetTextInputField() {
		PointOfSale pos = PointOfSale.getInstance();
		InputView iv = new InputView("001", "view name", DeviceCategory.DISPLAY, View.getInstance(pos));
		String text = "sdsadasdareterbcbvc3453";
		iv.setTextInputField(text );
		assertEquals(iv.capture().length(), text.length());
		PointOfSale.dispose();
		View.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.input.InputView#getCategory()}.
	 */
	@Test
	public void testGetCategory() {
		PointOfSale pos = PointOfSale.getInstance();
		String code = "001";
		String name = "view name";
		InputView iv = new InputView(code, name, DeviceCategory.DISPLAY, View.getInstance(pos));
		assertEquals(iv.getCategory(), DeviceCategory.DISPLAY);
		PointOfSale.dispose();
		View.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.input.InputView#getName()}.
	 */
	@Test
	public void testGetName() {
		PointOfSale pos = PointOfSale.getInstance();
		String code = "001";
		String name = "view name";
		InputView iv = new InputView(code, name, DeviceCategory.DISPLAY, View.getInstance(pos));
		assertEquals(iv.getName(), name);
		PointOfSale.dispose();
		View.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.input.InputView#getCode()}.
	 */
	@Test
	public void testGetCode() {
		PointOfSale pos = PointOfSale.getInstance();
		String code = "001";
		String name = "view name";
		InputView iv = new InputView(code, name, DeviceCategory.DISPLAY, View.getInstance(pos));
		assertEquals(iv.getCode(), code);
		PointOfSale.dispose();
		View.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.input.InputView#capture()}.
	 */
	@Test
	public void testCapture() {
		PointOfSale pos = PointOfSale.getInstance();
		InputView iv = new InputView("001", "view name", DeviceCategory.DISPLAY, View.getInstance(pos));
		String text = "sdsadasdareterbcbvc3453";
		iv.setTextInputField(text);
		assertEquals(iv.capture().length(), text.length());
		PointOfSale.dispose();
		View.dispose();
	}

}
