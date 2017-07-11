package com.secretescapes.commands

import grails.validation.Validateable

/**
 * Command object for defining input for filtering accounts of receiving users
 *
 * @author Biniam Asnake.
 */
@Validateable
class FilterAccountsCommand {

    String senderAccountId

    static constraints = {
        senderAccountId nullable: false, blank: false
    }
}


