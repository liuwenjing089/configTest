var passIndex = 0;
$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    passIndex = Request['passIndex'];
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
    $("#reId").val(id);
    //获取人员列表
    $.ajax({
        url: "../user/getBaoquan.do",
        type: "POST",
        dataType: "json",
        data: {
        },
        success: function (result) {
            if (result.ret == '1') {
                var list = result.data;
                for (var i = 0; i < list.length; i++) {
                    // 为Select下选框赋值
                    //先创建好select里面的option元素
                    var option = document.createElement("option");
                    //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
                    $(option).val(list[i].username);
                    //给option的text赋值,这就是你点开下拉框能够看到的东西
                    $(option).text(list[i].username + "-" + list[i].employeeName);
                    //获取select 下拉框对象,并将option添加进select
                    $('#workMan').append(option);
                }
                //去重
                $('#workMan').each(function(i,n){
                    var options = "";
                    $(n).find("option").each(function(j,m){
                        if(options.indexOf($(m)[0].outerHTML) == -1)
                        {
                            options += $(m)[0].outerHTML;
                        }
                    });
                    $(n).html(options);
                });
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
                alert(error);
            }
        }
    });
    
    //获取维修人员工作状态
    $.ajax({
        url: "../user/getUserRepairStauts.do",
        type: "GET",
        dataType: "json",
        success: function(result) {
            if(result.ret == '1') {
               viewList(result.data);
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

//表格赋值
function viewList(data){
    for(var i = 0; i < data.length; i++) {
        var userRepairStauts = data[i];
        var div  = "<tr class='text-c'>" +
			            "<td>"+ userRepairStauts.username +"</td>" +
			            "<td>"+ userRepairStauts.employeeName +"</td>" +
			            "<td>"+ (statusToString(userRepairStauts.stauts) || "") +"</td>" +
			       "</tr>";
        $("#userRepairStautsList").append(div);
    }
}

function statusToString(data){
	if(data != "" && data != null && data != undefined){
		data = "维修中"
	}else{
		date = "";
	}
	
	return data;
}


function save() {
    var workMan = $("#workMan option:selected").val();
    var id = $("#reId").val();
    //更新repair
    $.ajax({
        url: "../repair/update.do",
        type: "POST",
        dataType: "json",
        async:false,
        data:{
            "id":id,
            "workMan":workMan,
            "state":9
        },
        success: function(result) {
            if(result.ret == '1') {
            	
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
    // 更新equip_repair
    var eqReData={
        "id":Number(id),
        "maintainer":workMan,
        "mainTaskMan":workMan
    };
    var jsonData = JSON.stringify(eqReData);

    $.ajax({
        url: "../equipRepair/update.do",
        type: "POST",
        dataType: "json",
        data:jsonData,
        contentType: "application/json; charset=utf-8",
        success: function(result) {
            if(result.ret == '1') {
                layer.msg("指派成功");
                refurbish();
                setTimeout(function(){
                    //parent.window.location.href="serviceman.html";
                	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                	parent.layer.close(index); //再执行关闭
                }, 1000 );
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

function closeWindow(){
	removeIframe();
}

//刷新设备维修一览页面，同时不改变页数
function refurbish(){
	parent.reflush(passIndex);
}