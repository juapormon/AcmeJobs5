<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${status=='PUBLISHED'}">
	<acme:form-textbox code="auditor.audit.form.label.title" path="title"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:form-hidden path="job.id"/>
	</jstl:if>

	
	<jstl:if test="${command != 'create'}">
	<acme:form-select code="auditor.audit.form.label.status" path="status">
        <acme:form-option code="auditor.audit.form.label.status.draft" value="DRAFT" selected="${status == 'DRAFT'}"/>
        <acme:form-option code="auditor.audit.form.label.status.published" value="PUBLISHED" selected="${status == 'PUBLISHED'}"/>
    </acme:form-select>
	<acme:form-moment readonly="true" code="auditor.audit.form.label.creationMoment" path="creationMoment"/>
	</jstl:if>
	
	<acme:form-textarea code="auditor.audit.form.label.body" path="body"/>
	
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox readonly="true" code="auditor.audit.form.label.auditor" path="auditor.userAccount.username"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'show' and status=='DRAFT'}" code="administrator.announcement.form.button.update"
	action="update"/>
	
	<jstl:if test="${id==0}">
		<acme:form-submit test="${command == 'create'}" code="auditor.audit.form.button.create" action="create?id=${job.id}"/>
	</jstl:if>
	
	<jstl:if test="${id!=0}">
		<acme:form-submit test="${command == 'create'}" code="auditor.audit.form.button.create" action="create?id=${id}"/>
	</jstl:if>
	
	<acme:form-submit test="${command=='update' and status=='DRAFT'}" code="auditor.auditRecord.form.button.update" action="update"/>
	<acme:form-return code="auditor.audit.form.button.return"/>
</acme:form>