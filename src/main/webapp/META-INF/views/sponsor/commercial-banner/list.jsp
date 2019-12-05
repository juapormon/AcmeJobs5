<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="sponsor.commercial-banner.label.picture" path="picture" width="25%"/>
	<acme:list-column code="sponsor.commercial-banner.label.slogan" path="slogan" width="25%"/>	

</acme:list>