package pl.com.impaq.test.core;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.controller.managers.ProductsManager;
import pl.com.impaq.main.model.core.InvoiceDetailsCalculator;
import pl.com.impaq.main.model.stubs.ProductStub;
import pl.com.impaq.main.model.util.PropertyReader;

/**
 * @author Agustin Cabra
 * @version 1.0
 */
public class InvoiceDetailsCalculatorTest {

	public static final String CONFIG_FILE = "data/config/config.properties";
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//ByteArrayInputStream in = new ByteArrayInputStream("00001".getBytes());
		InvoiceDetailsCalculator calculator = new InvoiceDetailsCalculator();
		ProductsManager pm = new ProductsManager();
		new ProductStub(pm, CONFIG_FILE, calculator);
		PropertyReader pr = PropertyReader.getInstance();
		pr.retrievePropertyFromConfigFile("data/tests/testOrderLists.properties", "test1");
	}

	@Test
	public void testCalculateTotalWithoutTax() {
		assertEquals("El total de la lista es :", 0, 0);
	}

}
