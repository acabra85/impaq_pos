package pl.com.impaq.test.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pl.com.impaq.test.model.core.InvoiceDetailsCalculatorTest;
import pl.com.impaq.test.model.core.InvoiceTest;
import pl.com.impaq.test.model.core.ProductTest;
import pl.com.impaq.test.model.stubs.DevicesStubTest;
import pl.com.impaq.test.model.stubs.ProductStubTest;
import pl.com.impaq.test.model.util.PropertyReaderTest;
@RunWith(Suite.class)
@SuiteClasses({ DevicesStubTest.class, 
	InvoiceDetailsCalculatorTest.class, 
	ProductTest.class,ProductStubTest.class, 
	PropertyReaderTest.class, InvoiceTest.class
		 })
public class AllModelTests {

}
