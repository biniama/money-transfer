package com.secretescapes

import grails.test.spock.IntegrationSpec
import spock.lang.Shared

/**
 * Integration Test for Transaction Service
 * @author Biniam Asnake.
 */
class TransactionServiceIntegrationTests extends IntegrationSpec {

    TransactionService transactionService

    @Shared Account account1
    @Shared Account account2
    @Shared Account account3
    @Shared Account account4
    @Shared Account account5
    @Shared Transaction transaction1
    @Shared Transaction transaction2
    @Shared Transaction transaction3
    @Shared Transaction transaction4

    def setupSpec() {

        account1 = new Account(name: "Test Account 1", balance: 5000.00, emailAddress: "biniamasnake@gmail.com").save(flush: true, failOnError: true)
        account2 = new Account(name: "Test Account 2", balance: 21000.00, emailAddress: "biniamasnake@gmail.com").save(flush: true, failOnError: true)
        account3 = new Account(name: "Test Account 3", emailAddress: "biniamasnake@gmail.com").save(flush: true, failOnError: true) // default balance of 200.00 will be assigned
        account4 = new Account(name: "Test Account 4", balance: 60000.00, emailAddress: "biniamasnake@gmail.com").save(flush: true, failOnError: true)
        account5 = new Account(name: "Test Account 5", balance: 72000.00, emailAddress: "biniamasnake@gmail.com").save(flush: true, failOnError: true)

        transaction1 = new Transaction(from: account1, to: account2, amount: 300.00).save(flush: true, failOnError: true)
        transaction2 = new Transaction(from: account2, to: account3, amount: 100.00).save(flush: true, failOnError: true)
        transaction3 = new Transaction(from: account5, to: account4, amount: 4500.00).save(flush: true, failOnError: true)
        transaction4 = new Transaction(from: account5, to: account1, amount: 900.00).save(flush: true, failOnError: true)
    }

    void "should retrieve transactions to a given account"() {

        when:
            List<Transaction> transactionList = transactionService.getTransactionsToAccount(account2)

        then:
            transactionList
            transactionList.size() == 1
            transactionList.contains(transaction1)
    }

    void "should retrieve transactions from a given account"() {

        when:
        List<Transaction> transactionList = transactionService.getTransactionsFromAccount(account5)

        then:
        transactionList
        transactionList.size() == 2
    }

    void "should transfer money from one account to another with the given amount" () {

        given:
            BigDecimal transferAmount = 20.00
            BigDecimal fromAccountCurrentBalance = account3.balance
            BigDecimal toAccountCurrentBalance = account2.balance

        when:
            String response = transactionService.transferMoney(account3.id, account2.id, transferAmount)

        then:
            response
            response.equals("Transfer is successful and users are notified")
            account3.balance == fromAccountCurrentBalance - transferAmount
            account2.balance == toAccountCurrentBalance + transferAmount
    }
}