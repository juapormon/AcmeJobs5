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
	<acme:form-textbox code="authenticated.sponsor.form.label.organisationName" path="organisationName" />

	<acme:form-submit test="${command == 'create'}" code="authenticated.sponsor.form.button.create"
		action="/authenticated/sponsor/create" />
	<jstl:choose>
		<jstl:when test="${requestScope['creditCard'] == null}">
			<acme:form-submit test="${command != 'create'}" code="authenticated.sponsor.form.button.add-credit-card"
				action="/sponsor/credit-card/create" method="get" />
		</jstl:when>
		<jstl:otherwise>
			<acme:form-submit test="${command != 'create'}" code="authenticated.sponsor.form.button.edit-credit-card"
				action="/sponsor/credit-card/update?id=${requestScope['creditCard'].id}" method="get" />
		</jstl:otherwise>
	</jstl:choose>
	<acme:form-submit test="${command != 'create'}" code="authenticated.sponsor.form.button.update"
		action="/authenticated/sponsor/update" />
	<acme:form-return code="authenticated.sponsor.form.button.return" />
</acme:form>

<%--
<jstl:forEach items="${requestScope}" var="item">
	<jstl:out value="${item.key}" />
	</br>
	<jstl:out value="${item.value}" />
	</br>
	</br>
</jstl:forEach>
--%>