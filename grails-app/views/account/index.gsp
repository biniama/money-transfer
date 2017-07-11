<%@ page import="com.secretescapes.Account" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Accounts</title>
</head>

<body>
<div class="container">
    <div class="row">
        <h1><g:message code="account.list.label" default="Account List"/></h1>

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>

        <table class="table table-striped table-bordered table-hover">
            <thead>
            <tr>
                <g:sortableColumn property="name" title="${message(code: 'account.name.label', default: 'Name')}"/>

                <g:sortableColumn property="balance"
                                  title="${message(code: 'account.balance.label', default: 'Balance')}"/>

                <g:sortableColumn property="emailAddress"
                                  title="${message(code: 'account.emailAddress.label', default: 'Email Address')}"/>
            </tr>
            </thead>
            <tbody>
            <g:each in="${accountInstanceList}" status="i" var="account">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td><g:link action="show"
                                id="${account.id}">${fieldValue(bean: account, field: "name")}</g:link></td>

                    <td>${fieldValue(bean: account, field: "balance")}</td>

                    <td>${fieldValue(bean: account, field: "emailAddress")}</td>

                </tr>
            </g:each>
            </tbody>
        </table>

        <div class="pagination">
            <g:paginate total="${accountInstanceCount ?: 0}"/>
        </div>
    </div>
</div>
</body>
</html>
