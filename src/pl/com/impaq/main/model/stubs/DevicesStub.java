package pl.com.impaq.main.model.stubs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import pl.com.impaq.main.controller.managers.DeviceManager;
import pl.com.impaq.main.model.enums.DeviceCategory;
import pl.com.impaq.main.model.enums.DeviceType;
import pl.com.impaq.main.model.enums.MessagesEnum;
import pl.com.impaq.main.model.util.PropertyReader;

/**
 * @author Agustin Cabra
 * @version 1.0
 */
public class DevicesStub {
	
	private PrintStream standardOutput;
	
	/**
	 * Dependency injection is used here, to allow the DevicesStub to send the input devices
	 * found to the POS.
	 * 
	 * @param thePOS The point of sale that will receive the devices
	 */
	public DevicesStub(DeviceManager posDeviceManager, String configFileName) {
		standardOutput = System.out;
		PropertyReader pr = PropertyReader.getInstance();
		String devicesFileName = pr.retrievePropertyFromConfigFile(configFileName, "devicesFileSource");
		if(devicesFileName.length()>0) {
			loadDevices(devicesFileName, posDeviceManager);
		}
	}
	
	/**
	 * 
	 * @param posDeviceManager 
	 * @param outputDeviceFileName
	 */
	private void loadDevices(String devicesFileName, DeviceManager posDeviceManager) {
		BufferedReader bf = null;
		FileInputStream fis = null;
		standardOutput.println(MessagesEnum.SYSTEM_BOOT_INFO_START.toString());
		try {
			fis = new FileInputStream(devicesFileName);
			bf = new BufferedReader(new InputStreamReader(fis));
			bf.readLine();//read the line containing the definition of the file
			String deviceString = bf.readLine();
			while ( deviceString != null ) {
				String [] arrayDeviceInfo = deviceString.split(",");
				if(arrayDeviceInfo.length == 5) {
					createDevice(arrayDeviceInfo, posDeviceManager);					
				} else {
					standardOutput.println(MessagesEnum.ERROR_RETRIEVING_DEVICE.toString());
				}
				deviceString = bf.readLine();
			}
			if(posDeviceManager.getSizeInputDevices() > 0 ){
				standardOutput.println(MessagesEnum.INPUT_DEVICES_LOADED.toString());
			} else {
				standardOutput.println(MessagesEnum.NO_INPUT_DEVICES_LOADED.toString());
			}
			if(posDeviceManager.getSizeOutputDevices() > 0 ){
				standardOutput.println(MessagesEnum.OUTPUT_DEVICES_LOADED.toString());
			} else {
				standardOutput.println(MessagesEnum.NO_OUTPUT_DEVICES_LOADED.toString());
			}
				
		} catch(FileNotFoundException ex) {
			standardOutput.println(MessagesEnum.INPUT_DEVICES_FILE_NOT_FOUND.toString());
		} catch(IOException ex) {
			standardOutput.println(MessagesEnum.ERROR_RETRIEVING_DEVICE.toString());
		} finally {
			try {
				if(bf!= null)
					bf.close();
				if(fis != null)
					fis.close();
			} catch (IOException e) {
				standardOutput.println(MessagesEnum.ERROR_CLOSING_SOURCE.toString());
			}
		}
		
	}

	/**
	 * 
	 * @param arrayDeviceInfo
	 */
	private void createDevice(String[] arrayDeviceInfo, DeviceManager posDeviceManager) {
		String code = arrayDeviceInfo[0];
		String name = arrayDeviceInfo[1];
		String description = arrayDeviceInfo[2];
		DeviceType type = DeviceType.getType(arrayDeviceInfo[3]);
		DeviceCategory kind = DeviceCategory.getKind(arrayDeviceInfo[4]);
		posDeviceManager.createDevice(code, name, description, type, kind);
	}
}
