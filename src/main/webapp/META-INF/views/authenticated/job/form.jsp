<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.job.form.label.reference" path="reference" />
	<acme:form-textbox code="authenticated.job.form.label.title" path="title" />
	<acme:form-moment code="authenticated.job.form.label.deadline" path="deadline" />
	<acme:form-money code="authenticated.job.form.label.salary" path="salary" />
	<acme:form-url code="authenticated.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-textarea code="authenticated.job.form.label.description" path="description" />

	<acme:form-submit code="authenticated.job.form.button.list-duties" action="/authenticated/duty/list?id=${id}" method="get" />
	<acme:form-submit code="authenticated.job.form.button.list-audits" action="/authenticated/audit/list?id=${id}" method="get" />
	<acme:form-return code="authenticated.job.form.button.return" />
</acme:form>
