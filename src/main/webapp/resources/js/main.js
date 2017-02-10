var night = "#2F2F4C";
var maximum = "#C7504F";
var day = "#C0B48B";
var chartType = "column";
var jsonarray = "";

function createChart() {
	getTable();
	$("#chart").kendoChart({
		dataSource : {
			transport : {
				read : {
					url : "gson",
					dataType : "json"
				}
			},
		},
		title : {
			align : "left",
			text : "Активная энергия, A+"
		},
		legend : {
			visible : false
		},
		seriesDefaults : {
			type : chartType,
			labels : {
				visible : false,
				background : "transparent"
			}
		},
		series : [ {
			type : chartType,
			pannable : true,
			field : "value",
			colorField : "color"
		} ],
		valueAxis : {
			max : 160,
			majorGridLines : {
				visible : true
			},
			crosshair : {
				color : day,
				width : 1,
				dashType : "dot",
				visible : true
			},
			labels : {
				format : "{0} кВт"
			},
			visible : true
		},
		categoryAxis : {

			field : "interval",
			labels : {
				rotation : "auto",
				step : 1
			},
			majorGridLines : {
				visible : true
			},
			crosshair : {
				color : day,
				width : 1,
				dashType : "dot",
				visible : true
			},
			line : {
				visible : true
			}
		},
		tooltip : {
			visible : true,
			format : "{0}%",
			template : "#= value #"
		}
	});
}
$(document).ready(createChart);

$(document).bind("kendo:skinChange", createChart);
$(window).on("resize", function() {
	kendo.resize($(".chart-wrapper"));
});

function gystType() {

	chartType = "column";
	$("#gystChart").removeClass();
	$("#gystChart").addClass("k-button k-primary");
	$("#lineChart").removeClass();
	$("#lineChart").addClass("k-button");
	$("#areaChart").removeClass();
	$("#areaChart").addClass("k-button");
	createChart();

};

function lineType() {

	chartType = "line";
	$("#gystChart").removeClass();
	$("#gystChart").addClass("k-button");
	$("#lineChart").removeClass();
	$("#lineChart").addClass("k-button k-primary");
	$("#areaChart").removeClass();
	$("#areaChart").addClass("k-button");
	createChart();

};

function areaType() {

	chartType = "area";
	$("#gystChart").removeClass();
	$("#gystChart").addClass("k-button");
	$("#lineChart").removeClass();
	$("#lineChart").addClass("k-button");
	$("#areaChart").removeClass();
	$("#areaChart").addClass("k-button k-primary");
	createChart();

};

$(document).ready(function() {
	var dialog = $('#dialog'), undo = $("#undo");

	undo.click(function() {
		dialog.data("kendoDialog").open();
		undo.fadeOut();
	});

	function onClose() {
		undo.fadeIn();
	}

	dialog.kendoDialog({
		animation : {
			open : {
				effects : "fade:in",
				duration : 1000
			},

		},
		width : "400px",
		visible : false,
		title : "Опрос устройства",
		closable : false,
		modal : false,
		content : "<p>Вы желаете опросить устройство?<p>",
		actions : [ {
			text : 'да'
		}, {
			text : 'отмена',
			primary : true
		} ],
		close : onClose
	});
});
function getTable() {
	var src = new kendo.data.DataSource({
		transport : {
			read : {
				url : "gson",
				dataType : "json"
			}
		},
		pageSize : 20
	});
	$(document).ready(function() {
		$("#grid").kendoGrid({
			dataSource : src,
			height : 550,
			filterable : true,
			sortable : true,
			pageable : true,
			columns : [ {
				field : "interval",
				title : "Интервал",
				filterable : false,
				attributes : {
					"class" : "table-cell",
					style : "font-size: 12px"
				}
			},

			{
				field : "value",
				title : "Энергия",
				filterable : true,
				attributes : {
					"class" : "table-cell",
					style : "font-size: 12px"
				}
			}, "Показания", "Потери"

			]
		});
	});

}
function initWidgets() {
	$("#start-date").kendoDatePicker({
		value : new Date(2017, 0, 1),
		change : onCriteriaChange
	})

	$("#end-date").kendoDatePicker({
		value : new Date(2017, 0, 1),
		change : onCriteriaChange
	})
}
function onCriteriaChange() {
	var startDate = $("#start-date").data("kendoDatePicker"), endDate = $(
			"#end-date").data("kendoDatePicker"), filter = {
		startDate : kendo.format("{0:MM/dd/yyyy hh:mm:ss}", startDate.value()),
		endDate : kendo.format("{0:MM/dd/yyyy hh:mm:ss}", endDate.value())
	}
}

function meterInfo(label) {

	$.ajax({
		url : 'info',
		data : ({
			param : label

		}),
		success : function(data) {
			var json = JSON.parse(data);
			$("#deviceType").html(json[2]);
			$("#number").html(json[1]);
			$("#fiderName").html(json[0]);
			$("#iTransformer").html(json[3] + " [KI= " + json[4] + "]");
			$("#uTransformer").html(json[5] + " [KU= " + json[6] + "]");

		}
	});
}
$(document).ready(function() {

	$("#treeview").kendoTreeView({
		select : function(e) {
			var treeview = $("#treeview").data("kendoTreeView");
			// find the node with text
			// var label = treeview.element.find(e.parent);
			var parent = treeview.parent(treeview.element.find(e.node));
			meterInfo(treeview.text(parent));
			// kendoConsole.log(treeview.text(label));
		}
	}

	);
	getTable();
	initWidgets();

});
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
