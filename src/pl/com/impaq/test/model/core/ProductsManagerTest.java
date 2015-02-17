/**
 * 
 */
package pl.com.impaq.test.model.core;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.controller.managers.ProductsManager;
import pl.com.impaq.main.model.core.Product;

/**
 * @author acs
 *
 */
public class ProductsManagerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.ProductsManager#getSizeProducts()}.
	 */
	@Test
	public void testGetSizeProducts() {
		ProductsManager pm = new ProductsManager();
		ArrayList<Product> listP = new ArrayList<Product>();
		listP.add(new Product("00", "test", 10.0, "00099"));
		listP.add(new Product("00", "test", 10.0, "00101"));
		listP.add(new Product("00", "test", 10.0, "00102"));
		listP.add(new Product("00", "test", 10.0, "00102"));
		listP.add(new Product("00", "test", 10.0, "00102"));
		pm.addProducts(listP);
		assertEquals(pm.getSizeProducts(), listP.size()-2);
		pm = null;
		
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.ProductsManager#isBarCodeValid(java.lang.String)}.
	 */
	@Test
	public void testIsBarCodeValid() {
		ProductsManager pm = new ProductsManager();
		ArrayList<Product> listP = new ArrayList<Product>();
		listP.add(new Product("00", "test", 10.0, "00099"));
		listP.add(new Product("00", "test", 10.0, "00101"));
		listP.add(new Product("00", "test", 10.0, "00102"));
		pm.addProducts(listP);
		assertTrue(pm.isBarCodeValid(listP.get(0).getBarCode()));
		pm = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.ProductsManager#isBarCodeValid(java.lang.String)}.
	 */
	@Test
	public void testIsBarCodeNotValid() {
		ProductsManager pm = new ProductsManager();
		ArrayList<Product> listP = new ArrayList<Product>();
		listP.add(new Product("00", "test", 10.0, "00099"));
		listP.add(new Product("00", "test", 10.0, "00101"));
		listP.add(new Product("00", "test", 10.0, "00102"));
		pm.addProducts(listP);
		assertFalse(pm.isBarCodeValid("010101"));
		pm = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.ProductsManager#getProduct(java.lang.String)}.
	 */
	@Test
	public void testGetProductInexistent() {
		ProductsManager pm = new ProductsManager();
		ArrayList<Product> listP = new ArrayList<Product>();
		listP.add(new Product("00", "test", 10.0, "00099"));
		listP.add(new Product("00", "test", 10.0, "00101"));
		listP.add(new Product("00", "test", 10.0, "00102"));
		pm.addProducts(listP);
		assertEquals(pm.getProduct("010101"), null);
		pm = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.ProductsManager#getProduct(java.lang.String)}.
	 */
	@Test
	public void testGetProductExistent() {
		ProductsManager pm = new ProductsManager();
		ArrayList<Product> listP = new ArrayList<Product>();
		listP.add(new Product("00", "test", 10.0, "00099"));
		listP.add(new Product("00", "test", 10.0, "00101"));
		listP.add(new Product("00", "test", 10.0, "00102"));
		pm.addProducts(listP);
		assertEquals(pm.getProduct(listP.get(2).getBarCode()), listP.get(2));
		pm = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.ProductsManager#addProduct(java.lang.String, java.lang.String, double, java.lang.String)}.
	 */
	@Test
	public void testAddProductFromData() {
		ProductsManager pm = new ProductsManager();
		assertTrue(pm.addProduct("00", "test", 10.0, "00000"));
		pm = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.ProductsManager#addProduct(java.lang.String, java.lang.String, double, java.lang.String)}.
	 */
	@Test
	public void testAddProductFromDataExistent() {
		ProductsManager pm = new ProductsManager();
		pm.addProduct("00", "test", 10.0, "00000");
		assertFalse(pm.addProduct("00", "test", 10.0, "00000"));
		pm = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.ProductsManager#addProduct(pl.com.impaq.main.model.core.Product)}.
	 */
	@Test
	public void testAddProductProductInexistent() {
		ProductsManager pm = new ProductsManager();
		assertTrue(pm.addProduct(new Product("00", "test", 10.0, "00000")));
		pm = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.ProductsManager#addProduct(pl.com.impaq.main.model.core.Product)}.
	 */
	@Test
	public void testAddProductProductExistent() {
		ProductsManager pm = new ProductsManager();
		pm.addProduct(new Product("00", "test", 10.0, "00000"));
		assertFalse(pm.addProduct(new Product("00", "test", 10.0, "00000")));
		pm = null;
	}

	/**
	 * Test method for {@link pl.com.impaq.main.controller.managers.ProductsManager#addProducts(java.util.ArrayList)}.
	 */
	@Test
	public void testAddProducts() {
		ProductsManager pm = new ProductsManager();
		ArrayList<Product> listP = new ArrayList<Product>();
		listP.add(new Product("00", "test", 10.0, "00099"));
		listP.add(new Product("00", "test", 10.0, "00101"));
		listP.add(new Product("00", "test", 10.0, "00102"));
		listP.add(new Product("00", "test", 10.0, "00103"));
		listP.add(new Product("00", "test", 10.0, "00104"));		;
		assertEquals(pm.addProducts(listP), listP.size());
		pm = null;
	}

}
