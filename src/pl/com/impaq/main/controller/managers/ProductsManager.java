package pl.com.impaq.main.controller.managers;

import java.util.HashMap;

import pl.com.impaq.main.model.core.Product;

public class ProductsManager {

	private static ProductsManager instance;
	private HashMap<String, Product> products = new HashMap<String, Product>();
	
	/**
	 * Creator is private in order to control the singleton pattern
	 */
	private ProductsManager() {		
	}
	
	/**
	 * Returns the number of items loaded in the system
	 * 
	 * @return The size of the product list loaded in the system
	 */
	public int getSizeProducts(){
		return products.size();
	}
	
	/**
	 * Checks if the barcode given exists in the system
	 *
	 *@return true if the product barcode is stored, and false otherwise 
	 */
	public boolean isBarCodeValid(String barcode){
		return products.containsKey(barcode);
	}
	
	/**
	 * Returns the product if the barcode is found in the loaded products, otherwise returns null
	 * @param barCode the barcode of the product to be retrieved 
	 * @return the product if found, otherwise returns null
	 */
	public Product getProduct(String barCode) {
		if(isBarCodeValid(barCode)) {
			return products.get(barCode);
		}
		else {
			return null;
		}
	}

	/**
	 * Creates a new product and stores it
	 * 
	 * @param cod the code of the product
	 * @param name the name of the product
	 * @param price the price of the product
	 * @param barCode the barcode of the product
	 */
	public void addProduct(String cod, String name, double price, String barCode) {
		Product product = new Product(cod, name, price, barCode);
		products.put(product.getBarCode(), product);		
	}

	/**
	 * Singleton pattern to obtain the instance
	 * @return
	 */
	public static ProductsManager getInstance() {
		if(instance == null) {
			instance = new ProductsManager();
		}
		return instance;
	}
	
	
	/**
	 * Dispose the current instance
	 */
	public static void disposeInstance(){
		instance = null;
	}

}