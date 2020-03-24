$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    $("#id").val(id);
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
    $.ajax({
        url: "../equipRepair/getInfo.do",
        type: "POST",
        dataType: "json",
        data: {
            "id":Number(id)
        },
        success: function (result) {
            if (result.ret == '1') {
                $("#equipName").val(result.data.equipName);
                $("#equipNum").val(result.data.equipNum);
                $("#maintainer").val(result.data.maintainer);
                $("#equipUseDepartment").val(result.data.worksection);
                $("#equipUseSystem").val(result.data.system);
                $("#equipUseClass").val(result.data.classment);
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

function save(){
    var id = $("#id").val(),
        operator = $("#operator").val(),
        equipUseDepartment = $("#equipUseDepartment").val(),
        equipUseSystem = $("#equipUseSystem").val(),
        equipUseClass = $("#equipUseClass").val(),
        equipState = $("#equipState").val(),
        safetyDeviceConfirm = $("#safetyDeviceConfirm").val();
    $.ajax({
        url: "../equipRepair/update.do",
        type: "POST",
        dataType: "json",
        async:false,
        data:{
            "id":Number(id),
            "operator":operator,
            "equipUseDepartment":equipUseDepartment,
            "equipUseSystem":equipUseSystem,
            "equipUseClass":equipUseClass,
            "equipState":equipState,
            "safetyDeviceConfirm":safetyDeviceConfirm
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

    $.ajax({
        url: "../repair/update.do",
        type: "POST",
        dataType: "json",
        async:false,
        data:{
            "id":Number(id),
            "beginTime":CurentTime(),
            "state":3
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