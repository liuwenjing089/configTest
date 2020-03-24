$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    var type = Request['type'];
    $("#id").val(id);
    $("#type").val(type);
    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                $("#loginuser").val(result.data.username);
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
    query(id,type);
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
function query(id,type) {
    if(type==1){
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
                    }else if(result.data.isfirstEpisode==2){
                        document.getElementById("isfirstEpisode2").checked=true;
                    }
                    if(result.data.isOverNum==1){
                        document.getElementById("isOverNum1").checked=true;
                    }else if(result.data.isOverNum==2){
                        document.getElementById("isOverNum2").checked=true;
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
                    $("#supplier").val(result.data.supplier);
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
//确认完成
function confirm() {
    var id = $("#id").val();
    var type = $("#type").val();
    if(type==1){
        $.ajax({
            url: "../equipRepair/update.do",
            type: "POST",
            dataType: "json",
            async:false,
            data:{
                "id":Number(id),
                "taskConfirmMan":$("#loginuser").val()
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
        $.ajax({
            url: "../equipRepair/updateMould.do",
            type: "POST",
            dataType: "json",
            async:false,
            data:{
                "reId":Number(id),
                "shiftLeader":$("#loginuser").val()
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
    $.ajax({
        url: "../repair/update.do",
        type: "POST",
        dataType: "json",
        async:false,
        data:{
            "id":Number(id),
            "state":4
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

    window.location.href="serviceman.html";
}