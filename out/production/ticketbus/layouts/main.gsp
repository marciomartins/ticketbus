<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>TicktBus</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <asset:stylesheet src="admin"/>
    <asset:javascript src="admin"/>

    <link rel="shortcut icon" href="${resource(dir: 'img', file: 'favicon.ico')}" type="image/x-icon">
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <g:link url="" class="navbar-brand">TikectBus</g:link>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown
                        <g:if test="${['webSiteAdmin', 'tutorialAdmin', 'testimonialAdmin', 'wikiPage'].contains(controllerName)}"> active</g:if>
                ">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cadastros <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><g:link controller="agencia" action="create">Agência</g:link></li>
                        <li><g:link controller="aidf" action="create">AIDF</g:link></li>
                        <li><g:link controller="estado" action="create">Estado</g:link></li>
                        <li><g:link controller="testimonialAdmin" action="create">Testimonials</g:link></li>
                        <li><g:link controller="municipio" action="create">Município</g:link></li>
                        <li><g:link controller="pais" action="create">País</g:link></li>
                    </ul>
                </li>

                <li class="dropdown
                        <g:if test="${['pluginAdmin', 'pluginPendingApproval'].contains(controllerName)}"> active</g:if>
                ">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Movimentação <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><g:link controller="movimentacaoEstoque" action="index">Estoque</g:link></li>
                        <li><g:link controller="venda" action="create">Venda de Bilhetes</g:link></li>
                    </ul>
                </li>

                <li class="dropdown
                        <g:if test="${['webSiteAdmin', 'tutorialAdmin', 'testimonialAdmin', 'wikiPage'].contains(controllerName)}"> active</g:if>
                ">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Lista <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><g:link controller="agencia" action="index">Agência</g:link></li>
                        <li><g:link controller="aidf" action="index">AIDF</g:link></li>
                        <li><g:link controller="estado" action="index">Estado</g:link></li>
                        <li><g:link controller="testimonialAdmin" action="index">Testimonials</g:link></li>
                        <li><g:link controller="municipio" action="index">Município</g:link></li>
                        <li><g:link controller="pais" action="index">País</g:link></li>
                    </ul>
                </li>

            </ul>

            <p class="nav navbar-text navbar-right">
                Logged in as ${user?.login} | <g:link controller="user" action="logout">logout</g:link>
            </p>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">
    <g:layoutBody/>
</div>

<asset:deferredScripts/>

<script type="text/javascript">
    $(function() {
        $('.dropdown-toggle').dropdown();
    });
</script>

</body>
</html>
