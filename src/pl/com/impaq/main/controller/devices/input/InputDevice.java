/**
 * 
 */
package pl.com.impaq.main.controller.devices.input;

import java.io.InputStream;

/**
 * @author Agustin Cabra
 * @version 1.0 
 *
 * Interface representing the behavior of an input device
 */
public interface InputDevice {
	
	/**
	 * This method receives an input and decodes it into the system
	 * 
	 * @param msg the input captured
	 * @return the msg that was captured
	 */
	public String capture(InputStream in);
}
