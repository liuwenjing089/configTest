var sysCurrentTime;

$(document).ready(function() {
	
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
    
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    query(id);
});
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
function query(id) {
    //设置默认时间
	var nowDate = sysCurrentTime.split(" ")[0];
	var nowTime = sysCurrentTime.split(" ")[1];
	
    $("#reportReapirTime1").val(nowDate);
    $("#reportReapirTime2").val(nowTime);
    $.ajax({
        url: "../equip/getEqBylineID.do",
        type: "POST",
        dataType: "json",
        data: {
            "lineId": Number(id)
        },
        async:false,
        success: function (result) {
            if (result.ret == '1') {
                var eqList = result.data;
                for (var i = 0; i < eqList.length; i++) {
                	 var eq = eqList[i];
//                    //TODO 样式变更
//                    var radio=document.createElement('input');
//                    radio.type="radio";
//                    radio.name="equip";
//                    radio.value=eqList[i].id;
//                    radio.checked="";
//                    radio.style="width:80px;";
//                    $('#eqs').append(radio).append(eqList[i].equipName);

                	var html = "";
                	if(eq.anotherName == "" || eq.anotherName == null){
                 		html += "<li style='margin-top:1%;width:40%;float:left;display：inline-block;'><label class='am-radio-inline'>" +
			        				"<input type='radio'  value='"+ eq.id +"' name='equip' >" +
			        				"<span >"+ eq.equipName +"</span>"+
			        			"</lable></li>";
                	}else{
                		html += "<li style='margin-top:1%;width:40%;'><label class='am-radio-inline'>" +
			    		        	"<input type='radio'  value='"+ eq.id +"' name='equip' >" +
			    		        	"<span >"+ eq.equipName +"</span><br/><span style='font-size:10px'>"+ eq.anotherName +"</span>"+
		    		        	"</lable></li>";
                	}
                	$('#eqs').append(html);
                }
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
}
function save() {
	
    var reportReapirTime = $("#reportReapirTime1").val()+" "+$("#reportReapirTime2").val(),
        description = $("#description").val(); stamp = $("#stamp").val();
    
    var list= $('input:radio[name="equip"]:checked').val();
    if(list==null){
        layer.alert("请选择要报修的设备!");
        return;
    }
    
    if(isNullorEmpty(description)){
        layer.alert("故障描述不能为空!");
        return;
    }
    
    var eqCheck = $('input:radio[name="equip"]').is(":checked"); 
    if(!eqCheck){
        layer.alert("请选择设备!");
        return;
    }
    
    if(isNullorEmpty(stamp)){
        layer.alert("请勿重复提交!");
        return;
    }

    // 获取选中radio的value
    var eqId = $("input[name='equip']:checked").val();;

    var repairInput = {
        detailId: Number(eqId),
        reportRepairTime: reportReapirTime,
        appearance: description,
        stamp: stamp
    };
    
    var dataJson = JSON.stringify(repairInput);
    
    $.ajax({
        url: "../repair/addRepair.do",
        type: 'POST',
        data: repairInput,
        dataType : "json",
        async:false,
        beforeSend: function (XMLHttpRequest) {
        	$("#saveRepair").attr("disabled", true);
  			$("#stamp").val("");
        },
        complete: function (XMLHttpRequest, textStatus) {
  			$("#stamp").val("");
        },
        success: function(result) {
            if(result.ret == '1') {
                layer.msg("报修成功");
                setTimeout(function(){
                    window.location.href="site_serviceman.html";
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
                }
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("error");
        }
    });
}