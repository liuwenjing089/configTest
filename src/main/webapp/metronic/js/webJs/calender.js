var spotTaskList;  
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
    query();
});

function query(){
	$.ajax({
		url: '../task/getTaskToCalender.do',
		type:'GET',
		async: false,
		beforeSend: function(XMLHttpRequest) {

		},
		complete: function(XMLHttpRequest, textStatus) {

		},
		success: function(result) {
			if(result.ret == '1') {
				spotTaskList = result.data;
				
                $("#calendar").html("");
                var calendarEl = document.getElementById('calendar');
                var systemDate = format(new Date());
                
                var calendar = new FullCalendar.Calendar(calendarEl, {
                    schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source',
                    plugins: ['interaction', 'dayGrid', 'timeGrid', 'resourceTimeline'],
                    now: systemDate,
                    editable: false,
                    aspectRatio: 1.8,
                    scrollTime: '00:00',
                    header: {
                      left: 'today prev,next',
                      center: 'title',
                      // right: 'resourceTimelineDay,resourceTimelineThreeDays,timeGridWeek,dayGridMonth'
                      right: ''
                    },
                     monthNames: ["一月", "二月", "三月", "四月", 　//设置月份名称，中英文均可
                     "五月", "六月", "七月", "八月", "九月",
                     "十月", "十一月", "十二月"
                     ],
                     monthNamesShort: ["一月", "二月", "三月", 　　//设置月份的简称
                     "四月", "五月", "六月", "七月", "八月",
                     "九月", "十月", "十一月", "十二月"
                     ],
                     dayNames: ["周日", "周一", "周二", "周三", 　　//设置星期名称
                     "周四", "周五", "周六"
                     ],
                     dayNamesShort: ["周日", "周一", "周二", 　　　　//设置星期简称
                     "周三", "周四", "周五", "周六"
                     ],
                     buttonText: { 　　　　　　　　　　　　　　　　　//设置按钮文本
                     today: '今天',
                     month: '月',
                     week: '周',
                     day: '日',
                     prev: '上一月',
                     next: '下一月'
                     },
                    defaultView: 'dayGridMonth',
                    views: {
                      resourceTimelineThreeDays: {
                        type: 'resourceTimeline',
                        duration: {
                          days: 3
                        },
                        buttonText: '3 days'
                      }
                    },
                    resourceAreaWidth: '30%',
                    resourceColumns: [{
                        labelText: 'Room',
                        field: 'title'
                      },
                      {
                        labelText: 'Occupancy',
                        field: 'occupancy'
                      }
                    ],
                    resourceOrder: '-occupancy,title', // when occupancy tied, order by title
                    eventClick: function(calEvent, jsEvent, view) {
                    	var title = calEvent.event.title;
                    	var startDate = format(calEvent.event.start);
                    	if(title == '设备'){
                    		window.location.href="dateSpotDetailEquip.html?startTime="+ startDate;
                    	}else if(title == '模具'){
                    		window.location.href="dateSpotDetailMould.html?startTime="+ startDate;
                    	}
                    	
                    },
        			resources: result.data.dealerCalenders,
        			events: result.data.scheduleCalendars
                  });

        		  calendar.render();
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


function format(shijianchuo)
{
//shijianchuo是整数，否则要parseInt转换
var time = new Date(shijianchuo);
var y = time.getFullYear();
var m = time.getMonth()+1;
var d = time.getDate();
return y+'-'+add0(m)+'-'+add0(d);
}

function add0(m){return m<10?'0'+m:m }