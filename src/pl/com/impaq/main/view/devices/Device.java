package pl.com.impaq.main.view.devices;

import pl.com.impaq.main.model.enums.DeviceType;

public abstract class Device {
	protected String name;
	protected String code;
	protected String description;
	protected DeviceType type;
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
	public DeviceType getType() {
		return type;
	}	
}
