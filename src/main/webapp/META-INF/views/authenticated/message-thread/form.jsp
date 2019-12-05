<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.message-thread.form.label.title" path="title" />
	<acme:form-moment code="authenticated.message-thread.form.label.moment" path="moment" />
	<acme:form-textbox code="authenticated.message-thread.form.label.creator" path="creator.userAccount.username" />

	<acme:form-submit code="authenticated.message-thread.form.button.list-messages" action="/authenticated/message/list?id=${id}" method="get" />
	<acme:form-return code="authenticated.message-thread.form.button.return" />
</acme:form>