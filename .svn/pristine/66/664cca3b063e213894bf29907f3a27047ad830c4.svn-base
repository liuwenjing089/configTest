var id;
var userType;
var shopList;
$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    id = Request['id'];
    userType = Request['userType'];

    if(userType == 2){
    	$("#jdsj").hide();
    	$("#wxks").hide();
    	$("#bqxwxsc").hide();
    }else if(userType == 5|| userType == 6){
    	$("#scxwxsc").hide();
    	
    }
    

	//故障类型
	$.ajax({
		url: '../mould/initAllTypeCombox.do',
		type: 'GET',
		dataType: 'JSON',
		async:false,
		success: function(result) {
			if(result.ret == '1') {
				initComBox(result.data.manufacturerCombox, "faultType");
				initComBox(result.data.failurePeriodCombox, "failurePeriod");
				initComBox(result.data.faultReasonCombox, "faultReason");
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
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert("error");
		}
	});

    $.ajax({
        url: "../line/selectLineByMould.do",
        type: "GET",
        async:false,
        success: function(result) {
            if(result.ret == '1') {

                var selectList = result.data;
                var optionHtml = "<option value='0'>请选择</optin>";
            	$(selectList).each(function(i, e) {

            		optionHtml += "<option  value='" + e.id + "' >" + e.beltlineName + "</option>";
            	});
            	$("#line").append(optionHtml);
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

	//页面赋值
    $.ajax({
        url: "../mould/selectRepairMouldByConfirm.do",
        type: "POST",
        dataType: "json",
        data:{"id": id},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
               var data = result.data.MouldRepair;
               $("#line").val(data.line);
               $("#repairNumber").text(data.repairNumber);
				if(data.reportRepairTime != null && data.reportRepairTime != ""){
					var reportdate = data.reportRepairTime.split(" ");
					$("#reportRepairDate").val(reportdate[0]);
					$("#reportRepairTime").val(reportdate[1]);
				}

				if(data.beginTime != null && data.beginTime != ""){
					var beginTime = data.beginTime.split(" ");
					$("#beginTime").val(beginTime[0]);
					$("#beginTimeToTime").val(beginTime[1]);
				}

				if(data.endTime != null && data.endTime != ""){
					var endTime = data.endTime.split(" ");
					$("#endTime").val(endTime[0]);
					$("#endTimeToTime").val(endTime[1]);
				}

				if(data.orderTime != null && data.orderTime != ""){
					var orderDate = data.orderTime.split(" ");
					$("#orderTime").val(orderDate[0]);
					$("#orderTimeToTime").val(orderDate[1]);
				}
               
               
               $("#applicant").val(data.applicant);
               $("#figureNumber").val(data.figureNumber);
               $("#model").val(data.model);
               $("#phenomenalDescription").val(data.phenomenalDescription);
			   $("#failurePeriod").val(data.failurePeriod);
			   $("#id").val(data.id);
			   $("#reason").val(data.reason);
			   $("#emergencyDisposal").val(data.emergencyDisposal);
			   $("#reId").val(data.reId);
			   $("#permanentGame").val(data.permanentGame);
			   
			   //给维修用时累计赋值
			   maintenanceTimeView(data.beginTime, data.endTime);
			   
			   //生产线统计维修时间
			   maintenanceTimeSC(data.reportRepairTime, data.endTime);
			   
               //追加图片显示
               if(data.faultLocationUrl != null && data.faultLocationUrl !=""){
            	   pictureView(data.faultLocationUrl);
               }
			   
			   
               //确认部分
			   $("#mouldUser").text(data.mouldUser || "");
			   $("#shiftLeader").text(data.shiftLeader || "");
			   $("#preservationDepartment").text(data.preservationDepartment || "");
			   $("#preservationSectionChief").text(data.preservationSectionChief || "");
			   
			   $("#confirmationTime").text(data.confirmationTime || "");
			   $("#preDepChiefConTime").text(data.preDepChiefConTime || "");
			   $("#preSecChiefConTime").text(data.preSecChiefConTime || "");
			   
			   //表格赋值
			   textMouleTable(result.data.TestMouldList);


				// //故障类型选中
				// var all_options = document.getElementById("faultType").options;
				// for (var i=0; i<all_options.length; i++){
				// 	if (all_options[i].value == data.faultType) {
				// 		all_options[i].selected = true;
				// 	}
				// }
				// //部品品番
				// var partsList = data.partsList;
				// if(partsList!= null) {
				// 	for (var i = 0; i < partsList.length; i++) {
				// 		var div = "<tr class='text-c'>" +
				// 			"<td>" + checkNull(partsList[i].faultPartsNum) + "</td>" +
				// 			"</tr>";
				// 		$("#partsList").append(div);
				// 	}
				// }
				// //故障原因
				// var faultRestr=data.faultReason;
				// if(data.faultReason != null) {
				// 	for (var i = 0; i < faultRestr.length; i++) {
				// 		if (faultRestr.charAt(i) == '1') {
				// 			$('#faultReason1').attr('checked', true);
				// 		} else if (faultRestr.charAt(i) == '2') {
				// 			$('#faultReason2').attr('checked', true);
				// 		} else if (faultRestr.charAt(i) == '3') {
				// 			$('#faultReason3').attr('checked', true);
				// 		} else if (faultRestr.charAt(i) == '4') {
				// 			$('#faultReason4').attr('checked', true);
				// 		}
				// 	}
				// }
				// //损坏部件
				// shopList = JSON.parse(data.faultParts);
				// showList(shopList);
			   
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
    
});


//点击取消按钮
function layer_close(){
    window.location.href = "equipment-list.html";
}

//点击提交
function save() {

	//验证
	var formValidation = fv();
	if(!formValidation){
		return;
	}

	//取值
	var data = getValue();

      $.ajax({
  		url: '../mould/updateRepairMould.do',
  		type: 'POST',
  		data: data,
        dataType : "json",
        async:false,
  		beforeSend: function (XMLHttpRequest) {

          },
          complete: function (XMLHttpRequest, textStatus) {

          },
  		success: function(result) {
  			if(result.ret == '1') {
                layer.msg("保存成功");
				setTimeout(function(){
					window.location.href= "site_serviceman.html"
				}, 2000 );
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
  					flag = error;
  				}				
  			}   
  		},
  		error: function(XMLHttpRequest, textStatus, errorThrown) {
  			alert("error");
  		}

  	});

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


function custom_close(){
	window.location.href= "serviceman.html";
}

function maintenanceTimeView(beginTime, endTime){
	var start = new Date(beginTime);
	var end = new Date(endTime);
	
	var num = (end-start)/(1000*60);

	if(!isNaN(num)){
		$("#maintenanceTime").val(num + "分钟" );
	}else if(endTime==null){
		$("#maintenanceTime").val("");
	}
	else{
		$("#maintenanceTime").val("");
	}
}

function maintenanceTimeSC(reportRepairTime, endTime){
	var start = new Date(reportRepairTime);
	var end = new Date(endTime);
	
	var num = (end-start)/(1000*60);

	if(!isNaN(num)){
		if(endTime==null){
			$("#maintenanceTimeSC").val("");
		}else{
			$("#maintenanceTimeSC").val(num + "分钟");
		}
	}
	else{
		$("#maintenanceTimeSC").val("");
	}
}


function pictureView(url){
	var urls = url.split(",");
	if(urls != "" && urls.length > 0){
		for(var i=0; i < urls.length; i++){
			var picturUrl = urls[i];
			var html = "<div class='row cl'>" +
					   	  "<a href='#' onclick=\"view('"+ picturUrl +"')\" style='margin-left:50px;width:100px;' >图片"+ (i+1) +"&nbsp;&nbsp;&nbsp;&nbsp;点击查看</a>" +	
					   "</div>";
			$("#pictureView").append(html);
		}
	}

}

function view(url){

	window.open(url);
}

function allConfirm(){
	var flag = false;
	
    $.ajax({
  		url: '../mould/mouldRepairByMonitorConfirm.do?id=' + id,
  		type: 'GET',
        async:false,
  		beforeSend: function (XMLHttpRequest) {

        },
        complete: function (XMLHttpRequest, textStatus) {

        },
  		success: function(result) {
  			if(result.ret == '1') {
  				flag = true;

  			}else if(result.ret == '3'){
				layer.msg("登录超时,请重新登录");
				setTimeout(function(){
					top.window.location.href= "login.html"
				}, 1000 );
			} else {
  				  
  				var error = "";
  				for(var i = 0; i < result.data.length; i++) {
  					error += (result.data[i].message + "<br\>");
  				}
  				if(error != "") {
  					layer.msg(error);
  					flag = error;
  				}				
  			}   
  		},
  		error: function(XMLHttpRequest, textStatus, errorThrown) {
  			alert("error");
  		}

  	});
    
    return flag;
}

//试模记录表格
function textMouleTable(mouleList){
    for(var i = 0; i < mouleList.length; i++) {
        var data = mouleList[i];
        var div  = "<tr class='text-c'>" +
			        "<td>" + (i+1) + "</td>" +
			        "<td>"+ data.testMouldStartTime +"</td>" +
			        "<td>" + data.testMouldEndTime + "</td>" +
			        "<td>" + resultStr(data.testMouldResult) + "</td>" +
			        "<td>" + data.remarks + "</td>" +             
			        "</td>"+
			       "</tr>";
        $("#mouleList").append(div);
    }  
}

function resultStr(data){
	var dataStr = "";
	if(data == 1){
		dataStr = "失败";
	}else if(data == 2){
		dataStr = "成功";
	}
	return dataStr;
}


//故障类型
function initComBox(selectList, htmlId){
	var optionHtml = "<option value='0'>请选择</option>";
	$(selectList).each(function(i, e) {
		optionHtml += '<option value="' + e.id + '">' + e.name + '</option>';
	});
	$("#" + htmlId).append(optionHtml);
}