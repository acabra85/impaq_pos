package pl.com.impaq.main.controller.devices.output;

/**
 * @author Agustin Cabra
 *
 */
public interface OutputDevice {
	
	/**
	 * 
	 * @param outMsg the message to be sent out through the device
	 */
	public void print(String outMsg);
	
	/**
	 * 
	 * @param outMsg the message to be sent out through the device with a line after '\n'
	 */
	public void println(String outMsg);
}
