$(document).ready(function() {
    query();
});
function query() {
    $.ajax({
        url: "../line/selectEquipLine.do",
        type: "POST",
        dataType: "json",
        data:{
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#lineAdd").html("");
                var lineList = result.data;
                for(var i = 0; i < lineList.length; i++) {
                    var data = lineList[i];
                    var div  = "<div class='col-md-4 col-sm-4 col-lg-4' style='padding:1%;'>" +
                        "<a onclick='toSpot("+data.id+")'>" +
                        "<div class='card' style='border:1px solid #ddd;height:125px;'>"+
                        "<div class='card-body p-3 text-center'>" +
                        "<div class='h1'>"+data.beltlineName+"</div>"+
                        "<div class='text-muted mb-4'>"+data.beltlineDescription+"</div>"+
                        "</div></div></a></div>";
                    $("#lineAdd").append(div);
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
function toSpot(id) {
    window.location.href="spot.html?id="+id;
}
