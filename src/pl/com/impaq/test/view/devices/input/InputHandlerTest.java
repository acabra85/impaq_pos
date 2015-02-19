/**
 * 
 */
package pl.com.impaq.test.view.devices.input;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;

import javax.swing.JButton;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.com.impaq.main.controller.PointOfSale;
import pl.com.impaq.main.enums.DeviceCategory;
import pl.com.impaq.main.view.View;
import pl.com.impaq.main.view.devices.input.InputHandler;
import pl.com.impaq.main.view.devices.input.InputView;

/**
 * @author Agustin
 *
 */
public class InputHandlerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.input.InputHandler#actionPerformed(java.awt.event.ActionEvent)}.
	 */
	@Test
	public void testActionPerformed() {
		InputView iv = new InputView("0001", "Scanner", DeviceCategory.SCANNER, View.getInstance(PointOfSale.getInstance()));
		InputHandler ih = new InputHandler(View.getInstance(PointOfSale.getInstance()), iv);
		JButton jb = new JButton();
		jb.setActionCommand("InputButton");
		jb.addActionListener(ih);
		jb.doClick();		
		jb.setActionCommand("InputButtons");
		jb.doClick();	
		jb = null;
		ih = null;
		iv = null;
		assertTrue(true);
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.input.InputHandler#keyPressed(java.awt.event.KeyEvent)}.
	 */
	@Test
	public void testKeyPressed() {
		InputView iv = new InputView("0001", "Scanner", DeviceCategory.SCANNER, View.getInstance(PointOfSale.getInstance()));
		InputHandler ih = new InputHandler(View.getInstance(PointOfSale.getInstance()), iv);
		ih.keyPressed(new KeyEvent(iv,
			    KeyEvent.KEY_PRESSED, 10,
			    10, KeyEvent.VK_ENTER, '\n'));
		ih.keyPressed(new KeyEvent(iv,
			    KeyEvent.KEY_PRESSED, 0,
			    0, KeyEvent.VK_UNDEFINED, 'h'));
		ih = null;
		iv = null;
		assertTrue(true);
		PointOfSale.dispose();
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.input.InputHandler#keyReleased(java.awt.event.KeyEvent)}.
	 */
	@Test
	public void testKeyReleased() {
		InputView iv = new InputView("0001", "Scanner", DeviceCategory.SCANNER, View.getInstance(PointOfSale.getInstance()));
		InputHandler ih = new InputHandler(View.getInstance(PointOfSale.getInstance()), iv);
		ih.keyReleased(null);
		ih = null;
		iv = null;
		PointOfSale.dispose();
		assertTrue(true);
	}

	/**
	 * Test method for {@link pl.com.impaq.main.view.devices.input.InputHandler#keyTyped(java.awt.event.KeyEvent)}.
	 */
	@Test
	public void testKeyTyped() {
		InputView iv = new InputView("0001", "Scanner", DeviceCategory.SCANNER, View.getInstance(PointOfSale.getInstance()));
		InputHandler ih = new InputHandler(View.getInstance(PointOfSale.getInstance()), iv);
		ih.keyTyped(null);
		ih = null;
		iv = null;
		PointOfSale.dispose();
		assertTrue(true);
	}

}
