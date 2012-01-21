<%@ page import="org.kistenjunge.grails.scoreboard.Score" %>



<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'gamer', 'error')} required">
	<label for="gamer">
		<g:message code="score.gamer.label" default="Gamer" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="gamer" name="gamer.id" from="${org.kistenjunge.grails.scoreboard.Gamer.list()}" optionKey="id" required="" value="${scoreInstance?.gamer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'points', 'error')} required">
	<label for="points">
		<g:message code="score.points.label" default="Points" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="points" required="" value="${fieldValue(bean: scoreInstance, field: 'points')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: scoreInstance, field: 'result', 'error')} required">
	<label for="result">
		<g:message code="score.result.label" default="Result" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="result" name="result.id" from="${org.kistenjunge.grails.scoreboard.Result.list()}" optionKey="id" required="" value="${scoreInstance?.result?.id}" class="many-to-one"/>
</div>

