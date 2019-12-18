<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="sponsor.non-commercial-banner.list.label.targetURL" path="targetURL" width="50%" />
	<acme:list-column code="sponsor.non-commercial-banner.list.label.slogan" path="slogan" width="50%" />
</acme:list>