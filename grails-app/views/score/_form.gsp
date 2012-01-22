<%@ page import="org.kistenjunge.grails.scoreboard.Score" %>



<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'game', 'error')} required">
	<label for="game">
		<g:message code="score.game.label" default="Game" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="game" name="game.id" from="${org.kistenjunge.grails.scoreboard.Game.list()}" optionKey="id" required="" value="${scoreInstance?.game?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'gamer', 'error')} required">
	<label for="gamer">
		<g:message code="score.gamer.label" default="Gamer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="gamer" name="gamer.id" from="${org.kistenjunge.grails.scoreboard.Gamer.list()}" optionKey="id" required="" value="${scoreInstance?.gamer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'gamesNight', 'error')} required">
	<label for="gamesNight">
		<g:message code="score.gamesNight.label" default="Games Night" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="gamesNight" name="gamesNight.id" from="${org.kistenjunge.grails.scoreboard.Session.list()}" optionKey="id" required="" value="${scoreInstance?.gamesNight?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'points', 'error')} required">
	<label for="points">
		<g:message code="score.points.label" default="Points" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="points" required="" value="${fieldValue(bean: scoreInstance, field: 'points')}"/>
</div>

