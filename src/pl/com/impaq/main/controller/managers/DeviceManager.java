package pl.com.impaq.main.controller.managers;

import java.util.HashMap;

import pl.com.impaq.main.controller.devices.Device;
import pl.com.impaq.main.controller.devices.input.BarCodeScanner;
import pl.com.impaq.main.controller.devices.output.DisplayLCD;
import pl.com.impaq.main.controller.devices.output.Printer;
import pl.com.impaq.main.enums.DeviceCategory;
import pl.com.impaq.main.enums.DeviceType;

/**
 * 
 * @author Agustin Cabra
 * @version 1.0
 *
 */
public class DeviceManager {


	private HashMap<String, Device> inputDevices = new HashMap<String, Device>();
	private HashMap<String, Device> outputDevices = new HashMap<String, Device>();
	private Printer printer;
	private BarCodeScanner scanner;
	private DisplayLCD display;
	
	
	/**
	 * 
	 * @param pos
	 */
	public DeviceManager() {
	}
	
	/**
	 * 
	 * @param code
	 * @param name
	 * @param description
	 * @param category
	 * @param type
	 * @return 
	 */
	public boolean createDevice(String code, String name, String description,
			DeviceCategory category, DeviceType type) {
		switch (type) {
			case INPUT:
				if(code.length() == 0){
					return false;
				}
				return createInputDevice(code, name, description, category);
			case OUTPUT:
				if(code.length() == 0){
					return false;
				}
				return createOutputDevice(code, name, description, category);
			default:
				return false;
		}
	}

	/**
	 * 
	 * @param code
	 * @param name
	 * @param description
	 * @param category
	 * @return 
	 */
	public boolean createOutputDevice(String code, String name,
			String description, DeviceCategory category) {
		if(code.length() == 0){
			return false;
		}
		Device device = null;
		switch (category) {
			case PRINTER:
				device = new Printer(code, name, description, category);
				if(printer==null) printer = (Printer)device;
				break;
			case DISPLAY:
				device = new DisplayLCD(code, name, description, category);
				if(display==null) display = (DisplayLCD)device;
				break;
			default:
				break;
		}
		if (device != null) 
			return addOutputDevice(device);
		return false;
	}

	/**
	 * 
	 * @param code
	 * @param name
	 * @param description
	 * @param category
	 * @return boolean false if it the device was not successfully created true otherwise
	 */
	public boolean createInputDevice(String code, String name,
			String description, DeviceCategory category) {
		if(code.length() == 0){
			return false;
		}
		Device device = null;
		switch (category) {
			case SCANNER:
				device = new BarCodeScanner(code, name, description, category);
				if(scanner==null) scanner = (BarCodeScanner)device;
				break;
			default:
				break;
		}
		if(device != null) { 
			return addInputDevice(device);
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param device
	 */
	public boolean addInputDevice(Device device) {
		switch (device.getCategory()) {
			case SCANNER:
				if(!inputDevices.containsKey(device.getCode())) {
					inputDevices.put(device.getCode(), device);
					return true;
				}
				return false;
			default:
				return false;
		}
	}

	/**
	 * 
	 * @param device
	 * @return 
	 */
	public boolean addOutputDevice(Device device) {
		switch (device.getCategory()) {
			case PRINTER:
				if(!outputDevices.containsKey(device.getCode())) {
					outputDevices.put(device.getCode(), device);
					return true;
				}
				return false;
			case DISPLAY:
				if(!outputDevices.containsKey(device.getCode())) {
					outputDevices.put(device.getCode(), device);
					return true;
				}
				return false;
			default:
				return false;
		}
		
	}
	
	/**
	 * Searches for a printer in the output devices and returns it
	 * @return null if no printer was found, otherwise returns the printer
	 */
	public Printer getPrinter() {
		if(printer == null) {
			for(String key: outputDevices.keySet()){
				Device dev = outputDevices.get(key);
				if(dev.getCategory().equals(DeviceCategory.PRINTER)){
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
				if(dev.getCategory().equals(DeviceCategory.SCANNER)){
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
				if(dev.getCategory().equals(DeviceCategory.DISPLAY)){
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

}
