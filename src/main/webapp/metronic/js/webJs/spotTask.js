var jumpIndex = 0;
$(document).ready(function() {
    query();
});
function query() {
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../task/getTaskCount.do",
        type: "POST",
        dataType: "json",
        data:{
            "spotType":9,
            "state":9,
            "spotInterval":"",
            "beginTime":"",
            "endTime":""
        },
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                PageCount=result.data;
            } else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            }else {
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
    	var jumpIndex = index;
        InitTable(index);
    }
}
function InitTable(pageIndex) {
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../task/getTask.do",
        type: "POST",
        dataType: "json",
        data:{
            "spotType":9,
            "state":9,
            "spotInterval":"",
            "beginTime":"",
            "endTime":"",
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#taskList").html("");
                var equipList = result.data;
                for(var i = 0; i < equipList.length; i++) {
                    var data = equipList[i];
                    var j = i+1+beginIndex;
                    var interval = Number(data.spotInterval);
                    
                    var state = data.state;
                    var div  = "";
                    if(state == 0){
                    	div += "<tr class='text-c' style='background-color:  yellow'>";
                    }else{
                    	div += "<tr class='text-c'>";
                    }
                    
                     div += "<td><input type='checkbox' name='checkbox' value='" + data.id + "' /></td>" + 
                    	"<td>" + j + "</td>" +
                        "<td title='"+data.divName+"'>"+ getType(data.spotType) +"</td>" +
                        "<td>"+ getInterval(data.spotInterval, data.spotType) +"</td>" +
                        "<td>" + data.beginTime + "</td>" +
                        "<td>" + getState(data.state) + "</td>" +
                        "<td><a class='btn btn-primary radius' style='margin-right:10px;'"+
                        "onclick='delTask(" + data.id + ")'>删除</a>"+
                        "<a class='btn btn-primary radius' style='margin-right:10px;'"+
                        "onclick=\"taskSee('" + data.id +"','"+data.spotType +"','"+ interval+"','"+data.beginTime+"')\">详情</a>"+
                        "</td>"+
                        "</tr>";
                    $("#taskList").append(div);
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
            }
        }
    });
}

function getState(state) {
    if(state==0){
        return "未完成";
    }else{
        return "已完成";
    }
}

//模具显示图号 设备弹出div
function getType(type,divName) {
    if(type==1){
        return "设备";
    }else{
        return "模具";
    }
}


function mouseOver()
{
    document.getElementById("login-box").style.display="";
}
function mouseOut()
{
    document.getElementById("login-box").style.display="none";
}





function getInterval(interval, type) {
	if(type == 1){
	    if(interval=="1"){
	        return "月检";
	    }else if(interval=="3"){
	        return "季度检";
	    }else if(interval=="6"){
	        return "半年检";
	    }else if(interval=="12"){
	        return "年检";
	    }
	}else if(type == 2){
		return interval + "次";
	}
	

}

// 点击检索
function search(index) {
    var beginTime = $("#beginTime").val(),
        endTime = $("#endTime").val();
    var spotType = $("#spotType option:selected").val();
    var state = $("#state option:selected").val();
    var spotInterval = $("#spotInterval option:selected").val();
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = index;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../task/getTaskCount.do",
        type: "POST",
        dataType: "json",
        data: {
            "spotType":Number(spotType),
            "state":Number(state),
            "spotInterval":spotInterval,
            "beginTime":beginTime,
            "endTime":endTime
        },
        async: false,
        success: function (result) {
            if (result.ret == '1') {
                PageCount = result.data;
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
    InitTableNum(0, spotType, state,spotInterval,beginTime,endTime); //Load事件，初始化表格数据，页面索引为0（第一页）
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
        InitTableNum(index, spotType, state,spotInterval,beginTime,endTime);
    }
}
function InitTableNum(pageIndex,spotType, state,spotInterval,beginTime,endTime) {
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../task/getTask.do",
        type: "POST",
        dataType: "json",
        data:{
            "spotType":Number(spotType),
            "state":Number(state),
            "spotInterval":spotInterval,
            "beginTime":beginTime,
            "endTime":endTime,
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#taskList").html("");
                var equipList = result.data;
                for(var i = 0; i < equipList.length; i++) {
                    var data = equipList[i];
                    var j = i+1+beginIndex;
                    var interval = Number(data.spotInterval);
                    
                    var state = data.state;
                    var div  = "";
                    if(state == 0){
                    	div += "<tr class='text-c' style='background-color:  yellow'>";
                    }else{
                    	div += "<tr class='text-c'>";
                    }
                    
                    div += "<td><input type='checkbox' name='checkbox' value='" + data.id + "' /></td>" + 
                        "<td>" + j + "</td>" +
                        "<td title='"+data.divName+"'>"+ getType(data.spotType) +"</td>" +
                        "<td>"+ getInterval(data.spotInterval, data.spotType) +"</td>" +
                        "<td>" + data.beginTime + "</td>" +
                        "<td>" + getState(data.state) + "</td>" +
                        "<td><a class='btn btn-primary radius'"+
                        "onclick='delTask(" + data.id + ")' style='margin-right:10px;'>删除</a>"+
                        "<a  style='margin-right:10px;' class='btn btn-primary radius'"+
                        "onclick=\"taskSee('" + data.id +"','"+data.spotType +"','"+ interval+"','"+data.beginTime+"')\">详情</a>"+
                        "</td>"+
                        "</tr>";
                    $("#taskList").append(div);
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
            }
        }
    });
}

//详情
function taskSee(id,type,cycle,beginTime) {
    if(type==1){
        //window.location.href="dateSpotDetailEquip.html?taskId="+ id +"&startTime="+ beginTime;
    	Hui_admin_tabs("dateSpotDetailEquip.html?taskId="+ id +"&startTime="+ beginTime, "设备任务详情");
    }else{
        //window.location.href="dateSpotDetailMould.html?taskId="+ id + "&startTime="+ beginTime;
    	Hui_admin_tabs("dateSpotDetailMould.html?taskId="+ id + "&startTime="+ beginTime, "模具任务详情");

    }

}

//批量添加
function task_add(){
   // window.location.href="addTask.html";
	Hui_admin_tabs("addTask.html", "添加点检任务");
}

//批量添加
function task_batchAdd(){
   // window.location.href="addTask.html";
	Hui_admin_tabs("batchAddTask.html", "批量添加点检任务");
}

//删除
function delTask(id) {
    $.ajax({
        url: "../task/delete.do",
        type: "POST",
        dataType: "json",
        data: {
            "id": id
        },
        success: function (result) {
            if (result.ret == '1') {
                layer.msg("删除成功");
                setTimeout(function(){
                	search(jumpIndex);
                }, 1500 );
            }else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            } else {
                var error = "";
                for (var i = 0; i < result.data.length; i++) {
                    error += (result.data[i].message);
                }
                layer.alert(error);
            }
        }
    });

}

function batchDel(){
	var data = $('input:checkbox[name="checkbox"]:checked').map(function () {
        return $(this).val();
    }).get().join(",");
	
	if(data == ""){
		layer.msg("请选择任务");
		return;
	}
	
  //弹出结果
	$.ajax({
		  url: '../task/batchDelete.do',
		  type: 'POST',
	      data: {"batchDeleteId": data},
	      async:false,
		  beforeSend: function (XMLHttpRequest) {

	      },
	      complete: function (XMLHttpRequest, textStatus) {

	      },
			success: function(result) {
				if(result.ret == '1') {
					layer.msg("删除成功!");
					search(jumpIndex);
				} else if(result.ret == '3'){
					layer.msg("登录超时,请重新登录");
					setTimeout(function(){
						top.window.location.href= "login.html"
					}, 1000 );
				}else {
					var error = "";
					for(var i = 0; i < result.data.length; i++) {
						error += (result.data[i].message + "<br\>");
					}
					if(error != "") {
						layer.msg(error);
					}				
				}   
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				alert("error");
			}

		});

}