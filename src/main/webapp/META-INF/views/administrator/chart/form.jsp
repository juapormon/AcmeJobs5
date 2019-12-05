<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.chart.form.title.totalSectors" />
</h2>

<div>
	<canvas id="canvas"></canvas>
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
					backgroundColor:"rgb(0,255,255)",
					label:"<acme:message code='administrator.chart.form.label.companies'/>",
					data :[	<jstl:forEach var="i" items="${companiesCommonSectors}"><jstl:out value='${i[1]}'/>,</jstl:forEach>
							<jstl:forEach var="i" items="${investorSectors}"><jstl:out value='0'/>,</jstl:forEach>
							<jstl:forEach var="i" items="${companySectors}"><jstl:out value='${i[1]}'/>,</jstl:forEach>
						]
				},
				{
					backgroundColor:"rgb(255,0,0)",
					label:"<acme:message code='administrator.chart.form.label.investor'/>",
					data :[	<jstl:forEach var="i" items="${investorsCommonSectors}"><jstl:out value='${i[1]}'/>,</jstl:forEach>
							<jstl:forEach var="i" items="${investorSectors}"><jstl:out value='${i[1]}'/>,</jstl:forEach>
							<jstl:forEach var="i" items="${companySectors}"><jstl:out value='0'/>,</jstl:forEach>
				 	]
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
				display: true
			}
		};
	
		var canvas, context;
		canvas=document.getElementById("canvas");
		context=canvas.getContext("2d");
		new Chart( context, {
			type : "bar",
			data : data,
			options : options
		});
	});
	
</script>