<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.job.form.label.reference" path="reference" />
	<acme:form-select code="employer.job.form.label.status" path="status">
		<jstl:choose>
			<jstl:when test="${command == 'create'}">
				<acme:form-option code="employer.job.form.label.status.draft" value="DRAFT" selected="true" />
				<acme:form-option code="employer.job.form.label.status.published" value="PUBLISHED" />
			</jstl:when>
			<jstl:otherwise>
				<jstl:forEach var="status" items="<%=acme.entities.jobs.JobStatus.values()%>">
					<acme:form-option code="employer.job.form.label.status.${status.name().toLowerCase()}" value="${status.name()}"
						selected="${(requestScope['status'] == status) ? 'true' : 'false'}" />
				</jstl:forEach>
			</jstl:otherwise>
		</jstl:choose>
	</acme:form-select>
	<acme:form-textbox code="employer.job.form.label.title" path="title" />
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline" />
	<acme:form-money code="employer.job.form.label.salary" path="salary" />
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-textarea code="employer.job.form.label.descriptor.description" path="descriptor.description" />

	<acme:form-submit test="${command == 'show' && requestScope['status'] == 'DRAFT'}" code="employer.job.form.button.update"
		action="update" />
	<acme:form-submit test="${command == 'update'}" code="employer.job.form.button.update" action="update" />
	<acme:form-submit code="employer.job.form.button.list-audits" action="/authenticated/audit/list?id=${id}" method="get" />
	<acme:form-submit code="employer.job.form.button.list-duties"
		action="/employer/duty/list?id=${id}" method="get" />
	<acme:form-return code="employer.job.form.button.return" />
</acme:form>
