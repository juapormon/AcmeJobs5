<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.jobs-chart.form.title.jobs-status" />
</h2>

<div>
	<canvas id="jobs-canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"<acme:message code='administrator.jobs-chart.form.label.job-draft' />",
					"<acme:message code='administrator.jobs-chart.form.label.job-published' />"
			],
			datasets : [
				{
					label : "<acme:message code='administrator.jobs-chart.form.label.job'/>",
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
				display : true
			}
		};

		var jobsCanvas, context;
		jobsCanvas = document.getElementById("jobs-canvas");
		context = jobsCanvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<h2>
	<acme:message code="administrator.jobs-chart.form.title.applications-status" />
</h2>

<div>
	<canvas id="applications-canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
					"<acme:message code='administrator.jobs-chart.form.label.application-pending' />",
					"<acme:message code='administrator.jobs-chart.form.label.application-accepted' />",
					"<acme:message code='administrator.jobs-chart.form.label.application-rejected' />"
			],
			datasets : [
				{
					label : "<acme:message code='administrator.jobs-chart.form.label.application'/>",
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
				display : true
			}
		};

		var jobsCanvas, context;
		applicationsCanvas = document.getElementById("applications-canvas");
		context = applicationsCanvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>