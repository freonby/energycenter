var night = "#2F2F4C";
var maximum = "#C7504F";
var day = "#C0B48B";
var chartType = "column";
var chartText = "Активная энергия, прием (A+)";
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
			text : chartText
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
$(document).ready(function() {

	createChart();

});

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
				filterable : false,
				attributes : {
					"class" : "table-cell",
					style : "font-size: 12px"
				}
			}, {
				field : "readout",
				title : "Показания",
				filterable : false,
				attributes : {
					"class" : "table-cell",
					style : "font-size: 12px"
				}
			}

			]
		});
	});

}

function initWidgets() {
	$("#start-date").kendoDatePicker({

		change : onCriteriaChange,
		culture : "ru-RU",

	})

}
function onCriteriaChange() {
	var startDate = $("#start-date").data("kendoDatePicker");
	$.ajax({
		url : 'date',
		data : ({
			startDate : startDate.value()

		}),
		success : function(data) {
			var json = JSON.parse(data);
			createChart();
			kendoConsole.log(json);

		}
	});

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
			createChart();

		}
	});
}
$(document).ready(function() {

	$("#treeview").kendoTreeView({
		select : function(e) {
			var treeview = $("#treeview").data("kendoTreeView");
			// find the node with text
			// var label = treeview.element.find(e.node);
			var parent = treeview.parent(treeview.element.find(e.node));
			meterInfo(treeview.text(parent));
			// kendoConsole.log(treeview.text(parent));
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

				lineType();
				break;
			case 1:

				gystType();
				break;
			case 2:

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
				sendParamEnergy(1);
				chartText = "Активная энергия, прием (A+)";
				createChart();

				break;
			case 1:
				sendParamEnergy(2);
				chartText = "Активная энергия, отдача (A-)";
				createChart();

				break;
			case 2:
				sendParamEnergy(3);
				chartText = "Реактивная энергия, прием (R+)";
				createChart();

				break;
			case 3:
				sendParamEnergy(4);
				chartText = "Реактивная энергия, отдача (R-)";
				createChart();

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
				sendParamEnergy(30);
				createChart();
				// kendoConsole.log("Интервал: 30 мин");
				break;
			case 1:
				sendParamEnergy(60);
				createChart();

				break;

			}
		}
	});
});

function sendParamEnergy(typeEnergy) {
	$.ajax({
		url : 'dispatch',
		data : ({
			paramEnergy : typeEnergy

		}),
		success : function(data) {

		}
	});

}
