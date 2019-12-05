<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:form-textbox code="authenticated.req.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="authenticated.req.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.req.form.label.creationMoment" path="creationMoment"/>
	<acme:form-moment code="authenticated.req.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.req.form.label.text" path="text"/>
	<acme:form-money code="authenticated.req.form.label.reward" path="reward"/>
			
	<acme:form-return code="authenticated.req.form.button.return"/>
</acme:form>