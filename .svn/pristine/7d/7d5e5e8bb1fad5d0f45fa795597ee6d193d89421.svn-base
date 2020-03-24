var deId;
var passIndex;
$(document).ready(function() {
	
    var Request = new Object();
    Request = GetRequest();
    deId = Request['id'];
    passIndex = Request['passIndex'];
	
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
    queryMouldRepair();
    query();
});

function queryMouldRepair(){
	//页面赋值
    $.ajax({
        url: "../mouldPreventionType/selectRepairMouldByRepairIds.do",
        type: "POST",
        dataType: "json",
        data:{"deId": deId},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
               var data = result.data;
               var div = "<tr class='text-c'>";
               div += "<td>" + data.repairNumber + "</td>" +
               
               "<td>" + data.vehicleType + "</td>" +
               "<td>" + data.figureNumber + "</td>" +
               "<td>" + data.lineName + "</td>" +
               "<td>" + data.phenomenalDescription + "</td>" +
               "<td>" + getState(data.state) + "</td>" +
               "<td>" + data.applicantName + "</td>" +
               "<td>" + data.reportRepairTime + "</td>";
               "</tr">
               $("#mouleRepair").append(div);
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


function query() {
	
    $.ajax({
        url: "../testMould/getTestMouldList.do?deId=" + deId,
        type: "GET",
        success: function(result) {
            if(result.ret == '1') {
                $("#mouleList").html("");
                var mouleList = result.data.TestMouldList;
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
                
                //判断是否有未完成的记录
                //addView(result.data.TestMould);
                
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


function editMould(id){
    window.location.href="editMould.html?id="+id;
}


function saveTestMoule(){
    //验证表单
	//var predictedTestMouldTime = $("#predictedTestMouldTime").val().replace("T"," ");
	var predictedTestMouldTime = "";

	var testMouldStartTime = $("#testMouldStartTime").val().replace("T"," ");
	if(testMouldStartTime == ""){
		layer.msg("试模开始时间不能为空");
		return;
	}
	
	var testMouldEndTime = $("#testMouldEndTime").val().replace("T"," ");
	if(testMouldEndTime == ""){
		layer.msg("试模结束时间不能为空");
		return;
	}
	
	
	if(testMouldEndTime < testMouldStartTime){
		layer.msg("试模结束时间不能小于试模开始时间");
		return;
	}
	
	var testMouldResult = $("input:radio[name='testMouldResult']:checked").val();
    if(testMouldResult==null){
        alert("请选择试模结果!");
        return;
    }
	
	var remarks = $("#remarks").val();
	
	var id = $("#id").val();
	if(id == ""){
		id = 0;
	}
	
	var testMould = {
			id: id,
			deId: deId,
			predictedTestMouldTime: predictedTestMouldTime,
			testMouldStartTime: testMouldStartTime,
			testMouldEndTime: testMouldEndTime,
			testMouldResult: testMouldResult,
			remarks: remarks
	}
	
    $.ajax({
  		url: '../testMould/updateTestMould.do',
  		type: 'POST',
  		data: testMould,
        dataType : "json",
        async:false,
  		beforeSend: function (XMLHttpRequest) {
            $("#save").attr('disabled',true);
        },
        complete: function (XMLHttpRequest, textStatus) {
        	$("#save").removeAttr('disabled');
        },
  		success: function(result) {
  			if(result.ret == '1') {
                layer.msg("保存成功");
                query();
                $("#add").hide();
                $("#save").hide();
        		$("#testMouldStartTime").val("");
        		$("#testMouldEndTime").val("");
        		$("#remarks").val("");
        		$("#id").val("");
        		$("input:radio[name='testMouldResult']").removeAttr("checked");
        		refurbish();
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
}

function delMould(id) {
    $.ajax({
        url: "../equip/deleteEq.do",
        type: "POST",
        dataType: "json",
        data: {
            "id": id
        },
        success: function (result) {
            if (result.ret == '1') {
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
                layer.alert(error);
            }
        }
    });
    window.location.href="equipment-list.html"
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

//判断时候有未完成的记录
function addView(data){
	if(data == null){
		$("#addTestMould").show();
		$("#add").hide();
		$("#predictedTestMouldTime").val("");
		$("#testMouldStartTime").val("");
		$("#testMouldEndTime").val("");
		$("#remarks").val("");
		$("#id").val("");
		$("input:radio[name='testMouldResult']").removeAttr("checked");
		
		
	}else{
		$("#addTestMould").hide();
		$("#add").show();
		
	    var predictedTestMouldTime = data.predictedTestMouldTime.replace(" ", "T");
		$("#predictedTestMouldTime").val(predictedTestMouldTime);
		
		if(data.testMouldStartTime != null && data.testMouldStartTime != ""){
			var testMouldStartTime = data.testMouldStartTime.replace(" ", "T");			
			$("#testMouldStartTime").val(testMouldStartTime);
		}

		$("input:radio[value='"+ data.testMouldResult +"']").attr('checked','true');
		$("#remarks").val(data.remarks);		
		$("#id").val(data.id);
	}
}

function testMould_add(){
	$("#add").show();
	$("#save").show();
}

function closeWindow(){
	removeIframe();
}

//刷新设备维修一览页面，同时不改变页数
function refurbish(){
	var repairIfram = window.parent.document.getElementById("repairPrevention");
	var repairHtml = repairIfram.contentWindow;
	repairHtml.reflush(passIndex);
}


function getState(state){
    if(state==1){
        return "未修理";
    }else if(state==2){
        return "修理中";
    }else if(state==3){
        return "修理完了";
    }else if(state==4){
        return "成型班长确认完了";
    }else if(state==5){
        return "系长确认完了";
    }else if(state==6){
        return "科长确认完了";
    }else if(state==7){
        return "品保班长确认完了";
    }
}