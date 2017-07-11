import com.secretescapes.Account
import com.secretescapes.Transaction
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->

        log.debug("Bootstrap is loading ...")

        // On Test environment, another data is being setup.
        if (!Environment.TEST) {

            Account account1 = new Account(name: "Account 1", balance: 5000.00, emailAddress: "biniamasnake@gmail.com").save(flush: true)
            Account account2 = new Account(name: "Account 2", balance: 21000.00, emailAddress: "biniamasnake@gmail.com").save(flush: true)
            Account account3 = new Account(name: "Account 3", emailAddress: "biniamasnake@gmail.com").save(flush: true) // default balance of 200.00 will be assigned
            Account account4 = new Account(name: "Account 4", balance: 60000.00, emailAddress: "biniamasnake@gmail.com").save(flush: true)
            Account account5 = new Account(name: "Account 5", balance: 72000.00, emailAddress: "biniamasnake@gmail.com").save(flush: true)

            Transaction transaction1 = new Transaction(from: account1, to: account2, amount: 300.00).save(flush: true)
            Transaction transaction2 = new Transaction(from: account2, to: account3, amount: 100.00).save(flush: true)
            Transaction transaction3 = new Transaction(from: account5, to: account4, amount: 4500.00).save(flush: true)
        }

        log.debug("Bootstrap loaded")
    }
    def destroy = {
    }
}
