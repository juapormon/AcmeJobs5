<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="worker.application.form.label.reference" path="reference" />
	<acme:form-moment code="worker.application.form.label.moment" path="moment" />
	<acme:form-select code="worker.application.form.label.status" path="status">
		<jstl:forEach var="status" items="${requestScope['status'].values()}">
			<acme:form-option code="worker.application.form.label.status.${status.name().toLowerCase()}" value="${status.name()}"
				selected="${(requestScope['status'] == status) ? 'true' : 'false'}" />
		</jstl:forEach>
	</acme:form-select>
	<acme:form-textbox code="worker.application.form.label.statement" path="statement" />
	<acme:form-textarea code="worker.application.form.label.skills" path="skills" />
	<acme:form-textarea code="worker.application.form.label.qualifications" path="qualifications" />
	<acme:form-textbox code="worker.application.form.label.job.reference" path="job.reference" />

	<acme:form-return code="worker.application.form.button.return" />
</acme:form>
