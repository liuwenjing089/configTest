var userPower = 9;
var groupKey = 0;
$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    groupKey = Request['groupKey'];
	
    //初始值
    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                type = result.data.userType;
                $("#userType").val(result.data.userType);
                $("#loginuser").val(result.data.username);
                
                if(result.data.power == 0){
                	$("#type").show();
                }else{
                	$("#type").hide();
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
    query(type);
});

function query(type) {
    var beginTime = $("#beginTime").val().replace("T", " "),
    endTime = $("#endTime").val().replace("T", " ");
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../mouldPreventionType/getRepairToMouldPreventionCount.do",
        type: "POST",
        dataType: "json",
        data: {
            "type": 2,
            "state": 9,
            "beginTime": beginTime,
            "endTime": endTime,
            "createUser": "",
            "workMan": "",
            "groupKey": groupKey
        },
        async:false,
        success: function (result) {
            if (result.ret == '1') {
                PageCount = result.data;
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
    InitTableElse(0); //Load事件，初始化表格数据，页面索引为0（第一页）
    //分页，PageCount是总条目数，这是必选参数，其它参数都是可选
    $("#Pagination").pagination(PageCount, {
        callback: PageCallback, //PageCallback() 为翻页调用次函数。
        prev_text: "« 上一页",
        next_text: "下一页 »",
        items_per_page: pageSize,
        num_edge_entries: 2, //两侧首尾分页条目数
        num_display_entries: 6, //连续分页主体部分分页条目数
        current_page: pageIndex //当前页索引
    });
    //翻页调用
    function PageCallback(index, jq) {
        InitTableElse(index);
    }
}
//其他角色
function InitTableElse(pageIndex) {
    var type= $("#userType").val();
    var beginTime = $("#beginTime").val().replace("T", " "),
    endTime = $("#endTime").val().replace("T", " ");
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../mouldPreventionType/getRepairToMouldPreventionList.do",
        type: "POST",
        dataType: "json",
        data:{
            "type":2,
            "state":9,
            "beginTime":beginTime,
            "endTime":endTime,
            "createUser":"",
            "workMan":"",
            "groupKey": groupKey,
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#stateList").html("");
                var equipList = result.data;
                for(var i = 0; i < equipList.length; i++) {
                    var data = equipList[i];
                    var j = i+1+beginIndex;
                    var div  = "<tr class='text-c'>" +
                        "<td>" + j + "</td>" +
                        "<td title='"+checkNull(data.applicant)+"'>" + checkNull(data.applicantUserName) + "</td>" +
                        "<td>" + data.reportRepairTime + "</td>" +
                        "<td title='"+checkNull(data.workMan)+"'>" + checkNull(data.userName) + "</td>" +
                        "<td>" + checkNull(data.vehicleType) + "</td>" +
                        "<td>" + checkNull(data.equipName) + "</td>" +
                        "<td>" + checkNull(data.lineName) + "</td>" +
                        "<td>" + checkNull(data.appearance) + "</td>" +
                        "<td>"+
                        appState(data.id, data.repairType) +
                        "</td>"+
                        "</tr>";
                    $("#stateList").append(div);
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

function appState(id, repairType) {
	
	
	return "<a class='btn btn-primary radius' style='margin-left:10px;' onclick=\"detail('" + id + "','"+ repairType +"')\">详情</a>" ;
}

//点击详情按钮
function detail(id, repairType){
	var userType = $("#userType").val();
	
	var title = "模具维修详情";
	var url = "selectMouldReports.html?id=" + id+"&userType="+userType + "&repairType=" + repairType;

    open2(title, url);

}

function open2(title, url){
    var wWidth = window.parent.innerWidth*0.95;
    var wHeight = window.parent.innerHeight*0.95;
	
    parent.layer.open({
        type: 2,
        id: 'detailRepairs',
        title: title,
        btn: ['关闭'],
        area: [wWidth + 'px', wHeight + 'px'],
        content: url, //iframe的url，no代表不显示滚动条
        yes: function (index, layero) {

        	parent.layer.close(index);
        },
        cancel:function (index, layero) {
        }
    });
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