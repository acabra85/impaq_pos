/**
 * 
 */
package pl.com.impaq.main.view.devices.util;

import pl.com.impaq.main.enums.DeviceCategory;
import pl.com.impaq.main.view.View;
import pl.com.impaq.main.view.devices.input.InputView;
import pl.com.impaq.main.view.devices.output.OutputView;

/**
 * @author Agustin Cabra
 * @version 1.0
 *
 */
public class ViewMapper {

	public static OutputView toOutputView(String code, String name, 
			String category){
		return new OutputView(code, name, DeviceCategory.getCategory(category));
	}

	public static InputView toInputView(String code, String name,
			String category, View view) {
		InputView inputView = new InputView (code, name, DeviceCategory.getCategory(category), view);
		return inputView;
	}
}
