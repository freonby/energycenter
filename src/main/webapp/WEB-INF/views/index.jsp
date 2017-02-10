<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html lang="ru">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="">
<title>ENERGYCENTER PANEL</title>
<link rel="stylesheet"
	href="resources/css/kendo.common-material.min.css" />
<link rel="stylesheet" href="resources/css/kendo.metro.min.css" />
<link rel="stylesheet" href="resources/css/kendo.metro.mobile.min.css" />
<!-- Bootstrap core CSS -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="resources/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="resources/css/site.css" rel="stylesheet">
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/kendo.all.min.js"></script>
<script src="resources/js/main.js"></script>
<script src="resources/js/console.js"></script>
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="resources/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="resources/js/ie-emulation-modes-warning.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
				<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
				<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
				<![endif]-->
</head>
<body>
	<div class="container-fluid">
		<!--open container-->
		<div class="row row-offcanvas row-offcanvas-left">
			<div id="main-section" class="col-xs-12 column">
				<div id="main-section-header" class="row">
					<h2 id="team-efficiency" class="col-xs-3">ENERGYCENTER</h2>



					<div id="dateFilter" class="col-xs-9">
						<div class="period-wrapper">
							<button id="undo" class="k-button k-primary">Опрос</button>
							<label for="StartDate" class="select-period">Stats from</label> <input
								id="start-date" /> <span class="k-invalid-msg"
								data-for="StartDate"></span>
						</div>
						<div class="period-wrapper">
							<label for="EndDate" class="select-period">To</label> <input
								id="end-date" /> <span class="k-invalid-msg" data-for="EndDate"></span>
						</div>
					</div>
					<div style="clear: both;"></div>
				</div>
				<div class="main-section-content row" style="">
					<div id="employee-list" class="col col-xs-2">
						<h3>ПАНЕЛЬ УПРАВЛЕНИЯ</h3>
						<!--TREE container-->
						<ul id="treeview">

							<li data-expanded="true"><span class="k-icon k-i-folder"></span>${customer.companyName}
								<ul>
									<c:forEach var="stationItem" items="${customer.listStations}">
										<li data-expanded="true"><span class="k-icon k-i-folder"></span>${stationItem.name}
											<ul>
												<c:forEach var="electricalbusItem"
													items="${stationItem.listElectricalBusses}">
													<li data-expanded="true"><span
														class="k-icon k-i-folder"></span>${electricalbusItem.voltage}
														<ul>
															<c:forEach var="sectionbusItem"
																items="${electricalbusItem.listSections}">
																<li data-expanded="true"><span
																	class="k-icon k-i-folder"></span>${sectionbusItem.name}
																	<ul>
																		<c:forEach var="fiderItem"
																			items="${sectionbusItem.listFiders}">
																			<li data-expanded="true"><span
																				class="k-icon k-i-folder"></span>${fiderItem.name}
																				<ul>
																					<li><span class="k-icon k-i-calculator"></span>${fiderItem.energyMeter.energymeterType}</li>
																				</ul>
																		</c:forEach>

																	</ul>
															</c:forEach>
														</ul>
												</c:forEach>
											</ul></li>
									</c:forEach>
								</ul></li>
						</ul>
						<!--END TREE container-->
					</div>
					<div id="employee-details-wrapper" class="col col-xs-10">
						<div id="employee-details" class="row">
							<div id="employee-about" class="col-xs-12 placeholder">
								<div class="row">
									<div
										class="device-details col-xs-12 col-sm-4 col-lg-3 col-md-3">
										<p>
											<strong>Дата:</strong> ${deviceTime}
										</p>
										<p>
											<strong>Устройство:</strong> ${deviceType}
										</p>
										<p>
											<strong>Заводской номер:</strong> ${deviceNumber}
										</p>
										<p>
											<strong>Фидер:</strong> ${fider}
										</p>

									</div>

									<div class="col-xs-12 col-sm-4 col-lg-3 col-md-4">
										<ul id="select-energy">
											<li>A +</li>
											<li>A -</li>
											<li>R +</li>
											<li>R -</li>
										</ul>
									</div>
									<div class="col-xs-12 col-sm-6 col-lg-3 col-md-4">
										<ul id="select-interval">
											<li>30 мин</li>
											<li>60 мин</li>
										</ul>
									</div>
									<div class="col-xs-12 col-sm-6 col-lg-3 col-md-4">
										<ul id="select-chart">
											<li>линия</li>
											<li>гистограмма</li>
											<li>площадь</li>
										</ul>
									</div>
									<div class="col-xs-12 col-sm-12  box-graph box-border">
										<div class="chart-wrapper">
											<div id="chart"></div>
										</div>

									</div>
									<div class="col-xs-12 col-sm-12 col-lg-6 col-md-6">
										<div class="chart-wrapper box-graph">
											<div id="grid"></div>
										</div>
									</div>
									<div class="col-xs-12 col-sm-12 col-lg-6 col-md-6">

										<div class="box-console">
											<h3>Консоль</h3>
											<div class="console"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--close main column-->
		</div>
		<!--close row-->
	</div>
	<!--close container-->
	<!--dialog window container-->
	<div id="dialog"></div>
	<!--end dialog window container-->
	<script>
		$(document).ready(function() {

			$("#treeview").kendoTreeView();
			getTable();
			initWidgets();

		});
	</script>
	<script>
		// create ComboBox from select HTML element
		$("#size").kendoComboBox();
		$(function() {
			$("#select-chart").kendoMobileButtonGroup({
				select : function(e) {
					var index = this.current().index();
					switch (index) {
					case 0:
						kendoConsole.log("Тип графика: линия");
						lineType();
						break;
					case 1:
						kendoConsole.log("Тип графика: гистограмма");
						gystType();
						break;
					case 2:
						kendoConsole.log("Тип графика: площадь");
						areaType();
						break;
					}
				}
			});
		});
		$(function() {
			$("#select-energy").kendoMobileButtonGroup({
				select : function(e) {
					var index = this.current().index();
					switch (index) {
					case 0:
						kendoConsole.log("Энергия: А+");
						break;
					case 1:
						kendoConsole.log("Энергия: А-");
						break;
					case 2:
						kendoConsole.log("Энергия: R+");
						break;
					case 3:
						kendoConsole.log("Энергия: R-");
						break;
					}
				}
			});
		});
		$(function() {
			$("#select-interval").kendoMobileButtonGroup({
				select : function(e) {
					var index = this.current().index();
					switch (index) {
					case 0:
						kendoConsole.log("Интервал: 30 мин");
						break;
					case 1:

						$.ajax({
							url : 'adduser',
							data : ({
								param : "ЖМС"

							}),
							success : function(data) {
								kendoConsole.log("added");
							}
						});

						break;
					case 2:
						kendoConsole.log("Текущие показания");
						break;
					case 3:
						kendoConsole.log("параметры электросети");

						break;
					}
				}
			});
		});
	</script>
	<!-- Bootstrap core JavaScript
		================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="resources/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="resources/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>