package pl.com.impaq.test.controller.managers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.controller.PointOfSale;
import pl.com.impaq.main.controller.devices.input.BarCodeScanner;
import pl.com.impaq.main.controller.devices.output.DisplayLCD;
import pl.com.impaq.main.controller.devices.output.Printer;
import pl.com.impaq.main.controller.managers.DeviceManager;
import pl.com.impaq.main.enums.DeviceCategory;
import pl.com.impaq.main.enums.DeviceType;

public class DeviceManagerTest {
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#createDevice(java.lang.String, java.lang.String, java.lang.String, pl.com.impaq.main.enums.DeviceCategory, pl.com.impaq.main.enums.DeviceType)}.
	 */
	@Test
	public void testCreateDevice() {
		DeviceManager dm = new DeviceManager();
		assertTrue(dm.createDevice("002", "test", "test scanner desc", 
				DeviceCategory.getCategory("SCANNER"), DeviceType.getType("INPUT")));
		assertTrue(dm.createDevice("003", "test", "test scanner desc", 
				DeviceCategory.getCategory("PRINTER"), DeviceType.getType("OUTPUT")));
		
		assertTrue(dm.createDevice("XXXXXX", "test", "test scanner desc", 
				DeviceCategory.getCategory("DISPLAY"), DeviceType.getType("OUTPUT")));
		
		assertFalse(dm.createDevice("", "testTemplate3", "test scanner desc template", 
				DeviceCategory.getCategory("DISPLAY"), DeviceType.getType("OUTPUT")));
		assertFalse(dm.createDevice("", "testTemplate1", "test scanner desc template", 
				DeviceCategory.getCategory("DISPLAY"), DeviceType.getType("OUTPUT")));
		assertFalse(dm.createDevice("", "test", "test scanner desc", 
				DeviceCategory.getCategory("PRINTER"), DeviceType.getType("OUTPUT")));
		assertFalse(dm.createDevice("", "test", "test scanner desc", 
				DeviceCategory.getCategory("TEST"), DeviceType.getType("INPUT")));
		assertFalse(dm.createDevice("", "test", "test scanner desc", 
				DeviceCategory.getCategory("SCANNER"), DeviceType.getType("INPUT")));
		assertFalse(dm.createDevice("", "testTemplate2", "test scanner desc template", 
				DeviceCategory.getCategory("XXX"), DeviceType.getType("OUTPUT")));
		assertFalse(dm.createDevice("", "test", "test scanner desc", 
				DeviceCategory.getCategory("PRINTER"), DeviceType.getType("dsds")));
		assertFalse(dm.createDevice("", "test", "test scanner desc", 
				DeviceCategory.getCategory("DISPLAY"), DeviceType.getType("OUTPUT")));
		dm = null;
		PointOfSale.dispose();
	}
	
	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#addInputDevice(pl.com.impaq.main.view.devices.Device)}.
	 */
	@Test
	public void testAddInputDevice() {
		DeviceManager dm = new DeviceManager();
		BarCodeScanner barScanner = new BarCodeScanner("002", "test", "test scanner desc", DeviceCategory.getCategory("SCANNER"));
		BarCodeScanner barScanner2 = new BarCodeScanner("002", "test", "test scanner desc", DeviceCategory.getCategory("SCANNER"));
		dm.addInputDevice(barScanner);
		dm.addInputDevice(barScanner2);
		assertEquals(dm.getSizeInputDevices(), 1);
		barScanner = null;
		barScanner2 = null;
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#addInputDevice(pl.com.impaq.main.view.devices.Device)}.
	 */
	@Test
	public void testAddInputDeviceNonCategory() {
		DeviceManager dm = new DeviceManager();
		BarCodeScanner barScanner = new BarCodeScanner("002", "test", "test scanner desc", DeviceCategory.getCategory("SDSD"));
		BarCodeScanner barScanner2 = new BarCodeScanner("002", "test", "test scanner desc", DeviceCategory.getCategory("SDSD"));
		dm.addInputDevice(barScanner);
		dm.addInputDevice(barScanner2);
		assertEquals(dm.getSizeInputDevices(), 0);
		barScanner = null;
		barScanner2 = null;
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#addInputDevice(pl.com.impaq.main.view.devices.Device)}.
	 */
	@Test
	public void testAddInputDeviceManuallyPlugScannerToPOS() {
		DeviceManager dm = new DeviceManager();
		BarCodeScanner barScanner = new BarCodeScanner("002", "test", "test scanner desc", DeviceCategory.getCategory("SCANNER"));
		BarCodeScanner barScanner2 = new BarCodeScanner("002", "test", "test scanner desc", DeviceCategory.getCategory("SCANNER"));
		dm.addInputDevice(barScanner);
		dm.addInputDevice(barScanner2);
		assertEquals(dm.getSizeInputDevices(), 1);
		barScanner = null;
		barScanner2 = null;
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#addOutputDevice(pl.com.impaq.main.view.devices.Device)}.
	 */
	@Test
	public void testAddOutputDevices() {
		DeviceManager dm = new DeviceManager();
		Printer printer = new Printer("002", "test", "test scanner desc", DeviceCategory.getCategory("PRINTER"));
		Printer printer2 = new Printer("002", "test", "test scanner desc", DeviceCategory.getCategory("PRINTER"));
		DisplayLCD display = new DisplayLCD("003", "test", "test scanner desc", DeviceCategory.getCategory("DISPLAY"));
		DisplayLCD display2 = new DisplayLCD("003", "test", "test scanner desc", DeviceCategory.getCategory("DISPLAY"));
		dm.addOutputDevice(printer);
		dm.addOutputDevice(printer2);
		dm.addOutputDevice(display2);
		dm.addOutputDevice(display);
		assertEquals(dm.getSizeOutputDevices(), 2);
		printer = null;
		printer2 = null;
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#addOutputDevice(pl.com.impaq.main.view.devices.Device)}.
	 */
	@Test
	public void testAddOutPutDeviceManuallyPlugPrinterToPOS() {
		DeviceManager dm = new DeviceManager();
		Printer printer = new Printer("002", "test", "test scanner desc", DeviceCategory.getCategory("PRINTER"));
		Printer printer2 = new Printer("002", "test", "test scanner desc", DeviceCategory.getCategory("PRINTER"));
		Printer printer3 = new Printer("004", "test", "test scanner desc", DeviceCategory.getCategory("PRINTER"));
		dm.addOutputDevice(printer);
		dm.addOutputDevice(printer);
		dm.addOutputDevice(printer2);
		dm.addOutputDevice(printer3);
		assertEquals(dm.getSizeOutputDevices(), 2);
		printer = null;
		printer2 = null;
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#addOutputDevice(pl.com.impaq.main.view.devices.Device)}.
	 */
	@Test
	public void testAddOutPutDeviceManuallyPlugDisplayToPOS() {
		DeviceManager dm = new DeviceManager();
		DisplayLCD display2 = new DisplayLCD("004", "test", "test scanner desc", DeviceCategory.getCategory("DISPLAY"));
		DisplayLCD display3 = new DisplayLCD("003", "test", "test scanner desc", DeviceCategory.getCategory("DISPLAY"));
		dm.addOutputDevice(display3);
		dm.addOutputDevice(display2);
		assertEquals(dm.getSizeOutputDevices(), 2);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#addOutputDevice(pl.com.impaq.main.view.devices.Device)}.
	 */
	@Test
	public void testAddOutputDevicesNonCategory() {
		DeviceManager dm = new DeviceManager();
		Printer printer = new Printer("002", "test", "test scanner desc", DeviceCategory.getCategory("SDSD"));
		DisplayLCD display = new DisplayLCD("002", "test", "test scanner desc", DeviceCategory.getCategory("SDSD"));
		dm.addOutputDevice(printer);
		dm.addOutputDevice(display);
		assertEquals(dm.getSizeInputDevices(), 0);
		printer = null;
		display = null;
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#getPrinter()}.
	 */
	@Test
	public void testGetPrinterInexistent() {
		DeviceManager dm = new DeviceManager();
		assertEquals(dm.getPrinter(), null);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#getPrinter()}.
	 */
	@Test
	public void testGetPrinter() {
		DeviceManager dm = new DeviceManager();
		Printer printer = new Printer("00", "printer", "desc printer", DeviceCategory.getCategory("PRINTER"));
		dm.addOutputDevice(printer);
		assertEquals(dm.getPrinter(), printer);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#getPrinter()}.
	 */
	@Test
	public void testGetPrinterSecond() {
		DeviceManager dm = new DeviceManager();
		Printer printer3 = new Printer("03", "printer", "desc printer", DeviceCategory.getCategory("PRINTER"));
		Printer printer = new Printer("00", "printer", "desc printer", DeviceCategory.getCategory("PRINTER"));
		Printer printer2 = new Printer("01", "printer", "desc printer", DeviceCategory.getCategory("PRINTER"));
		dm.addOutputDevice(printer);
		dm.addOutputDevice(printer2);
		dm.addOutputDevice(printer3);
		assertEquals(dm.getPrinter(), printer3);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#getScanner()}.
	 */
	@Test
	public void testGetScannerInexistent() {
		DeviceManager dm = new DeviceManager();
		assertEquals(dm.getScanner(), null);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#getScanner()}.
	 */
	@Test
	public void testGetScanner() {
		DeviceManager dm = new DeviceManager();
		BarCodeScanner scanner = new BarCodeScanner("00", "scanner", "desc scanner", DeviceCategory.getCategory("SCANNER"));
		dm.addInputDevice(scanner);
		assertEquals(dm.getScanner(), scanner);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#getScanner()}.
	 */
	@Test
	public void testGetScannerSecond() {
		DeviceManager dm = new DeviceManager();
		BarCodeScanner scanner = new BarCodeScanner("00", "scanner", "desc scanner", DeviceCategory.getCategory("SCANNER"));
		BarCodeScanner scanner2 = new BarCodeScanner("01", "scanner", "desc scanner", DeviceCategory.getCategory("SCANNER"));
		BarCodeScanner scanner3 = new BarCodeScanner("02", "scanner", "desc scanner", DeviceCategory.getCategory("SCANNER"));
		dm.addInputDevice(scanner);
		dm.addInputDevice(scanner2);
		dm.addInputDevice(scanner3);
		assertEquals(dm.getScanner(), scanner3);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#getDisplayLCD()}.
	 */
	@Test
	public void testGetDisplayLCDInexistent() {
		DeviceManager dm = new DeviceManager();
		assertEquals(dm.getDisplayLCD(), null);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#getDisplayLCD()}.
	 */
	@Test
	public void testGetDisplayLCD() {
		DeviceManager dm = new DeviceManager();
		DisplayLCD display = new DisplayLCD("000", "display", "description", DeviceCategory.getCategory("DISPLAY"));
		dm.addOutputDevice(display);
		assertEquals(dm.getDisplayLCD(), display);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#getDisplayLCD()}.
	 */
	@Test
	public void testGetDisplayLCDSecond() {
		DeviceManager dm = new DeviceManager();
		DisplayLCD display = new DisplayLCD("000", "display", "description", DeviceCategory.getCategory("DISPLAY"));
		DisplayLCD display2 = new DisplayLCD("001", "display", "description", DeviceCategory.getCategory("DISPLAY"));
		DisplayLCD display3 = new DisplayLCD("002", "display", "description", DeviceCategory.getCategory("DISPLAY"));
		dm.addOutputDevice(display);
		dm.addOutputDevice(display2);
		dm.addOutputDevice(display3);
		assertEquals(dm.getDisplayLCD(), display3);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#getSizeInputDevices()}.
	 */
	@Test
	public void testGetSizeInputDevices() {
		DeviceManager dm = new DeviceManager();
		BarCodeScanner scanner3 = new BarCodeScanner("02", "scanner", "desc scanner", DeviceCategory.getCategory("SCANNER"));
		dm.addInputDevice(scanner3);
		assertEquals(dm.getSizeInputDevices(), 1);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.DeviceManager#getSizeOutputDevices()}.
	 */
	@Test
	public void testGetSizeOutputDevices() {
		DeviceManager dm = new DeviceManager();
		DisplayLCD display = new DisplayLCD("000", "display", "description", DeviceCategory.getCategory("DISPLAY"));
		DisplayLCD display2 = new DisplayLCD("001", "display", "description", DeviceCategory.getCategory("DISPLAY"));
		DisplayLCD display3 = new DisplayLCD("002", "display", "description", DeviceCategory.getCategory("DISPLAY"));
		dm.addOutputDevice(display);
		dm.addOutputDevice(display2);
		dm.addOutputDevice(display3);
		assertEquals(dm.getSizeOutputDevices(), 3);
		dm = null;
		PointOfSale.dispose();
	}

	@Test
	public void testCreateOutputDevice() {
		DeviceManager dm = new DeviceManager();		
		assertFalse(dm.createOutputDevice("", "test", "desc_test", DeviceCategory.getCategory("UNKNOWN")));
		assertFalse(dm.createOutputDevice("0903", "test", "desc_test", DeviceCategory.getCategory("UNKNOWN")));
		assertFalse(dm.createOutputDevice("", "test", "desc_test", DeviceCategory.getCategory("PRINTER")));

		assertTrue(dm.createOutputDevice("0002", "test", "desc_test", DeviceCategory.getCategory("PRINTER")));
		assertTrue(dm.createOutputDevice("0003", "test", "desc_test", DeviceCategory.getCategory("PRINTER")));
		assertTrue(dm.createOutputDevice("0004", "test", "desc_test", DeviceCategory.getCategory("DISPLAY")));
		
		assertFalse(dm.createOutputDevice("0004", "test", "desc_test", DeviceCategory.getCategory("DISPLAY")));
		dm = null;
		PointOfSale.dispose();
		
	}

	@Test
	public void testCreateInputDevice() {
		DeviceManager dm = new DeviceManager();
		assertTrue(dm.createInputDevice("0004", "test", "desc_test", DeviceCategory.getCategory("SCANNER")));
		assertTrue(dm.createInputDevice("00055", "test", "desc_test", DeviceCategory.getCategory("SCANNER")));
		assertFalse(dm.createInputDevice("0004", "test", "desc_test", DeviceCategory.getCategory("SCANNER")));
		assertFalse(dm.createInputDevice("005", "test", "desc_test", DeviceCategory.getCategory("UNKNOWN")));
		assertFalse(dm.createInputDevice("", "test", "desc_test", DeviceCategory.getCategory("SCANNER")));
		dm = null;
		PointOfSale.dispose();
	}

}
