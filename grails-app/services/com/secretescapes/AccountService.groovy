package com.secretescapes

import com.secretescapes.exceptions.AccountException

class AccountService {

    List<Account> getAllAccounts() {
        Account.list()
    }

    Account getAccountById(Long accountId) throws AccountException {

        if (accountId) {
            Account.get(accountId)
        } else {
            throw new AccountException("Account with id ${accountId} is not found", AccountException.ACCOUNT_NOT_FOUND_EXCEPTION)
        }
    }

    List<Account> getAllAccountsExcept(Account exceptAccount) {
        return Account.list() - exceptAccount
    }
}
