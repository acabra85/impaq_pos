package pl.com.impaq.main.controller.devices;

import pl.com.impaq.main.enums.DeviceCategory;
/**
 * 
 * @author Agustin Cabra
 * @version 1.0
 *
 *Abstract class representing the basic information from a Device
 */
public abstract class Device {
	protected String name;
	protected String code;
	protected String description;
	protected DeviceCategory category;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the type
	 */
	public DeviceCategory getCategory() {
		return category;
	}	
}
