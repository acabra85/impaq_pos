/**
 * 
 */
package pl.com.impaq.main.view.devices.output;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import pl.com.impaq.main.enums.DeviceCategory;

/**
 * @author Agustin Cabra
 * @version 1.0
 *
 */
public class OutputView extends JFrame implements OutputCapable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3342571844826038386L;
	private DeviceCategory category;
	private String name;
	private String code;
	private JTextArea deviceDisplay;
	private int xIni;
	private int yIni;
	private int width;
	private int height;
	
	public OutputView(String code, String name, DeviceCategory category){
		super(name);
		this.name = name;
		this.code = code;
		this.category = category;		
		OutputView frame = this;
		if(category.equals(DeviceCategory.PRINTER)){
			xIni = 150;
			yIni = 100;
			width = (int) (50*0.75);
			height = 45;
		} else if(category.equals(DeviceCategory.DISPLAY)){
			xIni = 455;
			yIni = 100;
			width = 70;
			height = 39;
		} else {
			xIni = 10;
			yIni = 10;
			width = 30;
			height = 30;			
		}
		frame.setBounds(xIni, yIni, width, height);
		deviceDisplay = new JTextArea(height-10, width-10);
		deviceDisplay.setEditable(false);
		//2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(closeWindow());		
		JLabel lable = new JLabel(name);
		frame.getContentPane().add(lable, BorderLayout.CENTER);
		frame.getContentPane().add(deviceDisplay, BorderLayout.SOUTH);
		frame.pack();
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setVisible(true);
	}

	private int closeWindow() {
		return JFrame.EXIT_ON_CLOSE;
	}

	public DeviceCategory getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	@Override
	public void print(String msg) {
		deviceDisplay.append(msg);
	}
	
	public void appendInformation(String msg){
		deviceDisplay.append(msg);
	}
}
