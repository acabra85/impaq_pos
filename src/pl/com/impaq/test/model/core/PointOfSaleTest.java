package pl.com.impaq.test.model.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.controller.PointOfSale;
import pl.com.impaq.main.enums.DeviceType;
import pl.com.impaq.main.enums.MessagesEnum;
import pl.com.impaq.main.model.core.Product;
import pl.com.impaq.main.view.devices.input.BarCodeScanner;
import pl.com.impaq.main.view.devices.output.DisplayLCD;
import pl.com.impaq.main.view.devices.output.Printer;

public class PointOfSaleTest {

	private static PointOfSale pos;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		pos = null;
	}

	@Test
	public void testGetResultsWithoutPrinterPlugged() {
		pos = PointOfSale.getInstance();	
		pos.plugPrinter(null);
		String invoiceResults = pos.getResults();
		//System.out.println("testGetResultsWithoutPrinterPlugged-> \n" + invoiceResults);			
		assertEquals(invoiceResults.length(), 25);
		PointOfSale.dispose();
	}

	@Test
	public void testGetResultsWithPrinterPlugged() {
		pos = PointOfSale.getInstance();
		Printer printer = new Printer("0001", "printer", "desc", DeviceType.PRINTER);
		pos.plugPrinter(printer);
		assertEquals(pos.getResults().length(), 90);
		PointOfSale.dispose();
	}

	@Test
	public void testGetResultsWithoutPrinterPluggedWithTax() {
		pos = PointOfSale.getInstance();
		pos.getInvoiceDetailsCalculator().setTax(10.0);
		String result = pos.getResults();	
		pos.plugPrinter(null);
		//System.out.println("testGetResultsWithoutPrinterPluggedWithTax-> \n" + result);	
		assertEquals(result.length(), 110);
		PointOfSale.dispose();
	}

	@Test
	public void testGetResultsWithPrinterPluggedWithoutTax() {
		pos = PointOfSale.getInstance();
		Printer printer = new Printer("0001", "printer", "desc", DeviceType.PRINTER);
		pos.plugPrinter(printer);
		String result = pos.getResults();
		//System.out.println("testGetResultsWithPrinterPluggedWithoutTax-> \n" + result);
		assertEquals(result.length(), 90);
		PointOfSale.dispose();
	}

	@Test
	public void testGetInvoiceResults() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlugPrinter() {
		pos = PointOfSale.getInstance();
		Printer printer = new Printer("0001", "printer", "desc", DeviceType.getType("PRINTER"));
		pos.plugPrinter(printer);
		assertFalse(pos.isPrinterUnplugged());
		PointOfSale.dispose();
	}

	@Test
	public void testPlugPrinterNull() {
		pos = PointOfSale.getInstance();
		pos.plugPrinter(null);
		assertTrue(pos.isPrinterUnplugged());
		PointOfSale.dispose();
	}

	@Test
	public void testPlugBarcodeScanner() {
		pos = PointOfSale.getInstance();
		BarCodeScanner scanner = new BarCodeScanner("0002", "scanner", "desc", DeviceType.getType("SCANNER"));
		pos.plugBarcodeScanner(scanner);
		assertFalse(pos.isScannerUnplugged());
		PointOfSale.dispose();
	}

	@Test
	public void testPlugBarcodeScannerNull() {
		pos = PointOfSale.getInstance();
		pos.plugBarcodeScanner(null);
		assertTrue(pos.isScannerUnplugged());
		PointOfSale.dispose();
	}

	@Test
	public void testPlugDisplayLCD() {
		pos = PointOfSale.getInstance();
		DisplayLCD scanner = new DisplayLCD("0003", "display", "desc", DeviceType.getType("DISPLAY"));
		pos.plugDisplayLCD(scanner);
		assertFalse(pos.isDisplayUnplugged());
		PointOfSale.dispose();
	}

	@Test
	public void testPlugDisplayLCDNull() {
		pos = PointOfSale.getInstance();
		pos.plugDisplayLCD(null);
		assertTrue(pos.isDisplayUnplugged());
		PointOfSale.dispose();
	}

	@Test
	public void testPlugOtherDevice() {
		pos = PointOfSale.getInstance();
		DisplayLCD scanner = new DisplayLCD("0003", "display", "desc", DeviceType.getType("OTROS") );
		pos.plugDisplayLCD(scanner);
		assertFalse(pos.isDisplayUnplugged());
		PointOfSale.dispose();
	}

	@Test
	public void testIsDeviceUnplugged() {
		pos = PointOfSale.getInstance();
		assertTrue(pos.isDeviceUnplugged(DeviceType.getType("OTROS")));
		PointOfSale.dispose();
	}

	@Test
	public void testGetInputDevice() {
		pos = PointOfSale.getInstance();
		BarCodeScanner scanner = new BarCodeScanner("0001", "scanner", "desc", DeviceType.getType("SCANNER"));
		pos.plugBarcodeScanner(scanner);
		assertEquals(pos.getInputDevice(), scanner);
		PointOfSale.dispose();
	}

	@Test
	public void testIsPrintingInvoice() {
		pos = PointOfSale.getInstance();
		assertTrue(pos.isPrintingInvoice("exit"));
		PointOfSale.dispose();
	}

	@Test
	public void testIsPrintingInvoiceFalse() {
		pos = PointOfSale.getInstance();
		assertFalse(pos.isPrintingInvoice("exitt"));
		PointOfSale.dispose();
	}

	@Test
	public void testReceiveBarcodeEmpty() {
		pos = PointOfSale.getInstance();		
		assertEquals(pos.receiveBarcode(""), MessagesEnum.BARCODE_EMPTY + "" + MessagesEnum.WAIT_BARCODE_INPUT);
		PointOfSale.dispose();
	}

	@Test
	public void testReceiveBarcodeInvalid() {
		pos = PointOfSale.getInstance();
		ArrayList<Product> listP = new ArrayList<Product>();
		listP.add(new Product("00", "test", 10.0, "00099"));
		pos.addProductsList(listP);
		assertEquals(pos.receiveBarcode("0000000"), MessagesEnum.BARCODE_NOT_FOUND + "" + MessagesEnum.WAIT_BARCODE_INPUT);
		PointOfSale.dispose();
	}

	@Test
	public void testReceiveBarcodeValid() {
		pos = PointOfSale.getInstance();
		ArrayList<Product> listP = new ArrayList<Product>();
		listP.add(new Product("00", "test", 10.0, "00099"));
		pos.addProductsList(listP);
		assertEquals(pos.receiveBarcode("00099"), MessagesEnum.DISTANCE_INVOICE_INFO + "" + listP.get(0)+ "" + MessagesEnum.WAIT_BARCODE_INPUT);
		PointOfSale.dispose();
	}
	
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

	@Test
	public void testAddPrinterToView() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddScannerToView() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddDisplayToView() {
		fail("Not yet implemented");
	}

}
