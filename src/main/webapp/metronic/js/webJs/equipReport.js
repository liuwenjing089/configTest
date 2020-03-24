var jumpIndex = 0;

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
                type = result.data.userType;
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
    queryTable(0);
    querySelect();

});

function queryTable(pageIndex) {

    $.ajax({
        url: "../report/lineReport.do",
        type: "GET",
        dataType: "json",
        success: function(result) {
            if(result.ret == '1') {
               var data = result.data;

               var productionLineList = data.productionLineList;
               var moldingMachineList = data.moldingMachineList;
               
               var div = "";
               
               for(var i = 0; i < productionLineList.length; i++){
            	   var leg = productionLineList.length;
            	   var productionLine = productionLineList[i];
            	   
            	   if(i==0){
                	   div += "<tr class='text-c'>" +
			    	               "<td rowspan=" + leg + ">生产故障</td>" +
				                   "<td>"+ productionLine.year +"</td>" +			 
				                   "<td>"+ productionLine.month +"</td>" +			 
				                   "<td>"+ productionLine.lineName +"</td>" +
				                   "<td>"+ productionLine.cropMovementByFaultQuantity +"</td>" +
				                   "<td>"+ productionLine.cropMovementByNotFaultQuantity +"</td>" +
				                   "<td>" + productionLine.faultTotalQuantity + "</td>" +
				                   "<td>" + productionLine.cropMovementByRepairTime + "</td>" +
				                   "<td>" + productionLine.cropMovementByNotRepairTime + "</td>" +
				                   "<td>" + productionLine.repairTotalTime + "</td>" +
				                   "<td>" + productionLine.longStopQuantity + "</td>" +
				                   "<td>" + productionLine.longStopTime + "</td>" +
				                   "<td>" + productionLine.poorDesign + "</td>" +
				                   "<td>" + productionLine.poorOperation + "</td>" +
				                   "<td>" + productionLine.badCleaning + "</td>" +
				                   "<td>" + productionLine.ageing + "</td>" +
				                   "<td>" + productionLine.findFault + "</td>" +
				                   "<td>" + productionLine.maintenanceTime + "</td>" +
				                   "<td>" + productionLine.productUseTime + "</td>" +
				                   "<td>" + productionLine.jobInconvenience + "</td>" +
				              "</tr>"; 
            	   }else{
                	   div += "<tr class='text-c'>" +
				                   "<td>"+ productionLine.year +"</td>" +			 
				                   "<td>"+ productionLine.month +"</td>" +			 
				                   "<td>"+ productionLine.lineName +"</td>" +
				                   "<td>"+ productionLine.cropMovementByFaultQuantity +"</td>" +
				                   "<td>"+ productionLine.cropMovementByNotFaultQuantity +"</td>" +
				                   "<td>" + productionLine.faultTotalQuantity + "</td>" +
				                   "<td>" + productionLine.cropMovementByRepairTime + "</td>" +
				                   "<td>" + productionLine.cropMovementByNotRepairTime + "</td>" +
				                   "<td>" + productionLine.repairTotalTime + "</td>" +
				                   "<td>" + productionLine.longStopQuantity + "</td>" +
				                   "<td>" + productionLine.longStopTime + "</td>" +
				                   "<td>" + productionLine.poorDesign + "</td>" +
				                   "<td>" + productionLine.poorOperation + "</td>" +
				                   "<td>" + productionLine.badCleaning + "</td>" +
				                   "<td>" + productionLine.ageing + "</td>" +
				                   "<td>" + productionLine.findFault + "</td>" +
				                   "<td>" + productionLine.maintenanceTime + "</td>" +
				                   "<td>" + productionLine.productUseTime + "</td>" +
				                   "<td>" + productionLine.jobInconvenience + "</td>" +
                              "</tr>"; 
            	   }

               }
               
               
               div += "<tr class='text-c'><td colspan='20'></td></tr>";
               
               for(var i = 0; i < moldingMachineList.length; i++){
            	   var leg = moldingMachineList.length;
            	   var moldingMachine = moldingMachineList[i];
            	   
            	   if(i==0){
                	   div += "<tr class='text-c'>" +
			    	               "<td rowspan=" + leg + ">成型故障</td>" +
				                   "<td>"+ moldingMachine.year +"</td>" +			 
				                   "<td>"+ moldingMachine.month +"</td>" +			 
				                   "<td>"+ moldingMachine.lineName +"</td>" +
				                   "<td>"+ moldingMachine.cropMovementByFaultQuantity +"</td>" +
				                   "<td>"+ moldingMachine.cropMovementByNotFaultQuantity +"</td>" +
				                   "<td>" + moldingMachine.faultTotalQuantity + "</td>" +
				                   "<td>" + moldingMachine.cropMovementByRepairTime + "</td>" +
				                   "<td>" + moldingMachine.cropMovementByNotRepairTime + "</td>" +
				                   "<td>" + moldingMachine.repairTotalTime + "</td>" +
				                   "<td>" + moldingMachine.longStopQuantity + "</td>" +
				                   "<td>" + moldingMachine.longStopTime + "</td>" +
				                   "<td>" + moldingMachine.poorDesign + "</td>" +
				                   "<td>" + moldingMachine.poorOperation + "</td>" +
				                   "<td>" + moldingMachine.badCleaning + "</td>" +
				                   "<td>" + moldingMachine.ageing + "</td>" +
				                   "<td>" + moldingMachine.findFault + "</td>" +
				                   "<td>" + moldingMachine.maintenanceTime + "</td>" +
				                   "<td>" + moldingMachine.productUseTime + "</td>" +
				                   "<td>" + moldingMachine.jobInconvenience + "</td>" +
				              "</tr>"; 
            	   }else{
                	   div += "<tr class='text-c'>" +
				                   "<td>"+ moldingMachine.year +"</td>" +			 
				                   "<td>"+ moldingMachine.month +"</td>" +			 
				                   "<td>"+ moldingMachine.lineName +"</td>" +
				                   "<td>"+ moldingMachine.cropMovementByFaultQuantity +"</td>" +
				                   "<td>"+ moldingMachine.cropMovementByNotFaultQuantity +"</td>" +
				                   "<td>" + moldingMachine.faultTotalQuantity + "</td>" +
				                   "<td>" + moldingMachine.cropMovementByRepairTime + "</td>" +
				                   "<td>" + moldingMachine.cropMovementByNotRepairTime + "</td>" +
				                   "<td>" + moldingMachine.repairTotalTime + "</td>" +
				                   "<td>" + moldingMachine.longStopQuantity + "</td>" +
				                   "<td>" + moldingMachine.longStopTime + "</td>" +
				                   "<td>" + moldingMachine.poorDesign + "</td>" +
				                   "<td>" + moldingMachine.poorOperation + "</td>" +
				                   "<td>" + moldingMachine.badCleaning + "</td>" +
				                   "<td>" + moldingMachine.ageing + "</td>" +
				                   "<td>" + moldingMachine.findFault + "</td>" +
				                   "<td>" + moldingMachine.maintenanceTime + "</td>" +
				                   "<td>" + moldingMachine.productUseTime + "</td>" +
				                   "<td>" + moldingMachine.jobInconvenience + "</td>" +
                              "</tr>"; 
            	   }

               }
               
               $("#lineReportList").append(div);
               
               
               
               
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


function querySelect(){
    $.ajax({
        url: '../report/equipReport.do',
        type: 'GET',
        dataType: 'JSON',
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                var list = result.data;
                var div = "";
                
                
                for(var i = 0; i < list.length; i++){
             	   var equipRepairReport = list[i];            	   

             	   div += "<tr class='text-c'>" +
             	   			   "<td>"+ (i+1) +"</td>" +		
			                   "<td>"+ equipRepairReport.equipName +"</td>" +			 
			                   "<td>"+ equipRepairReport.averageIntervalTime +"</td>" +			 
			                   "<td>"+ equipRepairReport.maintenanceTime +"</td>" +
			                   "<td>"+ equipRepairReport.averageMaintenanceTime +"</td>" +
			              "</tr>"; 
                }
                $("#equipReportList").append(div);

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
function ceshi(){
	if (navigator.userAgent.indexOf("MSIE") > 0) {//close IE  
		  if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {  
		   window.opener = null;  
		   window.close();  
		  } else {  
		   window.open('', '_top');  
		   window.top.close();  
		  }  
		 }  
		 else if (navigator.userAgent.indexOf("Firefox") > 0) {//close firefox  
		  window.location.href = 'about:blank ';  
		 } else {//close chrome;It is effective when it is only one.  
		  window.opener = null;  
		  window.open('', '_self');  
		  window.close();  
		 }  
}
