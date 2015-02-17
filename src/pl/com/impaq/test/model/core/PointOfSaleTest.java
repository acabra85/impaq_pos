package pl.com.impaq.test.model.core;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.controller.PointOfSale;
import pl.com.impaq.main.enums.DeviceType;
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
		assertEquals(pos.getResults().length(), 25);
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
		assertEquals(pos.getResults().length(), 25);
		PointOfSale.dispose();
	}

	@Test
	public void testGetResultsWithPrinterPluggedWithoutTax() {
		pos = PointOfSale.getInstance();
		Printer printer = new Printer("0001", "printer", "desc", DeviceType.PRINTER);
		pos.plugPrinter(printer);
		assertEquals(pos.getResults().length(), 90);
		PointOfSale.dispose();
	}

	@Test
	public void testGetInvoiceResults() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlugPrinter() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlugBarcodeScanner() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlugDisplayLCD() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDeviceUnplugged() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInputDevice() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsPrintingInvoice() {
		fail("Not yet implemented");
	}

	@Test
	public void testReceiveBarcode() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWaitingInputMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsPrinterUnplugged() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDisplayUnplugged() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsScannerUnplugged() {
		fail("Not yet implemented");
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
