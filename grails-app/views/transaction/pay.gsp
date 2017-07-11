<%@ page import="com.secretescapes.Account" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Pay</title>
</head>

<body>

<div class="container">
    <div class="row">
        <h1><g:message code="transaction.pay.label" default="Pay"/></h1>

        <g:if test="${flash.message}">
            <div class="alert alert-info" role="alert">${flash.message}</div>
        </g:if>

        <div>
            <g:form url="[action: 'pay']" class="form-horizontal">

                <div class="form-group">
                    <label for="from" class="col-sm-2 control-label"><g:message code="transaction.from.label" default="From Account"/></label>
                    <span class="required-indicator">*</span>
                    <div class="col-sm-4">
                        <g:select class="form-control" optionKey="id" optionValue="name"
                                  name="from" from="${accountList}" required="" noSelection="['': 'Select Sender Account']"
                                  onchange="${remoteFunction(controller: 'transaction',
                                          action: 'findReceivingAccounts',
                                          params: '\'senderAccountId=\' + this.value',
                                          update: [success: 'receivingAccounts', failure: 'ohno'],
                                          options: '[asynchronous: false]'
                                  )}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label"><g:message code="transaction.to.label" default="To Account"/></label>
                    <span class="required-indicator">*</span>
                    <div class="col-sm-4">
                        <div id="receivingAccounts">
                            <g:render template="receivingAccountsSelectBox"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="amount" class="col-sm-2 control-label"><g:message code="transaction.amount.label" default="Amount"/></label>
                    <div class="input-group col-sm-4">
                        <div class="input-group-addon">$</div>
                        <g:textField type="text" class="form-control" placeholder="Amount" name="amount" required=""/>
                        <div class="input-group-addon">.00</div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-6 text-right">
                        <g:submitButton name="pay" class="btn btn-primary"
                                        value="${message(code: 'transaction.pay.label', default: 'Pay')}"/>
                    </div>
                </div>

            </g:form>

        </div>
    </div>
</div>
</body>
</html>


