$(function () {
    setlink();
    loadUserInfo();
    loadWeiboMessage();
})

// 修改头像
function updateHeadImage() {
    alert("updateHeadImage");
    /*
    $.ajaxFileUpload({
        type : "post",
        url : "publishiweibo.do",
        secureuri : false,
        fileElementId : 'head-image',
        dataType : 'json',
        data : {},
        success : function(data, status) {
            if (data.code == 0) {
            } else {
                alert(data.msg);
            }
        },
        error : function(status, e) {
            alert(e);
        }
    })*/
}

// 上传图片到相册
function uploadImage(self) {
    $.ajaxFileUpload({
        type: "post",
        url: "uploadPicture.do",
        secureuri: false,
        fileElementId: 'images',
        dataType: 'json',
        data: {},
        success: function (data, status) {
            if (data.code == 0) {
                loadPictures();
            } else {
                alert(data.msg);
            }
        },
        error: function (status, e) {
            alert(e);
        }
    })
}


/*
 * 
 * <input type="file" id="images" name="file" class="file-btn" multiple="multiple" accept="image/*">
															<span>上传照片</span>
														</input>
 * <input type="file" id="head-image" name="file" class="file-btn" accept="image/*">
 * 
 */

function setlink() {
    $("[href='#']").attr("href", "javascript:void(0);");
    $("#logout").attr("href", "/SpringCloudWeibo/logout.do");
    $(".home").children().attr("href", "/SpringCloudWeibo/homePage");
    $("#updatePwd").attr("href", "/SpringCloudWeibo/updatePwd");
    $("#myPage").attr("href", "/SpringCloudWeibo/myPage");
    $($(".WB_cardmore")[0]).attr("href", "/SpringCloudWeibo/updateInfoPage");
}

function changeDisplay(self) {
    $(self).parent().addClass("current");
    $(self).parent().siblings().removeClass("current");
    var menu = $(self).attr("data-menu");
    $("#" + menu).css("display", "inline-block");
    if (menu == "mainPage") {
        $("#album").css("display", "none");
        loadWeiboMessage();
    }
    if (menu == "album") {
        $("#mainPage").css("display", "none");
        loadPictures();
    }
}

// 加载个人信息
function loadUserInfo() {
    $.ajax({
        type: "post",
        url: "showuserinfo.do",
        async: true,
        dataType: "json",
        data: {},
        success: function (data, status) {
            if (data.user != undefined) {
                $(".user").children().text(
                    data.user.nickName == undefined ? undefined
                        : data.user.nickName);
                $(".username").text(data.user.nickName == undefined ? undefined
                    : data.user.nickName);
                $(".photo_wrap").children().children().attr("src", data.user.headImg.url);
                $($(".W_f18")[0]).text(data.mFocusCount);
                $($(".W_f18")[1]).text(data.focusMCount);
                $($(".W_f18")[2]).text(data.weiboCount);
                $(".W_icon_level").text("LV." + data.user.lv);
            }
        },
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });
}

// 加载相册
function loadPictures() {
    $.ajax({
        type: "post",
        url: "shoAlbumPicture.do",
        async: true,
        dataType: "json",
        data: {},
        success: showPicture,
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });
}

// 显示所有图片
function showPicture(data, status) {
    if (data.pictures.length > 0) {
        $(".photo_album_list").empty();
        for (var i in data.pictures) {
            var picture = data.pictures[i];
            $(".photo_album_list")
                .append(
                    '<li class="photo_module" id="' + picture.id + '">'
                    + '<div class="photo_cont">'
                    + '<a class="ph_ar_box" href="javascript:void(0);">'
                    + '<img class="photo_pict" src="' + picture.url + '">'
                    + '</a>'
                    + '<div class="bot_cover">'
                    + '<span class="bot_icon"><a href="javascript:void(0);">'
                    //+ '<span class="bot_icon_f"><em class="W_ficon ficon_forward"></em>转发</span>'
                    + '</a>'
                    + '<em class="W_vline S_line1"></em>'
                    + '<a href="javascript:deletePicture(' + picture.id + ');">'
                    + '<span class="bot_icon_f"><em class="W_ficon ficon_repeat"></em>删除</span>'
                    + '</a>'
                    + '</span>'
                    + '</div>'
                    + '</div>'
                    + '</li>');
        }
    }
}

//加载微博内容
function loadWeiboMessage() {
    // 显示加载条
    $(".loading").css("display", "block");
    $.ajax({
        type: "post",
        url: "showmweibo.do",
        async: true,
        dataType: "json",
        data: {},
        success: weiboContentList,
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });
}

// 显示微博内容
function weiboContentList(data, status) {
    var weibo_list = data.weibolist;
    if (weibo_list.length > 0) {
        // 隐藏加载条
        $(".loading").css("display", "none");
        // 清空微博列表
        $(".weiboList").empty();
    }
    for (var i in weibo_list) {
        var weibo = weibo_list[i];
        var user = weibo.user;
        var pictures = weibo.pictures;
        var headImg = user.headImg;
        var pic_id = "" + i;

        $(".weiboList")
            .append(
                '<div class="weiboBox" id="' + weibo.id + '">'
                + '<div class="userInfoBox clearfix">'
                + '<div class="userInfo_sm">'
                + '<div class="userImg fl" title="' + headImg.name + '">'
                + '<a href="#"><img class="userImg"  src="' + headImg.url + '" alt="" /></a>'
                + '</div>'
                + '<div class="Info fl" title="' + user.nickName + '">'
                + '<h3 class="userTit">'
                + '<a href="#">' + user.nickName + '<img src="img/vipIcon2.png" alt="" /></a>'
                + '<img src="img/lv_big.png" alt="" />'
                + '</h3>'
                + '<p class="time">'
                + '<em>' + getWeiboTime(weibo.createDate) + '</em>'
                + '<em>来自</em>'
                + '<em>'
                + '<a href="#">微博weibo.com</a>'
                + '</em>'
                + '</p>'
                + '</div>'
                + '</div>'
                + '<div class="weiboText">'
                // 微博正文
                + '<div class="text_box">'
                // 标题
                + '<div class="title w_autocut">'
                + '<a target="_blank" href="#">' + weibo.title + '</a>'
                + '</div>'
                // 内容
                + '<div class="text text_cut s_txt2">'
                + weibo.content
                + '</div>'
                // 图片
                + '<div class="pic_mul">'
                + '<ul class="pic_m3 clearfix" id="' + pic_id + '"></ul>'
                + '</div>'
                + '</div>'
                + '</div>'
                + '</div>'
                + '<div class="weiboTool">'
                + '<ul>'
                + '<li>'
                + '<a href="javascript:transportNum(' + weibo.id + ')" class="transportNum iconNum">转发 ' + weibo.forwardingCount + '</a>'
                + '</li>'
                + '<li>'
                + '<a href="javascript:talkNum(' + weibo.id + ')" class="talkNum iconNum">评论 ' + weibo.commentsCount + '</a>'
                + '</li>'
                + '<li>'
                + '<a href="javascript:goodNum(' + weibo.id + ')" class="goodNum iconNum">赞 ' + weibo.likesCount + '</a>'
                + '</li>'
                + '<li>'
                + '<a href="javascript:deleteweibo(' + weibo.id + ')" class="iconNum">删除</a>'
                + '</li>'
                + '</ul>'
                + '</div>'
                + '</div>');
        var pic_m3 = $("#" + pic_id);
        for (var j in pictures) {
            var picture = pictures[j];
            var unlog_pic = $('<li class="unlog_pic"><img src="' + picture.url + '" alt="" class="piccut_v piccut_h"></li>');
            pic_m3.append(unlog_pic);
        }
    }
}

// 转发
function transportNum(self) {

    $.ajax({
        type: "post",
        url: "/SpringCloudWeibo/UHP/forwarding.do",
        async: true,
        dataType: "json",
        data: {
            msgid: self
        },
        success: function (data, status) {
            if (data.code == 0) {
                //$(".weiboList").empty();
                loadWeiboMessage();
            } else {
                alert(data.msg);
            }
        },
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });
}

// 点赞
function goodNum(self) {
    $.ajax({
        type: "post",
        url: "/SpringCloudWeibo/UHP/like.do",
        async: true,
        dataType: "json",
        data: {
            msgid: self
        },
        success: function (data, status) {
            if (data.code == 0) {
                //$(".weiboList").empty();
                loadWeiboMessage();
            } else {
                alert(data.msg);
            }
        },
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });
}

// 评论
function talkNum(self) {
    //alert("该功能待开发");
}

// 删除微博
function deleteweibo(self) {
    $.ajax({
        type: "post",
        url: "deleteweibo.do",
        async: true,
        dataType: "json",
        data: {
            msgid: self
        },
        success: function (data, status) {
            if (data.code == 0) {
                // 移除当前微博节点
                $("#" + self).remove();
                // 重新加载用户信息
                loadUserInfo();
            } else {
                alert(data.msg);
            }
        },
        error: function (jxhr, Status, errorThrown) {
            alert(Status);
        }
    });
}

// 删除图片
function deletePicture(self) {
    $.ajax({
        type: "post",
        url: "deletePicture.do",
        async: true,
        dataType: "json",
        data: {
            picid: self
        },
        success: function (data, status) {
            if (data.code == 0) {
                // 移除当前图片节点
                $("#" + self).remove();
            } else {
                alert(data.msg);
            }
        },
        error: function (jxhr, Status, errorThrown) {
            alert(Status);
        }
    });
}

//微博发表时间转换函数
function getWeiboTime(t) {
    t = t / 1000;
    var nowTime = new Date();
    nowTime = nowTime.getTime() / 1000;
    var time = nowTime - t;
    var day = Math.round((parseInt(time) / (60 * 60 * 24)));
    var str = "";
    if (time < 30) {
        str = "刚刚";
    } else if (time < 60 && time > 30) {
        str = Math.round(time) + "秒前";
    } else if (time < 60 * 60) {
        str = Math.round((parseInt(time) / 60)) + "分钟前";
    } else if (time < 60 * 60 * 24) {
        str = Math.round((parseInt(time) / (60 * 60))) + "小时前";
    } else if (time < 60 * 60 * 24 * 3) {
        if (day == 1) {
            str = "昨天";
        } else {
            str = day + "天前";
        }
    } else {
        str = day + "天前";
    }
    return str;
}
