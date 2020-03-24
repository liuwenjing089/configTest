var Constate;
var taskId;
var spotDetailState;
var confirmState ="";
$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    var cycle = Request['cycle'];
    var mouldId = Request['mouldId'];
    var beginTime = Request['startTime'];
    taskId = Request['taskId'];
    $("#beginTime").val(beginTime);
    $("#id").val(id);
    $("#mouldId").val(mouldId);
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
    query(id,mouldId,cycle);
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
function query(id,mouldId,cycle) {
    var type = $("#userType").val();
    $.ajax({
        url: "../mould/selectMouldById.do",
        type: "POST",
        dataType: "json",
        data: {
            "id": Number(mouldId)
        },
        async:false,
        success: function (result) {
            if (result.ret == '1') {
                var data = result.data;
                $("#vehicleType").val(data.vehicleType);
                $("#figureNumber").val(data.figureNumber);
            }else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            } else {
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
                    $("#spotDate").val(getNowDate());
                    document.getElementById("btnSave").style.display="";
                    if(result.data.confirmState==0){
                        document.getElementById("timeApp").style.display="";
                    }
                    document.getElementById("dianDate").style.display="none";
                }else if((type == 4 || type ==3) && confirmState != 0){
                	$("#btnSave").hide();
                	
                }else if(result.data.confirmState==3 && type==5 && result.data.state==1){
                    document.getElementById("btnConfirm").style.display="";
                }else if(result.data.confirmState==1 && type==6 && result.data.state==1){
                    document.getElementById("btnConfirm").style.display="";
                }else if(type==2 && result.data.classConfirmMan==null && result.data.state==1){
                    document.getElementById("btnClassConfirm").style.display="";                 
                }
                if(type==4||type==3){
                    $('#spotMan').val($("#loginuser").val());
                }else{
                    $('#spotMan').val(result.data.spotMan);
                    document.getElementById("upPic").style.display="none";
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
            "principalNumbe":Number(mouldId),
            "spotInterval":cycle,
            "type":2,
            "state":Constate
        },
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                var flag = true;
                $("#checkList").html("");
                var checkList = result.data;
                $("#length").val(checkList.length);
                for(var i = 0; i < checkList.length; i++) {
                    var k=0;
                    var str = "isUnusual"+i;
                    var str1 = "isUpdate"+i;
                    var str2 = "batchUrl"+i;
                    var data = checkList[i];
                    var j = i+1;
                    if((type==4||type==3) && confirmState == 0){
                        if(data.mouldRuleUrl!=null&&flag){
                            // $("#checkList1").append("<tr class='text-c'><td>"+pictureViewAll(data.mouldRuleUrl)+"</td></tr>");
                            $("#checkList1").append("<tr class='text-c'><td><img src ='"+data.mouldRuleUrl+"' width ='95%'/></td></tr>");
                            flag = false;
                        }
                        if(data.spotTypeName!="重点部位点检"){
                            var div  = "<tr class='text-c'>" +
                                "<td>" + j + "</td>" +
                                "<td>"+ checkunusualText(data.spotTypeName) +"</td>" +
                                "<td>"+ data.spotInspection +"</td>" +
                                "<td>"+ data.checkProject +"</td>" +
                                "<td>"+data.spotPosition +"</td>" +
                                "<td>" + data.checkMethod + "</td>" +
                                "<td>"+getIsNot(str)+"</td>" +
                                "<td contentEditable='true' >" + checkunusualText(data.unusualText) + "</td>" +
                                "<td>"+getIsNot(str1)+"</td>" +
                                "<td contentEditable='true'>" + checkupdateText(data.updateText) +"</td>" +
                                "<td style='display:none;'>" + checkspotTime(data.spotTime,i) +"</td>" +
                                "<td>" + data.remarks +"</td>" +
                                "<td>" + pictureView(data.prictureUrl) +"</td>" +
                                "<td><input accept='image/png,image/jpeg' type='file' multiple onchange=\"ulfs(this,'"+ str2 +"')\"></td>" +
                                "<td style='display:none;'><input type='text' id='"+str2+"' style='display: none'></td>" +
                                "<td style='display: none'>"+ data.id +"</td>" +
                                "<td>" + pictureView(data.uploadUrl) +"</td>" +
                                "</tr>";
                            $("#checkList").append(div);
                        }else{
                            var str3 = "batchUrlz"+k;
                            var div  = "<tr class='text-c'>" +
                                "<td>" + j + "</td>" +
                                "<td>"+ checkunusualText(data.spotTypeName) +"</td>" +
                                "<td>"+ data.spotInspection +"</td>" +
                                "<td>"+ data.checkProject +"</td>" +
                                "<td>"+data.spotPosition +"</td>" +
                                "<td>" + data.checkMethod + "</td>" +
                                "<td>"+getIsNot(str)+"</td>" +
                                "<td contentEditable='true' >" + checkunusualText(data.unusualText) + "</td>" +
                                "<td>"+getIsNot(str1)+"</td>" +
                                "<td contentEditable='true'>" + checkupdateText(data.updateText) +"</td>" +
                                "<td style='display:none;'>" + checkspotTime(data.spotTime,i) +"</td>" +
                                "<td>" + data.remarks +"</td>" +
                                "<td>" + pictureView(data.prictureUrl) +"</td>" +
                                "<td><input accept='image/png,image/jpeg' type='file' multiple onchange=\"ulfs(this,'"+ str3 +"')\"></td>" +
                                "<td style='display:none;'><input type='text' id='"+str3+"' style='display: none'></td>" +
                                "<td style='display: none'>"+ data.id +"</td>" +
                                "<td>" + pictureView(data.uploadUrl) +"</td>" +
                                "</tr>";
                            $("#checkList2").append(div);
                            k+=1;
                        }
                        if(data.isUnusual==0){
                            $('input:radio[name="'+str+'"][value="2"]').attr("checked",true);
                        }else{
                            var va1=String(data.isUnusual);
                            $('input:radio[name="'+str+'"][value="'+va1+'"]').attr("checked",true);
                        }
                        if(data.isUpdate==0){
                            $('input:radio[name="'+str1+'"][value="2"]').attr("checked",true);
                        }else{
                            var va2=String(data.isUpdate);
                            $('input:radio[name="'+str1+'"][value="'+va2+'"]').attr("checked",true);
                        }
                    }else{
                        if(data.mouldRuleUrl!=null&&flag){
                            //$("#checkList1").append("<tr class='text-c'><td>"+pictureViewAll(data.mouldRuleUrl)+"</td></tr>");
                            $("#checkList1").append("<tr class='text-c'><td><img src ='"+data.mouldRuleUrl+"' width ='95%'/></td></tr>");
                            flag = false;
                        }
                        if(data.spotTypeName!="重点部位点检"){
                        var div  = "<tr class='text-c'>" +
                            "<td style='width:3%'>" + j + "</td>" +
                            "<td>"+ checkunusualText(data.spotTypeName) +"</td>" +
                            "<td>"+ data.spotInspection +"</td>" +
                            "<td>"+ data.checkProject +"</td>" +
                            "<td>"+data.spotPosition +"</td>" +
                            "<td>" + data.checkMethod + "</td>" +
                            "<td>"+ getisUnusual(data.isUnusual)+"</td>" +
                            "<td>" + checkunusualText(data.unusualText) + "</td>" +
                            "<td>"+ getisUpdate(data.isUpdate)+"</td>" +
                            "<td>" + checkupdateText(data.updateText) +"</td>" +
                            "<td>" + checkNull(data.spotTime) +"</td>" +
                            "<td>" + data.remarks +"</td>" +
                            "<td style='width:10%'>" + pictureView(data.prictureUrl) +"</td>" +
                            "<td style='width:10%'>" + pictureView(data.uploadUrl) +"</td>" +
                            "<td style='display: none'>"+ data.id +"</td>" +
                            "</tr>";
                        $("#checkList").append(div);
                        }else{
                            var str3 = "batchUrlz"+k;
                            var div  = "<tr class='text-c'>" +
                                "<td style='width:3%'>" + j + "</td>" +
                                "<td>"+ checkunusualText(data.spotTypeName) +"</td>" +
                                "<td>"+ data.spotInspection +"</td>" +
                                "<td>"+ data.checkProject +"</td>" +
                                "<td>"+data.spotPosition +"</td>" +
                                "<td>" + data.checkMethod + "</td>" +
                                "<td>"+ getisUnusual(data.isUnusual)+"</td>" +
                                "<td>" + checkunusualText(data.unusualText) + "</td>" +
                                "<td>"+ getisUpdate(data.isUpdate)+"</td>" +
                                "<td>" + checkupdateText(data.updateText) +"</td>" +
                                "<td>" + checkNull(data.spotTime) +"</td>" +
                                "<td>" + data.remarks +"</td>" +
                                "<td style='width:10%'>" + pictureView(data.prictureUrl) +"</td>" +
                                "<td style='width:10%'>" + pictureView(data.uploadUrl) +"</td>" +
                                "<td style='display: none'>"+ data.id +"</td>" +
                                "</tr>";
                            $("#checkList2").append(div);
                            k+=1;
                        }
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

function getIsNot(str) {
    var html = "<input type='radio' value='1' name='"+str+"' >" +
        "<span >有</span>"+
        "<input type='radio' value='2' name='"+str+"'>" +
        "<span >无</span>";
    return html;
}

function getisUnusual(isUnusual) {
    if(isUnusual==1){
        return "有";
    }else if(isUnusual==2){
        return "无";
    }else{
        return "";
    }
}
function getisUpdate(isUpdate) {
    if(isUpdate==1){
        return "有";
    }else if(isUpdate==2){
        return "无";
    }else{
        return "";
    }
}

function checkunusualText(unusualText) {
    if(unusualText==null){
        return "";
    }else{
        return unusualText;
    }
}

function checkupdateText(updateText) {
    if(updateText==null){
        return "";
    }else{
        return updateText;
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

function save() {
    var jdList = [];
    var flag = true;
    $("#checkList tr").each(function (i) {
        var jd = {};
        $(this).children('td').each(function (j) {
            if (j == 6) {
                var str = "input[name=isUnusual"+i+"]:checked";
                var isUnusual = $(str).val();
                jd["isUnusual"] = Number(isUnusual);
                if(isUnusual=="9"){
                    flag = false;
                }
            }
            else if (j == 7) {
                var unusualText = $(this).text();
                jd["unusualText"] = unusualText;
            }
            else if (j == 8) {
                var str = "input[name=isUpdate"+i+"]:checked";
                var isUpdate = $(str).val();
                jd["isUpdate"] = Number(isUpdate);
                if(isUpdate=="9"){
                    flag = false;
                }
            }
            else if (j == 9) {
                var updateText = $(this).text();
                jd["updateText"] = updateText;
            }
            else if (j == 10) {
                var str = "#spotTime"+i;
                var spotTime = $(str).val();
                jd["spotTime"] = spotTime;
                if(spotTime==null||spotTime==""){
                    flag = false;
                }
                $("#urlDate").val(spotTime);
            }
            else if (j == 14) {
                var str = "#batchUrl"+i;
                var uploadUrl = $(str).val();
                jd["uploadUrl"] = uploadUrl;
            }
            else if (j == 15) {
                var id = $(this).text();
                jd["id"] = Number(id);
            }
        });
        jdList.push(jd);
    });

    $("#checkList2 tr").each(function (i) {
        var jd = {};
        $(this).children('td').each(function (j) {
            if (j == 6) {
                var str = "input[name=isUnusual"+i+"]:checked";
                var isUnusual = $(str).val();
                jd["isUnusual"] = Number(isUnusual);
                if(isUnusual=="9"){
                    flag = false;
                }
            }
            else if (j == 7) {
                var unusualText = $(this).text();
                jd["unusualText"] = unusualText;
            }
            else if (j == 8) {
                var str = "input[name=isUpdate"+i+"]:checked";
                var isUpdate = $(str).val();
                jd["isUpdate"] = Number(isUpdate);
                if(isUpdate=="9"){
                    flag = false;
                }
            }
            else if (j == 9) {
                var updateText = $(this).text();
                jd["updateText"] = updateText;
            }
            else if (j == 10) {
                var str = "#spotTime"+i;
                var spotTime = $(str).val();
                jd["spotTime"] = spotTime;
                if(spotTime==null||spotTime==""){
                    flag = false;
                }
                $("#urlDate").val(spotTime);
            }
            else if (j == 14) {
                var str = "#batchUrlz"+i;
                var uploadUrl = $(str).val();
                jd["uploadUrl"] = uploadUrl;
            }
            else if (j == 15) {
                var id = $(this).text();
                jd["id"] = Number(id);
            }
        });
        jdList.push(jd);
    });

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
                    window.location.href="dateSpotDetailMould.html?startTime="+ $("#beginTime").val()+ "&taskId=" + taskId;
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

    //更新状态
    if(flag) {
        $.ajax({
            url: "../spotDetail/updateState.do",
            type: "POST",
            dataType: "json",
            async: false,
            data: {
                "id":Number($("#id").val()),
                "spotMan":$("#spotMan").val()
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
    }
}

function pictureView(url){
    if(url==""||url==null){
        return "";
    }
    var html="";
    var urls = url.split(",");
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


function pictureViewAll(url){
    if(url==""||url==null){
        return "";
    }
    var html="";
    var urls = url.split(",");
    if(urls != "" && urls.length > 0){
        for(var i=0; i < urls.length; i++){
            var picturUrl = urls[i];
            html += "<p>" +
                "<a href='#' onclick=\"view('"+ picturUrl +"')\" style='width:100px;' >重点部位点检样式图</a>" +
                "<input type='text' name='fileUrls' value='"+ picturUrl +"' style='display:none'/>" +
                "</p>";
        }
        return html;
    }
}
function view(url){
    window.open(url);
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
            "type":2
        };
    }else if(type==6){
        //科长确认
        data={
            "id":Number($("#id").val()),
            "confirmChief":$("#loginuser").val(),
            "confirmState":2,
            "type":2
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
               // window.location.href="spotTask.html";
            	window.location.href="dateSpotDetailMould.html?startTime="+ $("#beginTime").val()+ "&taskId=" + taskId;
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
        "type":2
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
            	window.location.href="dateSpotDetailMould.html?startTime="+ $("#beginTime").val()+ "&taskId=" + taskId;
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

//多图片上传
function ulfs(obj,str2){
    var f = $(obj).val();
    // var htmlId = $(obj).attr('id');
    // htmlId = htmlId.slice(8);
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
        url: "../file/batchUploadImg.do",
        data: data,
        cache: false,
        contentType: false,    //不可缺
        processData: false,    //不可缺
        dataType: "json",
        success: function (result) {
            if (result.ret == '1') {
                var data = result.data;
                var ss = "#"+str2;
                $(ss).val(data);
            } else {
                alert(error);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("上传失败，请检查网络后重试");
        }
    });
}