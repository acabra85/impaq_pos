/**
 * 
 */
package pl.com.impaq.test.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.controller.PointOfSale;
import pl.com.impaq.main.model.core.Product;
import pl.com.impaq.main.view.devices.View;
import pl.com.impaq.main.view.devices.util.ViewInformationConstants;

/**
 * @author Agustin
 *
 */
public class ViewTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.View#start()}.
	 */
	@Test
	public void testStart() {
		PointOfSale pos = PointOfSale.getInstance();
		View.getInstance(pos);				
		View.dispose();
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.View#addDevice(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddDevice() {
		PointOfSale pos = PointOfSale.getInstance();
		View view = View.getInstance(pos);	
		assertTrue(view.addDevice("001", "Printer", "PRINTER", "OUTPUT"));
		assertFalse(view.addDevice("001", "Printer", "PRINTER", "OUTPUT"));

		assertTrue(view.addDevice("002", "Display LCD", "DISPLAY", "OUTPUT"));
		assertFalse(view.addDevice("002", "Display LCD", "DISPLAY", "OUTPUT"));

		assertTrue(view.addDevice("003", "Bar-code Scanner", "SCANNER", "INPUT"));
		assertFalse(view.addDevice("003", "Bar-code Scanner", "SCANNER", "INPUT"));
		assertFalse(view.addDevice("003", "Bar-code Scanner", "SCANNER", "INPUT_EXAMPLE"));
		View.dispose();
		PointOfSale.dispose();
		
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.View#sendBarCode()}.
	 */
	@Test
	public void testSendBarCode() {
		PointOfSale pos = PointOfSale.getInstance();
		View view = View.getInstance(pos);
		view.addDevice("001", "Printer", "PRINTER", "OUTPUT");
		view.addDevice("002", "Display LCD", "DISPLAY", "OUTPUT");
		view.addDevice("003", "Bar-code Scanner", "SCANNER", "INPUT");
		ArrayList<Product> listProducts = new ArrayList<Product>();
		listProducts.add(new Product("0001", "test-product", 3.50, "00001"));
		listProducts.add(new Product("0002", "test-product2", 7.50, "00002"));
		pos.addProductsList(listProducts );

		assertEquals(view.sendBarCode("0001"), ViewInformationConstants.PRODUCT_INFO_DISPLAYED);
		assertEquals(view.sendBarCode("0000"), ViewInformationConstants.PRODUCT_INFO_DISPLAYED);
		assertEquals(view.sendBarCode("exit"), ViewInformationConstants.RESULTS_PRINTED);
		View.dispose();
		PointOfSale.dispose();		
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.View#sendBarCode()}.
	 */
	@Test
	public void testSendBarCodeNoPrinter() {
		PointOfSale pos = PointOfSale.getInstance();
		View view = View.getInstance(pos);
		view.addDevice("002", "Display LCD", "DISPLAY", "OUTPUT");
		view.addDevice("003", "Bar-code Scanner", "SCANNER", "INPUT");
		ArrayList<Product> listProducts = new ArrayList<Product>();
		listProducts.add(new Product("0001", "test-product", 3.50, "00001"));
		listProducts.add(new Product("0002", "test-product2", 7.50, "00002"));
		pos.addProductsList(listProducts );

		assertEquals(view.sendBarCode("0001"), ViewInformationConstants.PRODUCT_INFO_DISPLAYED);
		assertEquals(view.sendBarCode("0000"), ViewInformationConstants.PRODUCT_INFO_DISPLAYED);
		assertEquals(view.sendBarCode(""), ViewInformationConstants.PRODUCT_INFO_DISPLAYED);
		assertEquals(view.sendBarCode("exit"), ViewInformationConstants.NO_PRINTER_FOUND);
		View.dispose();
		PointOfSale.dispose();		
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.View#sendBarCode()}.
	 */
	@Test
	public void testSendBarCodeNoScanner() {
		PointOfSale pos = PointOfSale.getInstance();
		View view = View.getInstance(pos);
		view.addDevice("002", "Display LCD", "DISPLAY", "OUTPUT");
		ArrayList<Product> listProducts = new ArrayList<Product>();
		listProducts.add(new Product("0001", "test-product", 3.50, "00001"));
		listProducts.add(new Product("0002", "test-product2", 7.50, "00002"));
		pos.addProductsList(listProducts );

		assertEquals(view.sendBarCode("0001"), ViewInformationConstants.NO_SCANNER_FOUND);
		assertEquals(view.sendBarCode("0000"), ViewInformationConstants.NO_SCANNER_FOUND);
		assertEquals(view.sendBarCode(""), ViewInformationConstants.NO_SCANNER_FOUND);
		assertEquals(view.sendBarCode("exit"), ViewInformationConstants.NO_SCANNER_FOUND);
		View.dispose();
		PointOfSale.dispose();		
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.View#sendBarCode()}.
	 */
	@Test
	public void testSendBarCodeNoDisplay() {
		PointOfSale pos = PointOfSale.getInstance();
		View view = View.getInstance(pos);
		view.addDevice("002", "Display LCD", "PRINTER", "OUTPUT");
		view.addDevice("003", "Bar-code Scanner", "SCANNER", "INPUT");
		ArrayList<Product> listProducts = new ArrayList<Product>();
		listProducts.add(new Product("0001", "test-product", 3.50, "00001"));
		listProducts.add(new Product("0002", "test-product2", 7.50, "00002"));
		pos.addProductsList(listProducts );

		assertEquals(view.sendBarCode("0001"), ViewInformationConstants.NO_DISPLAY_FOUND_SCANNING_PRODUCT);
		assertEquals(view.sendBarCode("0000"), ViewInformationConstants.NO_DISPLAY_FOUND_SCANNING_PRODUCT);
		assertEquals(view.sendBarCode(""), ViewInformationConstants.NO_DISPLAY_FOUND_SCANNING_PRODUCT);
		assertEquals(view.sendBarCode("exit"), ViewInformationConstants.NO_DISPLAY_FOUND_PRINTING_RESULTS);
		View.dispose();
		PointOfSale.dispose();		
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.View#start()}.
	 */
	@Test
	public void testStartAllDevices() {
		PointOfSale pos = PointOfSale.getInstance();
		View view = View.getInstance(pos);
		view.addDevice("003", "Bar-code Scanner", "SCANNER", "INPUT");
		view.addDevice("002", "Display LCD", "DISPLAY", "OUTPUT");
		
		assertEquals(view.start(), ViewInformationConstants.START);
		View.dispose();
		PointOfSale.dispose();		
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.View#start()}.
	 */
	@Test
	public void testStartNoDisplay() {
		PointOfSale pos = PointOfSale.getInstance();
		View view = View.getInstance(pos);
		view.addDevice("003", "Bar-code Scanner", "SCANNER", "INPUT");
		
		assertEquals(view.start(), ViewInformationConstants.NO_DISPLAY_FOUND);
		View.dispose();
		PointOfSale.dispose();		
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.View#start()}.
	 */
	@Test
	public void testStartNoScanner() {
		PointOfSale pos = PointOfSale.getInstance();
		View view = View.getInstance(pos);
		view.addDevice("002", "Display LCD", "DISPLAY", "OUTPUT");
		
		assertEquals(view.start(), ViewInformationConstants.NO_SCANNER_FOUND);
		View.dispose();
		PointOfSale.dispose();		
	}
}
