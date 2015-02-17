package pl.com.impaq.test.model.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pl.com.impaq.main.controller.managers.ProductsManager;
import pl.com.impaq.main.enums.DeviceType;

@RunWith(Suite.class)
@SuiteClasses({ InvoiceDetailsCalculatorTest.class, ProductTest.class, 
	PointOfSaleTest.class, DeviceType.class, ProductsManager.class, PropertyReaderTest.class })
public class AllTestsCore {
}
