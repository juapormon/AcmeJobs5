<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${status == 'PUBLISHED'}">
	<acme:form-hidden path="jobId" />
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="auditor.audit.form.label.creationMoment" path="creationMoment" readonly="true" />
	</jstl:if>
	<acme:form-textbox code="auditor.audit.form.label.title" path="title" />
	<acme:form-select code="auditor.audit.form.label.status" path="status">
		<jstl:forEach var="status" items="<%=acme.entities.audit.AuditStatus.values()%>">
			<acme:form-option code="auditor.audit.form.label.status.${status.name().toLowerCase()}" value="${status.name()}"
				selected="${(requestScope['status'] == status) ? 'true' : 'false'}" />
		</jstl:forEach>
	</acme:form-select>
	<acme:form-textarea code="auditor.audit.form.label.body" path="body" />

	<acme:form-submit test="${command == 'create'}" code="auditor.audit.form.button.create" action="create" />
	<acme:form-submit test="${(command == 'show' && status == 'DRAFT') || command == 'update'}" code="auditor.audit.form.button.update"
		action="update" />
	<acme:form-submit test="${command != create}" code="auditor.audit.form.button.show-job"
		action="/auditor/job/show?id=${requestScope['job.id']}" method="get" />
	<acme:form-return code="auditor.audit.form.button.return" />
</acme:form>