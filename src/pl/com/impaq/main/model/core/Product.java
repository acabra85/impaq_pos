package pl.com.impaq.main.model.core;

/**
 * @author Agustin Cabra
 * @version 1.0
 */
public class Product {
	private String id;
	private double price;
	private String name;
	private String barCode;
	
	public Product(String id, String name, double price, String barCode) {
		this.id = id;
		this.price = price;
		this.name = name;
		this.barCode = barCode;
	}

	/**
	 * @return the id of the product
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the price of the product
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the name of the product
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the barCode of the product
	 */
	public String getBarCode() {
		return barCode;
	}
	
	/**
	 * Returns a string representing the name of the product and the price
	 * 
	 */
	@Override public String toString() {
		return this.name + ":\t" + this.price;
	}
}
