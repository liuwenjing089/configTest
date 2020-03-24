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

    query(id, type);
});

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

function query(id, type) {
	var classification = id;
    $.ajax({
        url: "../spotRule/selectRuleList.do",
        type: "POST",
        data:{"type": type, "classification": classification},
        success: function(result) {
            if(result.ret == '1') {
            	var list = result.data;
                $("#mouleList").html("");

                for(var i = 0; i < list.length; i++) {
                    var data = list[i];
                    var div  = "<tr class='text-c'>" +
			                        "<td>" + (i+1) + "</td>" +
			                        "<td>"+ typeString(data.type) +"</td>" +
			                        "<td>"+ classificationString(data.classification, data.type) +"</td>" +
			                        "<td>" + cycleString(data.cycle) + "</td>" +   
			                        "<td>" +
			                        	"<a class='btn btn-primary radius'  style='margin-left:10px;' onclick=\"editRule('" + data.groupKey + "')\">修改</a>"+
			                        	"<a class='btn btn-primary radius'  style='margin-left:10px;' onclick=\"delRule('" + data.groupKey + "')\">删除</a>"+
			                        "</td>" +	
			                        		
			                   "</tr>";
                    $("#mouleList").append(div);
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

function typeString(type){
	var wz = "";
	
	if(type == 1){
		wz = "设备";
	}else if(type == 2){
		wz = "模具";
	}
	return wz;
}

function classificationString(classification, type){
	var wz = "";
	
	if(type == 1){
		wz = classification;
	}else if(type == 2){
		wz = "默认";
	}
	return wz;
}

function cycleString(cycle){
	var wz = "";
	
	if(type == 1){
		if(cycle == 1){
			wz = "月检";
		}else if(cycle == 3){
			wz = "季度检";
		}else if(cycle == 6){
			wz = "半年检";
		}else if(cycle == 12){
			wz = "年检";
		}else{
			wz = cycle + "次";
		}
	}else if(type == 2){
		wz = cycle + "次";
	}

	return wz;
}

//编辑一条规则
function editRule(groupKey){
	window.location.href = encodeURI("spotRuleEdit.html?id="+ id +"&annualOutput=" + annualOutput + "&type=" + type + "&groupKey=" + groupKey);
}

//删除一条规则
function delRule(groupKey){
	layer.confirm('确定删除吗？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
		    $.ajax({
		        url: "../spotRule/delRule.do",
		        type: "POST",
		        data:{"groupKey": groupKey},
		        success: function(result) {
		            if(result.ret == '1') {
		            	location.reload();
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
		}, function(){

		});

}

function createRule(){
	 window.location.href = encodeURI("spotRule.html?id="+ id +"&annualOutput=" + annualOutput + "&type=" + type);
}