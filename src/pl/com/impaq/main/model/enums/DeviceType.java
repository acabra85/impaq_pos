package pl.com.impaq.main.model.enums;

/**
 * Enums declaring the device types printer, scanner, display ... etc
 * 
 * @author Agustin Cabra
 * @version 1.0
 *
 */
public enum DeviceType {
	PRINTER {
		@Override
		public String toString() {
			return "PRINTER";
		}
	},
	SCANNER {
		@Override
		public String toString() {
			return "SCANNER";
		}
		
	},
	DISPLAY {
		@Override
		public String toString() {
			return "DISPLAY";
		}
		
	};
	
	public static DeviceType getType(String type) {		
		if(type.equals(PRINTER.toString())) {
			return PRINTER;
		} else if(type.equals(SCANNER.toString())) {
			return SCANNER;
		} else {
			return DISPLAY;
		}
	}
}
