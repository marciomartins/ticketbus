<!DOCTYPE html>
<%@ page import="ticketbus.Pais" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'pais.label', default: 'País')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<h1 class="page-header">
    <g:message code="default.show.label" args="[entityName]"/>
    <span class="pull-right">
        <g:form action="delete" id="${paisInstance?.id}">
            <g:link class="btn" action="index">
                <g:message code="default.list.label" args="[entityName]"/>
            </g:link>
            <g:link class="btn btn-info" action="edit" id="${paisInstance?.id}">
                <g:message code="default.button.edit.label" default="Edit"/>
            </g:link>
            <g:submitButton name="delete" class="btn btn-danger" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </g:form>
    </span>
</h1>

<g:if test="${flash.message}">
    <div class="alert alert-info">
        ${flash.message}
    </div>
</g:if>

<table class="table table-bordered table-striped">
    <tbody>
    
    <tr>
        <td class="show-label">
            <g:message code="pais.id.label" default="Id"/>
        </td>

        
        <td class="show-value">${fieldValue(bean: paisInstance, field: "id")}</td>
        
    </tr>
    
    <tr>
        <td class="show-label">
            <g:message code="pais.nome.label" default="Nome"/>
        </td>

        
        <td class="show-value">${fieldValue(bean: paisInstance, field: "nome")}</td>
        
    </tr>
    
    </tbody>
</table>

</body>
</html>
