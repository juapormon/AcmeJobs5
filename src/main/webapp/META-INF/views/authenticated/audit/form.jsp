<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.audit.form.label.title" path="title" />
	<acme:form-moment code="authenticated.audit.form.label.creationMoment" path="creationMoment" />
	<acme:form-textarea code="authenticated.audit.form.label.body" path="body" />
	
	<acme:form-return code="authenticated.audit.form.button.return" />
</acme:form>
