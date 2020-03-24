var passIndex = 0;
$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    passIndex = Request['passIndex'];
    
    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                $("#loginuser").val(result.data.username+ "-" + result.data.employeeName);
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
    $.ajax({
        url: "../kyList/getWorkName.do",
        type: "POST",
        dataType: "json",
        data: {
            "id":id
        },
        async:false,
        success: function (result) {
            if (result.ret == '1') {
                document.getElementById('workName').innerText=result.data.beltlineName +"-"+result.data.equipName+"-"+result.data.appearance;
                document.getElementById('commandMan').innerText=$("#loginuser").val();
                document.getElementById('workMan').innerText=$("#loginuser").val();
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

    //获取人员列表
    $.ajax({
        url: "../user/getBaoquan.do",
        type: "POST",
        dataType: "json",
        data: {
        },
        async:false,
        success: function (result) {
            if (result.ret == '1') {
                var list = result.data;
                for (var i = 0; i < list.length; i++) {
                    // 为Select下选框赋值
                    //先创建好select里面的option元素
                    var option = document.createElement("option");
                    if(i==0){
                        var option1 = document.createElement("option");
                        $(option1).val("");
                        //给option的text赋值,这就是你点开下拉框能够看到的东西
                        $(option1).text("请选择");
                        //获取select 下拉框对象,并将option添加进select
                        $('#allMan').append(option1);
                    }
                    //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
                    $(option).val(list[i].username);
                    //给option的text赋值,这就是你点开下拉框能够看到的东西
                    $(option).text(list[i].username + "-" + list[i].employeeName);
                    //获取select 下拉框对象,并将option添加进select
                    $('#allMan').append(option);
                }
                //去重
                $('#allMan').each(function(i,n){
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
}

//保存
function save() {
    var check1 = 0;
    if(document.getElementById("check1").checked){
        check1 = 1;
    }
    var check2 = 0;
    if(document.getElementById("check2").checked){
        check2 = 1;
    }
    
    var selectValue = $("#allMan option:selected").val();
    if(check2 == 1 && selectValue==""){
    	layer.msg("请选择指挥者姓名");
    	return;
    }
    
    var check3 = 0;
    if(document.getElementById("check3").checked){
        check3 = 1;
    }
    var check4 = 0;
    if(document.getElementById("check4").checked){
        check4 = 1;
    }
    var check5 = 0;
    if(document.getElementById("check5").checked){
        check5 = 1;
    }
    var check6 = 0;
    if(document.getElementById("check6").checked){
        check6 = 1;
    }
    var check7 = 0;
    if(document.getElementById("check7").checked){
        check7 = 1;
    }
    var id = $("#reId").val();
    var workName = $("#workName").text().split("-")[0];
    var workTips = document.getElementById('workTips').innerText;
    var commandMan = $("#commandMan").text().split("-")[0];
    var workMan = $("#workMan").text().split("-")[0];
    var proName = $("#allMan option:selected").val();
    if(check1 == 0){
        layer.alert("请确认作业安全检查");
        return;
    }
    if(check2 == 1 && proName=="请选择"){
        layer.alert("请选择指挥者");
        return;
    }

    $.ajax({
        url: "../kyList/add.do",
        type: "POST",
        dataType: "json",
        data: {
            "reId":Number($("#reId").val()),
            "workName": workName,
            "proName": proName,
            "assetsNum":"",
            "workTips":"",
            "commandMan":commandMan,
            "workMan":workMan,
            "check1":check1,
            "check2":check2,
            "check3":check3,
            "check4":check4,
            "check5":check5,
            "check6":check6,
            "check7":check7
        },
        async:false,
  		beforeSend: function (XMLHttpRequest) {

            $("#saveButton").attr('disabled',true);
        },
        complete: function (XMLHttpRequest, textStatus) {

//        	$("#save").removeAttr('disabled');
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

    //插入维修开始时间
    $.ajax({
        url: "../repair/update.do",
        type: "POST",
        dataType: "json",
        async:false,
        data:{
            "id":Number(id),
            "beginTime":"beginTime",
            "state":9
        },
        success: function(result) {
            if(result.ret == '1') {
                layer.msg("保存成功");
                refurbish();
                setTimeout(function(){
//                    window.location.href="serviceman.html";
                	removeIframe();
                }, 2000 );
                
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

function change1() {
    var obj = document.getElementById("check1");
    if (obj.checked == true) {
        $("#ch1-1").prop("checked", true);
        $("#ch1-2").prop("checked", true);
        $("#ch1-3").prop("checked", true);
    }else{
        $("#ch1-1").prop("checked", false);
        $("#ch1-2").prop("checked", false);
        $("#ch1-3").prop("checked", false);
    }
}
function change2() {
    var obj = document.getElementById("check2");
    if (obj.checked == true) {
        $("#ch2-1").prop("checked", true);
        $("#ch2-2").prop("checked", true);
    }else{
        $("#ch2-1").prop("checked", false);
        $("#ch2-2").prop("checked", false);
    }
}
function change3() {
    var obj = document.getElementById("check3");
    if (obj.checked == true) {
        $("#ch3-1").prop("checked", true);
        $("#ch3-2").prop("checked", true);
        $("#ch3-3").prop("checked", true);
    }else{
        $("#ch3-1").prop("checked", false);
        $("#ch3-2").prop("checked", false);
        $("#ch3-3").prop("checked", false);
    }
}
function change4() {
    var obj = document.getElementById("check4");
    if (obj.checked == true) {
        $("#ch4-1").prop("checked", true);
        $("#ch4-2").prop("checked", true);
        $("#ch4-3").prop("checked", true);
        $("#ch4-4").prop("checked", true);
    }else{
        $("#ch4-1").prop("checked", false);
        $("#ch4-2").prop("checked", false);
        $("#ch4-3").prop("checked", false);
        $("#ch4-4").prop("checked", false);
    }
}
function change5() {
    var obj = document.getElementById("check5");
    var obj1 = document.getElementById("check6");
    var obj2 = document.getElementById("check7");
    if (obj.checked == true) {
        $("#ch5-1").prop("checked", true);
        $("#ch5-2").prop("checked", true);
        $("#ch5-3").prop("checked", true);
        $("#ch7-3").prop("checked", true);
    }else{
        $("#ch5-1").prop("checked", false);
        $("#ch5-2").prop("checked", false);
        $("#ch5-3").prop("checked", false);
        if(obj1.checked == false &&obj2.checked == false){
            $("#ch7-3").prop("checked", false);
        }
    }
}

function change6() {
    var obj1 = document.getElementById("check5");
    var obj = document.getElementById("check6");
    var obj2 = document.getElementById("check7");
    if (obj.checked == true) {
        $("#ch6-1").prop("checked", true);
        $("#ch6-2").prop("checked", true);
        $("#ch7-3").prop("checked", true);
    }else{
        $("#ch6-1").prop("checked", false);
        $("#ch6-2").prop("checked", false);
        if(obj1.checked == false &&obj2.checked == false){
            $("#ch7-3").prop("checked", false);
        }
    }
}
function change7() {
    var obj1 = document.getElementById("check5");
    var obj2 = document.getElementById("check6");
    var obj = document.getElementById("check7");
    if (obj.checked == true) {
        $("#ch7-1").prop("checked", true);
        $("#ch7-2").prop("checked", true);
        $("#ch7-3").prop("checked", true);
    }else{
        $("#ch7-1").prop("checked", false);
        $("#ch7-2").prop("checked", false);
        if(obj1.checked == false &&obj2.checked == false){
            $("#ch7-3").prop("checked", false);
        }
    }
}

//刷新设备维修一览页面，同时不改变页数
function refurbish(){
	var repairIfram = window.parent.document.getElementById("repair");
	var repairHtml = repairIfram.contentWindow;
	repairHtml.reflush(passIndex);
}

//暂不维修按钮
function custom_close(){
	removeIframe();
}