package com.secretescapes

class AccountController {

    static allowedMethods = [index: "GET"]

    TransactionService transactionService

    def index() {
        params.max = Math.min(10, 100)
        respond Account.list(params), model:[ accountInstanceCount: Account.count() ]
    }

    def show(Account accountInstance) {

        List<Transaction> transactionsToAccount = transactionService.getTransactionsToAccount(accountInstance)
        List<Transaction> transactionsFromAccount = transactionService.getTransactionsFromAccount(accountInstance)

        respond accountInstance, model: [ transactionsToAccount: transactionsToAccount, transactionsFromAccount: transactionsFromAccount ]
    }
}
