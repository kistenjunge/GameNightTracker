<%@ page import="org.kistenjunge.grails.scoreboard.Result" %>



<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'game', 'error')} required">
	<label for="game">
		<g:message code="result.game.label" default="Game" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="game" name="game.id" from="${org.kistenjunge.grails.scoreboard.Game.list()}" optionKey="id" required="" value="${resultInstance?.game?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'session', 'error')} required">
	<label for="session">
		<g:message code="result.session.label" default="Session" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="session" name="session.id" from="${org.kistenjunge.grails.scoreboard.Session.list()}" optionKey="id" required="" value="${resultInstance?.session?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resultInstance, field: 'scores', 'error')} ">
	<label for="scores">
		<g:message code="result.scores.label" default="Scores" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${resultInstance?.scores?}" var="s">
    <li><g:link controller="score" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="score" action="create" params="['result.id': resultInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'score.label', default: 'Score')])}</g:link>
</li>
</ul>

</div>

