package pl.com.impaq.test.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pl.com.impaq.test.controller.PointOfSaleTest;
import pl.com.impaq.test.controller.managers.DeviceManagerTest;
import pl.com.impaq.test.controller.managers.ProductsManagerTest;
import pl.com.impaq.test.enums.DeviceCategoryTest;
import pl.com.impaq.test.model.core.InvoiceDetailsCalculatorTest;
import pl.com.impaq.test.model.core.ProductTest;
import pl.com.impaq.test.model.stubs.DevicesStubTest;
import pl.com.impaq.test.model.stubs.ProductStubTest;
import pl.com.impaq.test.model.util.PropertyReaderTest;
@RunWith(Suite.class)
@SuiteClasses({ DeviceCategoryTest.class, DeviceManagerTest.class,
		DevicesStubTest.class, InvoiceDetailsCalculatorTest.class,
		PointOfSaleTest.class, ProductsManagerTest.class,
		ProductStubTest.class, ProductTest.class, PropertyReaderTest.class })
public class AllModelTests {

}
