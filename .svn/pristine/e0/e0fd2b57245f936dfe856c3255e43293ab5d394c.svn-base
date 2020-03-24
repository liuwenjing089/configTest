$(document).ready(function() {
    var type=99;
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    var confirmType = Request['type'];
    $("#id").val(id);
    $("#type").val(type);
    $("#confirmType").val(confirmType);
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
                $("#loginuser").val(result.data.username);
                $("#type").val(type);
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
    query(id,type,confirmType);
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
function query(id,type,confirmType) {
    if(type==5){
        $("#loginuser1").val($("#loginuser").val());
    }else if(type==6){
        $("#loginuser2").val($("#loginuser").val());
    }
    if(confirmType==1){
        $.ajax({
            url: "../equipRepair/getInfo.do",
            type: "POST",
            dataType: "json",
            data: {
                "id":Number(id)
            },
            success: function (result) {
                if (result.ret == '1') {
                    if(result.data.isfirstEpisode==1){
                        document.getElementById("isfirstEpisode1").checked=true;
                    }else{
                        document.getElementById("isfirstEpisode2").checked=true;
                    }
                    $("#beginTime").val(result.data.beginTime);
                    $("#endTime").val(result.data.endTime);

                    //结束时间
                    end_str = (result.data.endTime).replace("T"," ").replace(/-/g,"/");//一般得到的时间的格式都是：yyyy-MM-dd hh24:mi:ss，所以我就用了这个做例子，是/的格式，就不用replace了。
                    var end_date = new Date(end_str);//将字符串转化为时间
                    sta_str = (result.data.beginTime).replace(/-/g,"/");
                    var sta_date = new Date(sta_str);
                    var num = (end_date-sta_date)/(1000*60);//求出两个时间的时间差
                    var min = parseInt(Math.ceil(num));
                    $("#stopTime").val(min);
                    $("#mainTaskMan").val(result.data.mainTaskMan);
                    $("#subTaskMan").val(result.data.subTaskMan);
                    document.getElementById("appearance").innerText="故障现象补充说明:"+'\n'+result.data.appearance;
                    document.getElementById("reason").innerText="原因追究:"+'\n'+result.data.reason;
                    document.getElementById("management").innerText="处置:"+'\n'+result.data.management;
                    document.getElementById("preventPlan").innerText="防止再发生措施:"+'\n'+result.data.preventPlan;
                    var all_options = document.getElementById("faultType").options;
                    for (i=0; i<all_options.length; i++){
                        if (all_options[i].value == result.data.faultType) {
                            all_options[i].selected = true;
                        }
                    }
                    var all_options1 = document.getElementById("repairUsetimeType").options;
                    for (i=0; i<all_options1.length; i++){
                        if (all_options1[i].value == result.data.repairUsetimeType) {
                            all_options1[i].selected = true;
                        }
                    }
                    if(!isNullorEmpty(result.data.saveConfirmCommander)){
                        $("#loginuser1").val(result.data.saveConfirmCommander);
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
                    alert(error);
                }
            }
        });
    }else{
        //TODO
    }
}

function confirm(){
    var id = $("#id").val();
    var type = $("#type").val();
    var confirmType = $("#confirmType").val();
    if(type==5){
        if(confirmType==1){
            $.ajax({
                url: "../equipRepair/update.do",
                type: "POST",
                dataType: "json",
                async:false,
                data:{
                    "id":Number(id),
                    "saveConfirmCommander":$("#loginuser1").val()
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
        }else{

        }
        $.ajax({
            url: "../repair/update.do",
            type: "POST",
            dataType: "json",
            async:false,
            data:{
                "id":Number(id),
                "state":5
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
    }else if(type==6){
        if(confirmType==1){
            $.ajax({
                url: "../equipRepair/update.do",
                type: "POST",
                dataType: "json",
                async:false,
                data:{
                    "id":Number(id),
                    "saveConfirmChief":$("#loginuser2").val()
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
        }else{

        }
        $.ajax({
            url: "../repair/update.do",
            type: "POST",
            dataType: "json",
            async:false,
            data:{
                "id":Number(id),
                "state":6
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
    }

    window.location.href="serviceman.html";
}