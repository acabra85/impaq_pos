package pl.com.impaq.main.controller.devices.output;

/**
 * @author Agustin Cabra
 * @version 1.0
 * 
 * Interface representing the behavior of an output device
 */
public interface OutputDevice {
	
	/**
	 * 
	 * @param outMsg the message to be sent out through the device
	 */
	public void print(String outMsg);
}
