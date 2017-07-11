package com.secretescapes.commands

import grails.validation.Validateable

/**
 * Command object for defining input for money transfer
 *
 * @author Biniam Asnake.
 */
@Validateable
class TransactionCommand {

    String from

    String to

    String amount

    static constraints = {
        from nullable: false, blank: false
        to nullable: false, blank: false
        amount nullable: false, blank: false
    }
}


