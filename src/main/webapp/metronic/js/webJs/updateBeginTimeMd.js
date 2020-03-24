$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    var taskId = Request['taskId'];
    var beginTime = Request['beginTime'];
    var deId = Request['deId'];
    $("#taskId").val(taskId);
    $("#time").val(beginTime);
    $("#deId").val(deId);
    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                //$("#loginuser").val(result.data.username);
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
    query(beginTime);
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

function query(beginTime) {
    $("#beginTime").val(beginTime);
}

function save() {
    var startDate = $("#time").val();
    var taskId= $("#taskId").val();
    var beginTime = $("#beginTime").val();
    var deId = $("#deId").val();
    $.ajax({
        url: "../task/updateTask.do",
        type: "POST",
        dataType: "json",
        data: {
            "taskId":taskId,
            "beginTime":beginTime,
            "deId":deId
        },
        success: function (result) {
            if (result.ret == '1') {
                parent.window.location.href="dateSpotDetailMould.html?startTime="+ startDate;
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