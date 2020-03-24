var id;
var figureNumber;
var model;
var sysCurrentTime;
$(document).ready(function() {
//    var Request = new Object();
//    Request = GetRequest();
//    id = Request['id'];
	id = parent.$("#transmit_id").val();
	figureNumber = parent.$("#transmit_figureNumber").val();
	model = parent.$("#transmit_model").val();
    
	//页面赋值
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
                $("#applicant").val(result.data.username);
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
    
    $.ajax({
        url: "../getCurrentTime/getTime.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
            	sysCurrentTime = result.data;
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
    
    //故障类型赋值
	$.ajax({
		url: '../mould/initAllTypeCombox.do',
		type: 'GET',
		dataType: 'JSON',
		async:false,
		success: function(result) {
			if(result.ret == '1') {

				initComBox(result.data.failurePeriodCombox, "failurePeriod");
				
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
    
    
    query();
	//页面赋值
    $("#figureNumber").val(figureNumber);
    if(model == "" || model == null || model == "null"){
    	model = "";
    }
    $("#model").val(model);
	var nowDate = sysCurrentTime.split(" ")[0];
	var nowTime = sysCurrentTime.split(" ")[1];
    
    $("#reportRepairDate").val(nowDate);
    $("#reportRepairTime").val(nowTime);
    
});

function query(){
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
}

//点击取消按钮
function layer_close(){
    window.location.href = "equipment-list.html";
}

//点击提交
function save() {
	var flag = "";
	//验证
	var formValidation = fv();
	if(!formValidation.flag){
		return formValidation.msg;
	}

	
	//取值
	var data = getValue();

      $.ajax({
  		url: '../mould/repairMould.do',
  		type: 'POST',
  		data: data,
        dataType : "json",
        async:false,
  		beforeSend: function (XMLHttpRequest) {
  			$("#stamp").val("");
        },
        complete: function (XMLHttpRequest, textStatus) {
        	$("#stamp").val("");
        },
  		success: function(result) {
  			if(result.ret == '1') {
  				flag = 1;
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

//表单验证
function fv(){
	var flag = {};
	flag["flag"] = true;
	
	var boolCheck = $("#failurePeriod").val(); 
	if(boolCheck == 0){
		flag["msg"] = "请选择故障时期";
		flag["flag"] = false;
	}
	
	var phenomenalDescription = $("#phenomenalDescription").val();
	phenomenalDescription = $.trim(phenomenalDescription);
	if(phenomenalDescription == ""){
		flag["msg"] = "请填写故障内容";
		flag["flag"] = false;
	}
	
	var line = $("#line").val();
	if(line == "0"){
		flag["msg"] = "选择成型机";
		flag["flag"] = false;
	}
	
	var applicant = $("#applicant").val();
	if(applicant == ""){
		flag["msg"] = "登录过期,请重新登录";
		flag["flag"] = false;
	}
	
	var stamp = $("#stamp").val();
	if(stamp == ""){
		flag["msg"] = "请勿重复提交";
		flag["flag"] = false;
	}
	
	return flag;
}

//页面取值
function getValue(){
	var data = {};
    data["mouldId"] = id;
    data["applicant"] = $("#applicant").val();
//    data["reportRepairTime"] = $("#reportRepairDate").val() + "  " + $("#reportRepairTime").val();
    data["failurePeriod"] = $("#failurePeriod").val();
    data["phenomenalDescription"] = $.trim($("#phenomenalDescription").val());
    data["line"] = $("#line").val();
    data["stamp"] = $("#stamp").val();
    return data;
}

function initComBox(selectList, htmlId){
	var optionHtml = "<option value='0'>请选择</option>";
	$(selectList).each(function(i, e) {
		optionHtml += '<option value="' + e.id + '">' + e.name + '</option>';
	});
	$("#" + htmlId).append(optionHtml);
}
