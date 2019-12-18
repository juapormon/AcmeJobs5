<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.auditor-request.form.label.username" path="userAccount.username" readonly="true" />
	<acme:form-textbox code="administrator.auditor-request.form.label.firm" path="firm" readonly="true" />
	<acme:form-textbox code="administrator.auditor-request.form.label.responsabilityStatement" path="responsabilityStatement"
		readonly="true" />
	<acme:form-select code="administrator.auditor-request.form.label.status" path="status">
		<jstl:forEach var="status" items="<%=acme.entities.auditorRequest.AuditorRequestStatus.values()%>">
			<acme:form-option code="administrator.auditor-request.form.label.status.${status.name().toLowerCase()}" value="${status.name()}"
				selected="${(requestScope['status'] == status) ? 'true' : 'false'}" />
		</jstl:forEach>
	</acme:form-select>
	
	<acme:form-submit test="${command == 'show' || command == 'update'}" code="administrator.auditor-request.form.button.update"
		action="/administrator/auditor-request/update" />
	<acme:form-return code="administrator.auditor-request.form.button.return" />
</acme:form>