$(document).ready(function() {
    var type=9;
    var user="";
    $.ajax({
        url: "../login/getUserSession.do",
        type: "POST",
        dataType: "json",
        data:{},
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                user = result.data.username;
                var b = result.data.password;
                type = result.data.userType;
                $("#username").val(result.data.username);
                $("#password").val(result.data.password);
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
    query(type,user);
});
function query(type,user) {
    //现场 保全页面独立分开
    document.getElementById("user").innerHTML=user;
    if(type==1||type==2||type ==7){
        document.getElementById("site-1").style.display="";
        document.getElementById("site-2").style.display="";
        document.getElementById("site-3").style.display="";
    }else{
        document.getElementById("menu-system").style.display="";
        document.getElementById("menu-relation1").style.display="";
        document.getElementById("menu-relation2").style.display="";
        document.getElementById("menu-relation3").style.display="";
        document.getElementById("menu-relation4").style.display="";
        document.getElementById("menu-system1").style.display="";
    }
}
/*个人信息*/
function myselfinfo() {
    layer.open({
        type: 1,
        area: ['300px', '200px'],
        fix: false, //不固定
        maxmin: true,
        shade: 0.4,
        title: '查看信息',
        content: '<div>管理员信息</div>'
    });
}
function logout() {
    //sessionStorage.clear();
    // $.ajax({
    //     url: "../login/logout.do",
    //     type: "POST",
    //     dataType: "json",
    //     data:{
    //         "username":$("#username").val(),
    //         "password":$("#password").val()
    //     },
    //     async:false,
    //     success: function(result) {
    //         if(result.ret == '1') {
    //             window.location.href="login.html";
    //         } else {
    //             var error = "";
    //             for(var i = 0; i < result.data.length; i++) {
    //                 error += (result.data[i].message);
    //             }
    //             layer.alert(error);
    //         }
    //     }
    // });

   window.location.href="login.html";
}