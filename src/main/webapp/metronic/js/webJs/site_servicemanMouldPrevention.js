var userPower = 9;
var passIndex = 0;
var userClass = 0;
var mouldId = 0;
$(document).ready(function() {

    //初始值
//    var type = 9;
    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
            	userPower = result.data.userType;
            	userClass = result.data.power;
                $("#userType").val(result.data.userType);
                $("#loginuser").val(result.data.username);
                
                if(result.data.power == 0){
                	$("#typeSpan").show();
                }else{
                	$("#typeSpan").hide();
                }

                if(result.data.power == 2){
                	$("#repairNum").hide();
                	$("#repairType").hide();
                	$("#repairEquipName").text("模具名称");
                	$("#repairLineName").text("成型机");
                	$("#repairAppearance").text("故障现象");
                	
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

    selectWorkMan();
    search(0);
});


function appState(state,id,type,detailId,workMan,userType,isKy, cssNum) {
    //班长
	if(userType==2){
        if(state==3){                                                           
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"detail('" + type + "','"+ detailId +"','"+ id +"','"+ userType +"','"+ state +"')\">详情</a>";
        }else{
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"detail('" + type + "','"+ detailId +"','"+ id +"','"+ userType +"','"+ state +"')\">详情</a>";
        }
        
    }else if(userType==7){     
        if(state==4){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"detail('" + type + "','"+ detailId +"','"+ id +"','"+ userType +"','"+ state +"')\">详情</a>";
        }else{
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"detail('" + type + "','"+ detailId +"','"+ id +"','"+ userType +"','"+ state +"')\">详情</a>";
        }
    //保全admin
    }else if(userType==3){
        //未维修显示指派和接单
        var user = $("#loginuser").val();
        if(state==1 && type ==1){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='receipt("+id+","+type+","+detailId+")'>接单</a>" +
                +  "<a class='btn btn-danger radius' style='margin-left:10px;' onclick='assign("+id+")'>指派</a>";

        }else if(state==1 && type ==2){
        	
        	return "<a class='btn btn-danger radius' style='margin-left:10px;' onclick='assign("+id+")'>指派</a>" + mouldButtonCss(id, type, detailId, cssNum);
        }else if(state==2 && type==1){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='tianxie("+id+","+type+","+detailId+","+isKy+")'>填写</a>";
        }else if(state==2 && type==2){
            return mouldButtonCss(id, type, detailId, cssNum);
                            
        }else if((state==3 ||state==4) && type == 1){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='buquan("+id+","+type+","+detailId+")'>补全</a>";
        }else if((state==3 ||state==4 || state==7) && type == 2){
        	
        	return mouldButtonCss(id, type, detailId, cssNum);

        }else{
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail("+type + ','+detailId + ','+ id + ','+ userType +','+ state +")'>详情</a>";
        }
    //保全
    }else if(userType==4){
        //未维修显示接单 修理中完了 可以补全维修信息
        if(state==1 && type ==1){
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='receipt("+id+","+type+","+detailId+")'>接单</a>"
            
        }else if(state==1 && type ==2){
        	
        	return mouldButtonCss(id, type, detailId, cssNum);
        }else if(state==2 && type==1){
        
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='tianxie("+id+","+type+","+detailId+","+isKy+")'>填写</a>";
        }else if(state==2 && type==2){
        	
            return mouldButtonCss(id, type, detailId, cssNum);
        }else if((state==3 ||state==4) && type == 1){
        	
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='buquan("+id+","+type+","+detailId+")'>补全</a>";
        }else if((state==3 ||state==4 || state==7) && type == 2){
        	
        	return mouldButtonCss(id, type, detailId, cssNum);

        }       
        
        else{
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail("+type + ','+detailId + ','+ id + ','+ userType +','+ state +")'>详情</a>" ;
        }
 
    //系长
    }else if(userType==5){
        if(state==7) {
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail(" + type + ',' + detailId + ',' + id + ',' + userType + ',' + state + ")'>详情</a>";
        }else if(state==1){
            return "<a class='btn btn-danger radius' style='margin-left:10px;' onclick='assign("+id+")'>指派</a>";
        }else{
            return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick='detail(" + type + ',' + detailId + ',' + id + ',' + userType + ',' + state + ")'>详情</a>" ;
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
function shimo(id, obj) {
    //window.location.href="testDie.html?id="+id;
	Hui_admin_tabs("testDiePrevention.html?id="+id+ "&passIndex="+ passIndex, "预防再发试模记录填写");
}
//填写
function tianxie(id,type,detailId,isKy) {
    if(type == 1){
        if(isKy==0){
            //window.location.href="ky.html?id="+id;
        	Hui_admin_tabs("ky.html?id="+id+ "&passIndex="+ passIndex, "填写KY");
        }else{
            //window.location.href="reportdoing.html?id="+id;
        	Hui_admin_tabs("reportdoing.html?id="+id+ "&passIndex="+ passIndex, "填写设备维修记录");
        }

        //摸具维修
    }else if(type == 2){
        //window.location.href="mouldReport.html?id="+id;
    	Hui_admin_tabs("mouldReportPrevention.html?id="+id+ "&passIndex="+ passIndex, "预防再发填写模具维修记录");
    }
}
//补全信息
function buquan(id,type,detailId) {
    if(type == 1){
        //window.location.href="reportdoingFinish.html?id="+id;
    	Hui_admin_tabs("reportdoingFinish.html?id="+id+ "&passIndex="+ passIndex, "补全设备维修记录");
        //摸具维修
    }else if(type == 2){
    	//window.location.href="mouldReportReplenish.html?id="+id;
    	Hui_admin_tabs("mouldReportReplenishPrevention.html?id="+id+ "&passIndex="+ passIndex, "预发再发补全模具维修记录");
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
//function assign(id) {
//    //window.location.href="assign.html?id="+ id;
//	Hui_admin_tabs("assign.html?id="+id, "指派人员");
//}

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
            "uuid":id,
            "orderTime":"orderTime",
            "beginTime":"beginTime",
            "workMan":user,
            "state":2
        };
    }
    //更新主表维修状态
    $.ajax({
        url: "../mouldPreventionType/update.do",
        type: "POST",
        dataType: "json",
        async:false,
        data:data,
        success: function(result) {
            if(result.ret == '1') {
                // 设备维修
                if(type == 1){
                    //window.location.href="ky.html?id="+id;
                	search(passIndex);
                	Hui_admin_tabs("ky.html?id="+id+ "&passIndex="+ passIndex, "填写KY");
                    //摸具维修
                }
                else{
                    //window.location.href="mouldky.html?id="+id+"&detailId="+detailId;
                    layer.msg("接单成功");
                    setTimeout(function(){
                    	search(passIndex);
                    }, 1000 );
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
		url = "site_selectMouldReportPrevention.html?id=" + id+"&userType="+userType;
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
        content: "assign.html?id="+id+ "&passIndex="+ passIndex, //iframe的url，no代表不显示滚动条
        yes: function (index, layero) {
            layer.close(index);
        },
        cancel:function (index, layero) {
        }
    });
}

//检索
function search(index, flag) {
	
	if(flag == 1){
		passIndex = 0;
	}

    var userType = $("#userType").val();
    var beginTime = $("#beginTime").val().replace("T", " "),
        endTime = $("#endTime").val().replace("T", " ");
    var type = $("#type option:selected").val();
    var state = $("#state option:selected").val();
    var workMan = $("#worker option:selected").val();
    var repairNumber = $("#repairNumber").val();
    
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = index;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../mouldPreventionType/getRepairPreventionCount.do",
        type: "POST",
        dataType: "json",
        data: {
            "type": Number(type),
            "state": Number(state),
            "beginTime": beginTime,
            "endTime": endTime,
            "createUser": "",
            "workMan": workMan,
            "repairNumber":repairNumber,
            "mouldId":mouldId
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
    InitTableSearch(index,type,state,beginTime,endTime,workMan,userType,repairNumber); //Load事件，初始化表格数据，页面索引为0（第一页）
    //翻页调用
    function PageCallback(index,jq) {
    	passIndex = index;
        InitTableSearch(index,type,state,beginTime,endTime,workMan,userType, repairNumber);
    }
}
function InitTableSearch(pageIndex,type,state,beginTime,endTime,workMan,userType, repairNumber) {
    var assUserType= $("#userType").val();
    var loginUser= $("#loginuser").val();
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../mouldPreventionType/getRepairPrevention.do",
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
            "mouldId":mouldId,
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
                    var assman = data.workMan;
 
                    var div = "";
                    if(loginUser==assman&&(assUserType==3||assUserType==4||assUserType==5)&&data.state==1) {
                    	                   
                        div = "<tr class='text-c' style='background-color: #dbafb2'>";
                        
                    }else if(loginUser!=assman&&(assUserType==3||assUserType==4||assUserType==5)&&data.state==1){
                    	
                    	if(assman == null || assman =="" || assman == undefined){
                        	div = "<tr class='text-c' style='background-color:  #FFBA3D'>";
                    	}else{
                    		div = "<tr class='text-c' style='background-color:  #CFFA5D'>";
                    	}

                    	
                    }else if(data.state==2){
                    	
                    	div = "<tr class='text-c' style='background-color: #FFF7B2'>";
                    	
                    }else{
                    	
                    	div = "<tr class='text-c'>";
                    	
                    }
                    
                    if(userClass == 2){
                    	
                        div +=  "<td>" + j + "</td>" +
                        "<td>" + getState(data.state, data.type) + "</td>" +
                        "<td title='"+checkNull(data.applicant)+"'>" + checkNull(data.applicantUserName) + "</td>" +
                        "<td>" + data.reportRepairTime + "</td>" +
                        "<td title='"+checkNull(data.workMan)+"'>" + checkNull(data.userName) + "</td>" +
                        "<td>" + checkNull(data.vehicleType) + "</td>" +
                        "<td>" + checkNull(data.equipName) + "</td>" +
                        "<td>" + checkNull(data.lineName) + "</td>" +
                        "<td>" + checkNull(data.appearance) + "</td>" +
                        "<td>" +
                        appState(data.state, data.uuid, data.type, data.detailId, data.workMan, userType,data.isKy, data.mouldCssNum) +
                        "</td>" +
                        "</tr>";                        	
                    }else{
                        
                        div +=  "<td>" + j + "</td>" +
                        	    "<td>" + data.repairNumber + "</td>" +
                                "<td>" + getType(data.type) + "</td>" +
                                "<td>" + getState(data.state, data.type) + "</td>" +
                                "<td title='"+checkNull(data.applicant)+"'>" + checkNull(data.applicantUserName) + "</td>" +
                                "<td>" + data.reportRepairTime + "</td>" +
                                "<td title='"+checkNull(data.workMan)+"'>" + checkNull(data.userName) + "</td>" +
                                "<td>" + checkNull(data.vehicleType) + "</td>" +
                                "<td>" + checkNull(data.equipName) + "</td>" +
                                "<td>" + checkNull(data.lineName) + "</td>" +
                                "<td>" + checkNull(data.appearance) + "</td>" +
                                "<td>" +
                                appState(data.state, data.uuid, data.type, data.detailId, data.workMan, userType,data.isKy, data.mouldCssNum) +
                                "</td>" +
                                "</tr>";                    	
                    }

                    
                    
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
					search(passIndex);
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

function reflush(index){

	search(index);
}

function getSearchDate(){
	//默认时间
	var myDate = new Date(), Y = myDate.getFullYear(), M = myDate.getMonth() + 1, D = myDate.getDate();
	//处理月是一位的情况
	if((M + '').length == 1){
		M = '0' + (M + '');
	}
	//处理日是一位的情况
	if((D + '').length == 1){
		D = '0' + (D + '')
	}
	var curDay = Y + '-' + M + '-' + D;

	curDay = curDay + 'T00:00';
	
	return curDay;
}


function selectWorkMan(){
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
                    $(option).text(list[i].username + "-" + list[i].employeeName);
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
}

//作业开始
function taskBegin(repairId, type, detailId, obj){

/*	$(obj).css("pointer-events","none");
	$(obj).removeClass('btn btn-primary radius').addClass('btn btn-inverse radius');*/
    $.ajax({
        url: "../repair/insertMouldTaskTime.do",
        type: "POST",
        dataType: "json",
        data:{"reId": repairId},
        async:false,
  		beforeSend: function (XMLHttpRequest) {
        	$(obj).css("pointer-events","none");
        	$(obj).removeClass('btn btn-primary radius').addClass('btn btn-inverse radius');
        },
        success: function(result) {
            if(result.ret == '1') {
            	$(obj).css("pointer-events","none");
            	$(obj).removeClass('btn btn-primary radius').addClass('btn btn-inverse radius');
            	search(passIndex);
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

//作业完成
function taskEnd(repairId, type, detailId, obj){
    $.ajax({
        url: "../repair/insertMouldTaskTime.do",
        type: "POST",
        dataType: "json",
        data:{"reId": repairId},
        async:false,
  		beforeSend: function (XMLHttpRequest) {
        	$(obj).css("pointer-events","none");
        	$(obj).removeClass('btn btn-primary radius').addClass('btn btn-inverse radius');
        },
        success: function(result) {
            if(result.ret == '1') {

            	search(passIndex);
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

//模具按钮不同状态显示不同的内容
function mouldButtonCss(id, type, detailId, cssNum){
	var html = "";
	
	if(cssNum == 1){
		
		
		
		html = "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"taskBegin('" + id + "','"+ type +"','"+ detailId +"', this)\"	>作业开始</a>"
        	+  "<a class='btn btn-inverse radius' style='margin-left:10px;pointer-events:none;' onclick=\"shimo('" + id + "', this)\">试模</a>"
        	+  "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"tianxie('" + id + "','"+ type +"','"+ detailId +"')\">补全</a>";
	}else if(cssNum == 2){
		html = "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"taskEnd('" + id + "','"+ type +"','"+ detailId +"', this)\">作业完成</a>"
    		+  "<a class='btn btn-inverse radius' style='margin-left:10px;pointer-events:none;' onclick=\"shimo('" + id + "', this)\">试模</a>"
    		+  "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"tianxie('" + id + "','"+ type +"','"+ detailId +"')\">补全</a>";
	}else if(cssNum == 3){
		html = "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"taskBegin('" + id + "','"+ type +"','"+ detailId +"', this)\"	>作业开始</a>"
			+  "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"shimo('" + id + "', this)\">试模</a>"
			+  "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"tianxie('" + id + "','"+ type +"','"+ detailId +"')\">补全</a>";
	}else if(cssNum == 4){
		html = "<a class='btn btn-inverse radius' style='margin-left:10px;pointer-events:none;' onclick=\"taskBegin('" + id + "','"+ type +"','"+ detailId +"', this)\">作业开始</a>"
			+  "<a class='btn btn-inverse radius' style='margin-left:10px;pointer-events:none;' onclick=\"shimo('" + id + "', this)\">试模</a>"
			+  "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"tianxie('" + id + "','"+ type +"','"+ detailId +"')\">补全</a>";
	}else if(cssNum == 5){
		html = "<a class='btn btn-inverse radius' style='margin-left:10px;pointer-events:none;' onclick=\"taskBegin('" + id + "','"+ type +"','"+ detailId +"', this)\"	>作业开始</a>"
			+  "<a class='btn btn-inverse radius' style='margin-left:10px;pointer-events:none;' onclick=\"shimo('" + id + "', this)\">试模</a>"
			+  "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"buquan('" + id + "','"+ type +"','"+ detailId +"')\">补全</a>";		
	}else if(cssNum == 6){
		html = "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"receipt('" + id + "','"+ type +"','"+ detailId +"')\">接单</a>"
			+  "<a class='btn btn-inverse radius' style='margin-left:10px;pointer-events:none;' onclick=\"shimo('" + id + "', this)\">试模</a>"
			+  "<a class='btn btn-inverse radius' style='margin-left:10px;pointer-events:none;' onclick=\"tianxie('" + id + "','"+ type +"','"+ detailId +"')\">补全</a>";
	}

	
	return html;
}