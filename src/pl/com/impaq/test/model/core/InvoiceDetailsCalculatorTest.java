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
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//ByteArrayInputStream in = new ByteArrayInputStream("00001".getBytes());
		calculator = new InvoiceDetailsCalculator();
		ProductsManager pm = new ProductsManager();
		new ProductStub(pm, CONFIG_FILE, calculator);
		PropertyReader pr = PropertyReader.getInstance();
		listProducts = new ArrayList<Product>();
		String[] listProductsString = pr.retrievePropertyFromConfigFile("data/tests/testOrderLists.properties", "test1").split(",");
		for (int i = 0; i < listProductsString.length; i++) {
			listProducts.add(pm.getProduct(listProductsString[i]));
		}
		
	}

	@Test
	public void testCalculateTotalWithoutTax() {
		assertEquals(calculator.calculateOrderTotal(listProducts), 84.35, 0.0);
	}

	@Test
	public void testCalculateSubTotalWithoutTax() {
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
		assertEquals(calculator.calculateOrderSubTotal(listProducts), 92.78, 0.01);
	}

}
