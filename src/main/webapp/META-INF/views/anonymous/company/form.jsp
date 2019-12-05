<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.company.form.label.name" path="incorporatedName"/>
	<acme:form-textbox code="anonymous.company.form.label.sector" path="sector"/>
	<acme:form-textbox code="anonymous.company.form.label.ceo" path="ceo"/>
	<acme:form-textarea code="anonymous.company.form.label.activities" path="activities"/>
	<acme:form-url code="anonymous.company.form.label.website" path="website"/>
	<acme:form-textbox code="anonymous.company.form.label.phone" path="phone"/>
	<acme:form-textbox code="anonymous.company.form.label.email" path="email"/>
	<acme:form-integer code="anonymous.company.form.label.stars" path="stars"/>
	
	<acme:form-return code="anonymous.company.form.button.return"/>
</acme:form>