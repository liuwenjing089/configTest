$(document).ready(function() {

});

//设备搜索
function equipModelSearch() {

	
	var equipModel = $("#equipModel").val();
	
	//加载周期下拉
	//selectCycle(equipModel, 1);

	var ids = new Array();
	$("#menuFuncBase option").each(function() {
		//遍历所有option  
		var id = $(this).val(); //获取option值   
		ids.push(id);
	});

	$.ajax({
		url: '../spotRule/selectEquipModelListByEquipModelToTest.do',
		type: 'POST',
		data: {
			equipModel: equipModel,
			ids: ids
		},
		beforeSend: function(XMLHttpRequest) {

		},
		complete: function(XMLHttpRequest, textStatus) {

		},
		success: function(result) {
			if(result.ret == '1') {

				var selectList = result.data;
				selectVaules(selectList);

			}else if(result.ret == '3'){
				layer.msg("登录超时,请重新登录");
				setTimeout(function(){
					top.window.location.href= "login.html"
				}, 1000 );
			} else {
				var error = "";
				for(var i = 0; i < result.data.length; i++) {
					error += (result.data[i].message + "<br\>");
				}
				if(error != "") {
					layer.msg(error);
				}
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error");
		}

	});
}

function selectVaules(selectList) {

	$('#transferContainer').transfer('refresh',selectList);

}

function getValue(){

	var data = $('#transferContainer').transfer('getData','selectData');
	var i = data.length;

	alert(JSON.stringify(data));
	alert("数据是"+ i + "条");
}
