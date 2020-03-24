
$(document).ready(function() {
	//获取生产故障分类图表数据
	query();

});

function query() {

    //获取生产故障分类图表数据
    $.ajax({
        url: "../kpiReport/equipSpotLastMonth.do",
        type: "GET",
        dataType: "json",
        success: function (result) {
            if (result.ret == '1') {

            	var data = result.data;
            	
                var div  = "<tr class='text-c'>" +
				                "<td>"+ data.year +"</td>" +
				                "<td>"+ data.month +"</td>" +
				                "<td>"+ data.plannedQuantity +"</td>" +
				                "<td>" + data.finishQuantity + "</td>" +
				                "<td>" + data.completionRate + "%</td>" +
				           "</tr>";
            $("#equipSpotReportList").append(div);

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
