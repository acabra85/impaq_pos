package pl.com.impaq.main.controller.managers;

import java.util.HashMap;

import pl.com.impaq.main.controller.PointOfSale;
import pl.com.impaq.main.enums.DeviceCategory;
import pl.com.impaq.main.enums.DeviceType;
import pl.com.impaq.main.view.devices.Device;
import pl.com.impaq.main.view.devices.input.BarCodeScanner;
import pl.com.impaq.main.view.devices.output.DisplayLCD;
import pl.com.impaq.main.view.devices.output.Printer;

/**
 * 
 * @author Agustin Cabra
 *
 */
public class DeviceManager {


	private HashMap<String, Device> inputDevices = new HashMap<String, Device>();
	private HashMap<String, Device> outputDevices = new HashMap<String, Device>();
	private PointOfSale thePOS;
	private Printer printer;
	private BarCodeScanner scanner;
	private DisplayLCD display;
	public static DeviceManager instance;
	
	
	/**
	 * 
	 * @param pos
	 */
	private DeviceManager(PointOfSale pos) {
		this.thePOS = pos;
	}
	
	/**
	 * 
	 * @param code
	 * @param name
	 * @param description
	 * @param type
	 * @param kind
	 */
	public void createDevice(String code, String name, String description,
			DeviceType type, DeviceCategory kind) {
		switch (kind) {
			case INPUT:
				createInputDevice(code, name, description, type);
				break;
			case OUTPUT:
				createOutputDevice(code, name, description, type);
				break;
			default:
				break;
		}
	}

	/**
	 * 
	 * @param code
	 * @param name
	 * @param description
	 * @param type
	 */
	private void createOutputDevice(String code, String name,
			String description, DeviceType type) {
		Device device = null;
		switch (type) {
			case PRINTER:
				device = new Printer(code, name, description, type);
				if(printer==null) printer = (Printer)device;
				break;
			case DISPLAY:
				device = new DisplayLCD(code, name, description, type);
				if(display==null) display = (DisplayLCD)device;
				break;
			default:
				break;
		}
		if(device != null) addOutputDevices(device);
	}

	/**
	 * 
	 * @param code
	 * @param name
	 * @param description
	 * @param type
	 */
	private void createInputDevice(String code, String name,
			String description, DeviceType type) {
		Device device = null;
		switch (type) {
			case SCANNER:
				device = new BarCodeScanner(code, name, description, type);
				if(scanner==null) scanner = (BarCodeScanner)device;
				break;
			default:
				break;
		}
		if(device != null) addInputDevice(device);
	}

	/**
	 * 
	 * @param device
	 */
	public void addInputDevice(Device device) {
		switch (device.getType()) {
			case SCANNER:
				if (thePOS.isDeviceUnplugged(DeviceType.SCANNER)) {
					thePOS.plugBarcodeScanner((BarCodeScanner) device);
					thePOS.addScannerToView(scanner);
				}
				break;
			default:
				break;
		}
		inputDevices.put(device.getCode(), device);		
	}

	/**
	 * 
	 * @param device
	 */
	public void addOutputDevices(Device device) {
		switch (device.getType()) {
			case PRINTER:
				if(thePOS.isDeviceUnplugged(DeviceType.PRINTER)) {
					thePOS.plugPrinter((Printer) device);
					thePOS.addPrinterToView(printer);
				}
				break;
			case DISPLAY:
				if(thePOS.isDeviceUnplugged(DeviceType.DISPLAY))  {
					thePOS.plugDisplayLCD((DisplayLCD) device);
					thePOS.addDisplayToView(display);
				}
				break;
			default:
				break;
		}
		outputDevices.put(device.getCode(), device);
		
	}
	
	/**
	 * Searches for a printer in the output devices and returns it
	 * @return null if no printer was found, otherwise returns the printer
	 */
	public Printer getPrinter() {
		if(printer == null) {
			for(String key: outputDevices.keySet()){
				Device dev = outputDevices.get(key);
				if(dev.getType().equals(DeviceType.PRINTER)){
					printer = (Printer) dev;
				}
			}
		}
		return printer;
	}
	
	/**
	 * Searches for a bar-code scanner in the output devices and returns it
	 * @return null if no bar-code scanner was found, otherwise returns the printer
	 */
	public BarCodeScanner getScanner() {
		if(scanner == null) {
			for(String key: inputDevices.keySet()){
				Device dev = inputDevices.get(key);
				if(dev.getType().equals(DeviceType.SCANNER)){
					scanner =  (BarCodeScanner) dev;
				}
			}			
		}
		return scanner;
	}
	
	/**
	 * Searches for a bar-code scanner in the output devices and returns it
	 * @return null if no bar-code scanner was found, otherwise returns the printer
	 */
	public DisplayLCD getDisplayLCD() {
		if(display == null) {
			for(String key: outputDevices.keySet()){
				Device dev = outputDevices.get(key);
				if(dev.getType().equals(DeviceType.DISPLAY)){
					display = (DisplayLCD) dev;
				}
			}
		}
		return display;
	}


	/**
	 * 
	 * @return
	 */
	public int getSizeInputDevices() {		
		return inputDevices.size();
	}

	/**
	 * 
	 * @return
	 */
	public int getSizeOutputDevices() {		
		return outputDevices.size();
	}

	public static DeviceManager getInstance(PointOfSale pointOfSale) {
		if(instance == null){
			instance = new DeviceManager(pointOfSale);
		}
		return instance;
	}

}
