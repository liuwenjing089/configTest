$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    var userType = Request['userType'];
    $("#id").val(id);
    $("#userType").val(userType);
    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                $("#loginuser").val(result.data.username);
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
    query(id,userType);
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
function query(id,userType) {

    if(userType==1||userType==2){
        document.getElementById("baoquan1").style.display="none";
        document.getElementById("baoquan2").style.display="none";
        document.getElementById("baoquan3").style.display="none";
    }else if(userType==3||userType==4){
        document.getElementById("produce").style.display="none";
    }

    var user = $("#loginuser").val();

    $.ajax({
        url: "../equipRepair/getInfo.do",
        type: "POST",
        dataType: "json",
        data: {
            "id":Number(id)
        },
        success: function (result) {
            if (result.ret == '1') {
                //作业完了时间默认值
            	$("#repairNumber").text(result.data.repairNumber);
                var eeT = result.data.endTime;
                if(eeT!= null){
                    $("#endTime1").val(eeT.substr(0,10));
                    $("#endTime2").val(eeT.substr(11,5));
                    var endT = $("#endTime1").val()+" "+$("#endTime2").val();
                }else{
                    $("#endTime1").val(result.data.nowDate);
                    $("#endTime2").val(result.data.nowTime);
                    var endT = $("#endTime1").val()+" "+$("#endTime2").val();
                }

                if(result.data.orderTime!=null){
                    var orderDate = result.data.orderTime.substr(0,10);
                    $("#orderTime1").val(orderDate);
                    var orderTime = result.data.orderTime.substr(11,5);
                    $("#orderTime2").val(orderTime);
                }else{
                    $("#orderTime1").val("");
                    $("#orderTime2").val("");
                }

                if(result.data.reportRepairTime!=null){
                    var repairDate = result.data.reportRepairTime.substr(0,10);
                    $("#reportReapirTime1").val(repairDate);
                    var repairTime = result.data.reportRepairTime.substr(11,5);
                    $("#reportRepairTime2").val(repairTime);
                }else{
                    $("#reportReapirTime1").val("");
                    $("#reportRepairTime2").val("");
                }

                $("#beginTime").val(result.data.beginTime);

                //实际停止时间
                var end_str = (endT).replace(/-/g,"/");//一般得到的时间的格式都是：yyyy-MM-dd hh24:mi:ss，所以我就用了这个做例子，是/的格式，就不用replace了。
                var end_date = new Date(end_str);//将字符串转化为时间
                var sta_str = (result.data.reportRepairTime).replace(/-/g,"/");
                var sta_date = new Date(sta_str);
                var num = (end_date-sta_date)/(1000*60);//求出两个时间的时间差
                var min = parseInt(Math.ceil(num));
                $("#stopTime").val(min);

                //作业时间doingTime
                // var end_str1 = (endT).replace(/-/g,"/");//一般得到的时间的格式都是：yyyy-MM-dd hh24:mi:ss，所以我就用了这个做例子，是/的格式，就不用replace了。
                // var end_date1 = new Date(end_str1);//将字符串转化为时间
                // var sta_str1 = (result.data.beginTime).replace(/-/g,"/");
                // var sta_date1 = new Date(sta_str1);
                // var num1 = (end_date1-sta_date1)/(1000*60);//求出两个时间的时间差
                // var min1 = parseInt(Math.ceil(num1));
                $("#doingTime").val("");
                $("#mainTaskMan").val(result.data.mainTaskMan);
                $("#mainTaskManView").val(result.data.mainTaskManView);
                $("#subTaskMan").val(result.data.subTaskMan);
                $("#newaddTaskMan").val(result.data.newaddTaskMan);
                $("#supplier").val(result.data.supplier);

                var all_options = document.getElementById("isfirstEpisode").options;
                for (i=0; i<all_options.length; i++){
                    if (all_options[i].value == result.data.isfirstEpisode) {
                        all_options[i].selected = true;
                    }
                }
                var all_options1 = document.getElementById("isOverNum").options;
                for (i=0; i<all_options1.length; i++){
                    if (all_options1[i].value == result.data.isOverNum) {
                        all_options1[i].selected = true;
                    }
                }
                var all_options2 = document.getElementById("faultType").options;
                for (i=0; i<all_options2.length; i++){
                    if (all_options2[i].value == result.data.faultType) {
                        all_options2[i].selected = true;
                    }
                }
                var all_options3 = document.getElementById("repairUsetimeType").options;
                for (i=0; i<all_options3.length; i++){
                    if (all_options3[i].value == result.data.repairUsetimeType) {
                        all_options3[i].selected = true;
                    }
                }
                $("#appearance").val(result.data.appearance);
                $("#reason").val(result.data.reason);
                $("#management").val(result.data.management);
                $("#preventPlan").val(result.data.preventPlan);

                $("#confirmationTime").val(result.data.confirmationTime);
                $("#preDepChiefConTime").val(result.data.preDepChiefConTime);
                $("#preSecChiefConTime").val(result.data.preSecChiefConTime);
                if(userType==2 && result.data.state == 3){
                    $("#taskConfirmMan").val(user);
                }else if(userType==5 && result.data.state == 4){
                    $("#taskConfirmMan").val(result.data.taskConfirmMan);
                    $("#saveConfirmCommander").val(user);
                }else if(userType==6  && result.data.state == 5){
                    $("#taskConfirmMan").val(result.data.taskConfirmMan);
                    $("#saveConfirmCommander").val(result.data.saveConfirmCommander);
                    $("#saveConfirmChief").val(user);
                }else{
                    $("#taskConfirmMan").val(result.data.taskConfirmMan);
                    $("#saveConfirmCommander").val(result.data.saveConfirmCommander);
                    $("#saveConfirmChief").val(result.data.saveConfirmChief);
                }

                pictureView(result.data.locationUrl);

                //部品
                var partsList = result.data.partsList;
                if(partsList!= null) {
                    for (var i = 0; i < partsList.length; i++) {
                        var div = "<tr class='text-c'>" +
                            "<td>" + checkNull(partsList[i].partsName) + "</td>" +
                            "<td>" + checkNull(partsList[i].mould) + "</td>" +
                            "<td>" + checkNull(partsList[i].brand) + "</td>" +
                            "<td>" + checkNull(partsList[i].partsNumber) + "</td>" +
                            "</tr>";
                        $("#partsList").append(div);
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
                alert(error);
            }
        }
    });
}

//确认 allConfirm
function allConfirm(){
    // var flag = false;
    var id = $("#id").val();
    var userType = $("#userType").val();
    var taskConfirmMan = $("#loginuser").val();
    var saveConfirmCommander = $("#loginuser").val();
    var saveConfirmChief = $("#loginuser").val();
    var data={};
    var eqreData={};
    if(userType ==2){
/*        data={
            "id":Number(id),
            "state":4
        };*/
        eqreData={
            "id":Number(id),
            "state":4,
            "taskConfirmMan":taskConfirmMan
        };
    }else if(userType ==5){
/*        data={
            "id":Number(id),
            "state":5
        };*/
        eqreData={
            "id":Number(id),
            "state":5,
            "saveConfirmCommander":saveConfirmCommander
        };
    }else if(userType ==6){
/*        data={
            "id":Number(id),
            "state":6
        };*/
        eqreData={
            "id":Number(id),
            "state":6,
            "saveConfirmChief":saveConfirmChief
        };
    }
//    $.ajax({
//        url: "../repair/update.do",
//        type: "POST",
//        dataType: "json",
//        async:false,
//        data:data,
//        success: function(result) {
//            if(result.ret == '1') {
//
//                // flag = true;
//            } else if(result.ret == '3'){
//                layer.msg("登录超时,请重新登录");
//                setTimeout(function(){
//                    top.window.location.href= "login.html"
//                }, 1000 );
//            }else {
//                var error = "";
//                for(var i = 0; i < result.data.length; i++) {
//                    error += (result.data[i].message);
//                }
//                layer.alert(error);
//            }
//        }
//    });
    $.ajax({
        url: "../equipRepair/updateDetailState.do",
        type: "POST",
        dataType: "json",
        async:false,
        data:JSON.stringify(eqreData),
        contentType: "application/json; charset=utf-8",
        success: function(result) {
            if(result.ret == '1') {
                layer.msg("确认完了");
                setTimeout(function(){
                    parent.window.location.href="site_serviceman.html";
                }, 2000 );
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
    if(url != null){
    var urls = url.split(",");
        for(var i=0; i < urls.length; i++){
            var picturUrl = urls[i];
            var html = "<div class='row cl'>" +
                "<a href='#' onclick=\"view('"+ picturUrl +"')\" style='margin-left:50px;width:100px;' >图片"+ (i+1) +"&nbsp;&nbsp;&nbsp;&nbsp;点击查看</a>" +
                "</div>";
            $("#pictureView").append(html);
        }
    }

}

function view(url){
    window.open(url);
}
// function allConfirm() {
//
// }