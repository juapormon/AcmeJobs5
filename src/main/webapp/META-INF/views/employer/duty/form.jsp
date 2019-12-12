<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${requestScope['job.status'] == 'PUBLISHED'}">
	<acme:form-hidden path="jobId" />
	<acme:form-textbox code="employer.duty.form.label.title" path="title" />
	<acme:form-textarea code="employer.duty.form.label.description" path="description" />
	<acme:form-double code="employer.duty.form.label.weekPercentage" path="weekPercentage" />

	<acme:form-submit test="${(command == 'show' && requestScope['job.status'] == 'DRAFT') || command == 'update'}" code="employer.duty.form.button.update" action="update" />
	<acme:form-submit test="${(command == 'show' && requestScope['job.status'] == 'DRAFT') || command == 'update'}" code="employer.duty.form.button.delete" action="delete" />
	<acme:form-submit test="${command == 'create'}" code="employer.duty.form.button.create" action="create" />
	<acme:form-return code="employer.duty.form.button.return" />
</acme:form>
