function login(id, pwd) {
    $.ajax({
        type: "post",
        url: "login.do",
        async: true,
        dataType: "json",
        data: {
            uid: $("#" + id).val(),
            password: $("#" + pwd).val()
        },
        success: function (data, status) {
            $("#" + id).val();
            $("#" + pwd).val();
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

/*
 * $(function() { $("#login").click(function() {
 * 
 * }); })
 */