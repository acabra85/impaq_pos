package pl.com.impaq.test.model.core;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.controller.managers.ProductsManager;
import pl.com.impaq.main.model.core.InvoiceDetailsCalculator;
import pl.com.impaq.main.model.core.Product;
import pl.com.impaq.main.model.stubs.ProductStub;
import pl.com.impaq.main.model.util.PropertyReader;

public class ProductTest {
	
	
	private static ArrayList<Product> listProducts;
	public static final String CONFIG_FILE = "data/config/config.properties";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		ProductsManager pm = ProductsManager.getInstance();
		InvoiceDetailsCalculator calculator = InvoiceDetailsCalculator.getInstance();
		new ProductStub(pm, CONFIG_FILE, calculator);
		PropertyReader pr = PropertyReader.getInstance();
		listProducts = new ArrayList<Product>();
		String[] listProductsString = pr.retrievePropertyFromConfigFile("data/tests/testOrderLists.properties", "test1").split(",");
		for (int i = 0; i < listProductsString.length; i++) {
			listProducts.add(pm.getProduct(listProductsString[i]));
		}
	}
	
	@Test
	public void testGetId() {
		Product testProduct = listProducts.get(0);
		assertEquals(testProduct.getId(), "01");
	}

	@Test
	public void testGetPrice() {
		Product testProduct = listProducts.get(0);
		assertEquals(testProduct.getPrice(), 2.5, 0.01);
	}

	@Test
	public void testGetName() {
		Product testProduct = listProducts.get(0);
		assertEquals(testProduct.getName().toLowerCase(), "Milk Bottle 1lt".toLowerCase());
	}

	@Test
	public void testGetBarCode() {
		Product testProduct = listProducts.get(0);
		assertEquals(testProduct.getBarCode(), "00001");
	}

	@Test
	public void testToString() {
		Product testProduct = listProducts.get(0);
		assertEquals((testProduct+"").toLowerCase(), "milk bottle 1lt:\t2.5".toLowerCase());
	}

}
