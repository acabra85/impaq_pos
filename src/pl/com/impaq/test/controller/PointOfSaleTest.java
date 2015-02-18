/**
 * 
 */
package pl.com.impaq.test.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.controller.PointOfSale;
import pl.com.impaq.main.controller.devices.input.BarCodeScanner;
import pl.com.impaq.main.controller.devices.output.DisplayLCD;
import pl.com.impaq.main.controller.devices.output.Printer;
import pl.com.impaq.main.controller.managers.DeviceManager;
import pl.com.impaq.main.enums.DeviceCategory;
import pl.com.impaq.main.enums.MessagesEnum;
import pl.com.impaq.main.model.core.InvoiceDetailsCalculator;
import pl.com.impaq.main.model.core.Product;
import pl.com.impaq.main.model.stubs.DevicesStub;
import pl.com.impaq.main.view.View;

/**
 * @author Agustin Cabra
 * @version 1.0
 *
 */
public class PointOfSaleTest {
	

	private static PointOfSale pos;
	private static final String CONFIG_FILE = "data/config/config.properties";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		pos = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#getInvoiceDetailsCalculator()}.
	 */
	@Test
	public void testGetInvoiceDetailsCalculator() {
		pos = PointOfSale.getInstance();
		assertFalse(pos.getInvoiceDetailsCalculator() == null);
		assertTrue(pos.getInvoiceDetailsCalculator() instanceof InvoiceDetailsCalculator);
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#startView()}.
	 */
	@Test
	public void testStartView() {
		pos = PointOfSale.getInstance();
		pos.setView(View.getInstance(pos));
		DeviceManager deviceManager = new DeviceManager(pos);
		pos.setDeviceManager(deviceManager);
		new DevicesStub(deviceManager, CONFIG_FILE);
		pos.startView();
		assertEquals(deviceManager.getSizeInputDevices(), 1);
		assertEquals(deviceManager.getSizeOutputDevices(), 2);
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#main()}.
	 */
	@Test
	public void testMain() {
		PointOfSale.main(null);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#getResults()}.
	 */
	@Test
	public void testGetResultsWithPrinterPlugged() {
		pos = PointOfSale.getInstance();
		Printer printer = new Printer("0001", "printer", "desc", DeviceCategory.PRINTER);
		pos.plugPrinter(printer);
		assertEquals(pos.getResults().length(), 0);
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#getResults()}.
	 */
	@Test
	public void testGetResultsWithoutPrinterPlugged() {
		pos = PointOfSale.getInstance();	
		pos.plugPrinter(null);
		String invoiceResults = pos.getResults();		
		assertEquals(invoiceResults.length(), 0);
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#getResults()}.
	 */
	@Test
	public void testGetResultsWithoutPrinterPluggedListProducts() {		
		pos = PointOfSale.getInstance();
		pos.plugPrinter(null);
		ArrayList<Product> listProducts = new ArrayList<Product>();
		listProducts.add(new Product("00", "test", 10.0, "00099"));
		listProducts.add(new Product("001", "test1", 20.0, "00019"));
		listProducts.add(new Product("002", "test2", 30.0, "00029"));
		assertEquals(pos.addProductsList(listProducts), 3);
		pos.receiveBarcode(listProducts.get(2).getBarCode());
		pos.receiveBarcode(listProducts.get(1).getBarCode());
		pos.receiveBarcode(listProducts.get(0).getBarCode());
		String invoiceResults = pos.getResults();
		assertEquals(invoiceResults.length(), 102);
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#getResults()}.
	 */
	@Test
	public void testGetResultsWithPrinterPluggedListProducts() {		
		pos = PointOfSale.getInstance();
		Printer printer = new Printer("0001", "printer", "desc", DeviceCategory.PRINTER);
		pos.plugPrinter(printer);
		ArrayList<Product> listProducts = new ArrayList<Product>();
		listProducts.add(new Product("00", "test", 10.0, "00099"));
		listProducts.add(new Product("001", "test1", 20.0, "00019"));
		listProducts.add(new Product("002", "test2", 30.0, "00029"));
		assertEquals(pos.addProductsList(listProducts), 3);
		pos.receiveBarcode(listProducts.get(2).getBarCode());
		pos.receiveBarcode(listProducts.get(1).getBarCode());
		pos.receiveBarcode(listProducts.get(0).getBarCode());
		String invoiceResults = pos.getResults();
		assertEquals(invoiceResults.length(), 102);
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#getResults()}.
	 */
	@Test
	public void testGetResultsWithoutPrinterPluggedWithTax() {
		pos = PointOfSale.getInstance();
		pos.getInvoiceDetailsCalculator().setTax(10.0);
		ArrayList<Product> listProducts = new ArrayList<Product>();
		listProducts.add(new Product("00", "test", 10.0, "00099"));
		listProducts.add(new Product("001", "test1", 20.0, "00019"));
		listProducts.add(new Product("002", "test2", 30.0, "00029"));
		assertEquals(pos.addProductsList(listProducts), 3);
		pos.receiveBarcode(listProducts.get(2).getBarCode());
		pos.receiveBarcode(listProducts.get(1).getBarCode());
		pos.receiveBarcode(listProducts.get(0).getBarCode());
		String result = pos.getResults();	
		pos.plugPrinter(null);	
		assertEquals(result.length(), 188);
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#getResults()}.
	 */
	@Test
	public void testGetResultsWithPrinterPluggedWithoutTax() {
		pos = PointOfSale.getInstance();
		Printer printer = new Printer("0001", "printer", "desc", DeviceCategory.PRINTER);
		pos.plugPrinter(printer);
		String result = pos.getResults();
		assertEquals(result.length(), 0);
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#getInvoiceResults()}.
	 */
	@Test
	public void testGetInvoiceResultsEmpty() {
		pos = PointOfSale.getInstance();
		String invoiceResults = pos.getInvoiceResults();
		assertEquals(invoiceResults.length(), 0);
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#getInvoiceResults()}.
	 */
	@Test
	public void testGetInvoiceResultsProducts() {
		pos = PointOfSale.getInstance();
		ArrayList<Product> listProducts = new ArrayList<Product>();
		listProducts.add(new Product("0004444", "test product", 25.2, "00005"));
		pos.addProductsList(listProducts);
		String invoiceResults = pos.getInvoiceResults();
		assertEquals(invoiceResults.length(), 0);
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#plugPrinter(pl.com.impaq.main.controller.devices.output.Printer)}.
	 */
	@Test
	public void testPlugPrinter() {
		pos = PointOfSale.getInstance();
		Printer printer = new Printer("0001", "printer", "desc", DeviceCategory.getCategory("PRINTER"));
		pos.plugPrinter(printer);
		assertFalse(pos.isPrinterUnplugged());
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#plugPrinter(pl.com.impaq.main.controller.devices.output.Printer)}.
	 */
	@Test
	public void testPlugPrinterNull() {
		pos = PointOfSale.getInstance();
		pos.plugPrinter(null);
		assertTrue(pos.isPrinterUnplugged());
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#plugBarcodeScanner(pl.com.impaq.main.controller.devices.input.BarCodeScanner)}.
	 */
	@Test
	public void testPlugBarcodeScanner() {
		pos = PointOfSale.getInstance();
		BarCodeScanner scanner = new BarCodeScanner("0002", "scanner", "desc", DeviceCategory.getCategory("SCANNER"));
		pos.plugBarcodeScanner(scanner);
		assertFalse(pos.isScannerUnplugged());
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#plugBarcodeScanner(pl.com.impaq.main.controller.devices.input.BarCodeScanner)}.
	 */
	@Test
	public void testPlugBarcodeScannerNull() {
		pos = PointOfSale.getInstance();
		pos.plugBarcodeScanner(null);
		assertTrue(pos.isScannerUnplugged());
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#plugDisplayLCD(pl.com.impaq.main.controller.devices.output.DisplayLCD)}.
	 */
	@Test
	public void testPlugDisplayLCD() {
		pos = PointOfSale.getInstance();
		DisplayLCD display = new DisplayLCD("0003", "display", "desc", DeviceCategory.getCategory("DISPLAY"));
		pos.plugDisplayLCD(display);
		assertFalse(pos.isDisplayUnplugged());
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#plugDisplayLCD(pl.com.impaq.main.controller.devices.output.DisplayLCD)}.
	 */
	@Test
	public void testPlugDisplayLCDNull() {
		pos = PointOfSale.getInstance();
		pos.plugDisplayLCD(null);
		assertTrue(pos.isDisplayUnplugged());
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#plugDisplayLCD(pl.com.impaq.main.controller.devices.output.DisplayLCD)}.
	 */
	@Test
	public void testPlugDisplayOtherDevice() {
		pos = PointOfSale.getInstance();
		DisplayLCD scanner = new DisplayLCD("0003", "display", "desc", DeviceCategory.getCategory("OTROS") );
		pos.plugDisplayLCD(scanner);
		assertFalse(pos.isDisplayUnplugged());
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#isDeviceUnplugged(pl.com.impaq.main.enums.DeviceCategory)}.
	 */
	@Test
	public void testIsDeviceUnplugged() {
		pos = PointOfSale.getInstance();
		assertTrue(pos.isDeviceUnplugged(DeviceCategory.getCategory("OTROS")));
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#getInputDevice()}.
	 */
	@Test
	public void testGetInputDevice() {
		pos = PointOfSale.getInstance();
		BarCodeScanner scanner = new BarCodeScanner("0001", "scanner", "desc", DeviceCategory.getCategory("SCANNER"));
		pos.plugBarcodeScanner(scanner);
		assertEquals(pos.getInputDevice(), scanner);
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#isPrintingInvoice(java.lang.String)}.
	 */
	@Test
	public void testIsPrintingInvoice() {
		pos = PointOfSale.getInstance();
		assertTrue(pos.isPrintingInvoice("exit"));
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#isPrintingInvoice(java.lang.String)}.
	 */
	@Test
	public void testIsPrintingInvoiceFalse() {
		pos = PointOfSale.getInstance();
		assertFalse(pos.isPrintingInvoice("exitt"));
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#receiveBarcode(java.lang.String)}.
	 */
	@Test
	public void testReceiveBarcodeEmpty() {
		pos = PointOfSale.getInstance();		
		assertEquals(pos.receiveBarcode(""), MessagesEnum.BARCODE_EMPTY + "");
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#receiveBarcode(java.lang.String)}.
	 */
	@Test
	public void testReceiveBarcodeInvalid() {
		pos = PointOfSale.getInstance();
		ArrayList<Product> listP = new ArrayList<Product>();
		listP.add(new Product("00", "test", 10.0, "00099"));
		pos.addProductsList(listP);
		assertEquals(pos.receiveBarcode("0000000"), MessagesEnum.BARCODE_NOT_FOUND  + "\n");
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#receiveBarcode(java.lang.String)}.
	 */
	@Test
	public void testReceiveBarcodeValid() {
		pos = PointOfSale.getInstance();
		ArrayList<Product> listP = new ArrayList<Product>();
		listP.add(new Product("00", "test", 10.0, "00099"));
		pos.addProductsList(listP);
		assertEquals(pos.receiveBarcode("00099"), "" + listP.get(0)+ "\n");
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#isPrinterUnplugged()}.
	 */
	@Test
	public void testIsPrinterUnplugged() {
		pos = PointOfSale.getInstance();
		Printer printer = new Printer("0001", "printer", "desc", DeviceCategory.getCategory("PRINTER"));
		assertTrue(pos.isPrinterUnplugged());
		pos.plugPrinter(printer);
		assertFalse(pos.isPrinterUnplugged());
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#isDisplayUnplugged()}.
	 */
	@Test
	public void testIsDisplayUnplugged() {
		pos = PointOfSale.getInstance();
		DisplayLCD display = new DisplayLCD("0003", "display", "desc", DeviceCategory.getCategory("DISPLAY") );
		assertTrue(pos.isPrinterUnplugged());
		pos.plugDisplayLCD(display);
		assertFalse(pos.isDisplayUnplugged());
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#isScannerUnplugged()}.
	 */
	@Test
	public void testIsScannerUnplugged() {
		pos = PointOfSale.getInstance();
		BarCodeScanner scanner = new BarCodeScanner("0002", "scanner", "desc", DeviceCategory.getCategory("SCANNER"));
		assertTrue(pos.isScannerUnplugged());
		pos.plugBarcodeScanner(scanner);
		assertFalse(pos.isScannerUnplugged());
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#finishCurrentOrder()}.
	 */
	@Test
	public void testFinishCurrentOrder() {
		pos = PointOfSale.getInstance();		
		assertFalse(pos.finishCurrentOrder()); //No products are stored
		ArrayList<Product> listP = new ArrayList<Product>();
		listP.add(new Product("00", "test", 10.0, "00099"));
		listP.add(new Product("00", "test", 10.0, "00099"));
		listP.add(new Product("00", "test", 10.0, "00088"));
		listP.add(new Product("00", "test", 10.0, "00088"));
		assertEquals(pos.addProductsList(listP), 2); //validate the products loaded
		pos.receiveBarcode(listP.get(0).getBarCode());
		pos.receiveBarcode(listP.get(2).getBarCode());
		pos.receiveBarcode(listP.get(0).getBarCode());
		pos.receiveBarcode(listP.get(2).getBarCode());
		assertTrue(pos.finishCurrentOrder()); //validate current order
		PointOfSale.dispose();
		
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#addProductsList(java.util.ArrayList)}.
	 */
	@Test
	public void testAddProductsList(){
		pos = PointOfSale.getInstance();
		ArrayList<Product> listP = new ArrayList<Product>();
		listP.add(new Product("00", "test", 10.0, "00099"));
		listP.add(new Product("00", "test", 10.0, "00099"));
		listP.add(new Product("00", "test", 10.0, "00088"));
		listP.add(new Product("00", "test", 10.0, "00088"));
		assertEquals(pos.addProductsList(listP), 2);
		PointOfSale.dispose();
	}


	/**
	 * Test method for {@link pl.com.impaq.main.controller.PointOfSale#addProductsList(java.util.ArrayList)}.
	 */
	@Test
	public void testAddProductsListEmpty() {
		pos = PointOfSale.getInstance();
		ArrayList<Product> listProducts = new ArrayList<Product>();
		assertEquals(pos.addProductsList(listProducts), 0);
		PointOfSale.dispose();
		
	}

}
