package com.secretescapes.exceptions

/**
 * Definition of all Account related exceptions with specific message and code
 *
 * @author Biniam Asnake.
 */
class AccountException extends ServiceException {

    public static final Integer ACCOUNT_NOT_FOUND_EXCEPTION = 1000

    AccountException(String message, Integer code) {
        super(message, code)
    }
}
