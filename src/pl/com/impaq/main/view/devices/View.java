package pl.com.impaq.main.view.devices;

import java.util.HashMap;

import pl.com.impaq.main.controller.PointOfSale;
import pl.com.impaq.main.enums.DeviceCategory;
import pl.com.impaq.main.enums.DeviceType;
import pl.com.impaq.main.enums.MessagesEnum;
import pl.com.impaq.main.view.devices.input.InputView;
import pl.com.impaq.main.view.devices.output.OutputView;
import pl.com.impaq.main.view.devices.util.ViewInformationConstants;
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
	
	public int start(){
		System.out.println(MessagesEnum.PRINTING_LCD+""); //update user on printing output device
		String keyDisplay = isDisplayUnplugged();
		if(keyDisplay.length() == 0) {
			System.out.println(MessagesEnum.NO_DISPLAY_FOUND + "");
			return ViewInformationConstants.NO_DISPLAY_FOUND;
		} else {
			String keyInput = isScannerUnplugged();
			if(keyInput.length() == 0) {
				System.out.println(MessagesEnum.NO_SCANNER_FOUND + "");
				return ViewInformationConstants.NO_SCANNER_FOUND;
			} else {
				return ViewInformationConstants.START;
			}
		}
	}


	/** Waits for the user to scans product bar-codes through the scanner,
	 * when receives the input 'exit', the POS will send the order list to print the results 
	 * the LCD Screen
	 * @param in type of input to capture information
	 * @return 
	 */
	public boolean addDevice(String code, String name, String category, String type) {
		switch(DeviceType.getType(type)){
			case INPUT:
				if(!myInputComponents.containsKey(code)){
					myInputComponents.put(code, ViewMapper.toInputView(code, name, category, this));
					return true;
				}else {
					return false;
				}
			case OUTPUT:
				if(!myOutputComponents.containsKey(code)) {
					myOutputComponents.put(code, ViewMapper.toOutputView(code, name, category));
					return true;
				}else {
					return false;
				}
			default:
				System.out.println(MessagesEnum.NO_DEVICE_TYPE_FOUND+"");
				return false;
		}		
	}

	/**
	 * @param barCode 
	 * 
	 */
	public int sendBarCode(String barCode) {
		String keyScanner = isScannerUnplugged();
		if(keyScanner.length() == 0){
			return ViewInformationConstants.NO_SCANNER_FOUND;
		}
		String keyDisplay = "";
		if(!myPOS.isPrintingInvoice(barCode)){
			keyDisplay = isDisplayUnplugged();
			if(keyDisplay.length() == 0) {
				System.out.println(MessagesEnum.NO_DISPLAY_FOUND+"");
				return ViewInformationConstants.NO_DISPLAY_FOUND_SCANNING_PRODUCT;
			} else{
				String receiveBarcode = myPOS.receiveBarcode(barCode);
				myOutputComponents.get(keyDisplay).print(receiveBarcode);
				return ViewInformationConstants.PRODUCT_INFO_DISPLAYED;
			}
		} else {
			keyDisplay = isDisplayUnplugged();
			if(keyDisplay.length() == 0) {
				System.out.println(MessagesEnum.NO_DISPLAY_FOUND+"");
				return ViewInformationConstants.NO_DISPLAY_FOUND_PRINTING_RESULTS;
			} else {
				String results = myPOS.getResults();
				myOutputComponents.get(keyDisplay).print(results);
				String keyPrinter = isPrinterUnplugged();
				if(keyPrinter.length() == 0) {
					System.out.println(MessagesEnum.NO_PRINTER_FOUND+"");
					myPOS.finishCurrentOrder();
					return ViewInformationConstants.NO_PRINTER_FOUND;
				} else {
					String invoiceResults = myPOS.getInvoiceResults();
					myOutputComponents.get(keyPrinter).print(invoiceResults);
					myPOS.finishCurrentOrder();
					return ViewInformationConstants.RESULTS_PRINTED;
				}
			}
		}
	}


	/**
	 * if the device is plugged, returns the key on the map, otherwise returns empty string
	 * @return key String representing the key
	 */
	private String isPrinterUnplugged() {
		for (String key : myOutputComponents.keySet()) {
			if((myOutputComponents.get(key).getCategory()).equals(DeviceCategory.PRINTER)){	
				return key;
			}
		}
		return "";
	}

	/**
	 * if the device is plugged, returns the key on the map, otherwise returns empty string
	 * @return key String representing the key
	 */
	private String isScannerUnplugged() {
		for (String key : myInputComponents.keySet()) {
			if(myInputComponents.get(key).getCategory().equals(DeviceCategory.SCANNER)){				
				return key;
			}
		}
		return "";
	}

	/**
	 * if the device is plugged, returns the key on the map, otherwise returns empty string
	 * @return key String representing the key
	 */
	private String isDisplayUnplugged() {
		for (String key : myOutputComponents.keySet()) {
			if((myOutputComponents.get(key).getCategory()).equals(DeviceCategory.DISPLAY)){
				return key;
			}
		}
		return "";
	}
}
