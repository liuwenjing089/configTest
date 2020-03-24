$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
    var cycle = Request['cycle'];
    $("#cycle").val(cycle);
    $("#id").val(id);

    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
//                if(result.data.userType==2){
//                    document.getElementById("classSearch").style.display="";
//                }
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
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../spotDetail/getDetailEquipCount.do",
        type: "POST",
        dataType: "json",
        data:{
            "id":id,
            "assetNum":"",
            "equipNum":"",
            "equipName":"",
            "equipModel":"",
            "standard":"",
            "state":9,
            "confirmState":9,
            "classConfirmMan":"9"
        },
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                PageCount=result.data;
            }else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            } else {
                PageCount = 1;
            }
        }
    });
    InitTable(0,id); //Load事件，初始化表格数据，页面索引为0（第一页）
    //分页，PageCount是总条目数，这是必选参数，其它参数都是可选
    $("#Pagination").pagination(PageCount, {
        callback: PageCallback, //PageCallback() 为翻页调用次函数。
        prev_text: "« 上一页",
        next_text: "下一页 »",
        items_per_page: pageSize,
        num_edge_entries: 2, //两侧首尾分页条目数
        num_display_entries: 6, //连续分页主体部分分页条目数
        current_page: pageIndex, //当前页索引
    });
    //翻页调用
    function PageCallback(index, jq) {
        InitTable(index,id);
    }
}
function InitTable(pageIndex,id) {
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../spotDetail/getDetailEquip.do",
        type: "POST",
        dataType: "json",
        data:{
            "id":id,
            "assetNum":"",
            "equipNum":"",
            "equipName":"",
            "equipModel":"",
            "standard":"",
            "state":9,
            "confirmState":9,
            "classConfirmMan":"9",
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#equipList").html("");
                var equipList = result.data;
                for(var i = 0; i < equipList.length; i++) {
                    var data = equipList[i];
                    var j = i+1+beginIndex;
                    var div  = "<tr class='text-c'>" +
                        "<td>" + j + "</td>" +
                        "<td>"+ data.assetNum +"</td>" +
                        "<td>"+data.equipNum +"</td>" +
                        "<td>" + data.equipName + "</td>" +
                        "<td>" + data.equipModel + "</td>" +
                        "<td>" + data.standard + "</td>" +
                        "<td>" + getState(data.state) + "</td>" +
                        "<td>" + getconfirmState(data.confirmState) + "</td>" +
                        "<td><a class='btn btn-primary radius'"+
                        "onclick=\"taskSee('" + data.id + "','"+data.equipModel+"','"+data.equipId+"')\">详情</a>"+
                        "</td>"+
                        "</tr>";
                    $("#equipList").append(div);
                }
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
            }
        }
    });
}
// 点击检索
function search() {
    var id = $("#id").val();
    var assetNum = $("#assetNum").val(),
        equipNum = $("#equipNum").val(),
        equipName = $("#equipName").val(),
        equipModel = $("#equipModel").val(),
        standard = $("#standard").val();
    var state = $("#state option:selected").val();
    var confirmState = $("#confirmState option:selected").val();
    var classConfirmMan ="9";
    //班长确认状态检索
    var classConfirmState = $("#classConfirmState option:selected").val();
    if(classConfirmState =="0"){
        classConfirmMan = "";
    }else if(classConfirmState =="1"){
        classConfirmMan = "1";
    }

    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../spotDetail/getDetailEquipCount.do",
        type: "POST",
        dataType: "json",
        data:{
            "id":Number(id),
            "assetNum":assetNum,
            "equipNum":equipNum,
            "equipName":equipName,
            "equipModel":equipModel,
            "standard":standard,
            "state":Number(state),
            "confirmState":Number(confirmState),
            "classConfirmMan":classConfirmMan
        },
        async: false,
        success: function (result) {
            if (result.ret == '1') {
                PageCount = result.data;
            } else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            }else {
                PageCount = 1;
            }
        }
    });
    InitTableNum(0, id, assetNum,equipNum,equipName,equipModel,standard,state,confirmState,classConfirmMan); //Load事件，初始化表格数据，页面索引为0（第一页）
    //分页，PageCount是总条目数，这是必选参数，其它参数都是可选
    $("#Pagination").pagination(PageCount, {
        callback: PageCallback, //PageCallback() 为翻页调用次函数。
        prev_text: "« 上一页",
        next_text: "下一页 »",
        items_per_page: pageSize,
        num_edge_entries: 2, //两侧首尾分页条目数
        num_display_entries: 6, //连续分页主体部分分页条目数
        current_page: pageIndex, //当前页索引
    });

    //翻页调用
    function PageCallback(index, jq) {
        InitTableNum(index, id, assetNum,equipNum,equipName,equipModel,standard,state,confirmState,classConfirmMan);
    }
}
function InitTableNum(pageIndex,id, assetNum,equipNum,equipName,equipModel,standard,state,confirmState,classConfirmMan) {
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../spotDetail/getDetailEquip.do",
        type: "POST",
        dataType: "json",
        data:{
            "id":Number(id),
            "assetNum":assetNum,
            "equipNum":equipNum,
            "equipName":equipName,
            "equipModel":equipModel,
            "standard":standard,
            "state":Number(state),
            "confirmState":Number(confirmState),
            "classConfirmMan":classConfirmMan,
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#equipList").html("");
                var equipList = result.data;
                for(var i = 0; i < equipList.length; i++) {
                    var data = equipList[i];
                    var j = i+1+beginIndex;
                    var div  = "<tr class='text-c'>" +
                        "<td>" + j + "</td>" +
                        "<td>"+ data.assetNum +"</td>" +
                        "<td>"+data.equipNum +"</td>" +
                        "<td>" + data.equipName + "</td>" +
                        "<td>" + data.equipModel + "</td>" +
                        "<td>" + data.standard + "</td>" +
                        "<td>" + getState(data.state) + "</td>" +
                        "<td>" + getconfirmState(data.confirmState) + "</td>" +
                        "<td><a class='btn btn-primary radius'"+
                        "onclick=\"taskSee('" + data.id + "','"+data.equipModel+"','"+data.equipId+"')\">详情</a>"+
                        "</td>"+
                        "</tr>";
                    $("#equipList").append(div);
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
            }
        }
    });
}
function getState(state) {
    if(state==0){
        return "未完成";
    }else{
        return "已完成";
    }
}
function getconfirmState(state) {
    if(state==0){
        return "未确认";
    }else if(state==1){
        return "系长已确认";
    }else if(state==2){
        return "科长已确认";
    }
}
function taskSee(id,equipModel,equipId) {
    var cycle =  Number($("#cycle").val());
    window.location.href="spotCheckEquip.html?id="+id+"&model="+equipModel+"&cycle="+cycle+"&equipId="+equipId;
}