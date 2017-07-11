package com.secretescapes

class Transaction {

    Account from

    Account to

    Double amount

    static constraints = {

        from nullable: false
        to nullable: false
        amount nullable: false
    }
}