package pl.com.impaq.main.controller;

import java.io.InputStream;
import java.util.ArrayList;

import pl.com.impaq.main.controller.managers.DeviceManager;
import pl.com.impaq.main.controller.managers.ProductsManager;
import pl.com.impaq.main.model.core.InvoiceDetailsCalculator;
import pl.com.impaq.main.model.core.Product;
import pl.com.impaq.main.model.enums.DeviceType;
import pl.com.impaq.main.model.enums.MessagesEnum;
import pl.com.impaq.main.model.stubs.DevicesStub;
import pl.com.impaq.main.model.stubs.ProductStub;
import pl.com.impaq.main.view.devices.input.BarCodeScanner;
import pl.com.impaq.main.view.devices.output.DisplayLCD;
import pl.com.impaq.main.view.devices.output.Printer;

public class PointOfSale {
	
	private static final String CONFIG_FILE_NAME = "data/config/config.properties";
	private Printer printer;
	private BarCodeScanner scanner;
	private DisplayLCD display;
	private ProductsManager myProductManager;
	private DeviceManager myDeviceManager;
	InvoiceDetailsCalculator calculator;
	private ArrayList<Product> listProducts;
	
	/**
	 * 
	 */
	public PointOfSale() {
		printer = null;
		scanner = null;
		display = null;
		listProducts = new ArrayList<Product>();
		calculator = new InvoiceDetailsCalculator();
		myProductManager = new ProductsManager(); 
		myDeviceManager = new DeviceManager(this);
		new DevicesStub(myDeviceManager, CONFIG_FILE_NAME); 
		new ProductStub(myProductManager, CONFIG_FILE_NAME, calculator);
	}
	
	public static void main(String[] args) {
		PointOfSale myPOS = new PointOfSale();
		myPOS.start();
	}

	/**
	 * Starts the POS, checking the basic output and input devices are available if so, starts the capture input loop.
	 */
	public void start() {
		System.out.println(MessagesEnum.PRINTING_LCD + ""); //update user on printing output device
		if(isDeviceUnplugged(DeviceType.DISPLAY)) {
			System.out.println(MessagesEnum.NO_DISPLAY_FOUND.toString());
		} else {
			display.print(MessagesEnum.WAITING_MESSAGE + "" + MessagesEnum.WAIT_BARCODE_INPUT);
			if(isDeviceUnplugged(DeviceType.SCANNER)) {
				System.out.println(MessagesEnum.NO_SCANNER_FOUND.toString());
			} else {
				captureBarcodeLoop(System.in);
			}
		}
	}

	/** Waits for the user to scans product bar-codes through the scanner,
	 * when receives the input 'exit', the POS will send the order list to print the results 
	 * the LCD Screen
	 * @param in type of input to capture information
	 */
	private void captureBarcodeLoop(InputStream in) {
		String barCode = scanner.capture(in);
		while(!barCode.equals(MessagesEnum.PRINT_RECEIPT+"")) {
			if(barCode.trim().length() == 0) {
				display.print(MessagesEnum.BARCODE_EMPTY + "" + MessagesEnum.WAIT_BARCODE_INPUT);
			} else {
				if(myProductManager.isBarCodeValid(barCode)){
					listProducts.add(myProductManager.getProduct(barCode));
					display.print(MessagesEnum.DISTANCE_INVOICE_INFO + "" +
							listProducts.get(listProducts.size()-1 ) + MessagesEnum.WAIT_BARCODE_INPUT + "");
				} else {
					display.print(MessagesEnum.BARCODE_NOT_FOUND + "" + MessagesEnum.WAIT_BARCODE_INPUT );
				}
			}
			barCode = scanner.capture(in);
		}
		printResults(listProducts);
	}

	/**
	 * Based on a list of products, prints out on the display the total of the order 
	 * and on the printer the invoice
	 * 
	 * @param listProducts list of products of the order
	 */
	private void printResults(ArrayList<Product> listProducts) {
		if(calculator.getTax() > 0.0) {
			display.print("\n"+ MessagesEnum.DISTANCE_INVOICE_INFO + "Tax %:\t" + calculator.getTax());
			double subtotal = calculator.calculateOrderSubTotal(listProducts);
			display.print("\n"+ MessagesEnum.DISTANCE_INVOICE_INFO + "Subtotal:\t" + subtotal);
			display.println("\n"+ MessagesEnum.DISTANCE_INVOICE_INFO + "Tax Collected:\t" + calculator.calculateTaxCollected(subtotal));
		}
		display.println(MessagesEnum.DISTANCE_INVOICE_INFO + "Total: \t"  + calculator.calculateOrderTotal(listProducts));
		if(isDeviceUnplugged(DeviceType.PRINTER)) {
			System.out.println(MessagesEnum.NO_PRINTER_FOUND.toString());
		} else {
			display.println(MessagesEnum.PRINTING_PRINTER + "");//update user on printing output device
			printer.print(calculator.getInvoiceDetails(listProducts));
		}
	}

	/**
	 * Plugs a printer to the POS
	 * @param printer the printer to be plugged
	 */
	public void plugPrinter(Printer device) {
		if(device != null) this.printer = device;
	}
	
	/**
	 * Plugs a scanner to the POS
	 * @param scanner the scanner to be plugged
	 */
	public void plugBarcodeScanner(BarCodeScanner device) {
		if(device != null) this.scanner = device;
	}
	
	/**
	 * Plugs a display to the POS
	 * @param display the display to be plugged
	 */
	public void plugDisplayLCD(DisplayLCD device) {
		if(device != null) this.display = device;
	}

	/**
	 * 
	 * @param type the type of the device to check
	 * @return boolean indicating whether or not the device is plugged
	 */
	public boolean isDeviceUnplugged(DeviceType type) {
		boolean unplugged = true;
		switch (type) {
			case SCANNER:
				unplugged = (scanner == null);
				break;
			case PRINTER:
				unplugged = (printer == null);
				break;
			case DISPLAY:
				unplugged = (display == null);
				break;
			default:
				break;
		}
		return unplugged;
	}

	public String getPrintInvoiceCode() {
		return MessagesEnum.PRINT_RECEIPT + "";
	}

	public BarCodeScanner getInputDevice() {
		return scanner;
	}

	public String receiveBarcodeScanned(String barCode) {
		
		return "";
	}
}