package pl.com.impaq.main.model.core;

import java.util.ArrayList;

import pl.com.impaq.main.enums.MessagesEnum;

/**
 * @author Agustin Cabra
 * @version 1.0
 */
public class InvoiceDetailsCalculator {
	
	private double tax;


	/**
	 * 
	 */
	public InvoiceDetailsCalculator() {
		this.tax =0.0;
	}


	/**
	 * Calculates the total of the order base on the products list received, 
	 * and stores it in the variable totalLastInvoice
	 * 
	 * @param listProducts list of products to calculate the total
	 * @return invoiceDetail string containing the invoice to be printed
	 */
	public String getInvoiceDetails(ArrayList<Product> listProducts) {
		double totalLastInvoice = 0.0;
		double subTotalLastInvoice = 0.0;
		String invoiceDetails = "";
		StringBuffer sb = new StringBuffer(MessagesEnum.INVOICE_HEADER + "");
		for (int i = 0; i < listProducts.size(); i++) {
			subTotalLastInvoice += listProducts.get(i).getPrice();
			sb.append(MessagesEnum.DISTANCE_INVOICE_INFO + "" + listProducts.get(i) + "\n");
		}
		if(tax != 0.0) {
			subTotalLastInvoice = Math.round(subTotalLastInvoice*100.0)/100.0; //round subtotal
			double taxCollected = subTotalLastInvoice*(tax/100.0);
			taxCollected = Math.round(taxCollected*100.0)/100.0; // round tax collected
			sb.append("\n"+ MessagesEnum.DISTANCE_INVOICE_INFO + "" + MessagesEnum.TAX + "\t" + tax);
			sb.append("\n"+ MessagesEnum.DISTANCE_INVOICE_INFO + "" + MessagesEnum.SUBTOTAL + "\t" + subTotalLastInvoice);
			sb.append("\n"+ MessagesEnum.DISTANCE_INVOICE_INFO + "" + MessagesEnum.TAX_COLLECTED + "\t" + taxCollected);
			totalLastInvoice = subTotalLastInvoice + taxCollected;
		} else {
			totalLastInvoice = subTotalLastInvoice;
		}
		totalLastInvoice = Math.round(totalLastInvoice*100.0)/100.0; // round total
		sb.append("\n" + MessagesEnum.DISTANCE_INVOICE_INFO + "Total:\t" + totalLastInvoice);
		sb.append(MessagesEnum.INVOICE_FOOTER + "");
		invoiceDetails = sb.toString();
		return invoiceDetails;			
	}
	
	/**
	 * Calculates the total sum of products prices in the list
	 * 
	 * @param listProducts list of products of an order
	 * @return calculates the total of sum of prices of the products in the list listProducts
	 */
	public double calculateOrderTotal(ArrayList<Product> listProducts) {
		double total=0;
		for(Product p: listProducts) {
			total += p.getPrice();
		}
		double subtotal = Math.round(total*100.0)/100.0;
		return subtotal + calculateTaxCollected(subtotal);
	}
	
	/**
	 * Calculates the total tax to be collected based on a subtotal
	 * @param subtotal the subtotal of the order
	 * @return total tax to be collected
	 */
	public double calculateTaxCollected(double subtotal) {
		double taxCollected = subtotal*tax/100;
		taxCollected = Math.round(taxCollected*100.0)/100.0;
		return taxCollected;
	}
	
	/**
	 * Calculates the subtotal of an order based on list products
	 * @param listProducts list of products
	 * @return subtotal of the order
	 */
	public double calculateOrderSubTotal(ArrayList<Product> listProducts){
		double total=0;
		for(Product p: listProducts) {
			total += p.getPrice();
		}
		return Math.round(total*100.0)/100.0;
	}
	
	/**
	 * This setter method allows to change the tax if the tax is between 0-100
	 * @param tax the tax percentage [0-100]
	 */
	public void setTax(double tax) {
		if(tax >= 0.0) {
			this.tax = tax;		
		}
	}


	/**
	 * 
	 * @return the tax value
	 */
	public double getTax() {
		return tax;
	}
}
