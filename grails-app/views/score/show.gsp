
<%@ page import="org.kistenjunge.grails.scoreboard.Score" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'score.label', default: 'Score')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-score" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="show-score" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list score">
			
				<g:if test="${scoreInstance?.game}">
				<li class="fieldcontain">
					<span id="game-label" class="property-label"><g:message code="score.game.label" default="Game" /></span>
					
						<span class="property-value" aria-labelledby="game-label"><g:link controller="game" action="show" id="${scoreInstance?.game?.id}">${scoreInstance?.game?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scoreInstance?.gamer}">
				<li class="fieldcontain">
					<span id="gamer-label" class="property-label"><g:message code="score.gamer.label" default="Gamer" /></span>
					
						<span class="property-value" aria-labelledby="gamer-label"><g:link controller="gamer" action="show" id="${scoreInstance?.gamer?.id}">${scoreInstance?.gamer?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scoreInstance?.gamesNight}">
				<li class="fieldcontain">
					<span id="gamesNight-label" class="property-label"><g:message code="score.gamesNight.label" default="Games Night" /></span>
					
						<span class="property-value" aria-labelledby="gamesNight-label"><g:link controller="session" action="show" id="${scoreInstance?.gamesNight?.id}">${scoreInstance?.gamesNight?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${scoreInstance?.points}">
				<li class="fieldcontain">
					<span id="points-label" class="property-label"><g:message code="score.points.label" default="Points" /></span>
					
						<span class="property-value" aria-labelledby="points-label"><g:fieldValue bean="${scoreInstance}" field="points"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${scoreInstance?.id}" />
					<g:link class="edit" action="edit" id="${scoreInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
