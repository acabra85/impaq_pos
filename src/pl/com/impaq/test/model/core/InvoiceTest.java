/**
 * 
 */
package pl.com.impaq.test.model.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.model.core.Invoice;

/**
 * @author Agustin
 *
 */
public class InvoiceTest {

	private static Date date1;
	private static Invoice invoice;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		List<String> barcodeList = new ArrayList<String>();
		barcodeList.add("00001");
		barcodeList.add("00001");
		barcodeList.add("00001");
		barcodeList.add("00002");
		barcodeList.add("00003");
		barcodeList.add("00004");
		barcodeList.add("00005");
		barcodeList.add("00006");
		date1  = new Date();
		invoice = new Invoice("0001", date1, "CC10234", barcodeList  , 1110.0, 20.25, 1135.25);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.core.Invoice#getInvoiceNumber()}.
	 */
	@Test
	public void testGetInvoiceNumber() {
		assertEquals(invoice.getInvoiceNumber(), "0001");
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.core.Invoice#getDate()}.
	 */
	@Test
	public void testGetDate() {
		assertEquals(invoice.getDate(), date1);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.core.Invoice#getCustomerId()}.
	 */
	@Test
	public void testGetCustomerId() {
		assertEquals(invoice.getCustomerId(), "CC10234");
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.core.Invoice#getSubTotal()}.	
	 */
	@Test
	public void testGetSubTotal() {
		assertEquals(invoice.getSubTotal(), 1110.0, 0.01);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.core.Invoice#getTaxCollected()}.
	 */
	@Test
	public void testGetTaxCollected() {
		assertEquals(invoice.getTaxCollected(), 20.25, 0.01);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.core.Invoice#getTotal()}.
	 */
	@Test
	public void testGetTotal() {
		assertEquals(invoice.getTotal(), 1135.25, 0.01);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.core.Invoice#toString()}.
	 */
	@Test
	public void testToString() {
		String actual = "0001#"+date1+"#CC10234#00001&3,00002&1,00003&1,00004&1,00005&1,00006&1#1110.0#20.25#1135.25";
		assertEquals(invoice.toString(), actual);
	}

}
