var userPower = 9;

$(document).ready(function() {
    //初始值
    var type = 9;
    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                type = result.data.userType;
                $("#userType").val(result.data.userType);
                $("#loginuser").val(result.data.username);
                
                if(result.data.power == 0){
                	$("#type").show();
                }else{
                	$("#type").hide();
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
    query(type);
});

function query(type) {
    //给工作者下拉框赋值
    $.ajax({
        url: "../user/getBaoquan.do",
        type: "POST",
        dataType: "json",
        data: {},
        async:false,
        success: function (result) {
            if (result.ret == '1') {
                var list = result.data;
                for (var i = 0; i < list.length; i++) {
                    // 为Select下选框赋值
                    //先创建好select里面的option元素
                    var option = document.createElement("option");
                    //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
                    $(option).val(list[i].username);
                    //给option的text赋值,这就是你点开下拉框能够看到的东西
                    $(option).text(list[i].username);
                    //获取select 下拉框对象,并将option添加进select
                    $('#worker').append(option);
                }
                //去重
                $('#worker').each(function (i, n) {
                    var options = "";
                    $(n).find("option").each(function (j, m) {
                        if (options.indexOf($(m)[0].outerHTML) == -1) {
                            options += $(m)[0].outerHTML;
                        }
                    });
                    $(n).html(options);
                });
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
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../repair/getRepairCount.do",
        type: "POST",
        dataType: "json",
        data: {
            "type": 9,
            "state": 9,
            "beginTime": "",
            "endTime": "",
            "createUser": "",
            "workMan": null
        },
        async:false,
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
    InitTableElse(0); //Load事件，初始化表格数据，页面索引为0（第一页）
    //分页，PageCount是总条目数，这是必选参数，其它参数都是可选
    $("#Pagination").pagination(PageCount, {
        callback: PageCallback, //PageCallback() 为翻页调用次函数。
        prev_text: "« 上一页",
        next_text: "下一页 »",
        items_per_page: pageSize,
        num_edge_entries: 2, //两侧首尾分页条目数
        num_display_entries: 6, //连续分页主体部分分页条目数
        current_page: pageIndex //当前页索引
    });
    //翻页调用
    function PageCallback(index, jq) {
        InitTableElse(index);
    }
}
//其他角色
function InitTableElse(pageIndex) {
    var type= $("#userType").val();
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../repair/getRepair.do",
        type: "POST",
        dataType: "json",
        data:{
            "type":9,
            "state":9,
            "beginTime":"",
            "endTime":"",
            "createUser":"",
            "workMan":"",
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#stateList").html("");
                var equipList = result.data;
                for(var i = 0; i < equipList.length; i++) {
                    var data = equipList[i];
                    var j = i+1+beginIndex;
                    var div  = "<tr class='text-c'>" +
                        "<td>" + j + "</td>" +
                	    "<td>" + data.repairNumber + "</td>" +
                        "<td>"+ getType(data.type) +"</td>" +
                        "<td>"+ getState(data.state, data.type) +"</td>" +
                        "<td title='"+checkNull(data.applicant)+"'>" + checkNull(data.applicantUserName) + "</td>" +
                        "<td>" + data.reportRepairTime + "</td>" +
                        "<td title='"+checkNull(data.workMan)+"'>" + checkNull(data.userName) + "</td>" +
                        "<td>" + checkNull(data.vehicleType) + "</td>" +
                        "<td>" + checkNull(data.equipName) + "</td>" +
                        "<td>" + checkNull(data.lineName) + "</td>" +
                        "<td>" + checkNull(data.appearance) + "</td>" +
                        "<td>"+
                        appState(data.state,data.id,data.type,data.detailId,data.workMan,type)+
                        "</td>"+
                        "</tr>";
                    $("#stateList").append(div);
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
}

function appState(state,id,type,detailId,workMan,userType) {
    //班长
    if(userType==2){
        if(state==3){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail("+type + ','+detailId + ','+ id + ','+ userType +','+ state +")'>详情</a>";
        }else{
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail("+type + ','+detailId + ','+ id + ','+ userType +','+ state +")'>详情</a>";
        }
        
    }else if(userType==7){     
        if(state==4){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail("+type + ','+detailId + ','+ id + ','+ userType +','+ state +")'>详情</a>";
        }else{
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail("+type + ','+detailId + ','+ id + ','+ userType +','+ state +")'>详情</a>";
        }
    //保全admin
    }else if(userType==3){
        //未维修显示指派和接单
        var user = $("#loginuser").val();
        if(state==1 && workMan==null){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='receipt("+id+","+type+","+detailId+")'>接单</a>" +
                    "<a class='btn btn-danger radius' style='margin-left:10px;' onclick='assign("+id+")'>指派</a>";
        }else if(state==2 && type==1){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='tianxie("+id+","+type+","+detailId+")'>填写</a>";
        }else if(state==2 && type==2){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='tianxie("+id+","+type+","+detailId+")'>填写</a>"+
                "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='shimo("+id+")'>试模</a>";
        }else if(state==3 ||state==4 ||state==5 ){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='buquan("+id+","+type+","+detailId+")'>补全</a>";
        }else{
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail("+type + ','+detailId + ','+ id + ','+ userType +','+ state +")'>详情</a>";
        }
    //保全
    }else if(userType==4){
        //未维修显示接单 修理中完了 可以补全维修信息
        if(state==1){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='receipt("+id+","+type+","+detailId+")'>接单</a>";
        }else if(state==2 && type==1){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='tianxie("+id+","+type+","+detailId+")'>填写</a>";
        }else if(state==2 && type==2){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='tianxie("+id+","+type+","+detailId+")'>填写</a>"+
                "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='shimo("+id+")'>试模</a>";
        }else if(state==3 ||state==4 ||state==5 ){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='buquan("+id+","+type+","+detailId+")'>补全</a>";
        }else{
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail("+type + ','+detailId + ','+ id + ','+ userType +','+ state +")'>详情</a>" ;
        }
    //系长
    }else if(userType==5){
        if(state==4){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail("+type + ','+detailId + ','+ id + ','+ userType +','+ state +")'>详情</a>" ;
        }else{
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail("+type + ','+detailId + ','+ id + ','+ userType +','+ state +")'>详情</a>" ;
        }
    //科长
    }else if(userType==6){
        if(state==5){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail("+type + ','+detailId + ','+ id + ','+ userType +','+ state +")'>详情</a>" ;
        }else{
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail("+type + ','+detailId + ','+ id + ','+ userType +','+ state +")'>详情</a>" ;
        }
    }
}

//试模
function shimo(id) {
    window.location.href="testDie.html?id="+id;
}
//填写
function tianxie(id,type,detailId) {
    if(type == 1){
        window.location.href="reportdoing.html?id="+id;
        //摸具维修
    }else if(type == 2){
        window.location.href="mouldReport.html?id="+id;
    }
}
//补全信息
function buquan(id,type,detailId) {
    if(type == 1){
        window.location.href="reportdoingFinish.html?id="+id;
        //摸具维修
    }else if(type == 2){
    	window.location.href="mouldReportReplenish.html?id="+id;
    }
}
function getState(state, type){
	
	if(type == 1){
	    if(state==1){
	        return "未修理";
	    }else if(state==2){
	        return "修理中";
	    }else if(state==3){
	        return "修理完了";
	    }else if(state==4){
	        return "班长确认完了";
	    }else if(state==5){
	        return "系长确认完了";
	    }else if(state==6){
	        return "科长确认完了";
	    }
	}else if(type == 2){
	    if(state==1){
	        return "未修理";
	    }else if(state==2){
	        return "修理中";
	    }else if(state==3){
	        return "修理完了";
	    }else if(state==4){
	        return "成型确认完了";
	    }else if(state==5){
	        return "系长确认完了";
	    }else if(state==6){
	        return "科长确认完了";
	    }else if(state==7){
	    	return "品保确认完了";
	    }
	}

}
function getType(type) {
    if(type==1){
        return "设备";
    }else{
        return "模具";
    }
}

//指派
function assign(id) {
    window.location.href="assign.html?id="+ id;
}

//接单
function receipt(id,type,detailId) {
    var user = $("#loginuser").val();
    var data = null;
    if(type==1){
        data={
            "id":id,
            "orderTime":"orderTime",
            "workMan":user,
            "state":2
        };
    }else{
        data={
            "id":id,
            "orderTime":"orderTime",
            "beginTime":"beginTime",
            "workMan":user,
            "state":2
        };
    }
    //更新主表维修状态
    $.ajax({
        url: "../repair/update.do",
        type: "POST",
        dataType: "json",
        async:false,
        data:data,
        success: function(result) {
            if(result.ret == '1') {
                // 设备维修
                if(type == 1){
                    window.location.href="ky.html?id="+id;
                    //摸具维修
                }
                else{
                    //window.location.href="mouldky.html?id="+id+"&detailId="+detailId;
                    layer.msg("接单成功");
                    setTimeout(function(){
                        window.location.href="serviceman.html";
                    }, 2000 );
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
}

//点击详情按钮
function detail(type, detailId,id,userType, state){
	var title = "";
	var url = "";
	if(type == 1){
		title = "设备维修详情";
		url = "site_equipRepairDetail.html?id=" + id+"&userType="+userType;
	}else if(type == 2){
		title = "模具维修详情";
		url = "site_selectMouldReport.html?id=" + id+"&userType="+userType;
	}

	
	if(state == 3 && userType == 2){
        open1(title, url);
    }else if(state == 4 && userType == 7){
        open1(title, url,type);
    }else if(state == 7 && userType == 5){
        open1(title, url,type);
    }else if(state == 5 && userType == 6){
        open1(title, url);
    }else{
        open2(title, url);
    }

}
//指派
function assign(id){
    var wWidth = window.innerWidth*0.95;
    var wHeight = window.innerHeight*0.95;
    layer.open({
        type: 2,
        id: 'assign',
        title: '指派',
        btn: ['关闭'],
        area: [wWidth + 'px', wHeight + 'px'],
        content: "assign.html?id="+id, //iframe的url，no代表不显示滚动条
        yes: function (index, layero) {
            layer.close(index);
        },
        cancel:function (index, layero) {
        }
    });
}

//检索
function search() {
    var userType = $("#userType").val();
    var beginTime = $("#beginTime").val().replace("T", " "),
        endTime = $("#endTime").val().replace("T", " ");
    var type = $("#type option:selected").val();
    var state = $("#state option:selected").val();
    var workMan = $("#worker option:selected").val();
    var repairNumber = $("#repairNumber").val();
    
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../repair/getRepairCount.do",
        type: "POST",
        dataType: "json",
        data: {
            "type": Number(type),
            "state": Number(state),
            "beginTime": beginTime,
            "endTime": endTime,
            "createUser": "",
            "workMan": workMan,
            "repairNumber": repairNumber
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
    //分页，PageCount是总条目数，这是必选参数，其它参数都是可选
    $("#Pagination").pagination(PageCount, {
        callback: PageCallback, //PageCallback() 为翻页调用次函数。
        prev_text: "« 上一页",
        next_text: "下一页 »",
        items_per_page: pageSize,
        num_edge_entries: 2, //两侧首尾分页条目数
        num_display_entries: 6, //连续分页主体部分分页条目数
        current_page: pageIndex //当前页索引
    });
    InitTableSearch(0,type,state,beginTime,endTime,workMan,userType, repairNumber); //Load事件，初始化表格数据，页面索引为0（第一页）
    //翻页调用
    function PageCallback(index,jq) {
        InitTableSearch(index,type,state,beginTime,endTime,workMan,userType, repairNumber);
    }
}
function InitTableSearch(pageIndex,type,state,beginTime,endTime,workMan,userType, repairNumber) {
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../repair/getRepair.do",
        type: "POST",
        dataType: "json",
        data:{
            "type":Number(type),
            "state":Number(state),
            "beginTime":beginTime,
            "endTime":endTime,
            "createUser":"",
            "workMan":workMan,
            "repairNumber":repairNumber,
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#stateList").html("");
                var equipList = result.data;
                for(var i = 0; i < equipList.length; i++) {
                    var data = equipList[i];
                    var j = i+1+beginIndex;
                    var div  = "<tr class='text-c'>" +
                        "<td>" + j + "</td>" +
                	    "<td>" + data.repairNumber + "</td>" +
                        "<td>"+ getType(data.type) +"</td>" +
                        "<td>"+ getState(data.state, data.type) +"</td>" +
                        "<td>" + data.applicant + "</td>" +
                        "<td>" + data.reportRepairTime + "</td>" +
                        "<td>" + checkNull(data.workMan) + "</td>" +
                        "<td>" + checkNull(data.vehicleType) + "</td>" +
                        "<td>" + checkNull(data.equipName) + "</td>" +
                        "<td>" + checkNull(data.lineName) + "</td>" +
                        "<td>" + checkNull(data.appearance) + "</td>" +
                        "<td>"+
                        appState(data.state,data.id,data.type,data.detailId,data.workMan,userType)+
                        "</td>"+
                        "</tr>";
                    $("#stateList").append(div);
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

function open1(title, url){
    var wWidth = window.innerWidth*0.95;
    var wHeight = window.innerHeight*0.95;
	
    layer.open({
        type: 2,
        id: 'detailRepair',
        title: title,
        btn: ['确认完成','关闭'],
        area: [wWidth + 'px', wHeight + 'px'],
        content: url, //iframe的url，no代表不显示滚动条
        yes: function (index, layero) {
        	var frameObj = window.document.getElementById('detailRepair').getElementsByTagName('iframe')[0];
            var result = frameObj.contentWindow.allConfirm();
            if(result){
            	layer.close(index);
				setTimeout(function(){
					window.location.reload()
				}, 1000 );
            }else{
            	return;
            }
            	
        },
        cancel:function (index, layero) {
        }
    });
}

function open2(title, url){
    var wWidth = window.innerWidth*0.95;
    var wHeight = window.innerHeight*0.95;
	
    layer.open({
        type: 2,
        id: 'detailRepair',
        title: title,
        btn: ['关闭'],
        area: [wWidth + 'px', wHeight + 'px'],
        content: url, //iframe的url，no代表不显示滚动条
        yes: function (index, layero) {

        	layer.close(index);
        },
        cancel:function (index, layero) {
        }
    });
}