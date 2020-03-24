var repairId;
var passIndex = 0;
$(document).ready(function() {

    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    passIndex = Request['passIndex'];
    
    repairId = Request['id'];
    $("#id").val(id);
    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                $("#userType").val(result.data.userType);
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
        url: '../equipRepair/initFaultTypeCombox.do',
        type: 'GET',
        dataType: 'JSON',
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                initComBox(result.data, "faultType");
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
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.alert("error");
        }
    });

    $.ajax({
        url: '../equipRepair/initRepairUsetimeTypeCombox.do',
        type: 'GET',
        dataType: 'JSON',
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                initComBox(result.data, "repairUsetimeType");
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
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.alert("error");
        }
    });

    // var userType=$("#userType").val();

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
                $("#endTime1").val(result.data.nowDate);
                $("#endTime2").val(result.data.nowTime);
                var endT = $("#endTime1").val()+" "+$("#endTime2").val();

            	var equipName = result.data.equipName;
            	var anotherName = result.data.anotherName;
            	if(anotherName == "" || anotherName == null){
            		$("#equipName").val(equipName);
            	}else{
            		$("#equipName").val(equipName+"("+anotherName+")");
            	}
            	
                var repairDate = result.data.reportRepairTime.substr(0,10);
                $("#reportReapirTime1").val(repairDate);
                var repairTime = result.data.reportRepairTime.substr(11,5);
                $("#reportRepairTime2").val(repairTime);

                var orderDate = result.data.orderTime.substr(0,10);
                $("#orderTime1").val(orderDate);
                var orderTime = result.data.orderTime.substr(11,5);
                $("#orderTime2").val(orderTime);

                $("#beginTime").val(result.data.beginTime);
                $("#subTaskMan").val(result.data.subTaskMan);
                $("#newaddTaskMan").val(result.data.newaddTaskMan);
                $("#supplier").val(result.data.supplier);


                //作业时间doingTime
                if(result.data.beginTime!=null){
                    var end_str1 = (endT).replace(/-/g,"/");//一般得到的时间的格式都是：yyyy-MM-dd hh24:mi:ss，所以我就用了这个做例子，是/的格式，就不用replace了。
                    var end_date1 = new Date(end_str1);//将字符串转化为时间
                    var sta_str1 = (result.data.beginTime).replace(/-/g,"/");
                    var sta_date1 = new Date(sta_str1);
                    var num1 = (end_date1-sta_date1)/(1000*60);//求出两个时间的时间差
                    var min1 = parseInt(Math.ceil(num1));
                    $("#doingTime").val(min1);
                }else{
                    $("#doingTime").val("");
                }

                $("#mainTaskMan").val(result.data.mainTaskMan);
                $("#mainTaskManView").val(result.data.mainTaskManView);

                var all_options = document.getElementById("isfirstEpisode").options;
                for (var i=0; i<all_options.length; i++){
                    if (all_options[i].value == result.data.isfirstEpisode) {
                        all_options[i].selected = true;
                    }
                }
                var all_options1 = document.getElementById("isOverNum").options;
                for (var i=0; i<all_options1.length; i++){
                    if (all_options1[i].value == result.data.isOverNum) {
                        all_options1[i].selected = true;
                    }
                }
                var all_options2 = document.getElementById("faultType").options;
                for (var i=0; i<all_options2.length; i++){
                    if (all_options2[i].value == result.data.faultType) {
                        all_options2[i].selected = true;
                    }
                }
                var all_options3 = document.getElementById("repairUsetimeType").options;
                for (var i=0; i<all_options3.length; i++){
                    if (all_options3[i].value == result.data.repairUsetimeType) {
                        all_options3[i].selected = true;
                    }
                }

                //稼动赋值

                var cropMovementValue = result.data.cropMovement;
                if(cropMovementValue != 0){
                	$("input[name='cropMovement'][value='"+ result.data.cropMovement +"']").prop("checked",true);
                }
                   
                $("#appearance").val(result.data.appearance);
                $("#faultDescription").val(result.data.faultDescription);
                $("#reason").val(result.data.reason);
                $("#management").val(result.data.management);
                $("#preventPlan").val(result.data.preventPlan);
                $("#equipUseDepartment").val(result.data.equipUseDepartment);
                
                pictureView(result.data.locationUrl);

                //部品
                var partsList = result.data.partsList;
                if(partsList!= null) {
                    for (var i = 0; i < partsList.length; i++) {
                        var div = "<tr class='text-c'>" +
                            "<td contentEditable='true'>" + checkNull(partsList[i].partsName) + "</td>" +
                            "<td contentEditable='true'>" + checkNull(partsList[i].mould) + "</td>" +
                            "<td contentEditable='true'>" + checkNull(partsList[i].brand) + "</td>" +
                            "<td contentEditable='true'>" + checkNull(partsList[i].partsNumber) + "</td>" +
                            "<td ><input type='button' value='删除' class='btn btn-danger radius' id='deleteTable' onclick='delecttr(this)'/></td>" +
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

function save() {
    var id = $("#id").val(),
        endTime =  $("#endTime1").val() + " "+$("#endTime2").val(),
        mainTaskMan = $("#mainTaskMan").val(),
        subTaskMan =  $("#subTaskMan").val(),
        newaddTaskMan = $("#newaddTaskMan").val(),
        supplier = $("#supplier").val(),
        appearance = $("#appearance").val(),
        faultDescription = $("#faultDescription").val(),
        reason = $("#reason").val(),
        management = $("#management").val(),
        preventPlan = $("#preventPlan").val(),
        equipUseDepartment = $("#equipUseDepartment").val();

    var isfirstEpisode = $("#isfirstEpisode option:selected").val();
    var isOverNum = $("#isOverNum option:selected").val();
    var faultType = $("#faultType option:selected").val();
    var repairUsetimeType = $("#repairUsetimeType option:selected").val();

    //判断是否稼动checkbox是否选中
    var cropMovement=$("input[name='cropMovement']:checked").val();
    if(cropMovement == null){
    	cropMovement = 0;
    }

    
    
    var fileUrls = "";
    var obj = $("input[name='fileUrls']");
    if(obj.length > 0){
        $(obj).each(function(j,item){
            console.log("下标:"+j);
            console.log("value值:"+item.value);
            fileUrls += j > 0 ? ","+item.value : item.value;
        });
    }

    var paList = [];
    var flag = true;
    $("#partsList tr").each(function (i) {
        var part = {};
        $(this).children('td').each(function (j) {
            if (j == 0) {
                var partsName = $(this).text();
                part["partsName"] = partsName;
            } else if (j == 1) {
                var mould = $(this).text();
                part["mould"] = mould;
            }
            else if (j == 2) {
                var brand = $(this).text();
                part["brand"] = brand;
            }
            else if (j == 3) {
                var partsNumber = $(this).text();
                part["partsNumber"] = partsNumber;
                if(!check_Number(partsNumber)){
                    flag=false;
                }
            }
            else if (j == 4) {

            }
        });
        paList.push(part);
    });
    if(!flag){
        layer.alert("数量只能输入数字");
        return;
    }
    var paListJson = JSON.stringify(paList);
    var inData ={
        "id":Number(id),
        "isfirstEpisode":Number(isfirstEpisode),
        "faultType":Number(faultType),
        "repairUsetimeType":Number(repairUsetimeType),
        "mainTaskMan":mainTaskMan,
        "subTaskMan":subTaskMan,
        "newaddTaskMan":newaddTaskMan,
        "supplier":supplier,
        "appearance":appearance,
        "reason":reason,
        "management":management,
        "preventPlan":preventPlan,
        "isOverNum":Number(isOverNum),
        "locationUrl":fileUrls,
        "equipUseDepartment":equipUseDepartment,      
        "partsListStr":paListJson,
        "cropMovement":cropMovement,
        "faultDescription":faultDescription
    };
    var jsonData = JSON.stringify(inData);

    $.ajax({
        url: '../equipRepair/update.do',
        type: 'POST',
        data: jsonData,
        dataType : "json",
        async:false,
        contentType: "application/json; charset=utf-8",
        beforeSend: function (XMLHttpRequest) {

        },
        complete: function (XMLHttpRequest, textStatus) {

        },
        success: function(result) {
            if(result.ret == '1') {
                layer.msg("保存成功");
                refurbish();
                setTimeout(function(){
                    //window.location.href="serviceman.html";
                	removeIframe();
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
}

//维修完了
function over() {

    var id = $("#id").val(),
        endTime =  $("#endTime1").val() + " "+$("#endTime2").val(),
        mainTaskMan = $("#mainTaskMan").val(),
        subTaskMan =  $("#subTaskMan").val(),
        newaddTaskMan = $("#newaddTaskMan").val(),
        supplier = $("#supplier").val(),
        appearance = $("#appearance").val(),
        faultDescription = $("#faultDescription").val(),
        reason = $("#reason").val(),
        management = $("#management").val(),
        equipUseDepartment = $("#equipUseDepartment").val(),
        preventPlan = $("#preventPlan").val();

    var isfirstEpisode = $("#isfirstEpisode option:selected").val();
    var isOverNum = $("#isOverNum option:selected").val();
    var faultType = $("#faultType option:selected").val();
    var repairUsetimeType = $("#repairUsetimeType option:selected").val();

    if(isfirstEpisode=='9'){
        layer.alert("请选择 此次故障是否属于首发或者再发");
        return;
    }
    if(isOverNum=='9'){
        layer.alert("请选择 完了代码");
        return;
    }

    //判断是否稼动checkbox是否选中
    var cropMovement=$("input[name='cropMovement']:checked").val();
    if(cropMovement == null){
    	cropMovement = 0;
    }
    
    var fileUrls = "";
    var obj = $("input[name='fileUrls']");
    if(obj.length > 0){
        $(obj).each(function(j,item){
            console.log("下标:"+j);
            console.log("value值:"+item.value);
            fileUrls += j > 0 ? ","+item.value : item.value;
        });
    }

    var paList = [];
    var flag = true;
    $("#partsList tr").each(function (i) {
        var part = {};
        $(this).children('td').each(function (j) {
            if (j == 0) {
                var partsName = $(this).text();
                part["partsName"] = partsName;
            } else if (j == 1) {
                var mould = $(this).text();
                part["mould"] = mould;
            }
            else if (j == 2) {
                var brand = $(this).text();
                part["brand"] = brand;
            }
            else if (j == 3) {
                var partsNumber = $(this).text();
                part["partsNumber"] = partsNumber;
                if(!check_Number(partsNumber)){
                    flag=false;
                }
            }
            else if (j == 4) {

            }
        });
        paList.push(part);
    });
    if(!flag){
        layer.alert("数量只能输入数字");
        return;
    }
    var paListJson = JSON.stringify(paList);
    var inData ={
        "id":Number(id),
        "isfirstEpisode":Number(isfirstEpisode),
        "faultType":Number(faultType),
        "repairUsetimeType":Number(repairUsetimeType),
        "mainTaskMan":mainTaskMan,
        "subTaskMan":subTaskMan,
        "newaddTaskMan":newaddTaskMan,
        "supplier":supplier,
        "appearance":appearance,
        "reason":reason,
        "management":management,
        "preventPlan":preventPlan,
        "isOverNum":Number(isOverNum),
        "locationUrl":fileUrls,
        "partsListStr":paListJson,
        "equipUseDepartment":equipUseDepartment,
        "cropMovement":cropMovement,
        "faultDescription":faultDescription

    };
    var jsonData = JSON.stringify(inData);

    $.ajax({
        url: '../equipRepair/update.do',
        type: 'POST',
        data: jsonData,
        dataType : "json",
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
                   // window.location.href="serviceman.html";
               	
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

    $.ajax({
        url: "../repair/update.do",
        type: "POST",
        dataType: "json",
        async:false,
        data:{
            "id":Number(id),
            "state":3,
            "endTime":endTime
        },
        success: function(result) {
            if(result.ret == '1') {
                //window.location.href="serviceman.html";
                refurbish();
            	removeIframe();
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

// 算工作时间
function getTime() {
    var endT = $("#endTime1").val()+" "+$("#endTime2").val();
    var beginTime = $("#beginTime").val();
    var end_str1 = (endT).replace(/-/g,"/");//一般得到的时间的格式都是：yyyy-MM-dd hh24:mi:ss，所以我就用了这个做例子，是/的格式，就不用replace了。
    var end_date1 = new Date(end_str1);//将字符串转化为时间
    var sta_str1 = (beginTime).replace(/-/g,"/");
    var sta_date1 = new Date(sta_str1);
    var num1 = (end_date1-sta_date1)/(1000*60);//求出两个时间的时间差
    var min1 = parseInt(Math.ceil(num1));
    $("#doingTime").val(min1);
}


//添加table行
    $("#addTable").click(function(){

        var tr="<tr class='text-c'>"+
            "<td contentEditable='true'></td>"+
            "<td contentEditable='true'></td>"+
            "<td contentEditable='true'></td>"+
            "<td contentEditable='true'></td>"+
            "<td contentEditable='true'><input type='button' value='删除' class='btn btn-danger radius' id='deleteTable' onclick='delecttr(this)'/></td>"+
            "</tr>";

        $("#partsList").append(tr);

    });

function delecttr(obj){
    var tr = obj.parentNode.parentNode;
    tr.parentNode.removeChild(tr);
}

function pictureView(url){
    if(url!=null){
    var urls = url.split(",");
        for(var i=0; i < urls.length; i++){
            var picturUrl = urls[i];
            var html = "<p>" +
                "<a href='#' onclick=\"view('"+ picturUrl +"')\" style='margin-left:50px;width:100px;' >图片"+ (i+1) +"&nbsp;&nbsp;&nbsp;&nbsp;点击查看</a>" +
                "<input type='text' name='fileUrls' value='"+ picturUrl +"' style='display:none'/>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type='button' onclick='delPictur(this)' class='btn btn-primary radius'>删除当前图片</button>" +
                "</p>";
            $("#pictureView").append(html);
        }
    }
}
function view(url){
    window.open(url);
}
//删除当前照片
function delPictur(obj){
    $(obj).parent().remove();
}

function initComBox(selectList, htmlId){
    var optionHtml = "<option value='0'>请选择</option>";
    $(selectList).each(function(i, e) {
        optionHtml += '<option value="' + e.id + '">' + e.name + '</option>';
    });
    $("#" + htmlId).append(optionHtml);
}


$(document).on("click","#kyDetail",function(){

	var url = "kyDetail.html?id="+ repairId;
	var title = "ky填写记录";
    var wWidth = window.innerWidth*0.95;
    var wHeight = window.innerHeight*0.95;
    layer.open({
        type: 2,
        id: 'kyDetails',
        title: title,
        btn: ['关闭'],
        area: [wWidth + 'px', wHeight + 'px'],
        content: url, //iframe的url，no代表不显示滚动条
        yes: function (index, layero) {

        	layer.close(index);
        },
        cancel:function (index, layero) {
        }
    });
})

function custom_close(){
	//window.location.href= "serviceman.html";
	removeIframe();
}

//刷新设备维修一览页面，同时不改变页数
function refurbish(){
	var repairIfram = window.parent.document.getElementById("repair");
	var repairHtml = repairIfram.contentWindow;
	repairHtml.reflush(passIndex);
}