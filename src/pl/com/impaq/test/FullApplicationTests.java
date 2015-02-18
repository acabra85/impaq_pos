package pl.com.impaq.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pl.com.impaq.test.controller.PointOfSaleTest;
import pl.com.impaq.test.model.DeviceCategoryTest;
import pl.com.impaq.test.model.DeviceManagerTest;
import pl.com.impaq.test.model.DevicesStubTest;
import pl.com.impaq.test.model.InvoiceDetailsCalculatorTest;
import pl.com.impaq.test.model.ProductStubTest;
import pl.com.impaq.test.model.ProductTest;
import pl.com.impaq.test.model.ProductsManagerTest;
import pl.com.impaq.test.model.PropertyReaderTest;
import pl.com.impaq.test.view.InputViewTest;
import pl.com.impaq.test.view.ViewTest;

@RunWith(Suite.class)
@SuiteClasses({ DeviceCategoryTest.class, DeviceManagerTest.class,
	DevicesStubTest.class, InvoiceDetailsCalculatorTest.class,
	PointOfSaleTest.class, ProductsManagerTest.class,
	ProductStubTest.class, ProductTest.class, PropertyReaderTest.class, InputViewTest.class, ViewTest.class})
public class FullApplicationTests {
}
