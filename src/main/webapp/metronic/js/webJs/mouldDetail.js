$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
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
        url: "../mould/selectMouldById.do",
        type: "POST",
        dataType: "json",
        data: {
            "id": Number(id)
        },
        success: function (result) {
            if (result.ret == '1') {
            	var data = result.data;
            	
                $('#id').val(id);
                $('#vehicleType').val(data.vehicleType);
                $('#figureNumber').val(data.figureNumber);
                $('#assetCoding').val(data.assetCoding);
                $('#manufacturer').val(data.manufacturer);
                $('#moldCompletionTime').val(data.moldCompletionTime);
                $('#cavityNumber').val(data.cavityNumber);
                $('#model').val(data.model);
                $('#useDate').val(data.useDate);
                $('#cuttingDate').val(data.cuttingDate);              
                $('#totalWeight').val(data.totalWeight);
                $('#outlineDimension').val(data.outlineDimension);
                $("#pictureUrlOld").attr("href", data.pictureUrl);                
                $('#drawingDeposit').val(data.drawingDeposit);


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

//点击保存按钮
function save(){

}