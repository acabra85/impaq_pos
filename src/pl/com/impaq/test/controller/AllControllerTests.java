package pl.com.impaq.test.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pl.com.impaq.test.controller.managers.DeviceManagerTest;
import pl.com.impaq.test.controller.managers.ProductsManagerTest;
import pl.com.impaq.test.enums.DeviceCategoryTest;

@RunWith(Suite.class)
@SuiteClasses({ PointOfSaleTest.class, 
	ProductsManagerTest.class, 
	DeviceManagerTest.class ,
	DeviceCategoryTest.class})
public class AllControllerTests {

}
