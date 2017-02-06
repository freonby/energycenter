<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html lang="ru">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>KENDO</title>
<link rel="stylesheet"
	href="resources/css/kendo.common-material.min.css" />
<link id="material" rel="stylesheet"
	href="resources/css/kendo.metro.min.css" />
<link id="mobile" rel="stylesheet"
	href="resources/css/kendo.metro.mobile.min.css" />


<!-- Bootstrap core CSS -->

<link href="resources/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resources/css/style.css" rel="stylesheet">
<link rel="stylesheet" href="resources/css/main.css">
<!-- <link rel="stylesheet" href="resources/css/examples.css"> -->
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/kendo.all.min.js"></script>
<script src="resources/js/main.js"></script>
<script src="resources/js/console.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div id="" class="container-fluid">
		<!-- start header -->
		<header>
			<div class="navbar navbar-default navbar-fixed-top">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-collapse">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="index.html">EnergyCenter</a>
					</div>
					<div class="navbar-collapse collapse ">
						<ul class="nav navbar-nav">
							<li class="active"><a href="index.html">Главная</a></li>
							<li class="dropdown"><a href="#" data-toggle="dropdown"
								class="dropdown-toggle">Точки учета<b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="#">СС301</a></li>
									<li><a href="#">СС101</a></li>
								</ul></li>
							<li><a href="#">Настройки</a></li>
							<li><a href="#">Выход</a></li>
						</ul>
					</div>
				</div>
			</div>
		</header>
		<!-- end header -->
		<div id="dialog"></div>
		<section id="content">
			<div class="container k-content">
				<div class="row  margin_top_100">
					<div class="col-md-6 col-sm-5 col-lg-4">
						<button id="undo" class="k-button k-primary">Опрос</button>
						
						
						
					</div>
					<div class="col-md-6 col-sm-5 col-lg-4">
						<p>Интервал</p>
						<ul id="select-interval">
							<li>30 мин</li>
							<li>60 мин</li>
							<li>Показания</li>
							<li>Параметры</li>
						</ul>
						<p>Энергия</p>
						<ul id="select-energy">
							<li>A +</li>
							<li>A -</li>
							<li>R +</li>
							<li>R -</li>
						</ul>
						<p>График</p>
						<ul id="select-chart">
							<li>линия</li>
							<li>гистограмма</li>
							<li>площадь</li>
						</ul>
						
					</div>


					
				</div>


			</div>
		</section>
		<section id="content">
			<div class="container">
				<!-- Nav tabs -->
				<ul class="nav nav-pills">
					<li class="active"><a href="#graph" data-toggle="tab"
						onclick="createChart()">график</a></li>
					<li><a href="#table" data-toggle="tab" onclick="getTable()">таблица</a></li>
					<li><a href="#console" data-toggle="tab">консоль</a></li>

				</ul>
				
				<!-- Tab panes -->
				<div class="tab-content">
					<div class="tab-pane active" id="graph">
						<div class="row">
							<div class="col-md-12 chart-wrapper box-chart tab-margin wide">
								<div id="chart"></div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="table">
						<div class="row">
							<div class="col-md-12 chart-wrapper box-chart tab-margin">
								<div id="grid"></div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="console">
						<p class=" tab-margin">Лог событий:</p>
						<div class="console"></div>
					</div>

				</div>

			</div>

		</section>


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
							kendoConsole.log("Интервал: 60 мин");
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

	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    -->
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>