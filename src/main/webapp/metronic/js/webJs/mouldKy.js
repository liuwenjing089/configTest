$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    var detailId = Request['detailId'];
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
    $("#reId").val(id);
}

function save() {
    var check1 = 0;
    if(document.getElementById("check1").checked){
        check1 = 1;
        $("#isOverInvoice").prop("checked", true);
        $("#isOverInvoice").prop("checked", true);
        $("#isOverInvoice").prop("checked", true);
    }
    var check2 = 0;
    if(document.getElementById("check2").checked){
        check2 = 1;
    }
    var check3 = 0;
    if(document.getElementById("check3").checked){
        check3 = 1;
    }
    var check4 = 0;
    if(document.getElementById("check4").checked){
        check4 = 1;
    }
    var check5 = 0;
    if(document.getElementById("check5").checked){
        check5 = 1;
    }
    var check6 = 0;
    if(document.getElementById("check6").checked){
        check6 = 1;
    }
    var check7 = 0;
    if(document.getElementById("check7").checked){
        check7 = 1;
    }
    var id = $("#reId").val();
    var workName = document.getElementById('workName').innerText;
    var proName = document.getElementById('proName').innerText;
    var assetsNum = document.getElementById('assetsNum').innerText;
    var workTips = document.getElementById('workTips').innerText;
    var commandMan = document.getElementById('commandMan').innerText;
    var workMan = document.getElementById('workMan').innerText;
    $.ajax({
        url: "../kyList/mouldKyAdd.do",
        type: "POST",
        dataType: "json",
        data: {
            "reId": id,
            "workName": workName,
            "proName": proName,
            "assetsNum":assetsNum,
            "workTips":workTips,
            "commandMan":commandMan,
            "workMan":workMan,
            "check1":check1,
            "check2":check2,
            "check3":check3,
            "check4":check4,
            "check5":check5,
            "check6":check6,
            "check7":check7
        },
        success: function (result) {
            if (result.ret == '1') {
                layer.alert("保存成功");
				setTimeout(function(){
//					window.location.href= "mouldReport.html?id="+id
					window.location.href= "serviceman.html"
					
				}, 2000 );

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

function change1() {
    var obj = document.getElementById("check1");
    if (obj.checked == true) {
        $("#ch1-1").prop("checked", true);
        $("#ch1-2").prop("checked", true);
        $("#ch1-3").prop("checked", true);
    }else{
        $("#ch1-1").prop("checked", false);
        $("#ch1-2").prop("checked", false);
        $("#ch1-3").prop("checked", false);
    }
}
function change2() {
    var obj = document.getElementById("check2");
    if (obj.checked == true) {
        $("#ch2-1").prop("checked", true);
        $("#ch2-2").prop("checked", true);
    }else{
        $("#ch2-1").prop("checked", false);
        $("#ch2-2").prop("checked", false);
    }
}
function change3() {
    var obj = document.getElementById("check3");
    if (obj.checked == true) {
        $("#ch3-1").prop("checked", true);
        $("#ch3-2").prop("checked", true);
        $("#ch3-3").prop("checked", true);
    }else{
        $("#ch3-1").prop("checked", false);
        $("#ch3-2").prop("checked", false);
        $("#ch3-3").prop("checked", false);
    }
}
function change4() {
    var obj = document.getElementById("check4");
    if (obj.checked == true) {
        $("#ch4-1").prop("checked", true);
        $("#ch4-2").prop("checked", true);
        $("#ch4-3").prop("checked", true);
        $("#ch4-4").prop("checked", true);
    }else{
        $("#ch4-1").prop("checked", false);
        $("#ch4-2").prop("checked", false);
        $("#ch4-3").prop("checked", false);
        $("#ch4-4").prop("checked", false);
    }
}
function change5() {
    var obj = document.getElementById("check5");
    var obj1 = document.getElementById("check6");
    var obj2 = document.getElementById("check7");
    if (obj.checked == true) {
        $("#ch5-1").prop("checked", true);
        $("#ch5-2").prop("checked", true);
        $("#ch5-3").prop("checked", true);
        $("#ch7-3").prop("checked", true);
    }else{
        $("#ch5-1").prop("checked", false);
        $("#ch5-2").prop("checked", false);
        $("#ch5-3").prop("checked", false);
        if(obj1.checked == false &&obj2.checked == false){
            $("#ch7-3").prop("checked", false);
        }
    }
}

function change6() {
    var obj1 = document.getElementById("check5");
    var obj = document.getElementById("check6");
    var obj2 = document.getElementById("check7");
    if (obj.checked == true) {
        $("#ch6-1").prop("checked", true);
        $("#ch6-2").prop("checked", true);
        $("#ch7-3").prop("checked", true);
    }else{
        $("#ch6-1").prop("checked", false);
        $("#ch6-2").prop("checked", false);
        if(obj1.checked == false &&obj2.checked == false){
            $("#ch7-3").prop("checked", false);
        }
    }
}
function change7() {
    var obj1 = document.getElementById("check5");
    var obj2 = document.getElementById("check6");
    var obj = document.getElementById("check7");
    if (obj.checked == true) {
        $("#ch7-1").prop("checked", true);
        $("#ch7-2").prop("checked", true);
        $("#ch7-3").prop("checked", true);
    }else{
        $("#ch7-1").prop("checked", false);
        $("#ch7-2").prop("checked", false);
        if(obj1.checked == false &&obj2.checked == false){
            $("#ch7-3").prop("checked", false);
        }
    }
}