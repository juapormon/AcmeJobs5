<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.become-auditor.form.label.firm" path="firm" />
	<acme:form-textbox code="authenticated.become-auditor.form.label.responsabilityStatement" path="responsabilityStatement" />
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.become-auditor.form.button.create" action="/authenticated/become-auditor/create"/>
	<acme:form-return code="authenticated.become-auditor.form.button.return"/>

</acme:form>