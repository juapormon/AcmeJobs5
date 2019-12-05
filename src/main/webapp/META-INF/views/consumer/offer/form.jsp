<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="consumer.offer.form.label.ticker" path="ticker" placeholder="OABCD-12345"/>
	<acme:form-textbox code="consumer.offer.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment
			code="consumer.offer.form.label.moment" path="moment" readonly="true"/>
	</jstl:if>
	<acme:form-moment code="consumer.offer.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="consumer.offer.form.label.description" path="description"/>
	<acme:form-money code="consumer.offer.form.label.minMoney" path="minMoney"/>
	<acme:form-money code="consumer.offer.form.label.maxMoney" path="maxMoney"/>

	<acme:form-checkbox code="consumer.offer.form.label.confirm" path="confirm" />
	<acme:form-submit test="${command == 'create' }" code="consumer.offer.form.button.create" action="/consumer/offer/create"/>
	
	<acme:form-return code="consumer.offer.form.button.return"/>
</acme:form>