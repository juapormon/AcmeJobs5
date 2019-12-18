<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.message-thread.form.label.title" path="title" />
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="authenticated.message-thread.form.label.moment" path="moment" />
		<acme:form-textbox code="authenticated.message-thread.form.label.creator" path="creator.userAccount.username" />
	</jstl:if>

	<acme:form-submit test="${command == 'create'}" code="authenticated.message-thread.form.button.create"
		action="create" />
	<acme:form-submit test="${command == 'show'}" code="authenticated.message-thread.form.button.add-message"
		action="/authenticated/message/create?threadId=${id}" method="get" />
	<acme:form-submit test="${command == 'show' && requestScope['creator.id'] == principal.getActiveRoleId()}" code="authenticated.message-thread.form.button.add-participant" action="/authenticated/participant/create?threadId=${id}"
		method="get" />
	<acme:form-submit test="${command == 'show'}" code="authenticated.message-thread.form.button.list-participants" action="/authenticated/participant/list?id=${id}"
		method="get" />
	<acme:form-submit test="${command == 'show'}" code="authenticated.message-thread.form.button.list-messages" action="/authenticated/message/list?id=${id}"
		method="get" />
	<acme:form-return code="authenticated.message-thread.form.button.return" />
</acme:form>