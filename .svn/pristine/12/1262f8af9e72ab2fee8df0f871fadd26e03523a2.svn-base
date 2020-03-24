
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
                type = result.data.userType;
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
    
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    query(id);
});

//查询维修历史
function query(id) {
	
	//头部
    $.ajax({
        url: "../mould/selectMouldById.do",
        type: "POST",
        dataType: "json",
        data: {
            "id": Number(id)
        },
        success: function (result) {
            if (result.ret == '1') {
            	var data = result.data;
            	

                $('#vehicleType').val(data.vehicleType);
                if(data.factory == 1){
                	$("#factory").val("天津");
                }else if(data.factory == 2){
                	$("#factory").val("长春");
                }else if(data.factory == 3){
                	$("#factory").val("佛山");
                }
                
                $('#anotherName').val(data.anotherName);               
                $('#figureNumber').val(data.figureNumber);    

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
                alert(error);
            }
        }
    });
	
	
	
	$.ajax({
		url: '../mould/spotRepairHistoryList.do?id=' + id,
		type: 'GET',
		beforeSend: function(XMLHttpRequest) {

		},
		complete: function(XMLHttpRequest, textStatus) {

		},
		success: function(result) {
			if(result.ret == '1') {

				var data = result.data;
				if(data != null && data.length > 0){

					//列表
	                htmlView(data);
				}


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


// 点击检索
function search() {
	query();
}


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

function htmlView(data){
	$("#mouleList").html("");
	
	for(var i=0; i<data.length; i++){
		var mouldRepairHistory = data[i];
        var j = i+1;
        
        var faultPartsView = faultParts(mouldRepairHistory.mouldFaultPartsList);
        var html = "";
        html += "<tr class='text-c'>" + 
					  "<td rowspan='2'>" + j + "</td>"+ 
					  "<td rowspan='2' style='display: none'>" + mouldRepairHistory.repairId + "</td>"+ 
					  "<td rowspan='2'>"+ mouldRepairHistory.reportRepairTime +"</td>"+ 
					  "<td rowspan='2'>"+ (mouldRepairHistory.endTime || "") +"</td>"+ 
					  "<td rowspan='2'>" + repairTime(mouldRepairHistory.orderTime, mouldRepairHistory.testMouldEndTime) + "</td>"+ 
					  "<td rowspan='2'>" + (mouldRepairHistory.faultTypeCodeName || "") + "</td>"+ 
					  "<td rowspan='2'>" + (mouldRepairHistory.faultReasonCodeName|| "") + "</td>"+ 
					  "<td rowspan='2'>" + (mouldRepairHistory.emergencyDisposal|| "") + "</td>"+ 
					  "<td rowspan='2' title='"+ faultPartsView +"'><a href='#'>部品</a></td>"+ 
					  "<td >" + (mouldRepairHistory.preservationSectionChiefName || "") + "</td>"+ 
					  "<td >" + (mouldRepairHistory.preservationDepartmentName || "") + "</td>"+ 
					  "<td >" + (mouldRepairHistory.shiftLeaderName || "") + "</td>"+ 
					  "<td >" + (mouldRepairHistory.mouldUserName || "") + "</td>"+ 
					  "<td rowspan='2'>" +
					    "<a class='btn btn-primary radius'  style='margin-left:10px;' style='display:none'>详情</a>" +
					  "</td>"+ 
				   "</tr>"+ 
				   
				  "<tr class='text-c'>"+ 
					  "<td >" + (mouldRepairHistory.preSecChiefConTime || "") + "</td>"+ 
					  "<td >" + (mouldRepairHistory.preDepChiefConTime || "") + "</td>"+ 
					  "<td >" + (mouldRepairHistory.confirmationTime || "") + "</td>"+ 
					  "<td >" + (mouldRepairHistory.qualityMonitorTime || "") + "</td>"+ 
				  "</tr>";

        $("#mouleList").append(html);
	}
	
}

function failurePeriodToString(data){
	var fs = "";
	switch(data){
	case 1: fs = "换模具";
		break;
	case 2: fs = "成型开始";
		break;
	case 3: fs = "生产中";
		break;
	case 4: fs = "成型结束";
		break;
	}
	
	return fs;
}

function repairTime(orderTime, testMouldEndTime){
	var timeString = "";
	if(testMouldEndTime == null || testMouldEndTime =="" || testMouldEndTime ==undefined){
		timeString = "暂未修完";
	}else{
		var start = new Date(orderTime);
		var end = new Date(testMouldEndTime);
		
		var num = (end-start)/(1000*60);
		
		if(!isNaN(num)){
			timeString = num.toFixed(1) + "分钟";
		}
	}
	return timeString;
}


function faultParts(list){
	var div = "";
	if(list != null && list.length > 0){
		for(var i=0; i< list.length; i++){
			var parts = list[i];
			if(i==0){
				div += "部品分类:"+ parts.partsType + "  名称:"+ parts.partsName + "  品番或型号:" + parts.partsNum;
			}else{
				div += "&#10;" + "部品分类:"+ parts.partsType + "  名称:"+ parts.partsName + "  品番或型号:" + parts.partsNum;
			}

		}
	}
	
	return div;
}