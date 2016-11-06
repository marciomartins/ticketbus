<!DOCTYPE html>
<html>
<head>
    <title>Venda</title>
</head>
<body>
<g:form action="save">
    <img src="${resource(dir: 'images', file: 'onibus.png')}" alt="Ônibus"/>
    <label for="tipoVenda.id"> Tipo de Venda: </label>
    <g:select id="tipoVenda" name="tipoVenda" from="${ticketbus.TipoVenda.toString()}"
                             noSelection="['':'Selecione...']" optionValue="key" required=""/>

    <g:actionSubmit value="Save" />
</g:form>
</body>
</html>
