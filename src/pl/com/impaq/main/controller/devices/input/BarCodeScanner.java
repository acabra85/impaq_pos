package pl.com.impaq.main.controller.devices.input;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import pl.com.impaq.main.controller.devices.Device;
import pl.com.impaq.main.enums.DeviceCategory;

public class BarCodeScanner extends Device implements InputDevice {

	@Override
	public String capture(InputStream in) {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			String inputVal = bf.readLine();
			return inputVal;
		} catch (Exception ex) {
			return "";
		}
	}
	
	/**
	 * 
	 * @param code
	 * @param name
	 * @param description
	 */
	public BarCodeScanner(String code, String name, String description, DeviceCategory type) {
		super();
		this.name = name;
		this.description = description;
		this.code = code;
		this.category = type;
		
	}

}