<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:form-panel code="administrator.dashboard.form.label.totals">
		<acme:form-integer code="administrator.dashboard.form.label.totalAnnouncements" path="totalNumberOfAnnouncements" />
		<acme:form-integer code="administrator.dashboard.form.label.totalCompanyRecords" path="totalNumberOfCompanyRecords" />
		<acme:form-integer code="administrator.dashboard.form.label.totalInvestorRecords" path="totalNumberOfInvestorRecords" />
	</acme:form-panel>

	<acme:form-panel code="administrator.dashboard.form.label.requests">
		<acme:form-double code="administrator.dashboard.form.label.maxRewardOfActiveRequests" path="maxRewardOfActiveRequests" />
		<acme:form-double code="administrator.dashboard.form.label.minRewardOfActiveRequests" path="minRewardOfActiveRequests" />
		<acme:form-double code="administrator.dashboard.form.label.avgRewardOfActiveRequests" path="avgRewardOfActiveRequests" />
		<acme:form-double code="administrator.dashboard.form.label.standardDeviationRewardOfActiveRequests"
			path="standardDeviationRewardOfActiveRequests" />
	</acme:form-panel>

	<acme:form-panel code="administrator.dashboard.form.label.offers">
		<acme:form-double code="administrator.dashboard.form.label.maxRewardOfActiveOffers" path="maxRewardOfActiveOffers" />
		<acme:form-double code="administrator.dashboard.form.label.minRewardOfActiveOffers" path="minRewardOfActiveOffers" />
		<acme:form-double code="administrator.dashboard.form.label.avgRewardOfActiveOffers" path="avgRewardOfActiveOffers" />
		<acme:form-double code="administrator.dashboard.form.label.standardDeviationRewardOfActiveOffers"
			path="standardDeviationRewardOfActiveOffers" />
	</acme:form-panel>

	<acme:form-panel code="administrator.dashboard.form.label.jobs">
		<acme:form-double code="administrator.dashboard.form.label.avgJobsPerEmployer" path="avgJobsPerEmployer" />
		<acme:form-double code="administrator.dashboard.form.label.avgApplicationsPerEmployer" path="avgApplicationsPerEmployer" />
		<acme:form-double code="administrator.dashboard.form.label.avgApplicationsPerWorker" path="avgApplicationsPerWorker" />
	</acme:form-panel>

</acme:form>

<h2>
	<acme:message code="administrator.dashboard.form.title.totalSectors" />
</h2>

<div>
	<canvas id="companies-investors-canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
			labels : [	<jstl:forEach var="i" items="${investorsCommonSectors}">"<jstl:out value='${i[0]}'/>",</jstl:forEach>
						<jstl:forEach var="i" items="${investorSectors}">"<jstl:out value='${i[0]}'/>",</jstl:forEach>
						<jstl:forEach var="i" items="${companySectors}">"<jstl:out value='${i[0]}'/>",</jstl:forEach>
					],
			datasets : [
				{
					backgroundColor:"#AED6F1",
					label:"<acme:message code='administrator.dashboard.form.label.companies'/>",
					data :[	<jstl:forEach var="i" items="${companiesCommonSectors}"><jstl:out value='${i[1]}'/>,</jstl:forEach>
							<jstl:forEach var="i" items="${investorSectors}"><jstl:out value='0'/>,</jstl:forEach>
							<jstl:forEach var="i" items="${companySectors}"><jstl:out value='${i[1]}'/>,</jstl:forEach>
						]
				},
				{
					backgroundColor:"#D7BDE2",
					label:"<acme:message code='administrator.dashboard.form.label.investor'/>",
					data :[	<jstl:forEach var="i" items="${investorsCommonSectors}"><jstl:out value='${i[1]}'/>,</jstl:forEach>
							<jstl:forEach var="i" items="${investorSectors}"><jstl:out value='${i[1]}'/>,</jstl:forEach>
							<jstl:forEach var="i" items="${companySectors}"><jstl:out value='0'/>,</jstl:forEach>
				 	]
				}
			]
		};
		
		var options = {
			legend : {
				display: true
			}
		};
	
		var canvas, context;
		canvas=document.getElementById("companies-investors-canvas");
		context=canvas.getContext("2d");
		new Chart( context, {
			type : "bar",
			data : data,
			options : options
		});
	});
	
</script>

<h2>
	<acme:message code="administrator.dashboard.form.title.jobs-status" />
</h2>

<div>
	<canvas id="jobs-canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"<acme:message code='administrator.dashboard.form.label.job-draft' />",
					"<acme:message code='administrator.dashboard.form.label.job-published' />"
			],
			datasets : [
				{
					backgroundColor:"#F5B7B1",
					data : [ <jstl:out value='${ratioOfDraftJobs}'/>, <jstl:out value='${ratioOfPublishedJobs}'/> ]
				}
			]
		};

		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};

		var canvas, context;
		canvas = document.getElementById("jobs-canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<h2>
	<acme:message code="administrator.dashboard.form.title.applications-status" />
</h2>

<div>
	<canvas id="applications-canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"<acme:message code='administrator.dashboard.form.label.application-pending' />",
					"<acme:message code='administrator.dashboard.form.label.application-accepted' />",
					"<acme:message code='administrator.dashboard.form.label.application-rejected' />"
			],
			datasets : [
				{
					backgroundColor:"#FDEBD0",
					data : [ <jstl:out value='${ratioOfPendingApplications}'/>, <jstl:out value='${ratioOfAcceptedApplications}'/>,
						<jstl:out value='${ratioOfRejectedApplications}'/>]
				}
			]
		};

		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};

		var canvas, context;
		canvas = document.getElementById("applications-canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>