package pl.com.impaq.main.controller.managers;

import java.util.HashMap;

import pl.com.impaq.main.model.core.Product;

public class ProductsManager {

	private HashMap<String, Product> products = new HashMap<String, Product>();
	
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

}

//total = (Math.round(total * 100.0) / 100.0);		
//if(tax != 0.0) {
	//displayLCD.print("\n\t Tax: \t\t\t" + tax  + "%");
	//displayLCD.print("\n\t SubTotal: \t\t\t" + total);
//}
//String totalStr = "Total : \t\t\t" + total + "\n";
//displayLCD.print(totalStr);
//printInvoiceInPrinter(sb, total, printer);


//tmpProduct = myProductManager.getProduct(barCode);
//total += tmpProduct.getPrice();
//item = tmpProduct.toString();
//sb.append(item + "\n");