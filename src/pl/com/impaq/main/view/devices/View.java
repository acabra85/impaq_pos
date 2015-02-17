package pl.com.impaq.main.view.devices;

import java.io.InputStream;

import pl.com.impaq.main.controller.PointOfSale;
import pl.com.impaq.main.enums.MessagesEnum;
import pl.com.impaq.main.view.devices.input.BarCodeScanner;
import pl.com.impaq.main.view.devices.output.DisplayLCD;
import pl.com.impaq.main.view.devices.output.Printer;

/**
 * 
 * @author Agustin Cabra
 *
 */
public class View {

	/**
	 * The instance of only this class
	 */
	public static View instance;
	
	private BarCodeScanner scanner;
	private Printer printer;
	private DisplayLCD display;
	private PointOfSale myPOS;
	
	/**
	 * The constructor will be private so the creation of this class is
	 * restricted only through the getInstance method
	 */
	private View() {
		
	}
	
	/**
	 * Singleton pattern to have only one instance of the view in the system
	 * 
	 * @return the instance of the View
	 */
	public static View getInstance(PointOfSale pos){
		if(instance == null){
			instance = new View();
		}
		instance.myPOS = pos;
		return instance;
	}
	
	/**
	 * Singleton pattern to dispose the instance
	 * 
	 * @return the instance of the View
	 */
	public static void dispose(){
		instance = null;
	}
	
	public void start(){
		System.out.println(MessagesEnum.PRINTING_LCD+""); //update user on printing output device
		if(myPOS.isDisplayUnplugged()) {
			System.out.println(MessagesEnum.NO_DISPLAY_FOUND + "");
		} else {
			display.print(myPOS.getWaitingInputMessage());
			if(myPOS.isScannerUnplugged()) {
				System.out.println(MessagesEnum.NO_SCANNER_FOUND + "");
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
		while(!myPOS.isPrintingInvoice(barCode)) {
			display.print(myPOS.receiveBarcode(barCode));
			barCode = scanner.capture(in);
		}
		display.print(myPOS.getResults());
		if(myPOS.isPrinterUnplugged()) {
			display.println(MessagesEnum.NO_PRINTER_FOUND+"");
		} else {
			printer.print(myPOS.getInvoiceResults());			
		}
	}
	
	/**
	 * 
	 * @param printer
	 */
	public void addPrinter(Printer printer){
		this.printer = printer;
	}
	
	/**
	 * 
	 * @param scanner
	 */
	public void addScanner(BarCodeScanner scanner){
		this.scanner = scanner;
	}
	
	/**
	 * 
	 * @param display
	 */
	public void addDisplay(DisplayLCD display){
		this.display = display;
	}
}
