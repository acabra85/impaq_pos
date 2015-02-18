package pl.com.impaq.test.model.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DeviceCategoryTest.class, DeviceManagerTest.class,
		DevicesStubTest.class, InvoiceDetailsCalculatorTest.class,
		PointOfSaleTest.class, ProductsManagerTest.class,
		ProductStubTest.class, ProductTest.class, PropertyReaderTest.class })
public class AllTests {

}
