/**
 * 
 */
package pl.com.impaq.main.view.devices.output;

import pl.com.impaq.main.enums.DeviceCategory;
import pl.com.impaq.main.view.devices.Device;

/**
 * @author Agustin
 *
 */
public class DisplayLCD extends Device implements OutputDevice {

	/* (non-Javadoc)
	 * @see pl.com.impaq.devices.OutputDevice#print(java.lang.String)
	 */
	@Override
	public void print(String outMsg) {
		System.out.print(outMsg);

	}

	/* (non-Javadoc)
	 * @see pl.com.impaq.devices.OutputDevice#println(java.lang.String)
	 */
	@Override
	public void println(String outMsg) {
		System.out.println(outMsg);
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
