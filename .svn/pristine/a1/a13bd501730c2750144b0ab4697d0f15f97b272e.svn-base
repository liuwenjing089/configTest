$(document).ready(function() {

    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                var a = result.data.username;
                var b = result.data.password;
                var c = result.data.userType;
            } else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            }else {
                var error = "";
                for(var i = 0; i < result.data.length; i++) {
                    error += (result.data[i].message);
                }
                layer.alert(error);
            }
        }
    });
    query();
});

function search(){
	query();
//	var equipModel = $("#equipModel").val();
//	
//    $.ajax({
//        url: "../spotRule/selectEquipModelList.do",
//        type: "POST",
//        data:{"equipModel": equipModel},
//        success: function(result) {
//            if(result.ret == '1') {
//               if(result.data > 0){
//            	   window.location.href = encodeURI("mouldSpotRuleList.html?id="+ equipModel + "&type=1");
//               }else{
//            	   layer.msg("设备中无此型号,请先添加设备！");
//               }
//            } else {
//                var error = "";
//                for(var i = 0; i < result.data.length; i++) {
//                    error += (result.data[i].message);
//                }
//                layer.alert(error);
//            }
//        }
//    });
}

function query() {
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    
    var equipModel = $("#equipModel").val();

    $.ajax({
        url: "../spotRule/selectEquipModelCount.do",
        type: "POST",
        dataType: "json",
        data:{"equipModel": equipModel},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                PageCount=result.data;
            }else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            } else {
                PageCount = 1;
            }
        }
    });
    InitTable(0); //Load事件，初始化表格数据，页面索引为0（第一页）
    //分页，PageCount是总条目数，这是必选参数，其它参数都是可选
    $("#Pagination").pagination(PageCount, {
        callback: PageCallback, //PageCallback() 为翻页调用次函数。
        prev_text: "« 上一页",
        next_text: "下一页 »",
        items_per_page: pageSize,
        num_edge_entries: 2, //两侧首尾分页条目数
        num_display_entries: 6, //连续分页主体部分分页条目数
        current_page: pageIndex, //当前页索引
    });
    //翻页调用
    function PageCallback(index, jq) {
    	jumpIndex = index;
        InitTable(index);
    }
}
function InitTable(pageIndex) {
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    var equipModel = $("#equipModel").val();

    
    $.ajax({
        url: "../spotRule/selectEquipModelList.do",
        type: "POST",
        dataType: "json",
        data:{
        	
        	"equipModel": equipModel, 
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#mouleList").html("");
                var mouleList = result.data;
                if(mouleList != null && mouleList.length > 0){
                    for(var i = 0; i < mouleList.length; i++) {
                        var data = mouleList[i];
                        var j = i+1+beginIndex;
                        var div  = "<tr class='text-c'>" +
    			                        "<td>" + j + "</td>" +			
    			                        "<td>"+ data.equipModel +"</td>" +
    			                        "<td>"+ (data.cycleString || "") +"</td>" +
    			                        "<td><a class='btn btn-primary radius'  style='margin-left:10px;' onclick=\"editSpot('" + data.equipModel + "')\">详情</a>"+ 
    			                        	 
    			                        "</td>"+
    			                   "</tr>";
                        $("#mouleList").append(div);
                    }
                }

            }else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            } else {
                var error = "";
                for(var i = 0; i < result.data.length; i++) {
                    error += (result.data[i].message);
                }
                layer.alert(error);
            }
        }
    });
}

function editSpot(equipModel){
	//window.location.href = encodeURI("mouldSpotRuleList.html?id="+ equipModel + "&type=1");
	Hui_admin_tabs("mouldSpotRuleList.html?id="+ equipModel + "&type=1", "设备型号点检详情");
}