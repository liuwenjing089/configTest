$(document).ready(function() {
	//加载下拉
	querySelect();
	//加载数据
    query();
});
function query() {
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../line/getLineCount.do",
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
        InitTable(index);
    }
}
function InitTable(pageIndex) {
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../line/getLine.do",
        type: "POST",
        dataType: "json",
        data:{
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#lineList").html("");
                var lineList = result.data;
                for(var i = 0; i < lineList.length; i++) {
                    var data = lineList[i];
                    var j = i+1+beginIndex;
                    var div  = "<tr class='text-c'>" +
                        "<td>" + j + "</td>" +
                        "<td style='display: none'>" + data.id + "</td>" +
                        "<td>"+ data.factoryName +"</td>" + 
                        "<td>"+ checkLine(data.lineType) +"</td>" +
                        "<td>"+ data.beltlineName +"</td>" +
                        "<td>"+ data.beltlineDescription +"</td>" +
                        "<td><a class='btn btn-primary radius'  style='margin-left:10px;'"+
                        "onclick='editline(" + data.id + ")'>修改</a>"+
                        "<a class='btn btn-primary radius'  style='margin-left:10px;'"+
                        "onclick='delline(" + data.id + ")'>删除</a>"+
                        "</td>"+
                        "</tr>";
                    $("#lineList").append(div);
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
function checkLine(type) {
    if(type==1){
        return "普通";
    }else{
        return "成型机";
    }
}
//添加
function line_add() {
    window.location.href="addline.html";
}

//检索
function search() {
    var beltlineName = $("#beltlineName").val(),
//    	factory = $("factory").val(),
        beltlineDescription = $("#beltlineDescription").val();
    var lineType = $("#lineType option:selected").val();
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可
    $.ajax({
        url: "../line/getLineByFirmCount.do",
        type: "POST",
        dataType: "json",
        data:{
//            "factory": factory,
        	"beltlineName":beltlineName,
            "beltlineDescription":beltlineDescription,
            "lineType":Number(lineType)
        },
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
    InitTableSearch(0, beltlineName, beltlineDescription, lineType); //Load事件，初始化表格数据，页面索引为0（第一页）
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
        InitTableSearch(index, beltlineName,beltlineDescription,lineType);
    }
}
function InitTableSearch(pageIndex, beltlineName,beltlineDescription,lineType) {
    var pageSize=10;
    var beginIndex = pageIndex*pageSize;
    $.ajax({
        url: "../line/getLineByFirm.do",
        type: "POST",
        dataType: "json",
        data:{
//            "factory": factory,
        	"beltlineName":beltlineName,
            "beltlineDescription":beltlineDescription,
            "lineType":lineType,
            "beginIndex":beginIndex,
            "pageSize":pageSize
        },
        success: function(result) {
            if(result.ret == '1') {
                $("#lineList").html("");
                var lineList = result.data;
                for(var i = 0; i < lineList.length; i++) {
                    var data = lineList[i];
                    var j = i+1+beginIndex;
                    var div  = "<tr class='text-c'>" +
                        "<td>" + j + "</td>" +
                        "<td style='display:none;'>" + data.id + "</td>" +
                        "<td>"+ data.factoryName +"</td>" + 
                        "<td>"+ checkLine(data.lineType) +"</td>" +
                        "<td>"+ data.beltlineName +"</td>" +
                        "<td>"+ data.beltlineDescription +"</td>" +
                        "<td><a class='btn btn-primary radius'  style='margin-left:10px;'"+
                        "onclick='editline(" + data.id + ")'>修改</a>"+
                        "<a class='btn btn-primary radius'  style='margin-left:10px;'"+
                        "onclick='delline(" + data.id + ")'>删除</a>"+
                        "</td>"+
                        "</tr>";
                    $("#lineList").append(div);
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

//修改
function editline(id) {
    window.location.href="editline.html?id="+id;
}

//删除
function delline(id) {
    var equip=[];
    $.ajax({
        url: "../equip/getEqBylineID.do",
        type: "POST",
        dataType: "json",
        data: {
            "lineId": Number(id)
        },
        async:false,
        success: function (result) {
            if (result.ret == '1') {
                equip = result.data;
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
    $.ajax({
        url: "../equip/deleteEqline.do",
        type: "POST",
        dataType: "json",
        data: JSON.stringify(equip),
        async:false,
        contentType: "application/json; charset=utf-8",
        beforeSend: function (XMLHttpRequest) {

        },
        complete: function (XMLHttpRequest, textStatus) {

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

    $.ajax({
        url: "../line/deleteLine.do",
        type: "POST",
        dataType: "json",
        data: {
            "id": Number(id)
        },
        async:false,
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
    window.location.href="produce-list.html";
}


function querySelect() {
	$.ajax({
		url: '../mould/initCombox.do',
		type: 'GET',
		dataType: 'JSON',
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