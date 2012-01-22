
<%@ page import="org.kistenjunge.grails.scoreboard.Game" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'game.label', default: 'Game')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-game" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-game" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list game">
			
				<g:if test="${gameInstance?.image}">
				<li class="fieldcontain">
					<span id="image-label" class="property-label"><g:message code="game.image.label" default="Image" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.imageType}">
				<li class="fieldcontain">
					<span id="imageType-label" class="property-label"><g:message code="game.imageType.label" default="Image Type" /></span>
					
						<span class="property-value" aria-labelledby="imageType-label"><g:fieldValue bean="${gameInstance}" field="imageType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gameInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="game.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${gameInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${gameInstance?.id}" />
					<g:link class="edit" action="edit" id="${gameInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
