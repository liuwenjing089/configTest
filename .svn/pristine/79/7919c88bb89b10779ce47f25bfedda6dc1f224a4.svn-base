var id;
var userType;
var shopList;
var mouldId = 0;
var mouldPartsTypeList;
var mouldPartsRepairContent;
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
    
    //相同故障再发清单下拉
    $.ajax({
        url: "../mould/getMouldPreventionTypeList.do",
        type: "POST",
        dataType: "json",
        data:{"reId": id},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
               var data = result.data;
               initComBoxToPreventionType(data, "preventionType");
               
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

            		optionHtml += "<option  value='" + e.id + "' >" + e.factoryName +"-" + e.beltlineName + "</option>";
            	});
            	$("#line").append(optionHtml);
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
	
	//损坏部件
	$.ajax({
		url: '../mould/initFaultPartsComboxToType.do',
		type: 'POST',
		dataType: 'JSON',
		async:false,
		success: function(result) {
			if(result.ret == '1') {
				mouldPartsTypeList = result.data.mouldPartsInfo;
				mouldPartsRepairContent = result.data.mouldPartsRepairContent;
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
               mouldId = data.mouldId;
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
               $("#anotherName").val(data.anotherName);
               $("#model").val(data.model);
               $("#phenomenalDescription").val(data.phenomenalDescription);
			   $("#failurePeriod").val(data.failurePeriod);
			   $("#id").val(data.id);
			   $("#appearance").val(data.appearance);
			   $("#reason").val(data.reason);
			   $("#emergencyDisposal").val(data.emergencyDisposal);
			   $("#reId").val(data.reId);
			   $("#permanentGame").val(data.permanentGame);
			   
               $("#warehouseTimeStart").val(data.warehouseTimeStart);
               $("#warehouseTimeEnd").val(data.warehouseTimeEnd);
               $("#preventionType").val((data.typeGroupKey || ""));
			   
			   //给维修用时累计赋值
			   maintenanceTimeView(data.beginTime, data.endTime);
			   
			   //生产线统计维修时间
			   maintenanceTimeSC(data.reportRepairTime, data.endTime);
			   
               //追加图片显示
               if(data.productUrl != null && data.productUrl !=""){
    			   pictureView(data.productUrl, "productView", "productUrl");
               }
               if(data.faultLocationUrl != null && data.faultLocationUrl !=""){
    			   pictureView(data.faultLocationUrl, "faultLocationView", "faultLocationUrl");
               }
               if(data.formingMachineUrl != null && data.formingMachineUrl !=""){
    			   pictureView(data.formingMachineUrl, "formingMachineView", "formingMachineUrl");
               }
               if(data.maintenanceCompletedUrl != null && data.maintenanceCompletedUrl !=""){
    			   pictureView(data.maintenanceCompletedUrl, "maintenanceCompletedView", "maintenanceCompletedUrl");
               }
			   
			   
               //确认部分
			   $("#mouldUser").text(data.mouldUser || "");
			   $("#shiftLeader").text(data.shiftLeader || "");
			   $("#preservationDepartment").text(data.preservationDepartment || "");
			   $("#preservationSectionChief").text(data.preservationSectionChief || "");
		   
			   $("#mouldUserView").text(data.mouldUserView || "");
			   $("#shiftLeaderView").text(data.shiftLeaderView || "");
			   $("#preservationDepartmentView").text(data.preservationDepartmentView || "");
			   $("#preservationSectionChiefView").text(data.preservationSectionChiefView || "");
			   
			   $("#confirmationTime").text(data.confirmationTime || "");
			   $("#qualityMonitorTime").text(data.qualityMonitorTime || "");			   
			   $("#preDepChiefConTime").text(data.preDepChiefConTime || "");
			   $("#preSecChiefConTime").text(data.preSecChiefConTime || "");
			   
			   //表格赋值
			   textMouleTable(result.data.TestMouldList);


				//故障类型选中
               $("#faultType").val(data.faultType);
				//部品品番
//				var partsList = data.partsList;
//				if(partsList!= null) {
//					for (var i = 0; i < partsList.length; i++) {
//						var div = "<tr class='text-c'>" +
//							"<td>" + checkNull(partsList[i].faultPartsNum) + "</td>" +
//							"</tr>";
//						$("#partsList").append(div);
//					}
//				}
				//故障原因
				$("#faultReason").val(data.faultReason);
				//损坏部件
				var partsList = data.partsList;
				if(partsList!=null && partsList.length > 0){

					faultPartsTable(partsList);
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
					window.location.href= "serviceman.html"
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
		var hours = Math.floor(num / 60).toString();
		var minutes = (num % 60).toString();
		$("#maintenanceTime").val(hours);
		$("#maintenanceTimeM").val(minutes);
	}else{
		$("#maintenanceTime").val("");
		$("#maintenanceTimeM").val("");
	}
}

function maintenanceTimeSC(reportRepairTime, endTime){
	var start = new Date(reportRepairTime);
	var end = new Date(endTime);
	
	var num = (end-start)/(1000*60);

	if(!isNaN(num)){
		$("#maintenanceTimeSC").val(num + "分钟");
	}else{
		$("#maintenanceTimeSC").val("");
	}
}

function pictureView(url, pictureDiv, pictureVal){
	var urls = url.split(",");
	$("#"+ pictureVal).val(urls);
	
	if(urls != "" && urls.length > 0){
		for(var i=0; i < urls.length; i++){
			var picturUrl = urls[i];
			var html = "<p>" +
					   	  "<a href='#' onclick=\"view('"+ picturUrl +"')\" style='margin-left:50px;width:100px;' >图片"+ (i+1) +"&nbsp;&nbsp;&nbsp;&nbsp;点击查看</a>" +					   	  
					   "</p>";	  
			$("#"+ pictureDiv).append(html);
		}
	}

}


function view(url){

	window.open(url);
}

function allConfirm(){
	var flag = false;

	//故障类型check
	var faultType = $("#faultType option:selected").text();
	if(faultType=="请选择"){
		layer.msg("请补全故障类型");
		return;
	}
	
	var preventionType = $("#preventionType").val();
	
	//如果确认时候此选项为空，则需要在模具报修类型表里新建一种类型的记录
	if(preventionType == ""){
		
	    $.ajax({
	  		url: '../mould/mouldRepairByMonitorConfirmAndAddType.do?id=' + id + '&mouldId='+ mouldId,
	  		type: 'GET',
	        async:false,
	  		beforeSend: function (XMLHttpRequest) {

	        },
	        complete: function (XMLHttpRequest, textStatus) {

	        },
	  		success: function(result) {
	  			if(result.ret == '1') {
	  				flag = true;

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
	}else{
		

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

//再发防止实施报修
function addPreventiveMaintenance(){
	layer.alert("准备中");
}

//再发防止实施详情
function preventiveMaintenanceDetail(){
	Hui_admin_tabs_From_storey("servicemanPrevention.html?mouldId="+ mouldId + "&reId=" + id, "再发预防报修列表", "repairPrevention");
}

function initComBoxToPreventionType(selectList, htmlId){
	
	var optionHtml = "<option value=''>请选择历史故障类型</option>";
	
	$(selectList).each(function(i, e) {
		optionHtml += '<option value="' + e.groupKey + '">' + e.remarks + '</option>';
	});
	
	$("#" + htmlId).append(optionHtml);
}

function preventionTypeDetail(){

	var groupKey = $("#preventionType").val();
	
	if(groupKey == ""){
		layer.alert("请选择历史报修类型");
		return;
	}
	
    var wWidth = window.parent.innerWidth*0.95;
    var wHeight = window.parent.innerHeight*0.95;
	var title = "模具报修类型记录";
	var url = "mouldPreventionList.html?groupKey="+ groupKey;
    
	
    parent.layer.open({
        type: 2,
        id: 'mouldPreventionList',
        title: title,
        btn: ['关闭'],
        area: [wWidth + 'px', wHeight + 'px'],
        content: url, //iframe的url，no代表不显示滚动条
        yes: function (index, layero) {

        	parent.layer.close(index);
        },
        cancel:function (index, layero) {
        }
    });
	
	//Hui_admin_tabs("mouldPreventionList.html?groupKey="+ groupKey, "模具报修类型记录");
}


//追加回显损坏部件
function faultPartsTable(partsList){
	
	//先创建行
	for(var i = 0; i<partsList.length; i++){
		var faultParts = partsList[i];
		

		var tr="<tr class='text-c'>"+
				"<td>"+ faultParts.partsType +"</td>"+
				"<td>"+ faultParts.partsName +"</td>"+
				"<td>"+ faultParts.partsNum +"</td>"+
				"<td>"+ faultParts.contentName +"</td>"+
				"<td>"+ faultParts.quantity +"</td>"+		
			"</tr>";
		$("#partsList").append(tr);
	}

}