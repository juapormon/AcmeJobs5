<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="sponsor.credit-card.form.label.creditCardNumber" path="creditCardNumber" />
	<acme:form-integer code="sponsor.credit-card.form.label.creditCardCvv" path="creditCardCvv" />
	<acme:form-integer code="sponsor.credit-card.form.label.creditCardMonth" path="creditCardMonth" />
	<acme:form-integer code="sponsor.credit-card.form.label.creditCardYear" path="creditCardYear" />

	<acme:form-submit test="${command == 'create'}" code="sponsor.credit-card.form.button.create" action="create" />
	<acme:form-submit test="${command != 'create'}" code="sponsor.credit-card.form.button.update" action="update" />
	<acme:form-submit test="${command != 'create'}" code="sponsor.credit-card.form.button.delete" action="delete" />
	<acme:form-return code="sponsor.credit-card.form.button.return" />
</acme:form>