var jumpIndex = 0;
$(document).ready(function() {
	
    var Request = new Object();
    Request = GetRequest();
    jumpIndex = Request['passIndex'];
	
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
});

//保存
function save() {
    var equipName = $("#equipName").val(),
        assetNum = $("#assetNum").val(),
        equipNum = $("#equipNum").val(),
        subsidiaryEq = $("#subsidiaryEq").val(),
        manufactory = $("#manufactory").val(),
        country = $("#country").val(),
        manuYear = $("#manuYear").val(),
        equipDescription = $("#equipDescription").val(),
        useBeginTime = $("#useBeginTime").val(),
        yearsLimit = $("#yearsLimit").val(),
        equipModel = $("#equipModel").val(),
        standard = $("#standard").val(),
        anotherName = $("#anotherName").val();
    var lineId = $("#lineId option:selected").val();
    var isBottleneck = $("#isBottleneck option:selected").val();
    var factory = $("#factory").val();
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
    }else if (isNullorEmpty(lineId)) {
        layer.alert("请先创建生产线");
        return;
    }else if(factory==9){
        layer.alert("请选择工厂");
        return;
    }

    $.ajax({
        url: "../equip/add.do",
        type: "POST",
        dataType: "json",
        data: {
            "lineId":Number(lineId),
            "anotherName":anotherName,
            "factory":factory,
            "assetNum":assetNum,
            "equipNum":equipNum,
            "equipName": equipName,
            "subsidiaryEq":subsidiaryEq,
            "manufactory":manufactory,
            "country":country,
            "manuYear":manuYear,
            "isBottleneck":Number(isBottleneck),
            "equipDescription": equipDescription,
            "useBeginTime": useBeginTime,
            "yearsLimit": yearsLimit,
            "equipModel": equipModel,
            "standard": standard
        },
        beforeSend: function (XMLHttpRequest) {
            $("#save").attr('disabled',true);
        },
        complete: function (XMLHttpRequest, textStatus) {
            //$("#save").removeAttr('disabled');
        },
        success: function (result) {
            if (result.ret == '1') {
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
                layer.alert(error);
            }
        }
    });
}

//点击取消按钮
function layer_close(){
   // window.location.href = "equipment-list.html";
	removeIframe();
}

function initComBox(selectList, htmlId){

    var optionHtml = "<option value='9'>请选择</option>";
    $(selectList).each(function(i, e) {

        optionHtml += '<option value="' + e.id + '">' + e.name + '</option>';
    });
    $("#" + htmlId).append(optionHtml);

}

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

//刷新模具一览页面，同时不改变页数
function refurbish(){
	var mouldListIfram = window.parent.document.getElementById("equipList");
	var mouldListHtml = mouldListIfram.contentWindow;
	mouldListHtml.reflush(jumpIndex);
}