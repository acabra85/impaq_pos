package pl.com.impaq.main.controller.devices.output;

import pl.com.impaq.main.controller.devices.Device;
import pl.com.impaq.main.enums.DeviceCategory;

/**
 * @author Agustin
 * @version 1.0 
 * 
 */
public class Printer extends Device implements OutputDevice {

	@Override
	/**
	 * @param outMsg	containing the message to be displayed at the current position of cursor
	 */
	public void print(String outMsg) {
		System.out.print(outMsg);
	}
	
	/**
	 * 
	 * @param code
	 * @param name
	 * @param description
	 */
	public Printer(String code, String name, String description, DeviceCategory category) {
		super();
		this.name = name;
		this.description = description;
		this.code = code;
		this.category = category;
	}

}
