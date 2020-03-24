function login() {
    var username = $("#username").val(),
        password = $("#password").val();
    if(isNullorEmpty(username)){
        layer.alert("用户名不能为空");
        return;
    }else if(isNullorEmpty(password)){
        layer.alert("密码不能为空");
        return;
    }
    $.ajax({
        url: "../login/loginIn.do",
        type: "POST",
        dataType: "json",
        data:{
            "username":username,
            "password":password
        },
        async:false,
        success: function(result) {
            if(result.ret == '1') {
                window.location.href="index.html";
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