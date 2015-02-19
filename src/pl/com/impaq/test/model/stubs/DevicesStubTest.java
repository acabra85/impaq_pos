/**
 * 
 */
package pl.com.impaq.test.model.stubs;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.controller.PointOfSale;
import pl.com.impaq.main.controller.managers.DeviceManager;
import pl.com.impaq.main.model.stubs.DevicesStub;

/**
 * @author Agustin Cabra
 * @version 1.0
 */
public class DevicesStubTest {


	public static final String CONFIG_FILE = "data/config/config.properties";
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.stubs.DevicesStub#DevicesStub(pl.com.impaq.main.controller.managers.DeviceManager, java.lang.String)}.
	 */
	@Test
	public void testDevicesStubConfigFileExisting() {
		DeviceManager dm = new DeviceManager();
		new DevicesStub(dm, CONFIG_FILE);
		assertEquals(dm.getSizeInputDevices()+dm.getSizeOutputDevices(), 3);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.stubs.DevicesStub#DevicesStub(pl.com.impaq.main.controller.managers.DeviceManager, java.lang.String)}.
	 */
	@Test
	public void testDevicesStubConfigFileNotExisting() {
		DeviceManager dm = new DeviceManager();
		new DevicesStub(dm, CONFIG_FILE+"fff");
		assertEquals(dm.getSizeInputDevices()+dm.getSizeOutputDevices(), 0);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.stubs.DevicesStub#DevicesStub(pl.com.impaq.main.controller.managers.DeviceManager, java.lang.String)}.
	 */
	@Test
	public void testDevicesStubConfigFileNotWorking() {
		DeviceManager dm = new DeviceManager();
		new DevicesStub(dm, CONFIG_FILE+"fff");
		assertEquals(dm.getSizeInputDevices()+dm.getSizeOutputDevices(), 0);
		dm = null;
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.stubs.DevicesStub#DevicesStub(pl.com.impaq.main.controller.managers.DeviceManager, java.lang.String)}.
	 */
	@Test
	public void testLoadDevicesFromInexsistentFile(){
		DeviceManager dm = new DeviceManager();
		DevicesStub dStub = new DevicesStub(dm, CONFIG_FILE+"fff");
		dStub.loadDevices("", dm);
		assertEquals(dm.getSizeInputDevices()+dm.getSizeOutputDevices(), 0);
		dm = null;
		dStub = null;
		PointOfSale.dispose();	
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.stubs.DevicesStub#DevicesStub(pl.com.impaq.main.controller.managers.DeviceManager, java.lang.String)}.
	 */
	@Test
	public void testLoadDevicesFromExistentMalformedFileInput(){
		DeviceManager dm = new DeviceManager();
		DevicesStub dStub = new DevicesStub(dm, CONFIG_FILE+"fff");
		dStub.loadDevices("data/tests/testDevicesStubInput.txt", dm);
		assertEquals(dm.getSizeInputDevices(), 1);
		assertEquals(dm.getSizeOutputDevices(), 0);
		dm = null;
		dStub = null;
		PointOfSale.dispose();	
	}

	/**
	 * Test method for {@link pl.com.impaq.main.model.stubs.DevicesStub#DevicesStub(pl.com.impaq.main.controller.managers.DeviceManager, java.lang.String)}.
	 */
	@Test
	public void testLoadDevicesFromExistentMalformedFileOutput(){
		DeviceManager dm = new DeviceManager();
		DevicesStub dStub = new DevicesStub(dm, CONFIG_FILE+"fff");
		dStub.loadDevices("data/tests/testDevicesStubOutput.txt", dm);
		assertEquals(dm.getSizeInputDevices(), 0);
		assertEquals(dm.getSizeOutputDevices(), 1);
		dm = null;
		dStub = null;
		PointOfSale.dispose();	
	}

}
