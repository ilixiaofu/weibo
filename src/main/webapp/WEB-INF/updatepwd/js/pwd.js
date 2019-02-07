$(function () {
    // input输入框后面的眼睛图片：点击切换密码是否可见。
    Array.prototype.forEach
        .call(
            document.querySelectorAll('.input-addon img'),
            function (node) {
                node.onclick = function () {
                    if (node.src.indexOf("img/eye.jpg") > 0) {
                        // 密码可见
                        node.parentNode.previousSibling.previousSibling.type = "text";
                        node.src = "img/eyedark.jpg";
                    } else {
                        // 密码不可见
                        node.parentNode.previousSibling.previousSibling.type = "password";
                        node.src = "img/eye.jpg";
                    }

                }
            });
})

function goBack() {
    console.log("back");
    window.history.back();
}

function update(e) {
    if (newpd.value != newpdagain.value) {
        flag = false;
        alert("两次输入不一致");
//	} else if (newpd.value.length < 8 || (/[^a-zA-Z0-9]/g).test(newpd.value)) {
//		flag = false;
//		alert("密码格式不正确");
    } else {
        $.ajax({
            type: "post",
            url: "showuserinfo.do",
            async: true,
            dataType: "json",
            data: {
                oldPwd: prevpd.value,
                newPwd: newpd.value
            },
            success: function (data, status) {
                alert(data.msg);
                if (data.code == 0) {
                    setTimeout(() = > {
                        location.href = data.data;
                },
                    2000
                )
                    ;
                }
            },
            error: function (jxhr, Status, errorThrown) {
                alert("连接服务器失败");
            }
        });
    }
}