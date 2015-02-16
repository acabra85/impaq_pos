package pl.com.impaq.main.model.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import pl.com.impaq.main.model.enums.MessagesEnum;

public class PropertyReader {

	private PrintStream standardOutput;
	private static PropertyReader instance;

	/**
	 * This method retrieves the property "productsFileSource" from the config.properties file, if the
	 * property is retrieved correctly, sets true the productFileNameRetrieved
	 * 
	 */
	public String retrievePropertyFromConfigFile(String configFileName, String property) {
		FileInputStream inputProperties = null;
		Properties prop = new Properties();
		String productsFileName = "";
		standardOutput = System.out;
		try {
			inputProperties = new FileInputStream(configFileName);
			prop.load(inputProperties);
			inputProperties.close();
			productsFileName = prop.getProperty(property);
			return productsFileName;	
		} catch(FileNotFoundException ex) {
			standardOutput.println(MessagesEnum.CONFIG_FILE_NOT_FOUND.toString());
			return productsFileName;
		} catch (IOException ex) {
			standardOutput.println(MessagesEnum.CONFIG_READING_ERORR.toString());
			return productsFileName;
		} finally {
			if(inputProperties != null){
				try{
					inputProperties.close();
				} catch(IOException ex){
					standardOutput.println(MessagesEnum.CONFIG_READING_ERORR.toString());
				}
			}
		}
	}
	
	/**
	 * Singleton implementation to get only on 
	 * @return
	 */
	public static PropertyReader getInstance() {
		if(instance == null) {
			instance = new PropertyReader();
		}
		return instance;
	}
	
	private PropertyReader() {
	}
}
