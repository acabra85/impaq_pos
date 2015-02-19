package pl.com.impaq.main.controller.managers;

import java.util.ArrayList;
import java.util.HashMap;

import pl.com.impaq.main.model.core.Product;

/**
 * @author Agustin Cabra
 * @version 1.0
 *
 * This class manages all the operations related to products for the POS (PointOfSale)
 */
public class ProductsManager {
	
	private HashMap<String, Product> products;
	
	/**
	 * Creator is private in order to control the singleton pattern
	 */
	public ProductsManager() {	
		products = new HashMap<String, Product>();
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
	 * Creates a new product and attempts stores it
	 * 
	 * @param cod the code of the product
	 * @param name the name of the product
	 * @param price the price of the product
	 * @param barCode the bar-code of the product
	 * @return boolean true if the product described by parameters was added or false otherwise
	 */
	public boolean createProduct(String cod, String name, double price, String barCode) {
		if(cod.length() > 0 && name.length() > 0 && price > 0.0 && barCode.length() > 0 ){
			Product product = new Product(cod, name, price, barCode);
			return addProduct(product);
		}
		return false;
	}
	
	/**
	 * 
	 * @param product
	 * @return
	 */
	public boolean addProduct(Product product) {
		if(products.containsKey(product.getBarCode())){
			return false;	
		} else {
			products.put(product.getBarCode(), product);
			return true;
		}
	}

	/**
	 * Attempts to add the products on the listProduct to the system
	 * @param listProducts the products to be added
	 * @return the
	 */
	public int addProducts(ArrayList<Product> listProducts) {
		int cantAddedProducts = 0;
		for(Product product: listProducts){
			if(addProduct(product))
				cantAddedProducts++;
		}
		return cantAddedProducts;
	}

}