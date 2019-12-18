<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.application.form.label.reference" path="reference" readonly="true" />
	<acme:form-moment code="employer.application.form.label.moment" path="moment" readonly="true" />
	<acme:form-select code="employer.application.form.label.status" path="status">
		<jstl:forEach var="status" items="<%=acme.entities.jobs.ApplicationStatus.values()%>">
			<acme:form-option code="employer.application.form.label.status.${status.name().toLowerCase()}" value="${status.name()}"
				selected="${(requestScope['status'] == status) ? 'true' : 'false'}" />
		</jstl:forEach>
	</acme:form-select>
	<jstl:if test="${command == 'update' || status != 'ACCEPTED'}">
		<acme:form-textarea code="employer.application.form.label.rejectionJustification" path="rejectionJustification" />
	</jstl:if>
	<acme:form-textbox code="employer.application.form.label.statement" path="statement" readonly="true" />
	<acme:form-textarea code="employer.application.form.label.skills" path="skills" readonly="true" />
	<acme:form-textarea code="employer.application.form.label.qualifications" path="qualifications" readonly="true" />
	<acme:form-textbox code="employer.application.form.label.job.reference" path="job.reference" readonly="true" />

	<acme:form-submit test="${(command == 'show' && status == 'PENDING') || command == 'update'}"
		code="employer.job.form.button.update" action="update" />
	<acme:form-return code="employer.application.form.button.return" />
</acme:form>
