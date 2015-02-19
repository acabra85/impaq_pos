package pl.com.impaq.main.controller.devices.output;

import pl.com.impaq.main.controller.devices.Device;
import pl.com.impaq.main.enums.DeviceCategory;

/**
 * @author Agustin Cabra
 * @version 1.0
 */
public class DisplayLCD extends Device implements OutputDevice {

	/* (non-Javadoc)
	 * @see pl.com.impaq.devices.OutputDevice#print(java.lang.String)
	 */
	@Override
	public void print(String outMsg) {
		System.out.print(outMsg);

	}
	
	/**
	 * 
	 * @param code
	 * @param name
	 * @param description
	 */
	public DisplayLCD(String code, String name, String description, DeviceCategory category) {
		super();
		this.name = name;
		this.description = description;
		this.code = code;
		this.category = category;		
	}

}
