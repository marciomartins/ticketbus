<%@ page import="ticketbus.Pais" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'pais.label', default: 'País')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>

<h1 class="page-header">
    <g:message code="default.create.label" args="[entityName]"/>
    <span class="pull-right">
        <g:link class="btn" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link>
    </span>
</h1>

<g:if test="${flash.message}">
    <div class="alert alert-info">${flash.message}</div>
</g:if>

<g:hasErrors bean="${paisInstance}">
    <div class="alert alert-error">
        <g:renderErrors bean="${paisInstance}" as="index"/>
    </div>
</g:hasErrors>

<g:form action="save" class="form-horizontal" >
<fieldset>

    <div class="control-group ${hasErrors(bean: paisInstance, field: 'nome', 'error')}">
        <label class="col-sm-2 control-label" for="nome">
            <g:message code="pais.nome.label" default="Nome"/>
        </label>

        <div class="col-sm-10">
            <g:textField class="form-control" name="nome" maxlength="15" required="" value="${paisInstance?.nome}"/>
        </div>
    </div>

    <div class="form-group"><div class="col-sm-offset-2 col-sm-10">
        <br>
        <g:submitButton name="create" class="btn btn-primary"
                        value="${message(code: 'default.button.save.label', default: 'Salvar')}"/>
        <g:link class="btn" action="index">Cancelar</g:link>
    </div></div>
</fieldset>
</g:form>

</body>
</html>
