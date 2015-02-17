package pl.com.impaq.main.enums;

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
		
	},
	UNKNOWN_DEVICE_TYPE {
		@Override
		public String toString() {
			return "UNKNOWN_DEVICE_TYPE";
		}
		
	};
	
	public static DeviceType getType(String type) {		
		if(type.equals(PRINTER +"")) {
			return PRINTER;
		} else if(type.equals(SCANNER+ "")) {
			return SCANNER;
		} else  if(type.equals(DISPLAY+ "")) {
			return DISPLAY;
		} else {
			return UNKNOWN_DEVICE_TYPE;
		}
	}
}
