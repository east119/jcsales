<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360浏览器优先以webkit内核解析-->
    <title>Jeecg 微云快速开发平台</title>
    <link rel="shortcut icon" href="images/favicon.ico">
    <link href="plug-in/hplus/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="plug-in/hplus/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="plug-in/hplus/css/animate.css" rel="stylesheet">
    <link href="plug-in/hplus/css/style.css?v=4.1.0" rel="stylesheet">
    <link rel="stylesheet" href="plug-in/themes/fineui/main/iconfont.css">
	<script src="plug-in/laydate/laydate.js"></script> 
    <style type="text/css">
	.gray-bg{
		background-color: #e9ecf3;
	}
	.col-sm-2 {
	    width: 10%;
		padding-left: 5px;
		padding-right: 5px;
		float: left;
	}
	.p-lg{
		padding:0px 0px 10px 0px;
	}
	.widget{
		margin-top: 0px;
	}
	.iconfont{
		font-size: 30px;
		color: white;
	}
	h2 {
        font-size: 19px;
    }
    .echart_div{
    	height:240px;width:100%;
    }
	.ibtn{
		cursor: pointer;
	}
	.flot-chart{
		height:400px;
	}
   /*  .top-navigation .wrapper.wrapper-content{padding:20px 5px !important;}
	.container {
    	 width:99% !important; margin:10px;
    	 padding-right: 1px !important;
    	 padding-left: 1px !important;
	}
	.color_red{color:#e55555;}
	.col-cs-2 {
	    width: 10%;
		padding-left: 5px;
		padding-right: 5px;
		float: left;
	}*/
	
	@media (min-width: 992px){
		.col-cs-2 {
		    width: 11.11%;
			padding-left: 5px;
			padding-right: 5px;
			float: left;
		}
	}

    </style>
</head>
 <body class="gray-bg">
	<div class="row  border-bottom white-bg dashboard-header" style="height: 100%">
	    <div class="col-sm-12">
	        <h2>
	            ERP项目维护系统
	        </h2>
	    </div>
	</div> 
<!-- 全局js -->
<script src="plug-in/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="plug-in/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<!-- 自定义js -->
<script src="plug-in/hplus/js/content.js"></script>
<script type="text/javascript" src="plug-in/echart/echarts.min.js"></script>
<script type="text/javascript" src="plug-in/jquery-plugs/i18n/jquery.i18n.properties.js"></script>
<t:base type="tools"></t:base>
<script type="text/javascript" src="plug-in/login/js/getMsgs.js"></script>
<script>
$(document).ready(function() {
	//直接嵌套显示
    laydate.render({
      elem: '#calendar'
      ,position: 'static'
      ,theme: 'molv'
    	
    });
	var chart1 = echarts.init(document.getElementById('chart1'));
	var chart2 = echarts.init(document.getElementById('chart2'));
	var chart3 = echarts.init(document.getElementById('chart3'));
	var chart4 = echarts.init(document.getElementById('chart4'));
	$.ajax({
		type : "POST",
		url : "jeecgListDemoController.do?broswerCount&reportType=pie",
		success : function(jsondata) {
			jsondata=JSON.parse(jsondata);
			var data=jsondata[0].data;
			var xAxisData=[];
			var seriesData=[];
			var picData = [];
			for(var i in data){
				xAxisData.push(data[i].name);
				seriesData.push(data[i].percentage);
				picData.push({"value":data[i].percentage,"name":data[i].name});
			}
			chart1.setOption({
				tooltip: {
					trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
			    legend: {
			        data: xAxisData
			    },
			    series : [
					        {
					            name: "用户人数",
					            type: 'pie',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data:picData,
					            itemStyle: {
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor: 'rgba(0, 0, 0, 0.5)'
					                }
					            }
					        }
					    ]
			});
		}
	});
	
	
	var option3 = {
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    toolbox: {
		        feature: {
		            restore: {},
		            saveAsImage: {}
		        }
		    },
		    series: [
		        {
		            name: '业务指标',
		            type: 'gauge',
		            detail: {formatter:'{value}%'},
		            data: [{value: 50, name: '完成率'}]
		        }
		    ]
		};

	$.ajax({
		type : "POST",
		url : "jeecgListDemoController.do?broswerCount&reportType=column",
		success : function(jsondata) {
			jsondata=JSON.parse(jsondata);
			var data=jsondata[0].data;
			var xAxisData=[];
			var seriesData=[];
			for(var i in data){
				xAxisData.push(data[i].name);
				seriesData.push(data[i].percentage);
			}
			var option3 = {
		            tooltip: {},
		            legend: {
		                data:[jsondata[0].name],
		                left:'center'
		            },
		            xAxis: {
		            	type: 'category',
		                data: xAxisData,
		                axisLabel:{
		                	interval:0,//横轴信息全部显示
		                	rotate:-30,//-10角度倾斜展示
		                }
		            },
		            yAxis: {},
		            series: [{
		                name: jsondata[0].name,
		                type: 'bar',
		                data: seriesData
		            }]
		        };
			chart3.setOption(option3);
		}
	});
		
		$.ajax({
			type : "POST",
			url : "jeecgListDemoController.do?broswerCount&reportType=line",
			success : function(jsondata) {
				jsondata=JSON.parse(jsondata);
				var data=jsondata[0].data;
				var xAxisData=[];
				var seriesData=[];
				for(var i in data){
					xAxisData.push(data[i].name);
					seriesData.push(data[i].percentage);
				}
				var option4 = {
					    color: ['#3398DB'],
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '10%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					            type : 'category',
					            data : xAxisData,
					            axisTick: {
					                alignWithLabel: true
					            },
					            axisLabel:{
				                	interval:0,//横轴信息全部显示
				                	rotate:-30,//-10角度倾斜展示
				                }
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:'用户人数',
					            type:'line',
					            barWidth: '60%',
					            data:seriesData
					        }
					    ]
					};
				chart4.setOption(option4, true);
			}
		});
		
		var colors = ['#5793f3', '#d14a61', '#675bba'];
		$.ajax({
			type : "POST",
			url : "graphReportController.do?datagridGraph",
			data:{
				configId:'yhcztj'
			},
			success : function(jsondata) {
				var data = jsondata.rows;
				var xAxisData=[];
				var seriesData=[];
				var loginData=[];
				if(data!=null){
					for(var i = data.length-1; i >= 0; i--){
						xAxisData.push(data[i].userid);	//用户名
						seriesData.push(data[i].ct);	//操作次数
						loginData.push(data[i].login_count);//登陆次数
					}
				}
				var option2 = {
			            tooltip : {
			                trigger: 'axis',
			                axisPointer : {
			                    type : 'shadow'
			                }
			            },
			            legend: {
			                data: ["操作次数",'登陆次数']
			            },
			            grid: {
			                left: '1%',
			                right: '6%',
			                bottom: '3%',
			                containLabel: true
			            },
			            xAxis:  {
			                type: 'value'
			            },
			            yAxis: {
			                type: 'category',
			                data: xAxisData
			            },
			            series: [
			                {
			                    name: '操作次数',
			                    type: 'bar',
			                    stack: '总量',
			                    label: {
			                        normal: {
			                            show: true,
			                            position: 'insideRight'
			                        }
			                    },
			                    data : seriesData
			                },
			                {
			                    name: '登陆次数',
			                    type: 'bar',
			                    stack: '总量',
			                    label: {
			                        normal: {
			                            show: true,
			                            position: 'insideRight'
			                        }
			                    },
			                    data : loginData
			                }
			            ]
			        };
				chart2.setOption(option2, true);
			}
		});
		
		
		
		$(window).resize(chart1.resize);
		$(window).resize(chart2.resize);
		$(window).resize(chart3.resize);
		$(window).resize(chart4.resize);
});
</script>
<!--统计代码，可删除-->
<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>
</html>