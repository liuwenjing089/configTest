var Constate;
var taskId;
var spotDetailState;
var confirmState ="";
$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    var model = Request['model'];
    var cycle = Request['cycle'];
    var equipId = Request['equipId'];
    var beginTime = Request['startTime'];
    taskId = Request['taskId'];
    $("#beginTime").val(beginTime);
    $("#id").val(id);
    $("#model").val(model);
    $("#cycle").val(cycle);
    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                $("#loginuser").val(result.data.username);
                $("#userType").val(result.data.userType);
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
    query(id,model,equipId);
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
function query(id,model,equipId) {
    var type = $("#userType").val();
    //设备信息
    $.ajax({
        url: "../equip/getEquipByid.do",
        type: "POST",
        dataType: "json",
        data: {
            "id": Number(equipId)
        },
        async:false,
        success: function (result) {
            if (result.ret == '1') {
                $('#equipName').val(result.data.equipName);
                $('#equipModel').val(result.data.equipModel);
                $('#standard').val(result.data.standard);
                $("#equipNum").val(result.data.equipNum);
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

    //确认信息
    $.ajax({
        url: "../spotDetail/getDetailById.do",
        type: "POST",
        dataType: "json",
        data: {
            "id": Number(id)
        },
        async:false,
        success: function (result) {
            if (result.ret == '1') {
            	spotDetailState = result.data.state;
            	confirmState = result.data.confirmState;
            	
                //保全不显示确认按钮
                if((type==4||type==3) && confirmState == 0){
                    document.getElementById("btnSave").style.display="";
                    document.getElementById("timeApp").style.display="";
                    $("#spotDate").val(getNowDate());
                    document.getElementById("dianDate").style.display="none";
                }else if((type == 4 || type ==3) && confirmState != 0){
                	$("#btnSave").hide();
                	
                }else if(result.data.confirmState==0 && type==5 && result.data.state==1){
                    document.getElementById("btnConfirm").style.display="";
                }else if(result.data.confirmState==1 && type==6 && result.data.state==1){
                    document.getElementById("btnConfirm").style.display="";
                }
                if(type==4||type==3){
                    $('#spotMan').val($("#loginuser").val());
                }else{
                    $('#spotMan').val(result.data.spotMan);
                }
                $('#classConfirmMan').val(result.data.classConfirmMan);
                $('#classConfirmTime').val(result.data.classConfirmTime);
                $('#confirmCommander').val(result.data.confirmCommander);
                $('#confirmCommanderTime').val(result.data.confirmCommanderTime);
                $('#confirmChief').val(result.data.confirmChief);
                $("#confirmChiefTime").val(result.data.confirmChiefTime);
                Constate = result.data.state;
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

    var cycle = $("#cycle").val();
    $.ajax({
        url: "../spotCheck/getCheck.do",
        type: "POST",
        dataType: "json",
        data:{
            "spotDetailId":id,
            "principalNumbe":model,
            "spotInterval":cycle,
            "type":1,
            "state":Constate
        },
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                $("#checkList").html("");
                var checkList = result.data;
                $("#length").val(checkList.length);
                for(var i = 0; i < checkList.length; i++) {
                    var str = "spotState"+i;
                    var data = checkList[i];
                    var j = i+1;
                    if((type==4||type==3) && confirmState == 0){
                        var div  = "<tr class='text-c'>" +
                            "<td>" + j + "</td>" +
                            "<td>"+ data.spotPosition +"</td>" +
                            "<td>"+data.checkMethod +"</td>" +
                            "<td>" + data.checkProject + "</td>" +
                            "<td>"+toInterval(data.spotInterval,i)+"</td>" +
                            "<td contentEditable='true'>" + checkplanTime(data.planTime) + "</td>" +
                            "<td style='width: 10%'><select name='spotState' id='"+str+"' class='select'>"+
                            "<option  value='1'>计划</option>"+
                            "<option  value='2' selected='selected'>完成</option>"+
                            "<option  value='3'>不良要修理(不急)</option>"+
                            "<option  value='4'>不良要修理(至急)</option>"+
                            "</select>" +"</td>" +
                            "<td style='display: none'>" + checkspotTime(data.spotTime,i) +"</td>" +
                            "<td>" + pictureView(data.prictureUrl) +"</td>" +
                            "<td style='display: none'>"+ data.id +"</td>" +
                            "</tr>";
                        $("#checkList").append(div);
                        var all_options = document.getElementById(str).options;
                        for (var p=0; p<all_options.length; p++){
                            if (Number(all_options[p].value) == data.spotState) {
                                all_options[p].selected = true;
                            }
                        }
                    }else {
                        var div = "<tr class='text-c'>" +
                            "<td>" + j + "</td>" +
                            "<td>" + data.spotPosition + "</td>" +
                            "<td>" + data.checkMethod + "</td>" +
                            "<td>" + data.checkProject + "</td>" +
                            "<td>" + toInterval(data.spotInterval, i) + "</td>" +
                            "<td>" + checkplanTime(data.planTime) + "</td>" +
                            "<td>" + getspotState(data.spotState) + "</td>" +
                            "<td>" + checkSpo(data.spotTime) + "</td>" +
                            "<td>" + pictureView(data.prictureUrl) +"</td>" +
                            "<td style='display: none'>" + data.id + "</td>" +
                            "</tr>";
                        $("#checkList").append(div);
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
            }
        }
    });
}

function checkSpo(date) {
    if(date==null){
        return "";
    }else{
        return date;
    }
}

function getspotState(state) {
    if(state==1){
        return "计划";
    }else if(state==2){
        return "完成";
    }else if(state==3){
        return "不良要修理(不急)";
    }else if(state==4){
        return "不良要修理(至急)";
    }else{
        return "";
    }
}

function checkplanTime(planTime) {
    if(planTime==null ||planTime==0){
        return "";
    }else{
        return planTime;
    }
}

function checkspotTime(spotTime,j) {
    var str = "spotTime"+j;
    var nowDate = $("#spotDate").val();
    if(spotTime==null){
        return "<input type='date' id='"+str+"' name='spotTime' class='input-text' value='"+nowDate+"'>";
    }else{
        return "<input type='date' id='"+str+"' name='spotTime' class='input-text' value='"+spotTime +"'>";
    }
}

//改变日期 更改明细日期
$("#spotDate").change(function () {
    var length = $("#length").val();
    if(length>0){
        for(var i=0;i<length;i++){
            var str = "spotTime"+i;
            $("#"+str).val($("#spotDate").val());
        }
    }
});

function toInterval(interval) {
    if(interval=='1'){
        return "月";
    }else if(interval=='3'){
        return "季度";
    }else if(interval=='6'){
        return "半年";
    }else if(interval=='12'){
        return "年";
    }
}

function initComBox(selectList, htmlId){

    var optionHtml = "<option value='9'>请选择</option>";
    $(selectList).each(function(i, e) {

        optionHtml += '<option value="' + e.id + '">' + e.name + '</option>';
    });
    $("#" + htmlId).append(optionHtml);

}

function save() {
    var jdList = [];
    var flag = true;
    var checkFlag = true;
    
    $("#checkList tr").each(function (i) {
        var jd = {};
        $(this).children('td').each(function (j) {
            if (j == 5) {
                var planTime = $(this).text();
                jd["planTime"] = Number(planTime);
                
                //在已经完成的情况下，再输入计划时间就要验证是否非法
                if(spotDetailState == 1){
                    if(planTime==null||planTime==""||planTime=="0"){
                    	checkFlag = false;
                    	layer.alert("第" + (i+1) + "行的计划时间值不能为空或0");
                    	return;
                    }
                }
                
                if(planTime==null||planTime==""||planTime=="0"){
                    flag = false;
                }
            }
            else if (j == 6) {
                var str = "#spotState"+i+" option:selected";
                var spotState = $(str).val();
                jd["spotState"] = Number(spotState);
                if(spotState=="9"){
                    flag = false;
                }
            }
            else if (j == 7) {
                var str = "#spotTime"+i;
                var spotTime = $(str).val();
                jd["spotTime"] = spotTime;
                if(spotTime==null||spotTime==""){
                    flag = false;
                }
                $("#urlDate").val(spotTime);
            }
            else if (j == 9) {
                var id = $(this).text();
                jd["id"] = Number(id);
            }
        });
            jdList.push(jd);
    });

    if(!checkFlag){
    	return;
    }
    
    var jdListJson = JSON.stringify(jdList);

    $.ajax({
        url: '../spotCheck/update.do',
        type: 'POST',
        data: jdListJson,
        async:false,
        contentType: "application/json; charset=utf-8",
        beforeSend: function (XMLHttpRequest) {

        },
        complete: function (XMLHttpRequest, textStatus) {

        },
        success: function(result) {
            if(result.ret == '1') {
                layer.msg("保存成功");
                setTimeout(function(){
                    window.location.href="dateSpotDetailEquip.html?startTime="+ $("#beginTime").val() + "&taskId=" + taskId;
                }, 2000 );
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

    //更新状态
    if(flag){
        $.ajax({
            url: "../spotDetail/updateState.do",
            type: "POST",
            dataType: "json",
            async:false,
            data:{
                "id":Number($("#id").val()),
                "spotMan":$("#spotMan").val()
            },
            success: function(result) {
                if(result.ret == '1') {
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
}

//确认
function confirm() {
    var type = $("#userType").val();
    var data = null;
    //系长确认
    if(type==5){
        data={
            "id":Number($("#id").val()),
            "confirmCommander":$("#loginuser").val(),
            "confirmState":1,
            "type":1
        };
    }else if(type==6){
        //科长确认
        data={
            "id":Number($("#id").val()),
            "confirmChief":$("#loginuser").val(),
            "confirmState":2,
            "type":1
        };
    }

    $.ajax({
        url: "../spotDetail/updateConfirmState.do",
        type: "POST",
        dataType: "json",
        async:false,
        data:data,
        success: function(result) {
            if(result.ret == '1') {
                //window.location.href="spotTask.html";
            	window.location.href="dateSpotDetailEquip.html?startTime="+ $("#beginTime").val() + "&taskId=" + taskId;;
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

//更新班长确认
function classConfirm() {
    var data={
        "id":Number($("#id").val()),
        "classConfirmMan":$("#loginuser").val(),
        "confirmState":3,
        "type":1
    };
    $.ajax({
        url: "../spotDetail/updateConfirmState.do",
        type: "POST",
        dataType: "json",
        async:false,
        data:data,
        success: function(result) {
            if(result.ret == '1') {
            	//window.location.href="dateSpotDetailMould.html?startTime="+ $("#beginTime").val();
            	window.location.href="dateSpotDetailEquip.html?startTime="+ $("#beginTime").val() + "&taskId=" + taskId;;
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

function pictureView(url){
    if(url==""||url==null){
        return "";
    }
    var urls = url.split(",");
    var html="";
    if(urls != "" && urls.length > 0){
        for(var i=0; i < urls.length; i++){
            var picturUrl = urls[i];
             html += "<p>" +
                "<a href='#' onclick=\"view('"+ picturUrl +"')\" style='width:100px;' >图片"+ (i+1) +"&nbsp;点击查看</a>" +
                "<input type='text' name='fileUrls' value='"+ picturUrl +"' style='display:none'/>" +
                "</p>";
        }
        return html;
    }
}
function view(url){
    window.open(url);
}