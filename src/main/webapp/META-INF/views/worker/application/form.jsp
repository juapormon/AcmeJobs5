<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${command != 'create'}">
	<acme:form-hidden path="jobId" />
	<acme:form-textbox code="worker.application.form.label.reference" path="reference" />
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="worker.application.form.label.moment" path="moment" readonly="true" />
		<acme:form-select code="worker.application.form.label.status" path="status">
			<jstl:forEach var="status" items="<%=acme.entities.jobs.ApplicationStatus.values()%>">
				<acme:form-option code="worker.application.form.label.status.${status.name().toLowerCase()}" value="${status.name()}"
					selected="${(requestScope['status'] == status) ? 'true' : 'false'}" />
			</jstl:forEach>
		</acme:form-select>
	</jstl:if>
	<acme:form-textbox code="worker.application.form.label.statement" path="statement" />
	<acme:form-textarea code="worker.application.form.label.skills" path="skills" />
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications" />

	<acme:form-submit test="${command != 'create'}" code="worker.application.form.button.show-job"
		action="/worker/job/show?id=${requestScope['job.id']}" method="get" />
	<acme:form-submit test="${command == 'create'}" code="worker.application.form.button.create" action="create" />
	<acme:form-return code="worker.application.form.button.return" />
</acme:form>
