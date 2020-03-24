$(document).ready(function() {

});

//设备保存
$("#saveEquipTask").click(function() {
	$("#errorView").empty();
	
	var spotName = $("#spotName").val(),
		beginTime = $("#beginTime").val();
	var spotType = $("#spotType option:selected").val();
	var year = $("#year option:selected").val();

	if(isNullorEmpty(beginTime)) {
		layer.alert("任务开始时间不能为空");
		return;
	}
	if(spotType == "9") {
		layer.alert("请选择任务类型");
		return;
	}

	//创建次数
	var times = 1;
	//任务周期
	var spotInterval;
	//设备或模具id集合  
	var ids = new Array();

	if(spotType == "1") {

		spotInterval = $("#equiqInterval").val();
//		spotInterval = $("#equiqInterval").find("option:selected").text();

		if(spotInterval == 9) {
			layer.alert("任务周期不能为空");
			return;
		}

		$("#menuFuncBase option").each(function() {
			//遍历所有option  
			var id = $(this).val(); //获取option值   
			ids.push(id);
		});

		if(ids == null || ids.length == 0) {
			layer.alert("请选择设备");
			return;
		}

	} else if(spotType == "2") {

		spotInterval = $("#mouldInterval").val();
		if(isNullorEmpty(spotInterval)) {
			layer.alert("任务周期不能为空");
			return;
		}
	}
	
	
	//验证所选设备是否已存在所选周期的点检规则
	var flag = false;

	$.ajax({
		url: "../task/checkSpot.do",
		type: "POST",
		dataType: "json",
		async:false,
		data: {
			"spotType": spotType,
			"spotInterval": spotInterval,
			"ids": ids
		},
		beforeSend: function(XMLHttpRequest) {
			$("#saveEquipTask").attr('disabled', false);
			$("#saveMouldTask").attr('disabled', false);
		},
		complete: function(XMLHttpRequest, textStatus) {
//			$("#saveEquipTask").removeAttr('disabled');
//			$("#saveMouldTask").removeAttr('disabled');
		},
		success: function(result) {
			if(result.ret == '1') {
               var data = result.data;
               if(data.code){
            	   flag = true;
            	   $("#errorView").hide();
               }else{
            	   flag = false;
            	   $("#errorView").show();
            	   errorView(data.equipList);
               }
		        
			} else if(result.ret == '3'){
				layer.msg("登录超时,请重新登录");
				setTimeout(function(){
					top.window.location.href= "login.html"
				}, 1000 );
			}else {
				$("#saveEquipTask").removeAttr('disabled');
				$("#saveMouldTask").removeAttr('disabled');
				var error = "";
				for(var i = 0; i < result.data.length; i++) {
					error += (result.data[i].message);
				}
				layer.alert(error);
			}
		}
	});
	
	if(!flag){	
		return;
	}
    //保存任务
	$.ajax({
		url: "../task/addTaskByOne.do",
		type: "POST",
		dataType: "json",
		data: {
			"spotName": spotName,
			"spotType": spotType,
			"spotInterval": spotInterval,
			"beginTime": beginTime,
			"state": 0,
			"times": times,
			"ids": ids
		},
		beforeSend: function(XMLHttpRequest) {
			$("#saveEquipTask").attr('disabled', false);
			$("#saveMouldTask").attr('disabled', false);
		},
		complete: function(XMLHttpRequest, textStatus) {
//			$("#saveEquipTask").removeAttr('disabled');
//			$("#saveMouldTask").removeAttr('disabled');
		},
		success: function(result) {
			if(result.ret == '1') {
				if(result.data == null){
//					layer.msg("保存成功");
//					setTimeout(function() {
						window.location.href = "spotTask.html"
//					}, 2000);
				}else{
					$("#saveEquipTask").removeAttr('disabled');
					$("#saveMouldTask").removeAttr('disabled');
					layer.alert(result.data);
				}

			} else if(result.ret == '3'){
				layer.msg("登录超时,请重新登录");
				setTimeout(function(){
					top.window.location.href= "login.html"
				}, 1000 );
			}else {
				$("#saveEquipTask").removeAttr('disabled');
				$("#saveMouldTask").removeAttr('disabled');
				var error = "";
				for(var i = 0; i < result.data.length; i++) {
					error += (result.data[i].message);
				}
				layer.alert(error);
			}
		}
	});
})

//模具保存
$("#saveMouldTask").click(function() {
	var spotName = $("#spotName").val(),
		beginTime = $("#beginTime").val();
	var spotType = $("#spotType option:selected").val();
	var year = $("#year option:selected").val();

	if(isNullorEmpty(beginTime)) {
		layer.alert("任务开始时间不能为空");
		return;
	}
	if(spotType == "9") {
		layer.alert("请选择任务类型");
		return;
	}

	//
	if(!$('input:radio[name="mouldCheckBox"]').is(":checked")) {
		layer.msg('请选择模具');
		return;
	}
	
	//设备或模具id集合  
	var ids = new Array();
	var annualOutput = 0;
	annualOutput =  $("input:radio[name='mouldCheckBox']:checked").parent().parent().children("td").eq(2).text();
	var mouldId = $("input:radio[name='mouldCheckBox']:checked").val();
	ids.push(mouldId);
//    $('input[name="mouldCheckBox"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数    
//    	ids.push($(this).val());//将选中的值添加到数组chk_value中    
//    	var tatr = $(this).parent().parent();
//    	var tatd = tatr.children("td").eq(2);
//    	annualOutput = tatd.text();
//    });
	
	//创建次数
	var times = 0;
	//任务周期
	var spotInterval = $("#mouldInterval").val();

	if(isNullorEmpty(spotInterval)) {
		layer.alert("任务周期不能为空");
		return;
	}
	
	//计算创建任务次数,如果不足1次，默认给1次
	times = Math.floor(annualOutput * 1/spotInterval);
	if(times < 1){
		times = 1;
	}
		
	$.ajax({
		url: "../task/addTaskByOne.do",
		type: "POST",
		dataType: "json",
		data: {
			"spotName": spotName,
			"spotType": spotType,
			"spotInterval": spotInterval,
			"beginTime": beginTime,
			"state": 0,
			"times": times,
			"year": 1,
			"ids": ids
		},
		beforeSend: function(XMLHttpRequest) {
			$("#saveEquipTask").attr('disabled', false);
			$("#saveMouldTask").attr('disabled', false);
		},
		complete: function(XMLHttpRequest, textStatus) {
//			$("#saveEquipTask").removeAttr('disabled');
//			$("#saveMouldTask").removeAttr('disabled');
		},
		success: function(result) {
			if(result.ret == '1') {
				if(result.data == null){
//					layer.msg("保存成功");
//					setTimeout(function() {
						window.location.href = "spotTask.html"
//					}, 2000);
				}else{
					$("#saveEquipTask").removeAttr('disabled');
					$("#saveMouldTask").removeAttr('disabled');
					layer.alert(result.data);

				}
			} else if(result.ret == '3'){
				layer.msg("登录超时,请重新登录");
				setTimeout(function(){
					top.window.location.href= "login.html"
				}, 1000 );
			}else {
				$("#saveEquipTask").removeAttr('disabled');
				$("#saveMouldTask").removeAttr('disabled');
				var error = "";
				for(var i = 0; i < result.data.length; i++) {
					error += (result.data[i].message);
				}
				layer.alert(error);
			}
		}
	});

})

//点击取消按钮
function layer_close() {
	window.location.href = "spotTask.html";
}

$("#spotType").change(function() {
	if($(this).val() == 1) {

		$("#equipCycle").show();
		$("#mouldCycle").hide();
		$("#equipChoose").show();
		$("#equipHtmlToSearch").show();
		$("#saveEquipTask").show();
		$("#saveMouldTask").hide();
		$("#mouldHtmlToSearch").hide();
		$("#mouldTable").hide();

	} else if($(this).val() == 2) {

		$("#equipCycle").hide();
		$("#mouldCycle").show();
		$("#equipChoose").hide();
		$("#equipHtmlToSearch").hide();
		$("#saveEquipTask").hide();
		$("#mouldHtmlToSearch").show();
		//		$("#mouldTable").show();
	} else {

		$("#equipCycle").hide();
		$("#mouldCycle").hide();
		$("#equipChoose").hide();
		$("#equipHtmlToSearch").hide();
		$("#saveEquipTask").hide();
		$("#saveMouldTask").hide();
		$("#mouldHtmlToSearch").hide();
		$("#mouldTable").hide();
	}

})

//设备搜索
function equipModelSearch() {

	
	var equipModel = $("#equipModel").val();
	
	//加载周期下拉
	//selectCycle(equipModel, 1);

	var ids = new Array();
	$("#menuFuncBase option").each(function() {
		//遍历所有option  
		var id = $(this).val(); //获取option值   
		ids.push(id);
	});

	$.ajax({
		url: '../spotRule/selectEquipModelListByEquipModel.do',
		type: 'POST',
		data: {
			equipModel: equipModel,
			ids: ids
		},
		beforeSend: function(XMLHttpRequest) {

		},
		complete: function(XMLHttpRequest, textStatus) {

		},
		success: function(result) {
			if(result.ret == '1') {

				var selectList = result.data;
				selectVaules(selectList);

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
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error");
		}

	});
}

//模具搜索
function mouldModelSearch() {

	var figureNumber = $("#figureNumber").val();

	var flag = true;
	if(figureNumber == "" || figureNumber == null) {
		layer.msg("请输入图号");
		flag = false;
	}

	if(!flag) {
		return;
	}

	$.ajax({
		url: '../spotRule/selectMouldByVehicleTypeAndFigureNumber.do',
		type: 'POST',
		data: {
			figureNumber: figureNumber
		},
		beforeSend: function(XMLHttpRequest) {

		},
		complete: function(XMLHttpRequest, textStatus) {

		},
		success: function(result) {
			if(result.ret == '1') {

				var datas = result.data;

				if(datas != null) {
					$("#mouleList").empty();
					$("#mouldTable").show();
					$("#saveMouldTask").show();
					
					for(var i = 0 ; i<datas.length; i++){
						var data = datas[i];
						var div = "<tr class='text-c'>" +
									"<td><input type='radio' name='mouldCheckBox' onclick='mouldRadioCheck(this)'   value='" + data.id + "'/></td>" +
									"<td>" + data.factoryName + "</td>" +
									"<td>" + data.annualOutput + "</td>" +
									"<td>" + data.vehicleType + "</td>" +
									"<td>" + data.figureNumber + "</td>" +
									"<td>" + data.assetCoding + "</td>" +
									"<td>" + data.model + "</td>" +
									"</td>" +
								 "</tr>";

						$("#mouleList").append(div);

					}

				} else {
					$("#mouleList").empty();
					layer.msg("无此模具！");
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

function selectVaules(selectList) {

	$("#menuFunc").empty();

	if(selectList != null && selectList.length > 0) {
		var menuFunc = "";
		$(selectList).each(function(i, e) {

		menuFunc += "<option value="+ e.id +">"+
						"<table style='width:70%;margin:0 auto;'>" +
							"<tr class='text-c'>" +
								"<td style='width:20%;text-align:left;'>"+ formatPartString(e.beltlineName)+"</td>" +
								"<td style='width:20%;text-align:left;'>"+ formatPartString(e.equipNum)+"</td>" +
								"<td style='width:40%;text-align:left;'>"+ formatPartString(e.equipName)+"</td>" +
								"<td style='width:20%;text-align:left;'>"+ formatPartString(e.equipModel)+"</td>" +
							"</tr>" +  
						"</table>" + 
				   "</option>";	 

		});
		$("#menuFunc").append(menuFunc);
	}

}

//加载周期下拉
function selectCycle(classification, type){
    $.ajax({
        url: "../spotRule/selectRuleList.do",
        type: "POST",
        data:{"type": type, "classification": classification},
        success: function(result) {
            if(result.ret == '1') {
            	var list = result.data;
            	if(list != null && list.length>0){
            		if(type == 1){
                        $("#equiqInterval").html("");
                        var optionHtml = "<option value='9'>请选择</option>";
                        
                        for(var i = 0; i < list.length; i++) {
                            var data = list[i];
                            if(data.cycle == 1){
                                optionHtml  += "<option value='1'>月检</option>";
                            }else if(data.cycle == 3){
                                optionHtml  += "<option value='3'>季度检</option>";
                            }else if(data.cycle == 6){
                                optionHtml  += "<option value='6'>半年检</option>";
                            }else if(data.cycle == 12){
                                optionHtml  += "<option value='12'>年检</option>";
                            }

                            
                        }
                        $("#equiqInterval").append(optionHtml);
            		}else{
                        $("#mouldInterval").html("");
                        var optionHtml = "<option value=''>请选择</option>";
                        
                        for(var i = 0; i < list.length; i++) {
                            var data = list[i];

                            optionHtml  += "<option value='"+ data.cycle +"'>"+ data.cycle +"次</option>";
                            
                        }
                        $("#mouldInterval").append(optionHtml);
            		}

            	}else{
            		if(type == 1){
                        $("#equiqInterval").html("");
                        var optionHtml = "<option value='9'>请选择</option>";
                        $("#equiqInterval").append(optionHtml);
            		}else{
            			$("#mouldInterval").html("");
                        var optionHtml = "<option value=''>请选择</option>";
                        $("#mouldInterval").append(optionHtml);
            		}
            		
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

//模具radio选中时
function mouldRadioCheck(obj){
	var id = $(obj).val();
	//加载周期下拉
	selectCycle(id, 2);
}

//选择设备不符合规范是错误提示
function errorView(data){
	var div = "<p style='color:red;text-align:center;'>已选列表中以下设备不存在已选任务周期的点检规则，请先创建其点检规则</p>";

	for(var i=0; i<data.length; i++){
		var equip = data[i];
		div += "<table style='width:70%;margin:0 auto;'>" +
				"<tr class='text-c'>" +
			        "<td style='width:20%;text-align:left;'>" + formatPartString(equip.lineName) + "</td>" +
			        "<td style='width:20%;text-align:left;'>" + formatPartString(equip.equipNum) + "</td>" +
			        "<td style='width:40%;text-align:left;'>" + formatPartString(equip.equipName) + "</td>" +
			        "<td style='width:20%;text-align:left;'>" + equip.equipModel + "</td>" +
			    "</tr>"+
		        "</table>";
	}
	
	$("#errorView").append(div);
}

function formatPartString(data){
	
	var len = strlen(data);
	
	if(len < 22){
		for(var i = 0; i< (22 - len); i++){
			data += "&ensp;";
		}
	}
	return data;
}

function strlen(str){
    var len = 0;
    for (var i=0; i<str.length; i++) { 
     var c = str.charCodeAt(i); 
    //单字节加1 
     if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
       len++; 
     } 
     else { 
      len+=2; 
     } 
    } 
    return len;
}