package pl.com.impaq.test.model.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ InvoiceDetailsCalculatorTest.class, ProductTest.class, 
	PointOfSaleTest.class, DeviceTypeTest.class, ProductsManagerTest.class, 
	PropertyReaderTest.class, DevicesStubTest.class, ProductStubTest.class, DeviceManagerTest.class})
public class AllTestsCore {
}
