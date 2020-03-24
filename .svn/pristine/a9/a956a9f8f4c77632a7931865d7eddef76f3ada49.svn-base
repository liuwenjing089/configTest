$(document).ready(function() {
    var Request = new Object();
    Request = GetRequest();
    var id = Request['id'];
	//加载下拉
	querySelect();
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
    $("#id").val(id);
    $.ajax({
        url: "../line/getLineByid.do",
        type: "POST",
        dataType: "json",
        data: {
            "id": Number(id)
        },
        success: function (result) {
            if (result.ret == '1') {
                $('#id').val(id);
                $("#factory").val(result.data.factory);
                $('#beltlineName').val(result.data.beltlineName);
                $('#beltlineDescription').val(result.data.beltlineDescription);

                var all_options = document.getElementById("lineType").options;
                for (var i=0; i<all_options.length; i++){
                    if (all_options[i].value == result.data.lineType) {
                        all_options[i].selected = true;
                    }
                }
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
                alert(error);
            }
        }
    });
}

//点击保存按钮
function save(){
    var id = $("#id").val(),
	    factory = $("#factory").val();
        beltlineName = $("#beltlineName").val(),
        beltlineDescription = $("#beltlineDescription").val();
    var lineType = $("#lineType option:selected").val();
    
    if (factory == "") {
        layer.alert("请选择工厂");
        return;
    }
    
    if (isNullorEmpty(beltlineName)) {
        layer.alert("生产线名称不能为空");
        return;
    }
    $.ajax({
        url: "../line/updateLine.do",
        type: "POST",
        dataType: "json",
        data: {
            "id":Number(id),
            "factory": factory,
            "beltlineName":beltlineName,
            "beltlineDescription":beltlineDescription,
            "lineType":Number(lineType)
        },
        success: function (result) {
            if (result.ret == '1') {
                window.location.href = "produce-list.html";
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
function layer_close() {
    window.location.href = "produce-list.html";
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