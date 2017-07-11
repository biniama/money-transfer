package com.secretescapes

import com.secretescapes.commands.FilterAccountsCommand
import com.secretescapes.commands.TransactionCommand

class TransactionController {

    AccountService accountService
    TransactionService transactionService

    def showPay() {
        render view: 'pay', model: [accountList: accountService.getAllAccounts()]
    }

    def pay(TransactionCommand transactionCommand) {

        if (transactionCommand.validate()) {

            BigDecimal amount = new BigDecimal(transactionCommand.amount)

            String transactionResult = transactionService.transferMoney(transactionCommand.from.toLong(), transactionCommand.to.toLong(), amount)
            flash.message = transactionResult
            showPay()
        } else {
            respond transactionCommand.errors, [format: "json"]
        }
    }

    def findReceivingAccounts(FilterAccountsCommand filterAccountsCommand) {

        if (filterAccountsCommand.validate()) {

            Account selectedSenderAccount = accountService.getAccountById(filterAccountsCommand.senderAccountId.toLong())
            List<Account> accountsExceptSelectedAccount = accountService.getAllAccountsExcept(selectedSenderAccount)
            render(template: 'receivingAccountsSelectBox', model:  [ receivingAccountsList: accountsExceptSelectedAccount ])
        } else {
            respond filterAccountsCommand.errors, [format: "json"]
        }
    }
}
