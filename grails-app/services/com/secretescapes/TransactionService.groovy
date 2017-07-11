package com.secretescapes

class TransactionService {

    AccountService accountService
    EmailService emailService

    List<Transaction> getTransactionsToAccount(Account account) {
        return Transaction.findAllByTo(account)
    }

    List<Transaction> getTransactionsFromAccount(Account account) {
        return Transaction.findAllByFrom(account)
    }

    String transferMoney(Long fromAccountId, Long toAccountId, BigDecimal amount) {

        Account fromAccount = accountService.getAccountById(fromAccountId)
        Account toAccount = accountService.getAccountById(toAccountId)

        transferMoney(fromAccount, toAccount, amount)
    }

    /**
     * Transfers the specified amount from one account to another
     *
     * @param fromAccount: sender account
     * @param toAccount: receiver account
     * @param amount: amount to be transferred
     * @return String: Message about success or fa
     */
    String transferMoney(Account fromAccount, Account toAccount, BigDecimal amount) {

        if (fromAccount.equals(toAccount)) {
            return "You cannot transfer money to the same account. Please try again."
        }

        if (fromAccount.balance >= amount) {
            Transaction transaction = new Transaction(from: fromAccount, to: toAccount, amount: amount).save(flush: true)

            if (transaction.hasErrors()) {
                return "Transfer failed."
            }

            //Credit from account
            fromAccount.balance -= amount
            fromAccount.save(flush: true)

            //Debit to account
            toAccount.balance += amount
            toAccount.save(flush: true)

            //send email
            emailService.sendEmailToMoneySenderAndReceiver(fromAccount, toAccount, amount)
            return "Transfer is successful and users are notified"
        } else {
            return "The sender account's balance is not sufficient."
        }
    }
}
