package com.secretescapes

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes = ['name', 'emailAddress'])
class Account {

    String name

    BigDecimal balance = 200.00

    String emailAddress

    static constraints = {

        name unique: true, blank: false
        balance nullable: false
        emailAddress email: true, nullable: false
    }
}
