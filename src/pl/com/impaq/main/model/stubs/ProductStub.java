package pl.com.impaq.main.model.stubs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import pl.com.impaq.main.controller.managers.ProductsManager;
import pl.com.impaq.main.enums.MessagesEnum;
import pl.com.impaq.main.model.core.InvoiceDetailsCalculator;
import pl.com.impaq.main.model.util.PropertyReader;

/**
 * @author Agustin Cabra
 * @version 1.0
 */
public class ProductStub {
	
	private PrintStream standardOutput;
	
	
	/**
	 * 
	 * @param calculator 
	 * @param myPOS 
	 * @param lcdDisplay the display to show the information when loading products
	 */
	public ProductStub(ProductsManager posProductManager, String configFileName, InvoiceDetailsCalculator calculator) {
		standardOutput = System.out;
		PropertyReader pr = PropertyReader.getInstance();
		String prodsFileName = pr.retrievePropertyFromConfigFile(configFileName, "productsFileSource");
		String taxString = getTaxString(configFileName);
		setTaxForInvoiceCalculator(calculator, taxString);
		if(prodsFileName.length()>0) {
			loadProducts(prodsFileName, posProductManager);
		}
	}


	/**
	 * @param calculator
	 * @param taxString
	 */
	public void setTaxForInvoiceCalculator(InvoiceDetailsCalculator calculator, String taxString) {
		if(taxString.length()>0) {
			try {
				calculator.setTax(Double.parseDouble(taxString));
			}catch(Exception e) {
				standardOutput.println(MessagesEnum.TAX_CONFIG_FILE_INVALID.toString());
			}
		}
	}


	/**
	 * @param configFileName
	 * @return
	 */
	public String getTaxString(String configFileName) {
		return PropertyReader.getInstance().retrievePropertyFromConfigFile(configFileName, "tax");
	}


	/**
	 * This method retrieves the product list in a text file and creates the products map <key,value> as 
	 * using the bar-code as the key and the value as the product
	 * 
	 * @param prodsFileName name of the file storing the text list with products
	 * @param posProductManager 
	 * 
	 */
	public void loadProducts(String prodsFileName, ProductsManager posProductManager) {
		BufferedReader bf = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(prodsFileName);
			bf = new BufferedReader(new InputStreamReader(fis));
			bf.readLine();//read the line of the products definition
			String productString = bf.readLine();
			while ( productString != null ) {
				String [] arr = productString.split(",");
				if(arr.length == 4) {
					posProductManager.createProduct(arr[0], arr[1], Double.parseDouble(arr[2]), arr[3]);
				}
				productString = bf.readLine();
			}
			if(posProductManager.getSizeProducts() > 0 ){
				standardOutput.println(MessagesEnum.PRODUCTS_LOADED.toString());
			} else {
				standardOutput.println(MessagesEnum.NO_PRODUCTS_LOADED.toString());
			}
				
		} catch(FileNotFoundException ex) {
			standardOutput.println(MessagesEnum.PRODUCTS_FILE_NOT_FOUND.toString());
		} catch(IOException ex) {
			standardOutput.println(MessagesEnum.ERROR_RETRIEVING_PRODUCT.toString());
		} finally {
			try {
				if(bf!=null) bf.close();
				if(fis != null) fis.close();
			} catch (IOException e) {
				standardOutput.println(MessagesEnum.ERROR_CLOSING_SOURCE.toString());
			}
			standardOutput.println(MessagesEnum.SYSTEM_BOOT_INFO_END + "\n\n");
		}
		
	}
}
