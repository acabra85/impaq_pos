/**
 * 
 */
package pl.com.impaq.main.view.devices.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pl.com.impaq.main.view.devices.View;

/**
 * @author Agustin
 *
 */
public class InputHandler implements ActionListener, KeyListener {

	private View view;

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("InputButton")){
			view.sendBarCode();
			view.clearCommandSent();
		}
	}
	
	public InputHandler(View view) {
		this.view = view;
	}

	@Override
	public void keyPressed(KeyEvent paramKeyEvent) {
		if(paramKeyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
			view.sendBarCode();
			view.clearCommandSent();
		}
	}

	@Override
	public void keyReleased(KeyEvent paramKeyEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent paramKeyEvent) {
		// TODO Auto-generated method stub
		
	}

}
