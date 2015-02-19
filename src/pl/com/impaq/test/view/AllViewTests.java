package pl.com.impaq.test.view;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import pl.com.impaq.test.view.devices.input.InputHandlerTest;
import pl.com.impaq.test.view.devices.input.InputViewTest;

@RunWith(Suite.class)
@SuiteClasses({ InputViewTest.class, ViewTest.class, InputHandlerTest.class })
public class AllViewTests {

}
