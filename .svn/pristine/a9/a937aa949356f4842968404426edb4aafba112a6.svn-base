var index;
$(document).ready(function() {
	index = parent.layer.getFrameIndex(window.name); //获取窗口索引
});

function shangchuan(){
    var fileUrls = "";
    var obj = $("input[name='fileUrls']");
    if(obj.length > 0){
        $(obj).each(function(j,item){
            console.log("下标:"+j);
            console.log("value值:"+item.value);
            fileUrls += j > 0 ? ","+item.value : item.value;
          });
    }
    
    var pictureType = $("#pictureType").val();
    if(pictureType == 0){
    	layer.msg("请选择图片所属类型");
    	return;
    }else if(pictureType == 1){
    	var parentUrl = parent.$('#productUrl').val();
    	pictureView(fileUrls, "productView");
    	parentUrl = urlString(parentUrl);
    	parent.$('#productUrl').val(parentUrl + fileUrls);
    	parent.layer.close(index);
    }else if(pictureType == 2){
    	var parentUrl = parent.$('#faultLocationUrl').val();
    	pictureView(fileUrls, "faultLocationView");
    	parentUrl = urlString(parentUrl);
    	parent.$('#faultLocationUrl').val(parentUrl + fileUrls);
    	parent.layer.close(index);
    }else if(pictureType == 3){
    	var parentUrl = parent.$('#formingMachineUrl').val();
    	pictureView(fileUrls, "formingMachineView");
    	parentUrl = urlString(parentUrl);
    	parent.$('#formingMachineUrl').val(parentUrl + fileUrls);
    	parent.layer.close(index);
    }else if(pictureType == 4){
    	var parentUrl = parent.$('#maintenanceCompletedUrl').val();
    	pictureView(fileUrls, "maintenanceCompletedView");
    	parentUrl = urlString(parentUrl);
    	parent.$('#maintenanceCompletedUrl').val(parentUrl + fileUrls);

    	parent.layer.close(index);
    }
}

function urlString(url){
	url = $.trim(url);
	if(url != ""){
		url = url + ","
	}
	return url;
}

function pictureView(url, pictureDiv){
	var urls = url.split(",");
	
	if(urls != "" && urls.length > 0){
		for(var i=0; i < urls.length; i++){
			var picturUrl = urls[i];
			var html = "<div>" +
					   	  "<a href='#' onclick=\"view('"+ picturUrl +"')\" style='margin-left:50px;width:100px;' ><span style='border:1px solid #fff;display:inline-block;width:50px;text-align:center;'>追加图片</span>"+ "<span style='display:inline-block;border:1px solid #fff;width:15px;text-align:center;'>"+(i+1)+"</span>" +"&nbsp;&nbsp;&nbsp;&nbsp;点击查看</a>" +
					   	  "<input type='text' name='fileUrls' value='"+ picturUrl +"' style='display:none'/>" +
						  "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type='button' onclick='delPictur(this)' class='btn-sm btn-primary radius'style='margin:5px;padding:2px;'>删除当前图片</button>" +
					   "</div>";	  
			parent.$("#"+ pictureDiv).append(html);
		}
	}

}