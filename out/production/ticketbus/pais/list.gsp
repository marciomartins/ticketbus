
<%@ page import="ticketbus.Pais" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<h1 class="page-header">
    <g:message code="default.list.label" args="[entityName]"/>

    <span class="pull-right">
        <g:link class="btn btn-primary" action="create">
              <g:message code="default.new.label" args="[entityName]"/>
          </g:link>

    </span>

</h1>

<g:if test="${flash.message}">
    <div class="alert alert-info">
        ${flash.message}
    </div>
</g:if>
<g:form url="[action:'search']">
   <input name="q"></input>
   <button class="btn btn-primary">Search</button>
</g:form>
<table class="table table-bordered table-striped">
    <thead>
    <tr>
        <g:sortableColumn property="id"
                          title="${message(code: 'pais.id.label', default: 'Id')}"/>

        <g:sortableColumn property="email"
                          title="${message(code: 'pais.nome.label', default: 'Email')}"/>
    </tr>
    </thead>
    <tbody>
    <g:each in="${paisInstanceList}" status="i" var="paisInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

            <td><g:link action="show"
                        id="${paisInstance.id}">${fieldValue(bean: paisInstance, field: "id")}</g:link></td>

            <td>${fieldValue(bean: paisInstance, field: "nome")}</td>

        </tr>
    </g:each>
    </tbody>
</table>

<div class="pagination">
    <g:paginate total="${userInstanceTotal ?: 0}"/>
</div>

</body>
</html>
