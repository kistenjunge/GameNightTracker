<%@ page import="org.kistenjunge.grails.scoreboard.Gamer" %>



<div class="fieldcontain ${hasErrors(bean: gamerInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="gamer.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${gamerInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gamerInstance, field: 'surname', 'error')} required">
	<label for="surname">
		<g:message code="gamer.surname.label" default="Surname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="surname" required="" value="${gamerInstance?.surname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gamerInstance, field: 'gamertag', 'error')} required">
	<label for="gamertag">
		<g:message code="gamer.gamertag.label" default="Gamertag" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="gamertag" required="" value="${gamerInstance?.gamertag}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gamerInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="gamer.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" required="" value="${gamerInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gamerInstance, field: 'avatar', 'error')} ">
	<label for="avatar">
		<g:message code="gamer.avatar.label" default="Avatar" />
		
	</label>
	<input type="file" id="avatar" name="avatar" />
</div>

<div class="fieldcontain ${hasErrors(bean: gamerInstance, field: 'avatarType', 'error')} ">
	<label for="avatarType">
		<g:message code="gamer.avatarType.label" default="Avatar Type" />
		
	</label>
	<g:textField name="avatarType" value="${gamerInstance?.avatarType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gamerInstance, field: 'scores', 'error')} ">
	<label for="scores">
		<g:message code="gamer.scores.label" default="Scores" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${gamerInstance?.scores?}" var="s">
    <li><g:link controller="score" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="score" action="create" params="['gamer.id': gamerInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'score.label', default: 'Score')])}</g:link>
</li>
</ul>

</div>

