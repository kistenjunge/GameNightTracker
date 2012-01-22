
<%@ page import="org.kistenjunge.grails.scoreboard.Score" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'score.label', default: 'Score')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-score" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-score" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="score.game.label" default="Game" /></th>
					
						<th><g:message code="score.gamer.label" default="Gamer" /></th>
					
						<th><g:message code="score.gamesNight.label" default="Games Night" /></th>
					
						<g:sortableColumn property="points" title="${message(code: 'score.points.label', default: 'Points')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scoreInstanceList}" status="i" var="scoreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scoreInstance.id}">${fieldValue(bean: scoreInstance, field: "game")}</g:link></td>
					
						<td>${fieldValue(bean: scoreInstance, field: "gamer")}</td>
					
						<td>${fieldValue(bean: scoreInstance, field: "gamesNight")}</td>
					
						<td>${fieldValue(bean: scoreInstance, field: "points")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scoreInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
