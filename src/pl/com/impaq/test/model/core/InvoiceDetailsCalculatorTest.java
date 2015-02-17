package pl.com.impaq.test.model.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.controller.managers.ProductsManager;
import pl.com.impaq.main.model.core.InvoiceDetailsCalculator;
import pl.com.impaq.main.model.core.Product;
import pl.com.impaq.main.model.stubs.ProductStub;
import pl.com.impaq.main.model.util.PropertyReader;

/**
 * @author Agustin Cabra
 * @version 1.0
 */
public class InvoiceDetailsCalculatorTest {

	public static final String CONFIG_FILE = "data/config/config.properties";
	public static InvoiceDetailsCalculator calculator;
	public static ArrayList<Product> listProducts;
	public static ArrayList<Product> listProducts2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//ByteArrayInputStream in = new ByteArrayInputStream("00001".getBytes());
		calculator = InvoiceDetailsCalculator.getInstance();
		ProductsManager pm = ProductsManager.getInstance();
		new ProductStub(pm, CONFIG_FILE, calculator);
		PropertyReader pr = PropertyReader.getInstance();
		listProducts = new ArrayList<Product>();
		String[] listProductsString = pr.retrievePropertyFromConfigFile("data/tests/testOrderLists.properties", "test1").split(",");
		for (int i = 0; i < listProductsString.length; i++) {
			listProducts.add(pm.getProduct(listProductsString[i]));
		}
		listProducts2 = new ArrayList<Product>();
		String[] listProductsString2 = pr.retrievePropertyFromConfigFile("data/tests/testOrderLists.properties", "test2").split(",");
		for (int i = 0; i < listProductsString2.length; i++) {
			listProducts2.add(pm.getProduct(listProductsString2[i]));
		}
		
	}

	@Test
	public void testCalculateTotalWithoutTax() {
		calculator.setTax(0.0);
		assertEquals(calculator.calculateOrderTotal(listProducts), 84.35, 0.01);
	}

	@Test
	public void testCalculateSubTotalWithoutTax() {
		calculator.setTax(0.0);
		assertEquals(calculator.calculateOrderSubTotal(listProducts), 84.35, 0.0);
	}

	@Test
	public void testCalculateTotalWithTax() {
		calculator.setTax(10.0);
		assertEquals(calculator.calculateOrderTotal(listProducts), 92.78, 0.01);
	}

	@Test
	public void testCalculateSubTotalWithTax() {
		calculator.setTax(10.0);
		assertEquals(calculator.calculateOrderSubTotal(listProducts), 84.35, 0.01);
	}

	@Test
	public void testCalculateInvoiceDetailsWithTax() {
		calculator.setTax(10.0);
		assertEquals(calculator.getInvoiceDetails(listProducts).length(), 812);
	}

	@Test
	public void testCalculateInvoiceDetailsWithoutTax() {
		calculator.setTax(0.0);
		assertEquals(calculator.getInvoiceDetails(listProducts).length(), 725);
	}

	@Test
	public void testTaxModification() {
		calculator.setTax(10.5);
		assertEquals(calculator.getTax(), 10.5, 0.01);
	}

	@Test
	public void testTaxInvalidModification() {
		calculator.setTax(0.0);
		calculator.setTax(-10.5);
		assertEquals(calculator.getTax(), 0.0, 0.01);
	}

	@Test
	public void testCollectedTax1() {
		calculator.setTax(10.0);
		assertEquals(calculator.calculateTaxCollected(calculator.calculateOrderSubTotal(listProducts)), 8.44, 0.01);
	}	

	//Test using test2 configured in file
	@Test
	public void testCollectedTax2() {
		calculator.setTax(10.0);
		assertEquals(calculator.calculateTaxCollected(calculator.calculateOrderSubTotal(listProducts2)), 2.5, 0.01);
	}	

	@Test
	public void testCalculateTotalWithoutTax2() {
		calculator.setTax(0.0);
		assertEquals(calculator.calculateOrderTotal(listProducts2), 25.0, 0.01);
	}

	@Test
	public void testCalculateSubTotalWithoutTax2() {
		calculator.setTax(0.0);
		assertEquals(calculator.calculateOrderSubTotal(listProducts2), 25.0, 0.0);
	}

	@Test
	public void testCalculateTotalWithTax2() {
		calculator.setTax(10.0);
		assertEquals(calculator.calculateOrderTotal(listProducts), 92.78, 0.01);
	}

	@Test
	public void testCalculateSubTotalWithTax2() {
		calculator.setTax(10.0);
		assertEquals(calculator.calculateOrderSubTotal(listProducts2), 25.0, 0.01);
	}

	@Test
	public void testCalculateInvoiceDetailsWithTax2() {
		calculator.setTax(10.0);
		assertEquals(calculator.getInvoiceDetails(listProducts2).length(), 552);
	}

	@Test
	public void testCalculateInvoiceDetailsWithoutTax2() {
		calculator.setTax(0.0);
		assertEquals(calculator.getInvoiceDetails(listProducts2).length(), 467);
	}

}
