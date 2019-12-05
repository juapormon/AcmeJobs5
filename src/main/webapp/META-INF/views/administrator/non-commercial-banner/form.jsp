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
	<acme:form-url code="administrator.non-commercial-banner.form.label.picture" path="picture"/>
	<acme:form-textarea code="administrator.non-commercial-banner.form.label.slogan" path="slogan"/>
	<acme:form-url code="administrator.non-commercial-banner.form.label.targetURL" path="targetURL"/>
	<acme:form-url code="administrator.non-commercial-banner.form.label.jingle" path="jingle"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.non-commercial-banner.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.non-commercial-banner.form.button.delete" action="delete"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.non-commercial-banner.form.button.update" action="update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.non-commercial-banner.form.button.delete" action="delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.non-commercial-banner.form.button.submit" action="create"/>
	<acme:form-return code="administrator.commercial-banner.form.button.return"/>
</acme:form>