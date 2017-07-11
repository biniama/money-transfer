package com.secretescapes

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Unit Test for Account Controller
 * @author Biniam Asnake.
 */
@TestFor(AccountController)
@Mock([Account, Transaction])
class AccountControllerSpec extends Specification {

    def setup() {
        controller.transactionService = new TransactionService()
    }

    void "index action should returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.accountInstanceList
            model.accountInstanceCount == 0
    }

    void "show action should returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404


        when:"A domain instance is passed to the show action"
        Account account1 = new Account(name: "Unit Test Account 1", balance: 5000.00, emailAddress: "biniamasnake@gmail.com").save()
        Account account2 = new Account(name: "Unit Test Account 2", balance: 21000.00, emailAddress: "biniamasnake@gmail.com").save()
        Account account3 = new Account(name: "Unit Test Account 3", balance: 1000.00, emailAddress: "biniamasnake@gmail.com").save()

        Transaction fromTransaction = new Transaction(from: account1, to: account2, amount: 300.00).save()
        Transaction toTransaction = new Transaction(from: account3, to: account1, amount: 100.00).save()

        controller.show(account1)

        then:"A model is populated containing the domain instance"
            model.accountInstance == account1
            model.transactionsToAccount.contains(toTransaction)
            model.transactionsFromAccount.contains(fromTransaction)
    }
}
