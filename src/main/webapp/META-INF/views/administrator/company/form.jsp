<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.company.form.label.name" path="name"/>
	<acme:form-textbox code="administrator.company.form.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.company.form.label.ceo" path="ceo"/>
	<acme:form-textarea code="administrator.company.form.label.activities" path="activities"/>
	<acme:form-url code="administrator.company.form.label.website" path="website"/>
	<acme:form-textbox code="administrator.company.form.label.phone" path="phone"/>
	<acme:form-textbox code="administrator.company.form.label.email" path="email"/>
	<acme:form-integer code="administrator.company.form.label.stars" path="stars" placeholder="1,2,3,4,5"/>
	<acme:form-checkbox code="administrator.company.form.label.incorporated" path="incorporated"/>
	
	<acme:form-submit test="${command == 'show' }" code="administrator.company.form.button.update" action="/administrator/company/update"/>
	<acme:form-submit test="${command == 'show' }" code="administrator.company.form.button.delete" action="/administrator/company/delete"/>
	<acme:form-submit test="${command == 'create' }" code="administrator.company.form.button.create" action="/administrator/company/create"/>
	<acme:form-submit test="${command == 'update' }" code="administrator.company.form.button.update" action="/administrator/company/update"/>
	<acme:form-submit test="${command == 'delete' }" code="administrator.company.form.button.delete" action="/administrator/company/delete"/>
	<acme:form-return code="administrator.company.form.button.return"/>
</acme:form>