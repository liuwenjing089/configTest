//保存
$(document).ready(function() {
	query();
})

function save() {
	var factory = $("#factory").val();
	
    var beltlineName = $("#beltlineName").val(),
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
    if (lineType=="9") {
        layer.alert("请选择生产线类型");
        return;
    }
    $.ajax({
        url: "../line/add.do",
        type: "POST",
        dataType: "json",
        data: {
            "factory": factory,
        	"beltlineName": beltlineName,
            "beltlineDescription": beltlineDescription,
            "lineType":Number(lineType)
        },
        async:false,
        beforeSend: function (XMLHttpRequest) {
            $("#save").attr('disabled',true);
        },
        complete: function (XMLHttpRequest, textStatus) {

        },
        success: function (result) {
            if (result.ret == '1') {
                window.location.href="produce-list.html";
                // layer.msg("保存成功");
                // setTimeout(function(){
                //     window.location.href="produce-list.html";
                // }, 2000 );
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
                $("#save").removeAttr('disabled');
            }
        }
    });
}
//取消
function layer_close(){
    window.location.href = "produce-list.html";
}

function query() {
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