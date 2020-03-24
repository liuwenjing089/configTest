$(document).ready(function() {
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
            } else {
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
                        "<td>" + data.id + "</td>" +
                        "<td>"+ data.equipName +"</td>" +
                        "<td>"+ data.equipDescription +"</td>" +
                        "<td>" + data.useBeginTime + "</td>" +
                        "<td>" + data.yearsLimit + "</td>" +
                        "<td>" + data.equipModel + "</td>" +
                        "<td>" + data.standard + "</td>" +
                        "<td><a class='btn btn-primary radius'  style='margin-left:10px;'"+
                        "onclick='supplierEdit(" + data.supID + ")'>详情</a>"+
                        "</td>"+
                        "</tr>";
                    $("#equipList").append(div);
                }
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


// 点击检索
function search() {
    var equipName = $("#equipName").val(),
        equipDescription = $("#equipDescription").val(),
        useBeginTime = $("#useBeginTime").val(),
        equipModel = $("#equipModel").val(),
        standard = $("#standard").val();
    var PageCount;  //总页数，通过ajax获取
    var pageIndex = 0;     //页面索引初始值
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
            "standard": standard
        },
        async: false,
        success: function (result) {
            if (result.ret == '1') {
                PageCount = result.data;
            } else {
                PageCount = 1;
            }
        }
    });
    InitTableNum(0, equipName, equipDescription,useBeginTime,equipModel,standard); //Load事件，初始化表格数据，页面索引为0（第一页）
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
        InitTableNum(index, equipName, equipDescription,useBeginTime,equipModel,standard);
    }
}
function InitTableNum(pageIndex,equipName, equipDescription,useBeginTime,equipModel,standard) {
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
                        "<td>" + data.id + "</td>" +
                        "<td>"+ data.equipName +"</td>" +
                        "<td>"+ data.equipDescription +"</td>" +
                        "<td>" + data.useBeginTime + "</td>" +
                        "<td>" + data.yearsLimit + "</td>" +
                        "<td>" + data.equipModel + "</td>" +
                        "<td>" + data.standard + "</td>" +
                        "<td><a class='btn btn-primary radius'  style='margin-left:10px;'"+
                        "onclick='supplierEdit(" + data.supID + ")'>详情</a>"+
                        "</td>"+
                        "</tr>";
                    $("#equipList").append(div);
                }
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


