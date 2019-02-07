function register(uid, pwdid, repwdid) {
    var password = $("#" + pwdid).val();
    var repassword = $("#" + repwdid).val();
    if (password != repassword) {
        alert("密码不一致");
    } else {
        $.ajax({
            type: "post",
            url: "register.do",
            async: true,
            dataType: "json",
            data: {
                uid: $("#" + uid).val(),
                password: $("#" + pwdid).val()
            },
            success: function (data, status) {
                $("#" + uid).val("");
                $("#" + pwdid).val("");
                $("#" + repwdid).val("");
                if (data.code == 0) {
                    setTimeout(function () {
                        location.href = data.data;
                    }, 200);
                } else {
                    alert(data.msg);
                }
            },
            error: function (jxhr, Status, errorThrown) {
                alert("连接服务器失败");
            }
        });
    }
}