<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.auditor-request.form.label.firm" path="firm" />
	<acme:form-textbox code="authenticated.auditor-request.form.label.responsabilityStatement" path="responsabilityStatement" />
	<jstl:if test="${command != 'create'}">
		<acme:form-select code="authenticated.auditor-request.form.label.status" path="status">
			<jstl:forEach var="status" items="<%=acme.entities.auditorRequest.AuditorRequestStatus.values()%>">
				<acme:form-option code="authenticated.auditor-request.form.label.status.${status.name().toLowerCase()}" value="${status.name()}"
					selected="${(requestScope['status'] == status) ? 'true' : 'false'}" />
			</jstl:forEach>
		</acme:form-select>
	</jstl:if>

	<acme:form-submit test="${command == 'create'}" code="authenticated.auditor-request.form.button.create"
		action="/authenticated/auditor-request/create" />
	<acme:form-return code="authenticated.auditor-request.form.button.return" />

</acme:form>