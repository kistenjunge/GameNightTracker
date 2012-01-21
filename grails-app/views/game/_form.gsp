<%@ page import="org.kistenjunge.grails.scoreboard.Game" %>



<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'image', 'error')} ">
	<label for="image">
		<g:message code="game.image.label" default="Image" />
		
	</label>
	<input type="file" id="image" name="image" />
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'imageType', 'error')} ">
	<label for="imageType">
		<g:message code="game.imageType.label" default="Image Type" />
		
	</label>
	<g:textField name="imageType" value="${gameInstance?.imageType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="game.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${gameInstance?.title}"/>
</div>

