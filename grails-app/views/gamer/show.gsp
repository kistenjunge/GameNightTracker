
<%@ page import="org.kistenjunge.grails.scoreboard.Gamer" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gamer.label', default: 'Gamer')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-gamer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-gamer" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list gamer">
			
				<g:if test="${gamerInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="gamer.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${gamerInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gamerInstance?.surname}">
				<li class="fieldcontain">
					<span id="surname-label" class="property-label"><g:message code="gamer.surname.label" default="Surname" /></span>
					
						<span class="property-value" aria-labelledby="surname-label"><g:fieldValue bean="${gamerInstance}" field="surname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gamerInstance?.gamertag}">
				<li class="fieldcontain">
					<span id="gamertag-label" class="property-label"><g:message code="gamer.gamertag.label" default="Gamertag" /></span>
					
						<span class="property-value" aria-labelledby="gamertag-label"><g:fieldValue bean="${gamerInstance}" field="gamertag"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gamerInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="gamer.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${gamerInstance}" field="password"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gamerInstance?.avatar}">
				<li class="fieldcontain">
					<span id="avatar-label" class="property-label"><g:message code="gamer.avatar.label" default="Avatar" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gamerInstance?.avatarType}">
				<li class="fieldcontain">
					<span id="avatarType-label" class="property-label"><g:message code="gamer.avatarType.label" default="Avatar Type" /></span>
					
						<span class="property-value" aria-labelledby="avatarType-label"><g:fieldValue bean="${gamerInstance}" field="avatarType"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${gamerInstance?.id}" />
					<g:link class="edit" action="edit" id="${gamerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
