<%@ page import="org.kistenjunge.grails.scoreboard.Session" %>



<div class="fieldcontain ${hasErrors(bean: sessionInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="session.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" format="dd-MM-yyyy" precision="day"  value="${sessionInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: sessionInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="session.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${sessionInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sessionInstance, field: 'results', 'error')} ">
	<label for="results">
		<g:message code="session.results.label" default="Results" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${sessionInstance?.results?}" var="r">
    <li><g:link controller="result" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="result" action="create" params="['session.id': sessionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'result.label', default: 'Result')])}</g:link>
</li>
</ul>

</div>

