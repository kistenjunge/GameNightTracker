
<%@ page import="org.kistenjunge.grails.scoreboard.Gamer" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gamer.label', default: 'Gamer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-gamer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-gamer" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="gamertag" title="${message(code: 'gamer.gamertag.label', default: 'Gamertag')}" />

						<g:sortableColumn property="name" title="${message(code: 'gamer.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="surname" title="${message(code: 'gamer.surname.label', default: 'Surname')}" />
					
						<g:sortableColumn property="avatar" title="${message(code: 'gamer.avatar.label', default: 'Avatar')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gamerInstanceList}" status="i" var="gamerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gamerInstance.id}">${fieldValue(bean: gamerInstance, field: "gamertag")}</g:link></td>
					
						<td>${fieldValue(bean: gamerInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: gamerInstance, field: "surname")}</td>
					
						<td><g:if test="${gamerInstance.avatar}">
  <img class="avatar" src="${createLink(controller:'gamer', action:'avatar_image', id:gamerInstance.ident())}" />
</g:if></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gamerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
