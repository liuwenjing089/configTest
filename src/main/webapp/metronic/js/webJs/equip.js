var jumpIndex = 0;
var userType;
$(document).ready(function() {
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
                var c = result.data.userType;
                userType = c;
                //根据权限是否显示添加按钮 目前4和5有权限
                if(userType == 4 || userType == 5){
                	$("#addButtion").show();
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
    query();
});
function query() {
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../equip/getEquipCount.do",
        type: "POST",
        dataType: "json",
        data:{},
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
    $.ajax({
        url: "../equip/getEquip.do",
        type: "POST",
        dataType: "json",
        data:{
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
                        "<td style='display: none'>" + data.id + "</td>" +
                        "<td>"+ data.factoryName +"</td>" +
                        "<td>"+ data.equipName +"</td>" +
                        "<td>"+ data.equipDescription +"</td>" +
                        "<td>" + data.useBeginTime + "</td>" +
                        "<td>" + data.yearsLimit + "</td>" +
                        "<td>" + data.equipModel + "</td>" +
                        "<td>" + data.standard + "</td>" +
                        operateButton(data) +

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
                // if(error != "") {
                //     layer.msg(error);
                //     window.location.href="login.html";
                // }
                //layer.alert(error);
            }
        }
    });
}

//修改
function editEq(id){
    //window.location.href="editequip.html?id="+id;
	Hui_admin_tabs("editequip.html?id="+id + "&passIndex="+ jumpIndex, "修改设备信息");
}

// 点击检索
function search(index) {
    var equipName = $("#equipName").val(),
        equipDescription = $("#equipDescription").val(),
        useBeginTime = $("#useBeginTime").val(),
        equipModel = $("#equipModel").val(),
        standard = $("#standard").val();
    var factory = $("#factory option:selected").val();
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = index;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../equip/getEquipByFrimCount.do",
        type: "POST",
        dataType: "json",
        data: {
            "equipName": equipName,
            "equipDescription": equipDescription,
            "useBeginTime": useBeginTime,
            "equipModel": equipModel,
            "standard": standard,
            "factory":Number(factory)
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
    InitTableNum(index, equipName, equipDescription,useBeginTime,equipModel,standard,factory); //Load事件，初始化表格数据，页面索引为0（第一页）
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
        InitTableNum(index, equipName, equipDescription,useBeginTime,equipModel,standard,factory);
    }
}
function InitTableNum(pageIndex,equipName, equipDescription,useBeginTime,equipModel,standard,factory) {
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../equip/getEquipByFrim.do",
        type: "POST",
        dataType: "json",
        data: {
            "equipName": equipName,
            "equipDescription": equipDescription,
            "useBeginTime": useBeginTime,
            "equipModel": equipModel,
            "standard": standard,
            "factory":Number(factory),
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function (result) {
            if (result.ret == '1') {
                $("#equipList").html("");
                var equipList = result.data;
                for (var i = 0; i < equipList.length; i++) {
                    var data = equipList[i];
                    var j = i + 1;
                    var div  = "<tr class='text-c'>" +
                        "<td>" + j + "</td>" +
                        "<td style='display: none'>" + data.id + "</td>" +
                        "<td>"+ data.factoryName +"</td>" +
                        "<td>"+ data.equipName +"</td>" +
                        "<td>"+ data.equipDescription +"</td>" +
                        "<td>" + data.useBeginTime + "</td>" +
                        "<td>" + data.yearsLimit + "</td>" +
                        "<td>" + data.equipModel + "</td>" +
                        "<td>" + data.standard + "</td>" +
                        operateButton(data) + 
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
                for (var i = 0; i < result.data.length; i++) {
                    error += (result.data[i].message);
                }
                layer.alert(error);
            }
        }
    });
}

//详情
function eqSee(id) {
   // window.location.href="equipsee.html?id="+id;
	Hui_admin_tabs("equipsee.html?id="+id, "设备信息详情");
}

//添加
function equip_add(){
    //window.location.href="addequip.html";
	Hui_admin_tabs("addequip.html?passIndex="+ jumpIndex, "创建设备信息");
}

//删除
function delEq(id) {
	
	layer.confirm('真的要删除吗？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
           delEquip(id);
		}, function(){

		});

}


function delEquip(id){
	
    $.ajax({
        url: "../equip/deleteEq.do",
        type: "POST",
        dataType: "json",
        data: {
            "id": id
        },
        success: function (result) {
            if (result.ret == '1') {
                layer.msg("删除成功");
                search(jumpIndex);
            }else if(result.ret == '3'){
                layer.msg("登录超时,请重新登录");
                setTimeout(function(){
                    top.window.location.href= "login.html"
                }, 1000 );
            } else {
                var error = "";
                for (var i = 0; i < result.data.length; i++) {
                    error += (result.data[i].message);
                }
                layer.alert(error);
            }
        }
    });
}

function initComBox(selectList, htmlId){

    var optionHtml = "<option value='9'>请选择</option>";
    $(selectList).each(function(i, e) {

        optionHtml += '<option value="' + e.id + '">' + e.name + '</option>';
    });
    $("#" + htmlId).append(optionHtml);

}

function operateButton(data){
	
	var div = "";
	
	if(userType == 5){
		div =                         
	        "<td><a class='btn btn-primary radius' style='margin-right:10px'"+
	        "onclick='editEq(" + data.id + ")'>修改</a>"+
	        "<a class='btn btn-primary radius' style='margin-right:10px'"+
	        "onclick='delEq(" + data.id + ")'>删除</a>"+
	        "<a class='btn btn-primary radius'"+
	        "onclick='eqSee(" + data.id + ")'>详情</a>"+
	        "</td>";
	}else{
		div =                         
	        "<td>" +
	        "<a class='btn btn-primary radius' style='margin-right:10px'"+
	        "onclick='delEq(" + data.id + ")'>删除</a>"+
	        "<a class='btn btn-primary radius'"+
	        "onclick='eqSee(" + data.id + ")'>详情</a>"+
	        "</td>";
	}
	

	
	return div;
	
}

function reflush(index){
	//query(userPower, index);
	search(index);
}