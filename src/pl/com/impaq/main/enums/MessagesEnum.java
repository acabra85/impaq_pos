package pl.com.impaq.main.enums;

/**
 * @author Agustin
 *
 */
public enum MessagesEnum { 
	BARCODE_NOT_FOUND {
        @Override
        public String toString() {
            return "                                        Product not found";
        }
    }, 
	BARCODE_EMPTY {
        @Override
        public String toString() {
            return "                                        Invalid bar-code";
        }
    },    
    PRODUCTS_FILE_NOT_FOUND {
        @Override
        public String toString() {
            return "Error retrieving products, products source not found, please report to System Administrator";
        }
    },    
    ERROR_RETRIEVING_PRODUCT {
        @Override
        public String toString() {
            return "Error retrieving product, please report to System Administrator";
        }
    }, ERROR_CLOSING_SOURCE {
        @Override
        public String toString() {
            return "Error closing file source, please report to System Administrator";
        }
    }, CONFIG_FILE_NOT_FOUND {
        @Override
        public String toString() {
            return "Error reading configuration file, file not found, please report to System Administrator";
        }
    }, CONFIG_READING_ERORR {
        @Override
        public String toString() {
            return "Error reading configuration file, please report to System Administrator";
        }
    }, PRODUCTS_LOADED {
        @Override
        public String toString() {
            return "                                             Products Successfully Loaded ";
        }
    }, NO_PRODUCTS_LOADED {
        @Override
        public String toString() {
            return "No Products were found";
        }
    }, 
    INPUT_DEVICES_LOADED {
        @Override
        public String toString() {
            return "                                             Input devices Successfully Loaded";
        }
    }, 
    NO_INPUT_DEVICES_LOADED {
        @Override
        public String toString() {
            return "No Input Devices loaded";
        }
    }, 
    INPUT_DEVICES_FILE_NOT_FOUND {
        @Override
        public String toString() {
            return "No input devices file found";
        }
    }, 
    ERROR_RETRIEVING_DEVICE {
        @Override
        public String toString() {
            return "Error retrieving device information";
        }
    }, OUTPUT_DEVICES_LOADED {
        @Override
        public String toString() {
            return "                                             Output Devices Successfully Loaded";
        }
    }, NO_OUTPUT_DEVICES_LOADED {
        @Override
        public String toString() {
            return "No output devices loaded";
        }
    }, PRINT_RECEIPT {
    	 @Override
    	 public String toString() {
    		 return "exit";
    	 }
    }, WAITING_MESSAGE {
	   	 @Override
	   	 public String toString() {
	   		 return "                                        <--- Please scan product, or input 'exit'";
	   	 }    	
    }, INVOICE_HEADER {
    	@Override public String toString() {
    		return "\n******************** INVOICE ******************* \n";
    	}
    }, INVOICE_FOOTER {
    	@Override public String toString() {
    		return "\n********************** END ********************* \n";
    	}
    }, PRINTING_PRINTER {
    	@Override public String toString() {
    		return "                                        <--- Printing on printer";
    	}
    }, PRINTING_LCD {
    	@Override public String toString() {
    		return "                                        <--- Printing on LCD Display";
    	}
    }, NO_SCANNER_FOUND {
    	@Override public String toString() {
    		return "Error: No bar-code Scanner found!, check the config.properties file and see if the SCANNER is INPUT device";
    	}
    }, NO_DISPLAY_FOUND {
    	@Override public String toString() {
    		return "Error: No Display Found!, check the config.properties file and see if the DISPLAY is OUTPUT device";
    	}
    }, NO_PRINTER_FOUND {
    	@Override public String toString() {
    		return "Error: No Printer Found!, check the config.properties file and see if the PRINTER is OUTPUT device";
    	}
    }, TAX_CONFIG_FILE_INVALID {
    	@Override public String toString() {
    		return "Error: The tax on the config file is not a valid tax percentage [0.0 - 100]";
    	}
    }, SYSTEM_BOOT_INFO_START {
    	@Override public String toString() {
    		return "                                          ********** SYSTEM BOOT INFO **********";
    	}
    }, SYSTEM_BOOT_INFO_END {
    	@Override public String toString() {
    		return "                                          ********** SYSTEM BOOT INFO **********";
    	}
    }, WAIT_BARCODE_INPUT {
    	@Override public String toString() {
    		return "\n___:";
    	}
    }, DISTANCE_INVOICE_INFO {
    	@Override public String toString() {
    		return "             ";
    	}
    }, TAX{
    	@Override public String toString() {
    		return "Tax %:";
    	}
    }, TAX_COLLECTED {
    	@Override public String toString() {
    		return "Tax Collected:";
    	}    	
    }, SUBTOTAL{
    	@Override public String toString() {
    		return "Subtotal:";
    	}    	
    };
    
}
