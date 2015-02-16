package pl.com.impaq.main.controller;

import java.util.ArrayList;

import pl.com.impaq.main.controller.managers.DeviceManager;
import pl.com.impaq.main.controller.managers.ProductsManager;
import pl.com.impaq.main.enums.DeviceType;
import pl.com.impaq.main.enums.MessagesEnum;
import pl.com.impaq.main.model.core.InvoiceDetailsCalculator;
import pl.com.impaq.main.model.core.Product;
import pl.com.impaq.main.model.stubs.DevicesStub;
import pl.com.impaq.main.model.stubs.ProductStub;
import pl.com.impaq.main.view.devices.View;
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
	private View myView;
	
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
		myView = View.getInstance(this);
		myDeviceManager = new DeviceManager(this);
		new DevicesStub(myDeviceManager, CONFIG_FILE_NAME); 
		new ProductStub(myProductManager, CONFIG_FILE_NAME, calculator);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PointOfSale myPOS = new PointOfSale();
		myPOS.start();
	}

	/**
	 * Starts the POS, checking the basic output and input devices are available if so, starts the capture input loop.
	 */
	public void start() {
		myView.start();
	}

	/**
	 * Based on a list of products, prints out on the display the total of the order 
	 * and on the printer the invoice
	 * 
	 * @param listProducts list of products of the order
	 */
	public String getResults() {
		StringBuffer result = new StringBuffer(); 
		if(calculator.getTax() > 0.0) {
			result.append("\n"+ MessagesEnum.DISTANCE_INVOICE_INFO + "Tax %:\t" + calculator.getTax());
			double subtotal = calculator.calculateOrderSubTotal(listProducts);
			result.append("\n"+ MessagesEnum.DISTANCE_INVOICE_INFO + "Subtotal:\t" + subtotal);
			result.append("\n"+ MessagesEnum.DISTANCE_INVOICE_INFO + 
					"Tax Collected:\t" + calculator.calculateTaxCollected(subtotal) + "\n");
		}
		result.append(MessagesEnum.DISTANCE_INVOICE_INFO + "Total: \t"  + calculator.calculateOrderTotal(listProducts)+ "\n");
		if(isDeviceUnplugged(DeviceType.PRINTER)) {
			System.out.println(MessagesEnum.NO_PRINTER_FOUND.toString());
		} else {
			result.append(MessagesEnum.PRINTING_PRINTER + "\n");//update user on printing output device
		}
		return result.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getInvoiceResults() {		
		return calculator.getInvoiceDetails(listProducts);
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

	/**
	 * 
	 * @return
	 */
	public String getPrintInvoiceCode() {
		return MessagesEnum.PRINT_RECEIPT + "";
	}

	/**
	 * 
	 * @return
	 */
	public BarCodeScanner getInputDevice() {
		return scanner;
	}

	/**
	 * 
	 * @param barCode
	 * @return
	 */
	public boolean isPrintingInvoice(String barCode) {		
		return (MessagesEnum.PRINT_RECEIPT+"").equals(barCode);
	}

	/**
	 * 
	 * @param barCode
	 * @return
	 */
	public String receiveBarcode(String barCode) {
		if(barCode.trim().length() == 0) {
			return MessagesEnum.BARCODE_EMPTY + "" + MessagesEnum.WAIT_BARCODE_INPUT;
		} else {
			if(myProductManager.isBarCodeValid(barCode)){
				listProducts.add(myProductManager.getProduct(barCode));
				return MessagesEnum.DISTANCE_INVOICE_INFO + "" +
						listProducts.get(listProducts.size()-1 ) + "" + MessagesEnum.WAIT_BARCODE_INPUT;
			} else {
				return MessagesEnum.BARCODE_NOT_FOUND + "" + MessagesEnum.WAIT_BARCODE_INPUT;
			}
		}		
	}

	/**
	 * Returns the message to display in case of missing printer
	 * @return message to display
	 */
	public String getErrorNoPrinter() {
		return MessagesEnum.NO_PRINTER_FOUND + "";
	}

	/**
	 * 
	 * @return
	 */
	public String getErrorNoDisplay() {
		return MessagesEnum.NO_DISPLAY_FOUND + "";
	}

	/**
	 * 
	 * @return
	 */
	public String getWaitingInputMessage() {
		return MessagesEnum.WAITING_MESSAGE + "" + MessagesEnum.WAIT_BARCODE_INPUT;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPrinterUnplugged() {		
		return isDeviceUnplugged(DeviceType.PRINTER);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDisplayUnplugged() {		
		return isDeviceUnplugged(DeviceType.DISPLAY);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isScannerUnplugged() {		
		return isDeviceUnplugged(DeviceType.SCANNER);
	}

	/**
	 * 
	 * @param device
	 */
	public void addPrinterToView(Printer device) {
		myView.addPrinter(device);
	}

	/**
	 * 
	 * @param device
	 */
	public void addScannerToView(BarCodeScanner device) {
		myView.addScanner(device);
	}

	/**
	 * 
	 * @param device
	 */
	public void addDisplayToView(DisplayLCD device) {
		myView.addDisplay(device);
	}

	/**
	 * 
	 * @return
	 */
	public String getErrorNoScanner() {
		return MessagesEnum.NO_SCANNER_FOUND + "";
	}

	/**
	 * 
	 * @return
	 */
	public String getMessagePrintingOnDisplay() {
		return MessagesEnum.PRINTING_LCD + "";
	}

	/**
	 * 
	 * @return
	 */
	public String getMessagePrintingOnPrinter() {		
		return MessagesEnum.PRINTING_PRINTER+"";
	}
}