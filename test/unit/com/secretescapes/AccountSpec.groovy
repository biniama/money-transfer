package com.secretescapes

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Unit Test for Account Domain
 * @author Biniam Asnake.
 */
@TestFor(Account)
class AccountSpec extends Specification {

    void "Account constraints"() {

        when:
        Account account = new Account(name: name, balance: balance, emailAddress: email)
        account.validate()

        then:
        account.hasErrors() == !valid

        where:
        name            | balance                   | email                 | valid
        ""              | new BigDecimal(20.00)     | 'email1@gmail.com'    | false   // name cannot be empty
        null            | new BigDecimal(100.00)    | 'email2@gmail.com'    | false   // name cannot be null
        "Account 1"     | new BigDecimal(30.00)     | ''                    | false  // email cannot be null
        "Account 2"     | new BigDecimal(40.00)     | 'email-no-at'         | false  // invalid email is not allowed
        "Account 3"     | null                      | 'email3@gmail.com'    | false  // balance cannot be null
        "Account 5"     | new BigDecimal(700.00)    | 'email4@gmail.com'    | true  // all valid values
    }

    void "Test default value setting for balance"() {
        when:
        Account account = new Account(name: "test", emailAddress: 'email9@gmail.com')
        account.validate()

        then:
        !account.hasErrors() // if value for 'balance' not given, the default value will be set
    }

    void "should return true for equal objects - name and email should be the same"() {
        given:
        Account a = new Account(name: 'a', emailAddress: 'email5@gmail.com')
        Account b = new Account(name: 'a', emailAddress: 'email5@gmail.com')

        expect:
        //noinspection ChangeToOperator
        a.equals(b)
    }

    void "should return false for objects with different name"() {
        given:
        Account a = new Account(name: 'b', emailAddress: 'email@gmail.com')
        Account b = new Account(name: 'a', emailAddress: 'email@gmail.com')

        expect:
        //noinspection ChangeToOperator
        !a.equals(b)
    }

    void "should return false for objects with different email"() {
        given:
        Account a = new Account(name: 'a', emailAddress: 'email@gmail.com')
        Account b = new Account(name: 'a', emailAddress: 'different-email@gmail.com')

        expect:
        //noinspection ChangeToOperator
        !a.equals(b)
    }

}
