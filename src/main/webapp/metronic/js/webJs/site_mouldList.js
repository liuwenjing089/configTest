var jumpIndex = 0;

$(document).ready(function() {
//	var avaf = 222;
//	var username = ("张三");
//	var value = 1;
//	
//	document.cookie="name="+ escape(username);
//	document.cookie="value="+ escape(value);
//    var Request = new Object();
//    Request = GetRequest();
//    jumpIndex = Request['jumpIndex'];
//    
//    if(jumpIndex == null || jumpIndex =="" || jumpIndex == undefined){
//    	jumpIndex = 0;
//    }
	
	
    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                var a = result.data.username;
                var b = result.data.password;
                type = result.data.userType;
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
    querySelect();
    query();
});
function query() {
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    
    var factory = $("#factory").val();
    var vehicleType = $("#vehicleType").val();
    var figureNumber = $("#figureNumber").val();
    var assetCoding = $("#assetCoding").val();
    
    
    $.ajax({
        url: "../mould/getMouldCount.do",
        type: "POST",
        dataType: "json",
        data:{"factory":factory, "vehicleType": vehicleType, "figureNumber": figureNumber, "assetCoding": assetCoding},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                PageCount=result.data;
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
    InitTable(0); //Load事件，初始化表格数据，页面索引为0（第一页）
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
    	jumpIndex = index;
        InitTable(index);
    }
}
function InitTable(pageIndex) {
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    
    var factory = $("#factory").val();
    var vehicleType = $("#vehicleType").val();
    var figureNumber = $("#figureNumber").val();
    var assetCoding = $("#assetCoding").val();
    
    $.ajax({
        url: "../mould/getMouldList.do",
        type: "POST",
        dataType: "json",
        data:{
        	"factory":factory,
        	"vehicleType": vehicleType, 
        	"figureNumber": figureNumber, 
        	"assetCoding": assetCoding,
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#mouleList").html("");
                var mouleList = result.data;
                for(var i = 0; i < mouleList.length; i++) {
                    var data = mouleList[i];
                    var j = i+1+beginIndex;
                    var div  = "<tr class='text-c'>" +
                        "<td>" + j + "</td>" +
                        "<td style='display: none'>" + data.id + "</td>" +
                        "<td>"+ data.factoryName +"</td>" +
                        "<td>"+ data.vehicleType +"</td>" +
                        "<td>"+ data.figureNumber +"</td>" +
                        "<td>" + data.assetCoding + "</td>" +
                        "<td>" + data.manufacturer + "</td>" +
                        "<td>" + data.useDate + "</td>" +
                        "<td>" + (data.model || "") + "</td>" +
                        "<td>"+
                        addBaoxiu(data.id,data.figureNumber,data.model)+
                        "</td>"+
                        "</tr>";
                    $("#mouleList").append(div);
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

function addBaoxiu(id,figureNumber,model) {
    if(type==1||type==2){
        return "<a class='btn btn-primary radius'  style='margin-left:10px;' onclick=\"repairMould('" + id + "','"+ figureNumber +"','"+ model +"')\">报修</a>";
    }else{
        return "";
    }
}

function editMould(id){
    window.location.href="editMould.html?id="+id;
}

// 点击检索
function search() {
	query();
}
function mould_add(){
    window.location.href="addMould.html?jumpIndex=" + jumpIndex;
}

function delMould(id) {
    $.ajax({
        url: "../equip/deleteEq.do",
        type: "POST",
        dataType: "json",
        data: {
            "id": id
        },
        success: function (result) {
            if (result.ret == '1') {
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
    window.location.href="equipment-list.html"
}

function repairMould(id, figureNumber, model){
	//给隐藏域赋值用于弹出层后使用
    $("#transmit_id").val(id);
    $("#transmit_figureNumber").val(figureNumber);
    $("#transmit_model").val(model);
 
    var wWidth = window.innerWidth*0.95;
    var wHeight = window.innerHeight*0.95;
    layer.open({
        type: 2,
        id: 'mouldRepairToOperation',
        title: '报修申请',
        btn: ['提交', '取消'],
        area: [wWidth + 'px', wHeight + 'px'],
        content: 'mouldRepairToOperation.html', //iframe的url，no代表不显示滚动条
        yes: function (index, layero) {
        	var frameObj = window.document.getElementById('mouldRepairToOperation').getElementsByTagName('iframe')[0];
            var result = frameObj.contentWindow.save();
            
            if(result == 1){
            	layer.msg("提交报修成功");
            	layer.close(index);
            }else{
            	layer.msg(result);
            	return;
            }
            
            

        },
        btn2: function (index, layero) {
        	
        },
        cancel:function (index, layero) {
        	
        }
    });
	
}

function spotRule(id, annualOutput){

   window.location.href="mouldSpotRuleList.html?id="+ id +"&annualOutput=" + annualOutput + "&type=2";	
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

function spotRepairHistory(id){
	var url = "mouldRepairHistory.html?id="+ id;
	var title = "维修记录";
	Hui_admin_tabs(url, title);
}


function querySelect(){
    $.ajax({
        url: '../mould/initCombox.do',
        type: 'GET',
        dataType: 'JSON',
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                initComBox(result.data, "factory");

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
}

function initComBox(selectList, htmlId){

    var optionHtml = "<option value=''>请选择</option>";
    $(selectList).each(function(i, e) {

        optionHtml += '<option value="' + e.id + '">' + e.name + '</option>';
    });
    $("#" + htmlId).append(optionHtml);

}