package pl.com.impaq.main.controller;

import java.util.ArrayList;

import pl.com.impaq.main.controller.devices.input.BarCodeScanner;
import pl.com.impaq.main.controller.devices.input.InputDevice;
import pl.com.impaq.main.controller.devices.output.DisplayLCD;
import pl.com.impaq.main.controller.devices.output.Printer;
import pl.com.impaq.main.controller.managers.DeviceManager;
import pl.com.impaq.main.controller.managers.ProductsManager;
import pl.com.impaq.main.enums.DeviceCategory;
import pl.com.impaq.main.enums.DeviceType;
import pl.com.impaq.main.enums.MessagesEnum;
import pl.com.impaq.main.model.core.InvoiceDetailsCalculator;
import pl.com.impaq.main.model.core.Product;
import pl.com.impaq.main.model.stubs.DevicesStub;
import pl.com.impaq.main.model.stubs.ProductStub;
import pl.com.impaq.main.view.View;

public class PointOfSale {
	
	private static final String CONFIG_FILE_NAME = "data/config/config.properties";
	private ProductsManager myProductManager;
	private static DeviceManager myDeviceManager;
	InvoiceDetailsCalculator calculator;
	private ArrayList<Product> currentOrderList;
	private static View myView;
	private static PointOfSale instance;
	
	/**
	 * 
	 */
	private PointOfSale() {
		currentOrderList = new ArrayList<Product>();
		calculator = new InvoiceDetailsCalculator();
		myProductManager = new ProductsManager(); 
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PointOfSale myPOS = PointOfSale.getInstance();
		myPOS.setUp();
		myPOS.startView();
	}

	/**
	 * Creates the view and the devices manager
	 */
	private void setUp() {
		setView(View.getInstance(this));
		setDeviceManager(new DeviceManager());
		setUpDevicesStub();
		setUpProductsStub();
	}
	
	/**
	 * load devices onto the manager reading from a stub text file
	 */
	private void setUpDevicesStub() {
		new DevicesStub(myDeviceManager, CONFIG_FILE_NAME); 		
	}
	
	/**
	 * load products to the product manager from a stub text file
	 */
	private void setUpProductsStub() {
		new ProductStub(myProductManager, CONFIG_FILE_NAME, calculator);
	}
	
	/**
	 * 
	 * @return return the calculator
	 */
	public InvoiceDetailsCalculator getInvoiceDetailsCalculator() {
		return calculator;
	}

	/**
	 * Starts the View of POS, checking the basic output and input devices are available if so, 
	 * starts the capture input loop.
	 */
	public void startView() {
		mapDevicesToView();
		myView.start();
	}

	private void mapDevicesToView() {
		if(myDeviceManager.getSizeOutputDevices()>0){
			if(!isDisplayUnplugged()) {
				DisplayLCD display = myDeviceManager.getDisplayLCD();
				myView.addDevice(
						display.getCode(), 
						display.getName(), 
						display.getCategory()+"",DeviceType.OUTPUT+"");
			}
			if(!isPrinterUnplugged()){
				Printer printer = myDeviceManager.getPrinter();
				myView.addDevice(printer.getCode(), printer.getName(), 
						printer.getCategory()+"",DeviceType.OUTPUT+"");
				
			}
		}
		if(myDeviceManager.getSizeInputDevices()>0){
			BarCodeScanner scanner = myDeviceManager.getScanner();
			if(!isScannerUnplugged()){
				myView.addDevice(scanner.getCode(), scanner.getName(), 
						scanner.getCategory()+"", DeviceType.INPUT+"");
			}
		}
	}

	/**
	 * Based on a list of products, prints out on the display the total of the order 
	 * and on the printer the invoice
	 */
	public String getResults() {
		if(currentOrderList.size()>0) {
			StringBuffer result = new StringBuffer(); 
			if(calculator.getTax() > 0.0) {
				result.append("\n"+ MessagesEnum.DISTANCE_INVOICE_INFO + "Tax %:\t" + calculator.getTax());
				double subtotal = calculator.calculateOrderSubTotal(currentOrderList);
				result.append("\n"+ MessagesEnum.DISTANCE_INVOICE_INFO + "Subtotal:\t" + subtotal);
				result.append("\n"+ MessagesEnum.DISTANCE_INVOICE_INFO + 
						MessagesEnum.TAX_COLLECTED + "\t" + calculator.calculateTaxCollected(subtotal) + "\n");
			}
			double orderTotal = calculator.calculateOrderTotal(currentOrderList);
			result.append(MessagesEnum.DISTANCE_INVOICE_INFO + "Total: \t"  + Math.round(orderTotal*100.0)/100.0+ "\n");
			result.append(MessagesEnum.INVOICE_FOOTER + "\n\n");
			if(isDeviceUnplugged(DeviceCategory.PRINTER)) {
				System.out.println(MessagesEnum.NO_PRINTER_FOUND.toString());
			} else {
				System.out.println(MessagesEnum.PRINTING_PRINTER + "\n");//update user on printing output device
			}
			return result.toString();
		} else {
			return "";
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getInvoiceResults() {		
		return calculator.getInvoiceDetails(currentOrderList);
	}

	/**
	 * 
	 * @param type the type of the device to check
	 * @return boolean indicating whether or not the device is plugged
	 */
	public boolean isDeviceUnplugged(DeviceCategory type) {
		boolean unplugged = true;
		if(myDeviceManager == null)
			return true;
		switch (type) {
			case SCANNER:
				unplugged = (myDeviceManager.getScanner() == null);
				break;
			case PRINTER:
				unplugged = (myDeviceManager.getPrinter() == null);
				break;
			case DISPLAY:
				unplugged = (myDeviceManager.getDisplayLCD() == null);
				break;
			default:
				unplugged = true;
				break;
		}
		return unplugged;
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
			return MessagesEnum.BARCODE_EMPTY + "";
		} else {
			if(myProductManager.isBarCodeValid(barCode)){
				currentOrderList.add(myProductManager.getProduct(barCode));
				return "" + currentOrderList.get(currentOrderList.size()-1 ) + "\n";
			} else {
				return MessagesEnum.BARCODE_NOT_FOUND + "\n";
			}
		}		
	}

	/**
	 * 
	 * @return
	 */
	public boolean isPrinterUnplugged() {		
		return isDeviceUnplugged(DeviceCategory.PRINTER);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDisplayUnplugged() {		
		return isDeviceUnplugged(DeviceCategory.DISPLAY);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isScannerUnplugged() {		
		return isDeviceUnplugged(DeviceCategory.SCANNER);
	}
	
	/**
	 * Singleton implementation for PointOfSale class
	 * @return instance the instance of the POS in the system
	 */
	public static PointOfSale getInstance(){
		if(instance == null){
			instance = new PointOfSale();
		}
		return instance;
	}

	/**
	 * Erase the instance, clearing all the objects referencing the current POS instance
	 */
	public static void dispose() {
		View.dispose();	
		myDeviceManager = null;
		instance = null;
	}

	/**
	 * Add a list of products to the system manager
	 * @param listP
	 * @return the size of successful inserted products from the list
	 */
	public int addProductsList(ArrayList<Product> listProducts) {
		if(listProducts.size()>0) {
			return myProductManager.addProducts(listProducts);
		}
		return 0;
	}

	/**
	 * 
	 */
	public boolean finishCurrentOrder() {
		if(currentOrderList.size() > 0) {
			storeOrder(currentOrderList);
			System.out.println(MessagesEnum.STARTING_NEW_ORDER+"");
			currentOrderList = new ArrayList<Product>();
			return true;
		} else {
			System.out.println(MessagesEnum.ORDER_LIST_EMPTY+"");
			return false;
		}
	}

	/**
	 * This mocks the storage on the database in this case is a file stored on the folder invoices.
	 * 
	 * @param currentOrderList the products involved in the sale
	 */
	private void storeOrder(ArrayList<Product> listProducts2) {
		if(listProducts2.size()>0) {
			System.out.println("Storing in database the order");
		}
	}
	
	/**
	 * Sets the device manager for the POS
	 * @param deviceManager the device manager to be stablished
	 */
	public void setDeviceManager(DeviceManager deviceManager) {
		myDeviceManager = deviceManager;
	}

	/**
	 * 
	 * @param view
	 */
	public void setView(View view) {
		PointOfSale.myView = view;
	}
	
	/**
	 * 
	 */
	public InputDevice getInputDevice(){
		return myDeviceManager.getScanner();
	}
}