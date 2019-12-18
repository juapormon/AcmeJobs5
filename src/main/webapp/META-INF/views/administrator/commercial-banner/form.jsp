<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-url code="administrator.commercial-banner.form.label.picture" path="picture" />
	<acme:form-textbox code="administrator.commercial-banner.form.label.slogan" path="slogan" />
	<acme:form-url code="administrator.commercial-banner.form.label.targetURL" path="targetURL" />
	<acme:form-textbox code="administrator.commercial-banner.form.label.creditCardNumber" path="creditCardNumber" />
	<acme:form-integer code="administrator.commercial-banner.form.label.creditCardCvv" path="creditCardCvv" />
	<acme:form-integer code="administrator.commercial-banner.form.label.creditCardMonth" path="creditCardMonth" />
	<acme:form-integer code="administrator.commercial-banner.form.label.creditCardYear" path="creditCardYear" />

	<acme:form-submit test="${command == 'show'}" code="administrator.commercial-banner.form.button.update" action="update" />
	<acme:form-submit test="${command == 'show'}" code="administrator.commercial-banner.form.button.delete" action="delete" />
	<acme:form-submit test="${command == 'update'}" code="administrator.commercial-banner.form.button.update" action="update" />
	<acme:form-submit test="${command == 'delete'}" code="administrator.commercial-banner.form.button.delete" action="delete" />
	<acme:form-submit test="${command == 'create'}" code="administrator.commercial-banner.form.button.submit" action="create" />
	<acme:form-return code="administrator.commercial-banner.form.button.return" />
</acme:form>