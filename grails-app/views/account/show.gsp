<%@ page import="com.secretescapes.Account" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'account.label', default: 'Account')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="container">
    <h3><g:message code="account.detail.label" default="Account Detail"/></h3>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div class="row">

        <div class="col-md-6">
            <table class="table">

                <g:if test="${accountInstance?.name}">
                    <tr>
                        <td>
                            <g:message code="account.name.label" default="Name"/>
                        </td>
                        <td>

                            <g:fieldValue bean="${accountInstance}" field="name"/>
                        </td>
                    </tr>
                </g:if>

                <g:if test="${accountInstance?.balance}">
                    <tr>
                        <td>
                            <g:message code="account.balance.label" default="Balance"/>
                        </td>
                        <td>
                            <g:fieldValue bean="${accountInstance}" field="balance"/>
                        </td>
                    </tr>

                </g:if>

                <g:if test="${accountInstance?.emailAddress}">
                    <tr>
                        <td>
                            <g:message code="account.emailAddress.label" default="Email Address"/>
                        </td>

                        <td>
                            <g:fieldValue bean="${accountInstance}" field="emailAddress"/>
                        </td>
                    </tr>
                </g:if>
            </table>
        </div>
    </div>

    <g:if test="${transactionsToAccount}">
        <div class="panel panel-default">
            <div class="panel-heading">Transactions to this account</div>

            <div class="panel-body">
                <table class="table">
                    <tr>
                        <th>From</th>
                        <th>Amount</th>
                    </tr>
                    <g:each in="${transactionsToAccount}" status="i" var="transactionTo">
                        <tr>
                            <td>${fieldValue(bean: transactionTo, field: "from.name")}</td>
                            <td>${fieldValue(bean: transactionTo, field: "amount")}</td>
                        </tr>
                    </g:each>
                </table>
            </div>
        </div>
    </g:if>

    <g:if test="${transactionsFromAccount}">
        <div class="panel panel-default">
            <div class="panel-heading">Transactions from this account</div>

            <div class="panel-body">
                <table class="table">
                    <tr>
                        <th>To</th>
                        <th>Amount</th>
                    </tr>
                    <g:each in="${transactionsFromAccount}" status="i" var="transactionFrom">
                        <tr>
                            <td>${fieldValue(bean: transactionFrom, field: "to.name")}</td>
                            <td>${fieldValue(bean: transactionFrom, field: "amount")}</td>
                        </tr>
                    </g:each>
                </table>
            </div>
        </div>
    </g:if>
</div>

</body>
</html>
