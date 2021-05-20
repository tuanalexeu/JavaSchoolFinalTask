package com.alekseytyan.logiweb.util.error;

import com.alekseytyan.logiweb.exception.NoSuchErrorException;

/**
 * Enum contain messages for different error codes
 */
public enum ErrorChecker {

    LORRY_ERROR("It seems you didn't choose any truck"),
    DRIVER_ERROR("It seems you didn't choose a driver"),
    ROUTE_ERROR("It appears that this route isn't possible"),
    DRIVERS_ERROR("The same driver cannot be assigned twice!");

    private final String ERROR;

    ErrorChecker(String ERROR) {
        this.ERROR = ERROR;
    }

    public static String getMessage(Integer errorCode) {
        switch (errorCode) {
            case 1: return LORRY_ERROR.getERROR();
            case 2: return ROUTE_ERROR.getERROR();
            case 3: return DRIVER_ERROR.getERROR();
            case 4: return DRIVERS_ERROR.getERROR();
            default: throw new NoSuchErrorException("There's no error message describing the action you just did");
        }
    }

    public String getERROR() {
        return ERROR;
    }
}
