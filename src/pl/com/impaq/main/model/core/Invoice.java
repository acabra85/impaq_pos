/**
 * 
 */
package pl.com.impaq.main.model.core;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Agustin Cabra
 * @version 1.0
 * 
 * For the initial version of the system, this Invoice Class is not taking part, but is 
 * expected to come in future versions
 */
public class Invoice {

	/**
	 * Invoice number
	 */
	private String invoiceNumber;
	
	/**
	 * Date the invoice was printed
	 */
	private Date date;

	/**
	 * Customer id
	 */
	private String customerId;	
	
	/**
	 * The map has key the barcode of the product, and the number of times stored
	 */
	private HashMap<String, Integer> orderMap;

	/**
	 * Subtotal as the sum of the prices of each product on the orderlist
	 */
	private double subTotal;

	/**
	 * Tax collected as the sum of the sum of each tax per product price on the orderlist
	 */
	private double taxCollected;
	
	/**
	 * total as the sum of the prices of each product + taxes where applicable
	 */
	private double total;
	
	/**
	 * Constructor method sets as new the orderList
	 */
	public Invoice (String iNumber, Date date, String customerId, 
			List<String> orderList, double subTotal, double taxCollected, double total) {
		this.invoiceNumber = iNumber;
		this.date = date;
		this.customerId = customerId;
		orderMap = getOrderListHashMap(orderList);
		this.subTotal = subTotal;
		this.taxCollected = taxCollected;
		this.total = total;
	}
	
	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @return the subTotal
	 */
	public double getSubTotal() {
		return subTotal;
	}

	/**
	 * @return the taxCollected
	 */
	public double getTaxCollected() {
		return taxCollected;
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * Creates a hashMap based on a list of barcodes
	 */
	private HashMap<String, Integer> getOrderListHashMap(List<String> orderList) {
		HashMap<String, Integer> orderMap = new HashMap<String, Integer>();
		for (String barcode : orderList) {
			if(orderMap.containsKey(barcode)){
				orderMap.put(barcode, orderMap.get(barcode)+1);
			} else {
				orderMap.put(barcode, Integer.valueOf(1));
			}
		}
		return orderMap;
	}

	/**
	 * Overrides the toString method, as a single string containing, the invoice number, 
	 * the date, list of barcodes and number, subtotal, taxcollected, 
	 */
	@Override public String toString() {
		return invoiceNumber + "#" + date + "#" + customerId + "#" + orderListToString() + 
				"#" + subTotal + "#" + taxCollected + "#" + total;
	}

	/**
	 * Creates a string representation for the orderList in format (barcode&quantity,barcode2&quantity2,...)
	 */
	private String orderListToString() {
		StringBuffer order = new StringBuffer();
		int cont = 0;
		for (String key : orderMap.keySet()) {
			if(cont == 0){
				order.append(key+"&"+orderMap.get(key));				
			} else {
				order.append(","+key+"&"+orderMap.get(key));
			}
			cont++;
		}
		return order.toString();
	}
	
	
}
