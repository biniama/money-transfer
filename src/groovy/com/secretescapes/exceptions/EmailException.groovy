package com.secretescapes.exceptions

/**
 * Definition of all Account related exceptions with specific message and code
 *
 * @author Biniam Asnake.
 */
class EmailException extends ServiceException {

    public static final Integer UNABLE_TO_SEND_EMAIL_EXCEPTION = 2000

    EmailException(String message, Integer code) {
        super(message, code)
    }
}