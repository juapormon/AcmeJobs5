<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.become-auditor.form.label.firm" path="firm" readonly="true"/>
	<acme:form-textbox code="administrator.become-auditor.form.label.responsabilityStatement" path="responsabilityStatement" readonly="true" />
	<acme:form-select code="administrator.become-auditor.form.label.status" path="status">
		<jstl:forEach var="status" items="${requestScope['status'].values()}">
			<acme:form-option code="administrator.become-auditor.form.label.status.${status.name().toLowerCase()}" value="${status.name()}"
				selected="${(requestScope['status'] == status) ? 'true' : 'false'}" />
		</jstl:forEach>
	</acme:form-select>
	<acme:form-submit test="${command == 'show' }" code="administrator.become-auditor.form.button.update"
		action="/administrator/become-auditor/update" />
	<acme:form-return code="administrator.become-auditor.form.button.return" />
</acme:form>