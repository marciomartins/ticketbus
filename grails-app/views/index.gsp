<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
<content tag="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cadastros<span class="caret"></span></a>
        <ul class="dropdown-menu">
            <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.shortName.replaceAll('Controller', '')}</g:link></li>
            </g:each>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Movimentos<span class="caret"></span></a>
        <ul class="dropdown-menu">
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Relatórios<span class="caret"></span></a>
        <ul class="dropdown-menu">
        </ul>
    </li>
</content>
<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Bem vindo ao Ticket Bus</h1>
        <p>
            O Ticket Bus é um sistema de gerenciamento de transporte rodoviário intermunicipal e interestadual.
        </p>
        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
            <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.shortName.replaceAll('Controller', '')}</g:link></li>
        </g:each>
    </section>
</div>

</body>
</html>
