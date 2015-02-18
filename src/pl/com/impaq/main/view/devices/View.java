package pl.com.impaq.main.view.devices;

import java.util.HashMap;

import pl.com.impaq.main.controller.PointOfSale;
import pl.com.impaq.main.enums.DeviceCategory;
import pl.com.impaq.main.enums.DeviceType;
import pl.com.impaq.main.enums.MessagesEnum;
import pl.com.impaq.main.view.devices.input.InputView;
import pl.com.impaq.main.view.devices.output.OutputView;
import pl.com.impaq.main.view.devices.util.ViewMapper;

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
	private PointOfSale myPOS;
	private HashMap<String, OutputView> myOutputComponents;

	private HashMap<String, InputView> myInputComponents; 
	
	/**
	 * The constructor will be private so the creation of this class is
	 * restricted only through the getInstance method
	 */
	private View() {
		myInputComponents = new HashMap<String, InputView>();
		myOutputComponents = new HashMap<String, OutputView>();
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
		String keyDisplay = isDisplayUnplugged();
		if(keyDisplay.length() == 0) {
			System.out.println(MessagesEnum.NO_DISPLAY_FOUND + "");
		} else {
			String keyInput = isScannerUnplugged();
			if(keyInput.length() == 0) {
				System.out.println(MessagesEnum.NO_SCANNER_FOUND + "");
			}
		}
	}
	


	private String isScannerUnplugged() {
		for (String key : myInputComponents.keySet()) {
			if(myInputComponents.get(key).getCategory().equals(DeviceCategory.SCANNER)){				
				return key;
			}
		}
		return "";
	}

	private String isDisplayUnplugged() {
		for (String key : myOutputComponents.keySet()) {
			System.out.println(""+myOutputComponents.get(key).getCategory());
			if((myOutputComponents.get(key).getCategory()).equals(DeviceCategory.DISPLAY)){	
				System.out.println(key);
				return key;
			}
		}
		return "";
	}


	/** Waits for the user to scans product bar-codes through the scanner,
	 * when receives the input 'exit', the POS will send the order list to print the results 
	 * the LCD Screen
	 * @param in type of input to capture information
	 */
	public void addDevice(String code, String name, String category, String type) {
		switch(DeviceType.getType(type)){
			case INPUT:
				if(!myInputComponents.containsKey(code))
					myInputComponents.put(code, ViewMapper.toInputView(code, name, category, this));
				break;
			case OUTPUT:
				if(!myOutputComponents.containsKey(code))
					myOutputComponents.put(code, ViewMapper.toOutputView(code, name, category));
				break;
			default:
				break;
		}		
	}

	/**
	 * 
	 */
	public void sendBarCode() {
		String keyScanner = isScannerUnplugged();
		if(keyScanner.length() == 0){
			return;
		}
		String barCode = myInputComponents.get(keyScanner).getTextInputField();
		String keyDisplay = "";
		if(!myPOS.isPrintingInvoice(barCode)){
			myPOS.receiveBarcode(barCode);
			keyDisplay = isDisplayUnplugged();
			if(keyDisplay.length() == 0) {
				
			}
			myOutputComponents.get(keyDisplay).appendInformation(myPOS.receiveBarcode(barCode));
		} else {

			keyDisplay = isDisplayUnplugged();
			if(keyDisplay.length() == 0) {
				System.out.println(MessagesEnum.NO_PRINTER_FOUND+"");
			} else {
				myOutputComponents.get(keyDisplay).appendInformation(myPOS.getResults());
				String keyPrinter = isPrinterUnplugged();
				if(keyPrinter.length() == 0) {
					System.out.println(MessagesEnum.NO_PRINTER_FOUND+"");
				} else {
					myOutputComponents.get(keyPrinter).appendInformation(myPOS.getInvoiceResults());			
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private String isPrinterUnplugged() {
		for (String key : myOutputComponents.keySet()) {
			System.out.println(""+myOutputComponents.get(key).getCategory());
			if((myOutputComponents.get(key).getCategory()).equals(DeviceCategory.PRINTER)){	
				System.out.println(key);
				return key;
			}
		}
		return "";
	}

	/**
	 * Clears the input captured
	 */
	public void clearCommandSent() {
		String key  = isScannerUnplugged();
		if(key.length() > 0) {
			myInputComponents.get(key).clearTextInputField();
		}
	}
}
