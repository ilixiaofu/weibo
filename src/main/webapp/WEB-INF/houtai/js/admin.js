$(function () {
    idManage();
})

function formatDateTime(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    minute = minute < 10 ? ('0' + minute) : minute;
    var second = date.getSeconds();
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
};

function changedisplaydata(params) {
    if (params == 1) {
        idManage();
    }

    if (params == 2) {
        contentManage();
    }

    if (params == 3) {
        pictureManage();
    }
}

function idManage() {
    $.ajax({
        type: "get",
        url: "showalluser",
        async: true,
        dataType: "json",
        data: {},
        success: function (data, status) {
            if (status == "success") {
                showUserInfo(data)
            }
        },
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });
}

// 显示用户信息
function showUserInfo(data) {
    var users = data.users;
    if (users.length > 0) {
        $("#content1").empty();
        for (var i in users) {
            var user = users[i];
            $("#content1").append(
                '<div class="weiboBox" id="' + user.uid + '">'
                + '<div class="userInfoBox clearfix">'
                + '<div class="userInfo_sm">'
                + '<div class="Info fl" title="">'
                + '<h3 class="userTit">' + '<a href="#">昵称 : '
                + user.nickName + '</a>' + '</h3>'
                + '<div class="info">' + '<ul>' + '<li>'
                + '<em>账号: </em>' + '<em>' + user.uid + '</em>'
                + '</li>' + '<li>' + '<em>创建时间: </em>' + '<em>'
                + formatDateTime(new Date(user.registerDate))
                + '</em>' + '</li>' + '<li>' + '<em>等级:</em>'
                + '<em> ' + user.lv + '</em>' + '</li>' + '</ul>'
                + '</div>' + '</div>' + '</div>' + '</div>'
                + '<div class="weiboTool">' + '<ul>' + '<li>'
                + '<a href="javascript:deleteUser(' + user.uid
                + ');">注销</a>' + '</li>' + '<li>'
                + '<a href="javascript:blockUser(' + user.uid
                + ');">封号</a>' + '</li>' + '<li>'
                + '<a href="javascript:unblockUser(' + user.uid
                + ');">解封</a>' + '</li>' + '</ul>' + '</div>'
                + '</div>');
        }
    }
}

// 注销用户
function deleteUser(uid) {

    $.ajax({
        type: "post",
        url: "deleteuser",
        async: true,
        dataType: "json",
        data: {
            uid: uid
        },
        success: function (data, status) {

            if (data.code == 0) {
                $("#" + uid).remove();
            } else {
                alert(data.msg);
            }
        },
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });

}

// 封锁用户
function blockUser(uid) {
    $.ajax({
        type: "post",
        url: "blockuser",
        async: true,
        dataType: "json",
        data: {
            uid: uid
        },
        success: function (data, status) {
            alert(data.msg);
        },
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });
}

// 接触封锁用户
function unblockUser(uid) {
    $.ajax({
        type: "post",
        url: "unblockuser",
        async: true,
        dataType: "json",
        data: {
            uid: uid
        },
        success: function (data, status) {
            alert(data.msg);
        },
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });
}

// weibo内容
function contentManage() {
    $.ajax({
        type: "get",
        url: "showweibolist",
        async: true,
        dataType: "json",
        data: {},
        success: function (data, status) {
            if (status == "success") {
                showWeiboList(data);
            }
        },
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });

}

// 显示微博内容
function showWeiboList(data) {
    var weibolist = data.weiboList;
    if (weibolist.length > 0) {
        $("#content2").empty();
        for (var i in weibolist) {
            var weibo = weibolist[i];
            $("#content2").append(
                '<div class="weiboBox" id="' + weibo.id + '">' +
                '<div class="userInfoBox clearfix">' +
                '<div class="userInfo_sm">' +
                '<div class="Info fl" title="">' +
                '<div class="info">' +
                '<ul>' +
                '<li>' +
                '<em>消息id: </em>' +
                '<em>' + weibo.id + '</em>' +
                '</li>' +
                '<li>' +
                '<em>创建时间: </em>' +
                '<em>' + formatDateTime(new Date(weibo.createDate)) + '</em>' +
                '</li>' +
                '</ul>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="weiboText">' +
                '<div class="text_box">' +
                '<div class="title w_autocut">' +
                '<a target="_blank" href="javascript:void(0);">' + weibo.title + '</a>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="weiboTool">' +
                '<ul>' +
                '<li>' +
                '<a href="javascript:deleteWeibo(' + weibo.id + ');">删除</a>' +
                '</li>' +
                '</ul>' +
                '</div>' +
                '</div>'
            );
        }
    }
}

// 删除微博
function deleteWeibo(msgid) {
    $.ajax({
        type: "post",
        url: "deleteweibo",
        async: true,
        dataType: "json",
        data: {
            msgid: msgid
        },
        success: function (data, status) {
            if (data.code == 0) {
                // 移除微博
                $("#" + msgid).remove();
            } else {
                alert(data.msg);
            }
        },
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });
}

function pictureManage() {

    $.ajax({
        type: "get",
        url: "showpics",
        async: true,
        dataType: "json",
        data: {},
        success: function (data, status) {
            if (status == "success") {
                showPicList(data);
            }
        },
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });

}

function showPicList(data) {
    var pictures = data.pictures;
    if (pictures.length > 0) {
        $(".photo_list").empty();
        for (var i in pictures) {
            var picture = pictures[i];
            $(".photo_list").append(
                '<li class="photo_module" id="' + picture.id + '">' +
                '<img src="' + picture.url + '" />' +
                '<div class="bot_cover" onclick="deletePic(' + picture.id + ')">' +
                '<span class="ficon">删除</span>' +
                '</div>' +
                '</li>'
            );
        }
    }
}

function deletePic(picid) {

    $.ajax({
        type: "post",
        url: "deletepicture",
        async: true,
        dataType: "json",
        data: {
            picid: picid
        },
        success: function (data, status) {
            if (data.code == 0) {
                // 移除图片
                $("#" + picid).remove();
            } else {
                alert(data.msg);
            }
        },
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });
}