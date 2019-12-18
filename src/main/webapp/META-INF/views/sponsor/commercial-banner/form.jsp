<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-url code="sponsor.commercial-banner.form.label.picture" path="picture" />
	<acme:form-textbox code="sponsor.commercial-banner.form.label.slogan" path="slogan" />
	<acme:form-url code="sponsor.commercial-banner.form.label.targetURL" path="targetURL" />
	<acme:form-textbox code="sponsor.commercial-banner.form.label.creditCardNumber" path="creditCardNumber" />
	<acme:form-integer code="sponsor.commercial-banner.form.label.creditCardCvv" path="creditCardCvv" />
	<acme:form-integer code="sponsor.commercial-banner.form.label.creditCardMonth" path="creditCardMonth" />
	<acme:form-integer code="sponsor.commercial-banner.form.label.creditCardYear" path="creditCardYear" />

	<acme:form-submit test="${command == 'create'}" code="sponsor.commercial-banner.form.button.create" action="create" />
	<acme:form-submit test="${command != 'create'}" code="sponsor.commercial-banner.form.button.update" action="update" />
	<acme:form-submit test="${command != 'create'}" code="sponsor.commercial-banner.form.button.delete" action="delete" />
  	<acme:form-return code="sponsor.commercial-banner.form.button.return"/>
</acme:form>