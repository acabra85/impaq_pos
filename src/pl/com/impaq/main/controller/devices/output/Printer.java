package pl.com.impaq.main.controller.devices.output;

import pl.com.impaq.main.controller.devices.Device;
import pl.com.impaq.main.enums.DeviceCategory;

/**
 * @author Agustin
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

	@Override
	/**
	 * @param string containing the message to be displayed and adds a new line after printing
	 */
	public void println(String outMsg) {
		System.out.println(outMsg);
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
