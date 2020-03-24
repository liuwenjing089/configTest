//处理null显示
function resetNull(obj) {
    if (typeof (obj) == "undefined" || obj == "" || obj == null || obj == "null" || obj.length == 0) {
        return "";
    }else{
        return obj;
    }
}

//空判断
function isNullorEmpty(obj) {
    var flag = false;
    if (typeof (obj) == "undefined" || obj.trim() == "" || obj == null || obj == "null" || obj.length == 0) {
        flag = true;
    }
    return flag;
}

//获取当前时间 默认时间
function getNowDate() {
    var time = new Date();
    var day = ("0" + time.getDate()).slice(-2);
    var month = ("0" + (time.getMonth() + 1)).slice(-2);
    var today = time.getFullYear() + "-" + (month) + "-" + (day);
    return today;
}
function getNowTime() {
    var now = new Date();
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();           //秒
    var clock = "";
    if (hh < 10)
        clock += "0";
    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm;
    return (clock);
}
//中文验证
function isChinese(str){
    var flag = false;
    var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
    if(reg.test(str)) {
        flag = true;
    }
    return flag;
}

function check_Number(value){
    //定义正则表达式部分
    var reg=/^[0-9]+$/; //判断字符串是否为数字 ，判断正整数用/^[1-9]+[0-9]*]*$/
    if(!reg.test(value)){
        return false;
    }else{
        return true;
    }
}
// 时间转换
function datetimeFormat(longTypeDate){
    var datetimeType = "";
    var date = new Date();
    date.setTime(longTypeDate);
    datetimeType+= date.getFullYear();   //年
    datetimeType+= "-" + getMonth(date); //月
    datetimeType += "-" + getDay(date);   //日
    datetimeType+= " " + getHours(date);   //时
    datetimeType+= ":" + getMinutes(date);      //分
    datetimeType+= ":" + getSeconds(date);      //分
    return datetimeType;
}
//返回 01-12 的月份值
function getMonth(date){
    var month = "";
    month = date.getMonth() + 1; //getMonth()得到的月份是0-11
    if(month<10){
        month = "0" + month;
    }
    return month;
}
//返回01-30的日期
function getDay(date){
    var day = "";
    day = date.getDate();
    if(day<10){
        day = "0" + day;
    }
    return day;
}
//返回小时
function getHours(date){
    var hours = "";
    hours = date.getHours();
    if(hours<10){
        hours = "0" + hours;
    }
    return hours;
}
//返回分
function getMinutes(date){
    var minute = "";
    minute = date.getMinutes();
    if(minute<10){
        minute = "0" + minute;
    }
    return minute;
}
//返回秒
function getSeconds(date){
    var second = "";
    second = date.getSeconds();
    if(second<10){
        second = "0" + second;
    }
    return second;
}

function CurentTime() {
    var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var clock = year + "-";
    if(month < 10)
        clock += "0";
    clock += month + "-";
    if(day < 10)
        clock += "0";
    clock += day + " ";
    if(hh < 10)
        clock += "0";
    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm;
    return(clock);
}

function checkNull(obj) {
    if (typeof (obj) == "undefined" || obj == "" || obj == null || obj == "null") {
        return "";
    }else{
        return obj;
    }
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