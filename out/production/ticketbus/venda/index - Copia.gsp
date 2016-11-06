<%@ page import="ticketbus.Venda; ticketbus.TipoVenda; ticketbus.Municipio; ticketbus.Aidf" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'venda.css')}">
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'app.min.21d22e92.css')}">
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'venda.label', default: 'Venda')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-venda" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                            default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="show" action="show"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="venda" class="content scaffold-list" role="main">
    <g:form action="save">
        <label for="tipoVenda.id">Tipo de Venda:</label>
        <g:select name="status" from="${TipoVenda.values()}" value="${TipoVenda}" optionKey="key"/>
        <label for="origem.id">Origem:</label>
        <g:select name="origem" optionKey="id" noSelection="${['null': 'Selecione...']}" from="${Municipio.list()}"
                  value="${origem?.id}"/>
        <label for="destino.id">Destino:</label>
        <g:select name="destino" optionKey="id" noSelection="${['null': 'Selecione...']}" from="${Municipio.list()}"
                  value="${destino?.id}"/>
        <label for="dataViagem">Data Viagem:</label>
        <g:field type="date" name="dataViagem" value="${new Date().format('dd/mm/yyyy')}"/>
        <div class="bus-seat bus-from">
            <div class="lines two">
                <ul class="window">
                    <li hide-seat="">00</li>
                    <li class="busy">01</li>
                    <li data-seat="05-2-0">05</li>
                    <li data-seat="09-3-0">09</li>
                    <li data-seat="13-4-0">13</li>
                    <li data-seat="17-5-0">17</li>
                    <li data-seat="21-6-0">21</li>
                    <li data-seat="25-7-0">25</li>
                    <li data-seat="29-8-0">29</li>
                    <li data-seat="33-9-0">33</li>
                    <li data-seat="37-10-0">37</li>
                    <li data-seat="41-11-0">41</li>
                    <li data-seat="45-12-0">45</li>
                </ul>
                <ul>
                    <li hide-seat="">00</li>
                    <li class="busy">02</li>
                    <li data-seat="06-2-1">06</li>
                    <li data-seat="10-3-1">10</li>
                    <li data-seat="14-4-1">14</li>
                    <li data-seat="18-5-1">18</li>
                    <li data-seat="22-6-1">22</li>
                    <li data-seat="26-7-1">26</li>
                    <li data-seat="30-8-1">30</li>
                    <li data-seat="34-9-1">34</li>
                    <li data-seat="38-10-1">38</li>
                    <li data-seat="42-11-1">42</li>
                    <li data-seat="46-12-1">46</li>
                </ul>
            </div>

            <div class="lines two">
                <ul>
                    <li hide-seat="">00</li>
                    <li data-seat="04-1-3">04</li>
                    <li data-seat="08-2-3">08</li>
                    <li data-seat="12-3-3">12</li>
                    <li data-seat="16-4-3">16</li>
                    <li data-seat="20-5-3">20</li>
                    <li data-seat="24-6-3">24</li>
                    <li data-seat="28-7-3">28</li>
                    <li data-seat="32-8-3">32</li>
                    <li data-seat="36-9-3">36</li>
                    <li data-seat="40-10-3">40</li>
                    <li data-seat="44-11-3">44</li>
                    <li hide-seat="">00</li>
                </ul>
                <ul class="window">
                    <li hide-seat="">00</li>
                    <li data-seat="03-1-4">03</li>
                    <li data-seat="07-2-4">07</li>
                    <li data-seat="11-3-4">11</li>
                    <li data-seat="15-4-4">15</li>
                    <li data-seat="19-5-4">19</li>
                    <li data-seat="23-6-4">23</li>
                    <li data-seat="27-7-4">27</li>
                    <li data-seat="31-8-4">31</li>
                    <li data-seat="35-9-4">35</li>
                    <li data-seat="39-10-4">39</li>
                    <li data-seat="43-11-4">43</li>
                    <li hide-seat="">00</li>
                </ul>
            </div>
        </div>
        <g:actionSubmit value="Save"/>
    </g:form>
</div>
</body>
</html>