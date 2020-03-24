
var jumpIndex = 0;
$(document).ready(function() {
    //获取生产线下拉列表
    $.ajax({
        url: "../line/selectEquipLine.do",
        type: "POST",
        dataType: "json",
        data:{
        },
        async:false,
        success: function (result) {
            if (result.ret == '1') {
                var lineList = result.data;
                for (var i = 0; i < lineList.length; i++) {
                    // 为Select下选框赋值
                    //先创建好select里面的option元素
                    var option = document.createElement("option");
                    //转换DOM对象为JQ对象,好用JQ里面提供的方法 给option的value赋值
                    $(option).val(lineList[i].id);
                    //给option的text赋值,这就是你点开下拉框能够看到的东西
                    $(option).text(lineList[i].beltlineName);
                    //获取select 下拉框对象,并将option添加进select
                    $('#lineId').append(option);
                }
                //去重
                $('#lineId').each(function(i,n){
                    var options = "";
                    $(n).find("option").each(function(j,m){
                        if(options.indexOf($(m)[0].outerHTML) == -1)
                        {
                            options += $(m)[0].outerHTML;
                        }
                    });
                    $(n).html(options);
                });
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
    
    $.ajax({
        url: '../mould/initCombox.do',
        type: 'GET',
        dataType: 'JSON',
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                initComBox(result.data, "factory");

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
    
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    jumpIndex = Request['passIndex'];
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
        url: "../equip/getEquipByid.do",
        type: "POST",
        dataType: "json",
        data: {
            "id": Number(id)
        },
        async:false,
        success: function (result) {
            if (result.ret == '1') {
                $('#id').val(id);
                $('#equipName').val(result.data.equipName);
                $('#factory').val(result.data.factory);
                $('#anotherName').val(result.data.anotherName);
                $('#equipDescription').val(result.data.equipDescription);
                $('#useBeginTime').val(result.data.useBeginTime);
                $('#yearsLimit').val(result.data.yearsLimit);
                $('#equipModel').val(result.data.equipModel);
                $('#standard').val(result.data.standard);
                $("#assetNum").val(result.data.assetNum);
                $("#equipNum").val(result.data.equipNum);
                $("#subsidiaryEq").val(result.data.subsidiaryEq);
                $("#manufactory").val(result.data.manufactory);
                $("#country").val(result.data.country);
                $("#manuYear").val(result.data.manuYear);
                var all_options = document.getElementById("lineId");
                for (i=0; i<all_options.length; i++){
                    if (all_options[i].value == result.data.lineId)  // 根据option标签的ID来进行判断  测试的代码这里是两个等号
                    {
                        all_options[i].selected = true;
                        break;
                    }
                }
                var is_options = document.getElementById("isBottleneck");
                for (i=0; i<is_options.length; i++){
                    if (is_options[i].value == result.data.isBottleneck)  // 根据option标签的ID来进行判断  测试的代码这里是两个等号
                    {
                        is_options[i].selected = true;
                        break;
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

//点击保存按钮
function save(){
    var id = $("#id").val(),
        equipName = $("#equipName").val(),
        equipDescription = $("#equipDescription").val(),
        useBeginTime = $("#useBeginTime").val(),
        yearsLimit = $("#yearsLimit").val(),
        equipModel = $("#equipModel").val(),
        standard = $("#standard").val(),
        assetNum = $("#assetNum").val(),
        equipNum = $("#equipNum").val(),
        subsidiaryEq = $("#subsidiaryEq").val(),
        manufactory = $("#manufactory").val(),
        country = $("#country").val(),
        anotherName = $("#anotherName").val(),
        factory = $("#factory").val(),
        manuYear = $("#manuYear").val();
    var lineId = $("#lineId option:selected").val();
    var isBottleneck = $("#isBottleneck option:selected").val();
    if (isNullorEmpty(equipName)) {
        layer.alert("设备名称不能为空");
        return;
    } else if (isNullorEmpty(equipModel)) {
        layer.alert("型号不能为空");
        return;
    }else if (isNullorEmpty(standard)) {
        layer.alert("规格不能为空");
        return;
    }else if (isNullorEmpty(yearsLimit)) {
        layer.alert("年限不能为空");
        return;
    }else if (isNullorEmpty(useBeginTime)) {
        layer.alert("使用开始时间不能为空");
        return;
    }else if(factory==9){
        layer.alert("请选择工厂");
        return;
    }
    $.ajax({
        url: "../equip/update.do",
        type: "POST",
        dataType: "json",
        data: {
            "id":Number(id),
            "lineId":Number(lineId),
            "anotherName":anotherName,
            "factory":factory,
            "equipName":equipName,
            "equipDescription":equipDescription,
            "useBeginTime":useBeginTime,
            "yearsLimit":yearsLimit,
            "equipModel":equipModel,
            "standard":standard,
            "assetNum":assetNum,
            "equipNum":equipNum,
            "subsidiaryEq":subsidiaryEq,
            "manufactory":manufactory,
            "country":country,
            "manuYear":manuYear,
            "isBottleneck":isBottleneck
        },
        success: function (result) {
            if (result.ret == '1') {
/*                layer.msg("保存成功");
                setTimeout(function(){
                    window.location.href = "equipment-list.html";
                }, 1000 );*/
	  			refurbish();
	  			removeIframe();
            	
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
//取消
function layer_close(){
   // window.location.href="equipment-list.html"
	removeIframe();
}


function initComBox(selectList, htmlId){

    var optionHtml = "<option value='9'>请选择</option>";
    $(selectList).each(function(i, e) {

        optionHtml += '<option value="' + e.id + '">' + e.name + '</option>';
    });
    $("#" + htmlId).append(optionHtml);

}


//刷新模具一览页面，同时不改变页数
function refurbish(){
	var mouldListIfram = window.parent.document.getElementById("equipList");
	var mouldListHtml = mouldListIfram.contentWindow;
	mouldListHtml.reflush(jumpIndex);
}