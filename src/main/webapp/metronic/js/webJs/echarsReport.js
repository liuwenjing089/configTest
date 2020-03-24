
$(document).ready(function() {
	//获取生产故障分类图表数据
	query();
	//获取成型故障分类图表数据
	query1();
	//获取总故障分类图表数据
	query2();
	//获取生产维修用时分类图表数据
	query3();
	//获取成型维修用时分类图表数据
	query4();
	//获取总维修用时分类图表数据
	query5();
});

function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}


function query() {

    //获取生产故障分类图表数据
    $.ajax({
        url: "../echartsReport/productionFaultClassification.do",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.ret == '1') {
            	//var bean = eval("("+result.data+")");
            	var bean = result.data;

                var myChart = echarts.init(document.getElementById('productionFaultClassification'));
                // 指定图表的配置项和数据
                var option = bean;
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            } else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            }else {
                var error = "";
                for (var i = 0; i < result.data.length; i++) {
                    error += (result.data[i].message);
                }
                alert(error);
            }
        }
    });
}

//获取成型故障分类图表数据
function query1() {

    $.ajax({
        url: "../echartsReport/formingFaultClassification.do",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.ret == '1') {
            	//var bean = eval("("+result.data+")");
            	var bean = result.data;

                var myChart = echarts.init(document.getElementById('formingFaultClassification'));
                // 指定图表的配置项和数据
                var option = bean;
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            } else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            }else {
                var error = "";
                for (var i = 0; i < result.data.length; i++) {
                    error += (result.data[i].message);
                }
                alert(error);
            }
        }
    });
}

//获取成型故障分类图表数据
function query2() {

    $.ajax({
        url: "../echartsReport/totalFaultClassification.do",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.ret == '1') {
            	//var bean = eval("("+result.data+")");
            	var bean = result.data;

                var myChart = echarts.init(document.getElementById('totalFaultClassification'));
                // 指定图表的配置项和数据
                var option = bean;
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            } else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            }else {
                var error = "";
                for (var i = 0; i < result.data.length; i++) {
                    error += (result.data[i].message);
                }
                alert(error);
            }
        }
    });
}

//获取生产维修用时分类图表数据
function query3() {

	//获取生产维修用时分类图表数据
    $.ajax({
        url: "../echartsReport/productionMaintenanceTimeClassification.do",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.ret == '1') {
            	//var bean = eval("("+result.data+")");
            	var bean = result.data;

                var myChart = echarts.init(document.getElementById('productionMaintenanceTimeClassification'));
                // 指定图表的配置项和数据
                var option = bean;
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            } else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            }else {
                var error = "";
                for (var i = 0; i < result.data.length; i++) {
                    error += (result.data[i].message);
                }
                alert(error);
            }
        }
    });
}

//获取成型维修用时分类图表数据
function query4() {

    $.ajax({
        url: "../echartsReport/formingMaintenanceTimeClassification.do",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.ret == '1') {
            	//var bean = eval("("+result.data+")");
            	var bean = result.data;

                var myChart = echarts.init(document.getElementById('formingMaintenanceTimeClassification'));
                // 指定图表的配置项和数据
                var option = bean;
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            } else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            }else {
                var error = "";
                for (var i = 0; i < result.data.length; i++) {
                    error += (result.data[i].message);
                }
                alert(error);
            }
        }
    });
}

//获取总维修用时分类图表数据
function query5() {

    $.ajax({
        url: "../echartsReport/totalMaintenanceTimeClassification.do",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.ret == '1') {
            	//var bean = eval("("+result.data+")");
            	var bean = result.data;

                var myChart = echarts.init(document.getElementById('totalMaintenanceTimeClassification'));
                // 指定图表的配置项和数据
                var option = bean;
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            } else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            }else {
                var error = "";
                for (var i = 0; i < result.data.length; i++) {
                    error += (result.data[i].message);
                }
                alert(error);
            }
        }
    });
}