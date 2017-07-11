package com.secretescapes.exceptions


/**
 * A super class for all exception Service classes
 *
 * @author Biniam Asnake
 */
class ServiceException extends RuntimeException {

    public static final Integer INTERNAL_EXCEPTION = 1000

    private final Integer code

    ServiceException(String message, Integer code) {
        super(message)
        this.code = code
    }

    Integer getCode() {
        return code;
    }

}
