var taskId = 0;
$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    var beginTime = Request['startTime'];
    taskId = Request['taskId'];
    $("#beginTime").val(beginTime);

    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                if(result.data.userType==2){
                    document.getElementById("classSearch").style.display="";
                }
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
    
    //如果是从点检一览页面进入的根据点检任务查询对应的记录
    if(taskId != 0 && taskId != null && taskId != undefined && taskId != "undefined"  && taskId != ""){
    	$("#lb").show();
    	$("#rl").hide();
    	queryByTaskId();

    }else{
    	$("#rl").show();
    	$("#lb").hide();
        query();

    }

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
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../spotDetail/getDateDetailEquipCount.do",
        type: "POST",
        dataType: "json",
        data:{
            "assetNum":"",
            "equipNum":"",
            "equipName":"",
            "equipModel":"",
            "standard":"",
            "state":9,
            "confirmState":9,
            "classConfirmMan":"9",
            "beginTime":$("#beginTime").val()
        },
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
        InitTable(index);
    }
}
function InitTable(pageIndex) {
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../spotDetail/getDateDetailEquip.do",
        type: "POST",
        dataType: "json",
        data:{
            "assetNum":"",
            "equipNum":"",
            "equipName":"",
            "equipModel":"",
            "standard":"",
            "state":9,
            "confirmState":9,
            "classConfirmMan":"9",
            "beginTime":$("#beginTime").val(),
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#equipList").html("");
                var equipList = result.data;
                for(var i = 0; i < equipList.length; i++) {
                    var data = equipList[i];
                    var j = i+1+beginIndex;
                    var div  = "<tr class='text-c'>" +
                        "<td>" + j + "</td>" +
                        "<td>"+ getInterval(data.spotInterval) +"</td>" +
                        "<td>"+ data.beginTime +"</td>" +
                        "<td>"+ data.assetNum +"</td>" +
                        "<td>"+data.equipNum +"</td>" +
                        "<td>" + data.equipName + "</td>" +
                        "<td>" + data.equipModel + "</td>" +
                        "<td>" + data.standard + "</td>" +
                        "<td>" + getState(data.state) + "</td>" +
                        "<td>" + getconfirmState(data.confirmState) + "</td>" +
                        "<td><a class='btn btn-primary radius'"+
                        "onclick=\"taskSee('" + data.id + "','"+data.equipModel+"','"+data.equipId+"','"+data.spotInterval+"','"+data.beginTime+"')\">详情</a>"+
                        "<a class='btn btn-primary radius'"+
                        // "onclick='editSee(" + data.taskId + ","+data.beginTime+","+data.id+")'>修改</a>"+
                        "onclick=\"editSee('" + data.taskId + "','"+data.beginTime +"','"+data.id +"')\">修改</a>"+
                        "</td>"+
                        "</tr>";
                    $("#equipList").append(div);
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
// 点击检索
function search() {
    var assetNum = $("#assetNum").val(),
        equipNum = $("#equipNum").val(),
        equipName = $("#equipName").val(),
        equipModel = $("#equipModel").val(),
        standard = $("#standard").val();
    var state = $("#state option:selected").val();
    var confirmState = $("#confirmState option:selected").val();
    var classConfirmMan ="9";
    //班长确认状态检索
    var classConfirmState = $("#classConfirmState option:selected").val();
    if(classConfirmState =="0"){
        classConfirmMan = "";
    }else if(classConfirmState =="1"){
        classConfirmMan = "1";
    }

    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../spotDetail/getDateDetailEquipCount.do",
        type: "POST",
        dataType: "json",
        data:{
            "assetNum":assetNum,
            "equipNum":equipNum,
            "equipName":equipName,
            "equipModel":equipModel,
            "standard":standard,
            "state":Number(state),
            "confirmState":Number(confirmState),
            "beginTime":$("#beginTime").val(),
            "classConfirmMan":classConfirmMan
        },
        async: false,
        success: function (result) {
            if (result.ret == '1') {
                PageCount = result.data;
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
    InitTableNum(0, assetNum,equipNum,equipName,equipModel,standard,state,confirmState,classConfirmMan); //Load事件，初始化表格数据，页面索引为0（第一页）
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
        InitTableNum(index, assetNum,equipNum,equipName,equipModel,standard,state,confirmState,classConfirmMan);
    }
}
function InitTableNum(pageIndex, assetNum,equipNum,equipName,equipModel,standard,state,confirmState,classConfirmMan) {
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../spotDetail/getDateDetailEquip.do",
        type: "POST",
        dataType: "json",
        data:{
            "assetNum":assetNum,
            "equipNum":equipNum,
            "equipName":equipName,
            "equipModel":equipModel,
            "standard":standard,
            "state":Number(state),
            "confirmState":Number(confirmState),
            "classConfirmMan":classConfirmMan,
            "beginTime":$("#beginTime").val(),
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#equipList").html("");
                var equipList = result.data;
                for(var i = 0; i < equipList.length; i++) {
                    var data = equipList[i];
                    var j = i+1+beginIndex;
                    var div  = "<tr class='text-c'>" +
                        "<td>" + j + "</td>" +
                        "<td>"+ getInterval(data.spotInterval) +"</td>" +
                        "<td>"+ data.beginTime +"</td>" +
                        "<td>"+ data.assetNum +"</td>" +
                        "<td>"+data.equipNum +"</td>" +
                        "<td>" + data.equipName + "</td>" +
                        "<td>" + data.equipModel + "</td>" +
                        "<td>" + data.standard + "</td>" +
                        "<td>" + getState(data.state) + "</td>" +
                        "<td>" + getconfirmState(data.confirmState) + "</td>" +
                        "<td><a class='btn btn-primary radius'"+
                        "onclick=\"taskSee('" + data.id + "','"+data.equipModel+"','"+data.equipId+"','"+data.spotInterval+"','"+data.beginTime+"')\">详情</a>"+
                        "<a class='btn btn-primary radius'"+
                        "onclick=\"editSee('" + data.taskId + "','"+data.beginTime +"','"+data.id +"')\">修改</a>"+
                        "</td>"+
                        "</tr>";
                    $("#equipList").append(div);
                }
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
function getconfirmState(state) {
    if(state==0){
        return "未确认";
    }else if(state==1){
        return "系长已确认";
    }else if(state==2){
        return "科长已确认";
    }else if(state==3){
        return "现场班长已确认";
    }
}
function taskSee(id,equipModel,equipId,spotInterval,beginTime,taskId) {
    var cycle =  Number(spotInterval);
    window.location.href="spotCheckEquip.html?id="+id+"&model="+equipModel+"&cycle="+cycle+"&equipId="+equipId+"&startTime="+beginTime+"&taskId="+taskId;
}

function getInterval(interval) {
        if(interval=="1"){
            return "月检";
        }else if(interval=="3"){
            return "季度检";
        }else if(interval=="6"){
            return "半年检";
        }else if(interval=="12"){
            return "年检";
        }
}
function editSee(taskId,beginTime,deId) {


    var url = "updateBeginTime.html?taskId=" + taskId+"&beginTime="+beginTime+"&deId="+ deId;
    var wWidth = window.innerWidth*0.95;
    var wHeight = window.innerHeight*0.95;
    layer.open({
        type: 2,
        id: 'dateUpdate',
        title: '修改开始时间',
        btn: ['关闭'],
        area: [wWidth + 'px', wHeight + 'px'],
        content: url, //iframe的url，no代表不显示滚动条
        yes: function (index, layero) {
            // var frameObj = window.document.getElementById('dateUpdate').getElementsByTagName('iframe')[0];
            // var result = frameObj.contentWindow.save();
            // if(result){
            //     //window.location.reload();
            //
            // }
            layer.close(index);
        },
        cancel:function (index, layero) {
        }
    });
}


function queryByTaskId(taskId){
	var assetNum = $("#assetNum").val(),
	    equipNum = $("#equipNum").val(),
	    equipName = $("#equipName").val(),
	    equipModel = $("#equipModel").val(),
	    standard = $("#standard").val();
	var state = $("#state option:selected").val();
	var confirmState = $("#confirmState option:selected").val();
	var classConfirmMan ="9";
	//班长确认状态检索
	var classConfirmState = $("#classConfirmState option:selected").val();
	if(classConfirmState =="0"){
	    classConfirmMan = "";
	}else if(classConfirmState =="1"){
	    classConfirmMan = "1";
	}
	
	var PageCount;  //总页数，通过ajax获取
	var pageIndex = 0;     //页面索引初始值
	var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
	$.ajax({
	    url: "../spotDetail/getDetailEquipCount.do",
	    type: "POST",
	    dataType: "json",
	    data:{
	        "assetNum":assetNum,
	        "equipNum":equipNum,
	        "equipName":equipName,
	        "equipModel":equipModel,
	        "standard":standard,
	        "state":Number(state),
	        "confirmState":Number(confirmState),
	        "beginTime":$("#beginTime").val(),
	        "classConfirmMan":classConfirmMan,
	        "id":taskId
	    },
	    async: false,
	    success: function (result) {
	        if (result.ret == '1') {
	            PageCount = result.data;
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
	InitTableByTaskId(0, assetNum,equipNum,equipName,equipModel,standard,state,confirmState,classConfirmMan); //Load事件，初始化表格数据，页面索引为0（第一页）
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
		InitTableByTaskId(index, assetNum,equipNum,equipName,equipModel,standard,state,confirmState,classConfirmMan);
	}
}

function InitTableByTaskId(pageIndex, assetNum,equipNum,equipName,equipModel,standard,state,confirmState,classConfirmMan) {
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../spotDetail/getDetailEquip.do",
        type: "POST",
        dataType: "json",
        data:{
            "assetNum":assetNum,
            "equipNum":equipNum,
            "equipName":equipName,
            "equipModel":equipModel,
            "standard":standard,
            "state":Number(state),
            "confirmState":Number(confirmState),
            "classConfirmMan":classConfirmMan,
	        "id":taskId,
            "beginTime":$("#beginTime").val(),
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#equipList").html("");
                var equipList = result.data;
                for(var i = 0; i < equipList.length; i++) {
                    var data = equipList[i];
                    var j = i+1+beginIndex;
                    var div  = "<tr class='text-c'>" +
                        "<td>" + j + "</td>" +
                        "<td>"+ getInterval(data.spotInterval) +"</td>" +
                        "<td>"+ data.beginTime +"</td>" +
                        "<td>"+ data.assetNum +"</td>" +
                        "<td>"+ data.equipNum +"</td>" +
                        "<td>" + data.equipName + "</td>" +
                        "<td>" + data.equipModel + "</td>" +
                        "<td>" + data.standard + "</td>" +
                        "<td>" + getState(data.state) + "</td>" +
                        "<td>" + getconfirmState(data.confirmState) + "</td>" +
                        "<td><a class='btn btn-primary radius'"+
                        "onclick=\"taskSee('" + data.id + "','"+data.equipModel+"','"+data.equipId+"','"+data.spotInterval+"','"+data.beginTime+"','"+data.taskId+"')\">详情</a>"+
                        "<a class='btn btn-primary radius'"+
                        "onclick=\"editSee('" + data.taskId + "','"+data.beginTime +"','"+data.id +"')\">修改</a>"+
                        "</td>"+
                        "</tr>";
                    $("#equipList").append(div);
                }
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
            }
        }
    });
}