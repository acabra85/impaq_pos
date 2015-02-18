package pl.com.impaq.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.controller.managers.ProductsManager;
import pl.com.impaq.main.model.core.InvoiceDetailsCalculator;
import pl.com.impaq.main.model.stubs.ProductStub;
import pl.com.impaq.main.model.util.PropertyReader;

/**
 * @author Agustin
 *
 */
public class ProductStubTest {

	public static final String CONFIG_FILE = "data/config/config.properties";
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.stubs.ProductStub#ProductStub(pl.com.impaq.main.controller.managers.ProductsManager, java.lang.String, pl.com.impaq.main.model.core.InvoiceDetailsCalculator)}.
	 */
	@Test
	public void testProductStub() {
		ProductsManager posProductManager = new ProductsManager(); 
		InvoiceDetailsCalculator calculator = new InvoiceDetailsCalculator();
		new ProductStub(posProductManager, CONFIG_FILE, calculator);
		assertEquals(posProductManager.getSizeProducts(), 16);
		calculator = null;		
		posProductManager = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.stubs.ProductStub#ProductStub(pl.com.impaq.main.controller.managers.ProductsManager, java.lang.String, pl.com.impaq.main.model.core.InvoiceDetailsCalculator)}.
	 */
	@Test
	public void testProductStubFromInexistentConfigFile() {
		ProductsManager posProductManager = new ProductsManager(); 
		InvoiceDetailsCalculator calculator = new InvoiceDetailsCalculator();
		new ProductStub(posProductManager, CONFIG_FILE+"dd", calculator);
		assertEquals(posProductManager.getSizeProducts(), 0);
		calculator = null;
		posProductManager = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.stubs.ProductStub#loadProducts(java.lang.String, pl.com.impaq.main.controller.managers.ProductsManager)}.
	 */
	@Test
	public void testLoadProductsFromInexistingProductsFile() {
		ProductsManager posProductManager = new ProductsManager(); 
		InvoiceDetailsCalculator calculator = new InvoiceDetailsCalculator();
		ProductStub ps = new ProductStub(posProductManager, CONFIG_FILE+"dd", calculator);
		ps.loadProducts("", posProductManager);
		assertEquals(posProductManager.getSizeProducts(), 0);
		ps = null;
		calculator = null;
		posProductManager = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.stubs.ProductStub#loadProducts(java.lang.String, pl.com.impaq.main.controller.managers.ProductsManager)}.
	 */
	@Test
	public void testLoadProductsFromExistentProductsMalformedFileEmpty() {
		ProductsManager posProductManager = new ProductsManager(); 
		InvoiceDetailsCalculator calculator = new InvoiceDetailsCalculator();
		ProductStub ps = new ProductStub(posProductManager, CONFIG_FILE+"dd", calculator);
		ps.loadProducts("data/tests/testProductsEmpty.txt", posProductManager);
		assertEquals(posProductManager.getSizeProducts(), 0);
		ps = null;
		calculator = null;
		posProductManager = null;
	}


	/**
	 * Test method for {@link pl.com.impaq.main.model.stubs.ProductStub#loadProducts(java.lang.String, pl.com.impaq.main.controller.managers.ProductsManager)}.
	 */
	@Test
	public void testGetTaxString() {
		ProductsManager posProductManager = new ProductsManager(); 
		InvoiceDetailsCalculator calculator = new InvoiceDetailsCalculator();
		ProductStub ps = new ProductStub(posProductManager, CONFIG_FILE+"dd", calculator);
		assertEquals(ps.getTaxString(CONFIG_FILE), PropertyReader.getInstance().retrievePropertyFromConfigFile(CONFIG_FILE, "tax"));
		ps = null;
		posProductManager = null;
		calculator = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.stubs.ProductStub#loadProducts(java.lang.String, pl.com.impaq.main.controller.managers.ProductsManager)}.
	 */
	@Test
	public void testLoadProductsFromExistentProductsMalformedFile() {
		ProductsManager posProductManager = new ProductsManager(); 
		InvoiceDetailsCalculator calculator = new InvoiceDetailsCalculator();
		ProductStub ps = new ProductStub(posProductManager, CONFIG_FILE+"dd", calculator);
		ps.loadProducts("data/tests/testProducts.txt", posProductManager);
		assertEquals(posProductManager.getSizeProducts(), 1);
		ps = null;
		calculator = null;
		posProductManager = null;
	}
	
	@Test
	public void testSetTaxInvalidDouble() {
		ProductsManager posProductManager = new ProductsManager(); 
		InvoiceDetailsCalculator calculator = new InvoiceDetailsCalculator();
		ProductStub ps = new ProductStub(posProductManager, CONFIG_FILE+"dd", calculator);
		ps.setTaxForInvoiceCalculator(calculator, "sds");
	}
	
	@Test
	public void testSetTaxValidDouble() {
		ProductsManager posProductManager = new ProductsManager(); 
		InvoiceDetailsCalculator calculator = new InvoiceDetailsCalculator();
		ProductStub ps = new ProductStub(posProductManager, CONFIG_FILE+"dd", calculator);
		ps.setTaxForInvoiceCalculator(calculator, "0.0");
	}
	

}
