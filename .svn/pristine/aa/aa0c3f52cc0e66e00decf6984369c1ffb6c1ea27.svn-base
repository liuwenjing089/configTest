var id;
var shopList;
var passIndex = 0;
var mouldId = 0;
var mouldPartsTypeList;
var mouldPartsRepairContent;
$(document).ready(function() {

    var Request = new Object();
    Request = GetRequest();
    id = Request['id'];
    passIndex = Request['passIndex'];
    
    //相同故障再发清单下拉
    $.ajax({
        url: "../mouldPreventionType/getMouldPreventionTypeList.do",
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

    //成型机下拉
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
        url: "../mouldPreventionType/selectRepairMouldByRepairId.do",
        type: "POST",
        dataType: "json",
        data:{"uuid": id},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
               var data = result.data;
               mouldId = data.mouldId;
               $("#line").val(data.line);
               $("#repairNumber").text(data.repairNumber);
               var reportdate = data.reportRepairTime.split(" ");
               $("#reportRepairDate").val(reportdate[0]);
               $("#reportRepairTime").val(reportdate[1]);
               $("#applicant").val(data.applicant);
               $("#figureNumber").val(data.figureNumber);
               $("#anotherName").val(data.anotherName);
               $("#model").val(data.model);
               $("#phenomenalDescription").val(data.phenomenalDescription);
			   $("#failurePeriod").val(data.failurePeriod);
			   $("#id").val(data.id);
			   $("#reId").val(data.reId);
			   $("#appearance").val(data.appearance);
			   $("#reason").val(data.reason);
			   $("#emergencyDisposal").val(data.emergencyDisposal);			   
			   $("#permanentGame").val(data.permanentGame);
			   
			   var beginTime = data.beginTime.split(" ");
			   $("#beginTime").val(beginTime[0]);
			   $("#beginTimeToTime").val(beginTime[1]);
			   
			   var endTime = data.endTime.split(" ");
			   $("#endTime").val(endTime[0]);
			   $("#endTimeToTime").val(endTime[1]);
			   
			   var orderDate = data.orderTime.split(" ");
               $("#orderTime").val(orderDate[0]);
               $("#orderTimeToTime").val(orderDate[1]);
               
               
               $("#warehouseTimeStart").val(data.warehouseTimeStart);
               $("#warehouseTimeEnd").val(data.warehouseTimeEnd);
               $("#preventionType").val((data.typeGroupKey || ""));
			   
			   //给维修用时累计赋值 
               if(data.endTime != null && data.endTime !=""){
            	   maintenanceTimeView(data.beginTime, data.endTime);
        		   
               }
			  
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
			   
               //故障类型选中
               $("#faultType").val(data.faultType);
				//部品品番
//				var partsList = result.data.partsList;
//				if(partsList!= null) {
//					for (var i = 0; i < partsList.length; i++) {
//						var div = "<tr class='text-c'>" +
//							"<td contentEditable='true'>" + checkNull(partsList[i].faultPartsNum) + "</td>" +
//							"<td ><input type='button' value='删除' class='btn btn-danger radius' id='deleteTable' onclick='delecttr(this)'/></td>" +
//							"</tr>";
//						$("#partsList").append(div);
//					}
//				}
				//故障原因
				$("#faultReason").val(result.data.faultReason);
				//损坏部件
				var partsList = result.data.partsList;
				if(partsList!=null && partsList.length > 0){

					faultPartsTable(partsList);
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
    $("#uploadInf").hide();
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
    if(data.flag == false){
    	return;
    }else{
    	delete data.flag;
    }

	data = JSON.stringify(data);
      $.ajax({
  		url: '../mould/updateRepairMoulds.do',
  		type: 'POST',
  		data: data,
        dataType : "json",
        contentType: "application/json; charset=utf-8",
        async:false,
  		beforeSend: function (XMLHttpRequest) {
            $("#saveForm").attr('disabled',true);
        },
        complete: function (XMLHttpRequest, textStatus) {
        	$("#saveForm").removeAttr('disabled');
        },
  		success: function(result) {
  			if(result.ret == '1') {
                layer.msg("保存成功");
                refurbish();
				setTimeout(function(){
					//window.location.href= "serviceman.html"
					removeIframe();  
				}, 1000 );
  			} else if(result.ret == '3'){
				layer.msg("登录超时,请重新登录");
				setTimeout(function(){
					top.window.location.href= "login.html"
				}, 1000 );
			}else {
  	        	$("#saveForm").removeAttr('disabled');
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
        	$("#saveForm").removeAttr('disabled');
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


//表单验证
function fv(){
	var flag = true;
	
	var boolCheck = $("#failurePeriod").val(); 
	if(boolCheck == 0){
		layer.alert("请选择故障时期");
		flag = false;
	}
	
	var phenomenalDescription = $("#phenomenalDescription").val();
	phenomenalDescription = $.trim(phenomenalDescription);
	if(phenomenalDescription == ""){
		layer.alert("请填写故障内容");
		flag = false;
	}
	
	var beginTime = $("#beginTime").val();
	if(beginTime == ""){
		layer.alert("请填写维修开始日期");
		flag = false;
	}
	
	var beginTimeToTime = $("#beginTimeToTime").val();
	if(beginTimeToTime == ""){
		layer.alert("请填写维修开始时间");
		flag = false;
	}
	
//	var endTime = $("#endTime").val();
//	if(endTime == ""){
//		layer.alert("请填写维修结束日期");
//		flag = false;
//	}
	
//	var endTimeToTime = $("#endTimeToTime").val();
//	if(endTimeToTime == ""){
//		layer.alert("请填写维修结束时间");
//		flag = false;
//	}
	
	var applicant = $("#applicant").val();
	if(applicant == ""){
		layer.alert("登录过期,请重新登录");
		flag = false;
	}
	
	return flag;
}


function pictureView(url, pictureDiv, pictureVal){
	var urls = url.split(",");
	$("#"+ pictureVal).val(urls);
	
	if(urls != "" && urls.length > 0){
		for(var i=0; i < urls.length; i++){
			var picturUrl = urls[i];
			var html = 
				"<div>" +
					   	  "<a href='#' onclick=\"view('"+ picturUrl +"')\" style='margin-left:50px;width:100px;'><span style='border:1px solid #fff;display:inline-block;width:50px;text-align:center;'>图片</span>"+ 
					   	  "<span style='display:inline-block;border:1px solid #fff;width:15px;text-align:center;'>"+(i+1)+"</span>" +"&nbsp;&nbsp;&nbsp;&nbsp;点击查看</a>" +
					   	  "<input type='text' name='fileUrls' value='"+ picturUrl +"' style='display:none'/>" +
						  "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type='button' onclick='delPictur(this)' class='btn-sm btn-primary radius' style='margin:5px;padding:2px;'>删除当前图片</button>" +
					   "</div>";	  
			$("#"+ pictureDiv).append(html);
		}
	}

}

function view(url){

	window.open(url);
}

//删除当前照片
function delPictur(obj){
	var url = $(obj).parent().children("input").eq(0).val();
    var urls = $(obj).parent().parent().children("input").eq(0).val();
    var u = urls.split(",");
    
    for(var i = 0 ; i< u.length; i++){
    	if(u[i] == url){
    		u.splice(i, 1); 
    		i--;
    	}
    }
    
    //再将数组转化为字符串
    var fileUrls = "";
    $(u).each(function(j,item){
        console.log("下标:"+j);
        console.log("value值:"+item);
        fileUrls += j > 0 ? ","+item : item;
      });
    
    $(obj).parent().parent().children("input").eq(0).val(fileUrls);
	$(obj).parent().remove();
}

//页面取值
function getValue(){
	var data = {};
    data["id"] = $("#id").val();
    data["reId"] = $("#reId").val();
    data["applicant"] = $("#applicant").val();
    data["reportRepairTime"] = $("#reportRepairDate").val() + "  " + $("#reportRepairTime").val();
    data["failurePeriod"] = $("#failurePeriod").val();
    data["phenomenalDescription"] = $.trim($("#phenomenalDescription").val());
	data["appearance"] = $.trim($("#appearance").val());
    data["reason"] = $.trim($("#reason").val());
    data["emergencyDisposal"] = $.trim($("#emergencyDisposal").val());
    data["permanentGame"] = $.trim($("#permanentGame").val());
    data["warehouseTimeStart"] = $("#warehouseTimeStart").val();
    data["warehouseTimeEnd"] = $("#warehouseTimeEnd").val();
    
//    var fileUrls = "";
//    var obj = $("input[name='fileUrls']");
//    if(obj.length > 0){
//        $(obj).each(function(j,item){
//            console.log("下标:"+j);
//            console.log("value值:"+item.value);
//            fileUrls += j > 0 ? ","+item.value : item.value;
//          });
//    }
//    data["faultLocationUrl"] = fileUrls;
    
    //四组图片
    data["faultLocationUrl"] = $("#faultLocationUrl").val();
    data["productUrl"] = $("#productUrl").val();
    data["formingMachineUrl"] = $("#formingMachineUrl").val();
    data["maintenanceCompletedUrl"] = $("#maintenanceCompletedUrl").val();
    
    data["beginTime"] = $("#beginTime").val() + " " +  $("#beginTimeToTime").val();
    data["endTime"] = $("#endTime").val() + " " +  $("#endTimeToTime").val();
    data["state"] = 9;

	// 故障类型
	data["faultType"]  = Number($("#faultType option:selected").val());

	//故障品番号
	var faultList = [];
    var flag = true;
	
	$("#partsList tr").each(function (i) {
		var part = {};
		part["reId"] = id;
		$(this).children('td').each(function (j) {
			if (j == 0) {
				
				var partsType = $(this).find("select").val();

				part["partsType"] = partsType;
				
			} else if (j == 1) {
				
				var partsName = $(this).find("select").val();

				part["partsName"] = partsName;
				
			}else if (j == 2) {
				
				var mouldPartsInfoUuid = $(this).find("select").val();
				
				if(mouldPartsInfoUuid == "" || mouldPartsInfoUuid == null || mouldPartsInfoUuid == undefined){
					layer.alert("部品信息第"+ (i+1)+ "行请选择 部品品番或型号");
					flag = false;
					return false;
				}
					
				part["mouldPartsInfoUuid"] = mouldPartsInfoUuid;

				
				var faultPartsNum = $(this).find("select option:selected").text();

				part["faultPartsNum"] = faultPartsNum;
				
			}else if (j == 3) {

				var content = $(this).find("select").val();
				if(content == "" || content == null || content == undefined){
					layer.alert("部品信息第"+ (i+1)+ "行请选择 作业内容");
					flag = false;
					return false;
				}

				part["content"] = content;
				
			}else if (j == 4) {
				var quantity = $(this).text().trim();
				
				if(quantity == "" || quantity == null || quantity == undefined){
					layer.alert("部品信息第"+ (i+1)+ "行请输入  数量");
					flag = false;
					return false;
				}
				
				var reg = /^\d+$/ ;
				if(!reg.test(quantity)){
					layer.alert("部品信息第"+ (i+1)+ "行的数量请输入  正确的数字");
					flag = false;
					return false;
				}
				
				part["quantity"] = quantity;

			}
		});
		faultList.push(part);
	})

	
	data["partsList"] = faultList;
	//故障原因
	data["faultReason"] =$("#faultReason").val();

	data["flag"] = flag;
    return data;
}


$("#beginTime").change(function(){
	
	var beginTime = $("#beginTime").val() + " " +  $("#beginTimeToTime").val();
	var endTime = $("#endTime").val() + " " +  $("#endTimeToTime").val();
	
	var start = new Date(beginTime);
	var end = new Date(endTime);
	
	var num = (end-start)/(1000*3600);

	if(!isNaN(num)){
		$("#maintenanceTime").val(num.toFixed(1) + "小时");
	}else{
		$("#maintenanceTime").val("");
	}
});

$("#beginTimeToTime").change(function(){
	
	var beginTime = $("#beginTime").val() + " " +  $("#beginTimeToTime").val();
	var endTime = $("#endTime").val() + " " +  $("#endTimeToTime").val();
	
	var start = new Date(beginTime);
	var end = new Date(endTime);
	
	var num = (end-start)/(1000*3600);

	if(!isNaN(num)){
		$("#maintenanceTime").val(num.toFixed(1) + "小时");
	}else{
		$("#maintenanceTime").val("");
	}
	
    
	
});

$("#endTime").change(function(){

	var beginTime = $("#beginTime").val() + " " +  $("#beginTimeToTime").val();
	var endTime = $("#endTime").val() + " " +  $("#endTimeToTime").val();
	
	var start = new Date(beginTime);
	var end = new Date(endTime);
	
	var num = (end-start)/(1000*3600);
	
	if(!isNaN(num)){
		$("#maintenanceTime").val(num.toFixed(1) + "小时");
	}else{
		$("#maintenanceTime").val("");
	}

});

$("#endTimeToTime").change(function(){

	var beginTime = $("#beginTime").val() + " " +  $("#beginTimeToTime").val();
	var endTime = $("#endTime").val() + " " +  $("#endTimeToTime").val();
	
	var start = new Date(beginTime);
	var end = new Date(endTime);
	
	var num = (end-start)/(1000*3600);
	
	if(!isNaN(num)){
		$("#maintenanceTime").val(num.toFixed(1) + "小时");
	}else{
		$("#maintenanceTime").val("");
	}

});

//损坏部件联动
var shopPlatformHtml="";
function initFaultComBox() {
	var count = Number($("#times").val())-1;
	var selectList = JSON.parse($("#listJson").val());
	var str1 = "faultOne"+count;
	var str2 = "faultTwo"+count;
	var div ="<div class='row cl'>"+"<label class='form-label col-xs-1 col-sm-1'></label>"
	+"<div class='formControls col-xs-4 col-sm-4'>"+
		"<select class='select' id='"+str1+"'  style='height:31px;'>"+
		"</select></div>"+
		"<div class='formControls col-xs-4 col-sm-4'>"+
		"<select class='select' id='"+str2+"'  style='height:31px;'>"+
		"</select></div>"+"</div>";
	var optionHtml = "<option value='' data-shopList='[]' data-ids='[]'>请选择</option>";
	$(selectList).each(function (i, e) {
		optionHtml += "<option value='" + e.id + "'data-shopList='" + JSON.stringify(e.subPartsList) + "'data-ids='"+JSON.stringify(e.subPartsIds)+"'>"+ e.name + "</option>";
	});
	$("#" + str1).append(optionHtml);
	$("#addSe").append(div);
	shopPlatformHtml = optionHtml;
	$("#" + str1).html(shopPlatformHtml).change(function () {
		var shopList = JSON.parse($(this).find('option:selected').attr('data-shopList'));
		var dataIds = JSON.parse($(this).find('option:selected').attr('data-ids'));
		var shopCodeHtml = "<option value=''>请选择</option>";
		$(shopList).each(function (p, o) {
			//var j = p + 1;
			var j = dataIds[p];
			shopCodeHtml += '<option value="' + j + '">' + o + '</option>';
		});
		$("#" + str2).html(shopCodeHtml);
	}).change();
}

//添加损坏零件
$("#addSelect").click(function(){
	//存储点击次数
	var count = Number($("#times").val());
    count += 1;
	$("#times").val(count);
	initFaultComBox();
});

//故障类型
function initComBox(selectList, htmlId){
	var optionHtml = "<option value='0'>请选择</option>";
	$(selectList).each(function(i, e) {
		optionHtml += '<option value="' + e.id + '">' + e.name + '</option>';
	});
	$("#" + htmlId).append(optionHtml);
}

//损坏部件二级联动赋值
function showList(list) {
	$.ajax({
		url: '../mould/initFaultPartsCombox.do',
		type: 'GET',
		dataType: 'JSON',
		async:false,
		success: function(result) {
			if(result.ret == '1') {
				var j=0;
				//二级list
				var shopList = null;
				var dataIds = null;
				if(list!=null) {
					for (var i = 0; i < list.length; i++) {
						var div = "";
						//存储的要显示的value值
						var value = list[i];
						//动态id
						var str1 = "faultOne" + j;
						var str2 = "faultTwo" + j;
						//主选框赋值
						if (i % 2 == 0) {
							div += "<div class='row cl'>"+"<label class='form-label col-xs-1 col-sm-1'></label>"+"<div class='formControls col-xs-4 col-sm-4'>" +
								"<select class='select abc' id='" + str1 + "'  style='height:31px;' onchange='shbjld(this)'>" +
								"</select></div>"+"</div>";
							var optionHtml = "<option value='' data-shopList='[]' data-ids='[]'>请选择</option>";
							$(result.data).each(function (i, e) {
								optionHtml += "<option value='" + e.id + "'data-shopList='" + JSON.stringify(e.subPartsList) + "'data-ids='"+JSON.stringify(e.subPartsIds)+"'>" + e.name + "</option>";
							});
							$("#addSe").append(div);
							$("#" + str1).append(optionHtml);
							$("#" + str1).find("option[value=" + value + "]").attr("selected", true);
							shopList = JSON.parse($("#" + str1).find("option[value='" + value + "']").attr('data-shopList'));
							dataIds = JSON.parse($("#"+str1).find("option[value='"+value+"']").attr('data-ids'));
						} else {
							//次选框赋值
							div += "<div class='row cl'>"+"<label class='form-label col-xs-1 col-sm-1'></label>"+"<div class='formControls col-xs-4 col-sm-4'>" +
								"<select class='select' id='" + str2 + "'  style='height:31px;'>" +
								"</select></div>"+"</div>";
							var shopCodeHtml = "<option value=''>请选择</option>";
							$(shopList).each(function (p, o) {
								//var s = p + 1;
								var s = dataIds[p];
								shopCodeHtml += '<option value="' + s + '">' + o + '</option>';
							});
							$("#addSe").append(div);
							$("#" + str2).html(shopCodeHtml);
							$("#" + str2).find("option[value=" + value + "]").attr("selected", true);
							//每两次循环 改变select id
							j += 1;


						}
					}
					//统计input数量 以便追加
					$("#times").val(list.length / 2);
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
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert("error");
		}
	});
}

//添加table行
$("#addTable").click(function(){
	
	var patrsType =   partsTypeSelect(mouldPartsTypeList);

	
	var patrsName =   "<select class='select' onchange='changePartsName(this)' >"
						+ "<option value='' >请选择</option>"
					  + "</select>";
	
	
	var patrsModel =   "<select class='select' >"
						+ "<option value=''>请选择</option>"
					  + "</select>";

	
	var patrsContent = partsRepairContentSelect();


	var tr="<tr class='text-c'>"+
		"<td>"+ patrsType +"</td>"+
		"<td>"+ patrsName +"</td>"+
		"<td>"+ patrsModel +"</td>"+
		"<td>"+ patrsContent +"</td>"+
		"<td contentEditable='true'></td>"+
		"<td><input type='button' class='btn btn-danger radius' value='删除' id='deleteTable' onclick='delecttr(this)'/></td>"+
		"</tr>";
	$("#partsList").append(tr);
});

function delecttr(obj){
	var tr = obj.parentNode.parentNode;
	tr.parentNode.removeChild(tr);
}


//上传图片
function uploadPicture(){
    var wWidth = window.innerWidth*0.95;
    var wHeight = window.innerHeight*0.95;
    layer.open({
        type: 2,
        id: 'uploadPictures',
        title: '上传图片',
        btn: ['上传', '取消'],
        area: [wWidth + 'px', wHeight + 'px'],
        content: 'uploadFile.html', //iframe的url，no代表不显示滚动条
        yes: function (index, layero) {
        	var frameObj = window.document.getElementById('uploadPictures').getElementsByTagName('iframe')[0];
            var resulte = frameObj.contentWindow.shangchuan();

        },
        btn2: function (index, layero) {
        	
        },
        cancel:function (index, layero) {
        	
        }
    });
}

function shbjld(obj){
	
	var shbjId = $(obj).attr("id");
    var idNum = shbjId.split("faultOne")[1];
	
	var shopList = JSON.parse($(obj).find('option:selected').attr('data-shopList'));
	var dataIds = JSON.parse($(obj).find('option:selected').attr('data-ids'));
	var shopCodeHtml = "<option value=''>请选择</option>";
	$(shopList).each(function (p, o) {
		//var z = p + 1;
		var z = dataIds[p];
		shopCodeHtml += '<option value="' + z+ '">' + o + '</option>';
	});
	$("#faultTwo" + idNum).html(shopCodeHtml);
}

function custom_close(){
	//window.location.href= "serviceman.html";
	removeIframe();
}

//刷新设备维修一览页面，同时不改变页数
function refurbish(){
	var repairIfram = window.parent.document.getElementById("repairPrevention");
	var repairHtml = repairIfram.contentWindow;
	repairHtml.reflush(passIndex);
}


//再发防止实施报修
function addPreventiveMaintenance(){
	layer.alert("准备中");
}

//再发防止实施详情
function preventiveMaintenanceDetail(){
	layer.alert("准备中");
}

function initComBoxToPreventionType(selectList, htmlId){
	
	var optionHtml = "<option value=''>请选择历史故障类型</option>";
	
	$(selectList).each(function(i, e) {
		optionHtml += '<option value="' + e.groupKey + '">' + e.remarks + '</option>';
	});
	
	$("#" + htmlId).append(optionHtml);
}

//确定为相同故障
function preventionTypeConfirm(){
	var groupKey = $("#preventionType").val();
	
	if(groupKey == ""){
		layer.alert("请选择历史报修类型");
		return;
	}
	
	var remarks = $("#preventionType").find("option:selected").text();
	
	var typeDate = {
			"reId":id,
			"mouldId":mouldId,
			"groupKey":groupKey,
			"remarks":remarks
	};
	
  $.ajax({
      url: "../mould/insertMouldPreventionType.do",
      type: "POST",
      dataType: "json",
      async:false,
		beforeSend: function (XMLHttpRequest) {

          $("#preventionTypeConfirm").attr('disabled',true);
      },
      complete: function (XMLHttpRequest, textStatus) {

      	$("#preventionTypeConfirm").removeAttr('disabled');
      },
      data: typeDate,
      success: function(result) {
          if(result.ret == '1') {
          	layer.msg("确认成功");
             
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
}


function partsTypeSelect(selectList){
	var optionHtml = "<select class='select' onchange='changePartsType(this)')>"
        		   + "<option value=''>请选择</option>";
	$(selectList).each(function(i, e) {
		optionHtml += '<option value="' + e.partsType + '">' + e.partsType + '</option>';
	});
	
	optionHtml += "</select>";
	
    return optionHtml;
}


function changePartsType(obj){
	var partsType = $(obj).val();
	var mouldPartsInfo = {
			"partsType": partsType
	}
	
	var elementId = $(obj).parent().parent().children("td").eq(1).find("select");
	
	var elementId2 = $(obj).parent().parent().children("td").eq(2).find("select");
	
	$(elementId).html("");
	$(elementId2).html("");
	$(elementId2).html("<option value=''>请选择</option>");
	
	$.ajax({
		url: '../mould/initFaultPartsComboxToName.do',
		type: 'POST',
		dataType: 'JSON',
		data: mouldPartsInfo,
		async:false,
		success: function(result) {
			if(result.ret == '1') {
				var selectList = result.data;
				
				var optionHtml = "<option value=''>请选择</option>";
				if(selectList !=null && selectList.length > 0){
					$(selectList).each(function(i, e) {
						optionHtml += '<option value="' + e.partsName + '">' + e.partsName + '</option>';
					});
				}
				
				$(elementId).append(optionHtml);
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
}


function changePartsName(obj){
	
	var partsType =  $(obj).parent().parent().children("td").eq(0).find("select").val();
	
	var partsName = $(obj).val();
	var mouldPartsInfo = {
			"partsType": partsType,
			"partsName": partsName
	}
	
	var elementId = $(obj).parent().parent().children("td").eq(2).find("select");
	$(elementId).html("");
	
	$.ajax({
		url: '../mould/initFaultPartsComboxToNum.do',
		type: 'POST',
		dataType: 'JSON',
		data: mouldPartsInfo,
		async:false,
		success: function(result) {
			if(result.ret == '1') {
				var selectList = result.data;
				
				var optionHtml = "<option value=''>请选择</option>";
				if(selectList !=null && selectList.length > 0){
					$(selectList).each(function(i, e) {
						optionHtml += '<option value="' + e.uuid + '">' + e.partsNum + '</option>';
					});
				}
				
				$(elementId).append(optionHtml);

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
}

function partsRepairContentSelect(){
	var optionHtml = "<select class='select' ><option value=''>请选择</option>";
	if(mouldPartsRepairContent !=null && mouldPartsRepairContent.length > 0){
		$(mouldPartsRepairContent).each(function(i, e) {
			optionHtml += '<option value="' + e.id + '">' + e.codeName + '</option>';
		});
	}
	optionHtml += "</select>";
	
	return optionHtml;
}

//追加回显损坏部件
function faultPartsTable(partsList){
	
	//先创建行
	for(var i = 0; i<partsList.length; i++){
		var faultParts = partsList[i];
		
		$("#addTable").click();
	}
	
	
	//再遍历行赋值
	for(var i = 0; i<partsList.length; i++){
		var faultParts = partsList[i];
		
		$('#partsList tr').each(function (j) {
			
			if(i == j){
				$(this).children('td').each(function (k) {
					if(k == 0){
						
						$(this).find("select").val(faultParts.partsType).change();
						
					}else if(k == 1){
						
						$(this).find("select").val(faultParts.partsName).change();
						
					}else if(k == 2){
						
						$(this).find("select").val(faultParts.mouldPartsInfoUuid);
						
					}else if(k == 3){
						
						$(this).find("select").val(faultParts.content);
						
					}else if(k == 4){
						
						$(this).text(faultParts.quantity);
						
					}
				})
			}

		})
	}
}