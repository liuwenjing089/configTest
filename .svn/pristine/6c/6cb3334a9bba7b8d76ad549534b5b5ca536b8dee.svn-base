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
        url: "../equip/getEqSee.do",
        type: "POST",
        dataType: "json",
        data: {
            "id":id
        },
        success: function (result) {
            if (result.ret == '1') {
                $("#equipName").val(result.data.equipName);
                $("#assetNum").val(result.data.assetNum);
                $("#equipNum").val(result.data.equipNum);
                $("#subsidiaryEq").val(result.data.subsidiaryEq);
                $("#manufactory").val(result.data.manufactory);
                $("#country").val(result.data.country);
                $("#manuYear").val(result.data.manuYear);
                $("#equipDescription").val(result.data.equipDescription);
                $("#useBeginTime").val(result.data.useBeginTime);
                $("#yearsLimit").val(result.data.yearsLimit);
                $("#equipModel").val(result.data.equipModel);
                $("#standard").val(result.data.standard);
                $("#lineName").val(result.data.beltlineName);
                if(result.data.isBottleneck==1){
                    $("#isBottleneck").val("是");
                }else{
                    $("#isBottleneck").val("否");
                }
                var all_options = document.getElementById("factory").options;
                for (i=0; i<all_options.length; i++){
                    if (all_options[i].value == String.valueOf(result.data.factory)) {
                        all_options[i].selected = true;
                    }
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
function layer_close() {
   // window.location.href="equipment-list.html";
	removeIframe();
}
