package com.alekseytyan.util.error;

public enum ErrorChecker {

    LORRY_ERROR("It seems you didn't choose any truck"),
    DRIVER_ERROR("It seems you didn't choose a driver"),
    ROUTE_ERROR("It appears that this route isn't possible");

    private final String ERROR;

    ErrorChecker(String ERROR) {
        this.ERROR = ERROR;
    }

    public static String getMessage(Integer errorCode) {
        switch (errorCode) {
            case 1: return LORRY_ERROR.getERROR();
            case 2: return DRIVER_ERROR.getERROR();
            case 3: return ROUTE_ERROR.getERROR();
            default: throw new NoSuchErrorException("There's no error message describing the action you just did");
        }
    }

    public String getERROR() {
        return ERROR;
    }
}
