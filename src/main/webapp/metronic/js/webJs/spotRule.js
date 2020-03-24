var type;
var id;
var annualOutput;
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

    
    var Request = new Object();
    Request = GetRequest();
    id = Request['id'];
    annualOutput = Request['annualOutput'];
    type = Request['type'];
	$("#type").val(type);
    headerAssignment(type, annualOutput);
});

function headerAssignment(type, annualOutput){
	if(type == 2){
		$("#mouldFile").show();
        $("#soptIncTh").show();
        $("#soptTypeString").show();     
		$("#mouldSopt").show();
		$("#spotInspectionInput").show();
		$("#equipmentSpot").hide();
		$("#annualOutput").val(annualOutput);

		
		querySpotTypeSelect();
	}else if(type == 1){
		$("#mouldFile").hide();
        $("#soptIncTh").hide();
        $("#soptTypeString").hide();    
		$("#equipmentType").val(id);
		$("#spotInspectionInput").hide();
		$("#equipmentSpot").show();
		$("#mouldSopt").hide();

	}
}

function GetRequest() {
    var url = decodeURI(location.search); //获取url中"?"符后的字串
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


// 点击检索
function search() {

}

//添加规则明细
function addRow(){
	var index = 1;
	
	var orderList = $("#spotRuleList").find("tr");
	if(orderList.length > 0){
		var xh = $('table:eq(0) tr:last');
		var ss = xh.children("td").eq(0).text();
		index = Number(ss) + 1;
	}
	
	var spotInspection = $("#spotInspection").val();
	var spotPosition = $("#spotPosition").val();
	var checkProject = $("#checkProject").val();
    var spotMethod = $("#spotMethod").val();
    var remarks = $("#remarks").val();
    var spotType = $("#spotType").val();

	//验证
	var formValidation = fv(spotInspection, spotPosition, checkProject, spotMethod, remarks, spotType);
	if(!formValidation){
		return;
	}
	
	//获取点检类型文字
    var spotTypeString = $("#spotType").find("option:selected").text();
    
    //获取图片url和名字
    var pricturList = [];
    
    var obj = $("input[name='fileUrls']");
    if(obj.length > 0){
        $(obj).each(function(j,item){
        	var pricturView = {};
            pricturView.name = $(this).attr("data-fileName");
            pricturView.url = item.value;
            pricturList.push(pricturView);
          });
    }
    
	var tablePricture = "";
	if(pricturList != null && pricturList.length > 0){
		for(var i = 0; i<pricturList.length; i++){
			var pricturView = pricturList[i];
			tablePricture += "<p>" +
			                 "<input type='text' value='"+ pricturView.url +"' style='display:none'/>" +
					         "<a href='#' onclick=\"view('"+ pricturView.url +"')\" style='margin-left:50px;width:100px;' id='frName8' >"+ pricturView.name +"</a></p>";
		}
	}
	
	var spotInspectionTd = "";
	if(type == 1){
		spotInspectionTd = "<td style='display:none'></td><td style='display:none'></td><td style='display:none'></td>";
	}else if(type == 2){
		spotInspectionTd = "<td contenteditable='true'>"+ spotInspection +"</td>" +
						   "<td style='display:none'>"+ spotType +"</td>"+ 
						   "<td>"+ spotTypeString +"</td>";
	}
	
	var htmlRow = "<tr class='text-c'>" + 
					"<td>"+ index +"</td>" + 
					spotInspectionTd + 
					"<td contenteditable='true'>"+ spotPosition +"</td>" + 
					"<td contenteditable='true'>"+ checkProject +"</td>" + 
					"<td contenteditable='true'>"+ spotMethod +"</td>" + 
					"<td>"+ remarks +"</td>" + 
					"<td>"+ tablePricture +"</td>" + 
					"<td><a class='btn btn-primary radius'  style='margin-left:10px;' onclick='delRow(this)'>删除</a></td>" + 
                  "</tr>";
	
	$("#spotRuleList").append(htmlRow);
	
	//重置表单
	document.getElementById("rule").reset();
    $("#uploadInf").empty();
    $("#preview").empty();
}

function delRow(obj){
	$(obj).parent().parent().remove();
	resetOrderNo();
}


//重置序号
function resetOrderNo() {
    var orderList = $("#spotRuleList").find("tr");
    for (var i = 0; i < orderList.length; i++) {
        $(orderList[i]).attr("data-item", i);
        $(orderList[i]).find("td:eq(0)").html(i + 1);
    }
}

function view(url){
	window.open(url);
}

//取值提交后台
function addRule(){
	
  	if(type == 1){
  		var equipmentType = $("#equipmentCycle").val();
        if(equipmentType == ""){
        	layer.msg("请输入点检周期");
        	return;
        }
  	}else if(type == 2){
  		var mouldCycle = $("#mouldCycle").val();
        if(mouldCycle == ""){
        	layer.msg("请输入点检周期");
        	return;
        }
  	}
  	
	var spotRule = getValue();
	
	if(spotRule == null || spotRule.length == 0){
		layer.msg("请添加规则");
		return;
	}
	
    var spotRuleJson = JSON.stringify(spotRule);

    
    $.ajax({
	  url: '../spotRule/add.do',
	  type: 'POST',
      data: spotRuleJson,
      async:false,
      contentType: "application/json; charset=utf-8",
	  beforeSend: function (XMLHttpRequest) {

      },
      complete: function (XMLHttpRequest, textStatus) {

      },
		success: function(result) {
			if(result.ret == '1') {
//                layer.msg("创建成功！");
//				setTimeout(function(){
					window.location.href= encodeURI("mouldSpotRuleList.html?id="+ id +"&annualOutput=" + annualOutput + "&type=" + type)
//				}, 2000 );
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

function getValue(){
	//取值
    var ruleList = [];

    $("#spotRuleList tr").each(function (i) {
    	
		  var spotRule = {};
		  
		  $(this).children('td').each(function (j) { 
			  
			  	spotRule["type"] = type;
			  	
			  	if(type == 1){
			  		spotRule["classification"] = $("#equipmentType").val();
			  		spotRule["cycle"] = $("#equipmentCycle").val();
			  	}else if(type == 2){
			  		spotRule["classification"] = id;
			  		spotRule["cycle"] = $("#mouldCycle").val();
			  	}
			  			  
	            if (j == 1) {
	            	var spotInspection = $(this).text().replace(/(^\s*)|(\s*$)/g, "");
	            	spotRule["spotInspection"] = spotInspection;
	                
	            }else if (j == 2) {
	            	if(type == 1){
						spotRule["spotType"] = 0;
					} else if(type == 2){
						var spotType = $(this).text().replace(/(^\s*)|(\s*$)/g, "");
						spotRule["spotType"] = spotType;
					}
				}else if (j == 4) {
	            	var spotPosition = $(this).text().replace(/(^\s*)|(\s*$)/g, "");
	            	spotRule["spotPosition"] = spotPosition;
	            	
				}else if (j == 5) {
	            	var checkProject = $(this).text().replace(/(^\s*)|(\s*$)/g, "");
	            	spotRule["checkProject"] = checkProject;
				}else if (j == 6) {
	            	var checkMethod = $(this).text().replace(/(^\s*)|(\s*$)/g, "");
	            	spotRule["checkMethod"] = checkMethod;
				}         
	             else if (j == 7) {
	            	var remarks = $(this).text().replace(/(^\s*)|(\s*$)/g, "");
	            	spotRule["remarks"] = remarks;
				}            
	             else if (j == 8) {
	            	 
	            	var fileUrls = $(this).children("p");
	            	var prictureUrl = "";
	            	$.each(fileUrls, function (i) {
	            		var fileUrl = $(this).find("input").eq(0).val();
	            		prictureUrl += i > 0 ? ","+fileUrl : fileUrl;
	            	})
	            	spotRule["prictureUrl"] = prictureUrl;
				}
		  })
		  ruleList.push(spotRule);
    })
    
    ruleList[0].mouldSpotRuleUrl = $("#ulfUrl").val();
    
    return ruleList;
}

function fv(spotInspection, spotPosition, checkProject, spotMethod, remarks, spotType){
	var flag = true;
	
	if(type == 2){
		if(spotInspection == ""){

			layer.alert("请输入点检部位");
			flag = false;
			return flag;
		}
	}
	
	if(type == 2){
		if(spotType == "0"){

			layer.alert("请选择点检类型");
			flag = false;
			return flag;
		}
	}

	if(spotPosition == ""){

		layer.alert("请输入检查项目");
		flag = false;
		return flag;
	}
	
	if(checkProject == ""){
		
		layer.alert("请输入点检基准");			
		flag = false;
		return flag;
	}
	
	if(spotMethod == ""){
		layer.alert("请输入检查方法");
		flag = false;
		return flag;
	}
	
	// if(remarks == ""){
	// 	layer.alert("请输入备注");
	// 	flag = false;
	// 	return flag;
	// }
	return flag;
}

//查询模具  点检类型下拉框
function querySpotTypeSelect(){
    $.ajax({
  	  url: '../spotRule/selectCodeListBySpotType.do',
  	  type: 'GET',
      async:false,
  	  beforeSend: function (XMLHttpRequest) {

      },
      complete: function (XMLHttpRequest, textStatus) {

      },
      success: function(result) {
  			if(result.ret == '1') {
                var selectList = result.data;
  				var optionHtml = "<option value='0'>请选择</option>";
  
  				$(selectList).each(function(i, e) {

  					optionHtml += "<option  value='" + e.id + "' >" + e.codeName + "</option>";
  				});
  				$("#spotType").append(optionHtml);
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

//上传规则总图片
function ulfs(obj){
	var f = $(obj).val();
	var htmlId = $(obj).attr('id');
	htmlId = htmlId.slice(8);
	//alert(f);
	if (f == null || f == undefined || f == '') {
		return false;
	}
	if (!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f)) {
		alert("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
		$(obj).val('');
		return false;
	}
	var data = new FormData();
	$.each($(obj)[0].files, function (i, file) {
		data.append('file', file);
	});
	$.ajax({
		type: "POST",
		url: "../file/uploadImg.do",
		data: data,
		cache: false,
		contentType: false,    //不可缺
		processData: false,    //不可缺
		dataType: "json",
		success: function (result) {
			if (result.ret == '1') {
				var data = result.data;
				$("#ulfUrl").val(data);
				
				var index = data.lastIndexOf("\/");  
				var pictureName  = data .substring(index + 1, data .length);
				
				$("#soptRulePicture").show();
				document.getElementById("soptRulePicture").href = data;
			} else {

				alert(error);
			}
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) {
			alert("上传失败，请检查网络后重试");
		}
	});
}