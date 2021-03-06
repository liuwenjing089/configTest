var jumpIndex = 0;
$(document).ready(function() {
//    var vas;
//	var strcookie = document.cookie;//获取cookie字符串
//	var arrcookie = strcookie.split("; ");//分割
//	//遍历匹配
//	for ( var i = 0; i < arrcookie.length; i++) {
//	var arr = arrcookie[i].split("=");
//		if (arr[0] == "name"){
//			vas = unescape(arr[1]);
//		}else{
//			vas ="";
//		}
//	}

    var Request = new Object();
    Request = GetRequest();
    jumpIndex = Request['passIndex'];
	
	query();
	
	$("#registerForm").validate({
		onfocusout: function(element) {
			$(element).valid();
		},
		submitHandler: function(form) {
			$("#registerForm").ajaxSubmit({
				type: "POST",
				url: "../mould/insertMould.do",
				dataType: "json",
		        async:false,
		  		beforeSend: function (XMLHttpRequest) {

		            $("#save").attr('disabled',true);
		        },
		        complete: function (XMLHttpRequest, textStatus) {

//		        	$("#save").removeAttr('disabled');
		        },
				success: function(result) {
					if(result.ret == '1') {
			  			refurbish();
			  			removeIframe();
					} else if(result.ret == '3'){
						layer.msg("登录超时,请重新登录");
						setTimeout(function(){
							top.window.location.href= "login.html"
						}, 1000 );
					}else {
						$("#save").removeAttr('disabled');
						var error = "";
						for(var i = 0; i < result.data.length; i++) {
							error += (result.data[i].message + "<br\>");
						}
						if(error != "") {
							$("#errorInfo").css('display', 'block');
							$("#errorInfo").css({
								color: "red"
							});
							$("#errorInfo").html(error);
						}
					}
				}
			});
		},
		rules: {
			vehicleType: {
				required: true
			},
			factory: {
				required: true
			},
			annualOutput:{
				required: true,
				number:true  
			},
			figureNumber: {
				required: true
			},
			assetCoding: {
				required: true
			},
			manufacturer: {
				required: true     
			},
			moldCompletionTime: {
				required: true        
			},
			cavityNumber: {
				required: true
			},
			model: {
				required: true
			},
			useDate: {
				required: true
			},
			totalWeight: {
				required: true,
			},
			outlineDimension: {
				required: true,
			},
			
			pictureUrl: {
				required: true,
			},
			drawingDeposit: {
				required: true,
			}

		},
		messages: {
			vehicleType: {
				required: "车种不能为空！"
			},
			factory: {
				required: "请选择工厂！"
			},
			annualOutput: {
				required: "车种不能为空！",
				number: "请输入合法的数字！"
			},
			figureNumber: {
				required: "图号不得为空！"
			},
			assetCoding: {
				required: "资产编码不得为空！"
			},
			manufacturer: {
				required: "生产厂家不得为空！"
			},
			moldCompletionTime: {
				required: "模具完成时间不得为空！"
			},
			cavityNumber: {
				required: "型腔数不得为空！"
			},
			model: {
				required: "型号不得为空！"
			},
			useDate: {
				required: "使用时间不得为空!"
			},
			totalWeight: {
				required: "总重量不得为空!!",
			},
			outlineDimension: {
				required: "外形尺寸不得为空!",
			},
			pictureUrl: {
				pictureUrl: "模具图纸位置不得为空!",
			},
			drawingDeposit: {
				required: "请选择模具类型!",
			},
		}
	});

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

//点击关闭按钮
function layer_close(){
    //window.location.href = "mould-list.html?jumpIndex="+ jumpIndex;
	removeIframe();
}
function save() {

}

function query() {
	$.ajax({
		url: '../mould/initComboxs.do',
		type: 'GET',
		dataType: 'JSON',
		success: function(result) {
			if(result.ret == '1') {
				initComBox(result.data.factory, "factory");
				initComBox(result.data.mouldType, "drawingDeposit");
				
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

	var optionHtml = "<option value='0'>请选择</option>";
	$(selectList).each(function(i, e) {

		optionHtml += '<option value="' + e.id + '">' + e.name + '</option>';
	});
	$("#" + htmlId).append(optionHtml);
	
}


//刷新模具一览页面，同时不改变页数
function refurbish(){
	var mouldListIfram = window.parent.document.getElementById("mouldList");
	var mouldListHtml = mouldListIfram.contentWindow;
	mouldListHtml.reflush(jumpIndex);
}