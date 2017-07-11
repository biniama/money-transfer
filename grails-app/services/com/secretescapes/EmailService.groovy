package com.secretescapes

import com.secretescapes.exceptions.EmailException
import grails.plugin.mail.MailService
import org.apache.log4j.Logger
import org.springframework.context.MessageSource

class EmailService {

    private static final Logger log = Logger.getLogger(EmailService.class)

    private static final String EMAIL_BODY_FOR_MONEY_RECEIVER = "email_body_for_money_receiver"
    private static final String EMAIL_SUBJECT_FOR_MONEY_RECEIVER = "email_subject_for_money_receiver"

    private static final String EMAIL_BODY_FOR_MONEY_SENDER = "email_body_for_money_sender"
    private static final String EMAIL_SUBJECT_FOR_MONEY_SENDER = "email_subject_for_money_sender"

    MailService mailService
    MessageSource messageSource

    private def sendEmailToMoneyReceiver (Account fromAccount, Account toAccount, BigDecimal amount) {

        String body = messageSource.getMessage(EMAIL_BODY_FOR_MONEY_RECEIVER, [toAccount.name, fromAccount.name, amount].toArray(), Locale.default)

        String subject = messageSource.getMessage(EMAIL_SUBJECT_FOR_MONEY_RECEIVER, [fromAccount.name].toArray(), Locale.default)

        sendEmail(toAccount.emailAddress, subject, body)
        log.debug("E-mail regarding a money transfer is sent to the receiver.")
    }

    private def sendEmailToMoneySender (Account fromAccount, Account toAccount, BigDecimal amount) {

        String body = messageSource.getMessage(EMAIL_BODY_FOR_MONEY_SENDER, [fromAccount.name, toAccount.name, amount].toArray(), Locale.default)

        String subject = messageSource.getMessage(EMAIL_SUBJECT_FOR_MONEY_SENDER, [toAccount.name].toArray(), Locale.default)

        sendEmail(fromAccount.emailAddress, subject, body)
        log.debug("E-mail regarding a money transfer is sent to the sender.")
    }

    def sendEmailToMoneySenderAndReceiver (Account fromAccount, Account toAccount, BigDecimal amount) {
        sendEmailToMoneyReceiver(fromAccount, toAccount, amount)
        sendEmailToMoneySender(fromAccount, toAccount, amount)
    }

    def sendEmail (String emailTo, String emailSubject, String emailBody) {

        try {
            mailService.sendMail {
                to emailTo
                subject emailSubject
                body emailBody
            }
        } catch (Exception ex) {
            throw new EmailException("Unable to send email because: ${ex.getMessage()}", EmailException.UNABLE_TO_SEND_EMAIL_EXCEPTION)
        }
    }
}
