package pl.com.impaq.test.model.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ InvoiceDetailsCalculatorTest.class, ProductTest.class, PointOfSaleTest.class })
public class AllTestsCore {
}
