/**
 * 
 */
package pl.com.impaq.main.model.enums;

/**
 * Enums declaring the device kinds input or output
 * 
 * @author Agustin Cabra
 * @version 1.0
 */
public enum DeviceCategory {
	
	INPUT {
		@Override public String toString() {
			return "INPUT"; 
		}		
	}, OUTPUT {
		@Override public String toString() {
			return "OUTPUT"; 
		}
	}, UNDEFINED {
		@Override public String toString() {
			return "UNDEFINED"; 
		}
	};

	
	public static DeviceCategory getKind(String kind) {
		if(kind.equals(INPUT.toString())) {
			return INPUT;
		} else if(kind.equals(OUTPUT.toString())) {
			return OUTPUT;
		} else {
			return UNDEFINED;
		}
	}
}
