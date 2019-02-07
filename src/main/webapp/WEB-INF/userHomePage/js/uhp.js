$(function () {
    setlink();
    loadUserInfo();
    loadWeiboMessage();
    $('.send').click(function () {
        publishWeibo();
    });
})

function setlink() {
    $("[href='#']").attr("href", "javascript:void(0);");
    $("#logout").attr("href", "/SpringCloudWeibo/logout.do");
    $(".home").children().attr("href", "/SpringCloudWeibo/homePage");
    $("#updatePwd").attr("href", "/SpringCloudWeibo/updatePwd");
    $("#myPage").attr("href", "/SpringCloudWeibo/myPage");
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
            $(".user").children().text(
                data.user.nickName == undefined ? undefined
                    : data.user.nickName);
            $(".userid").children().first().text(
                data.user.nickName == undefined ? undefined
                    : data.user.nickName);
            $(".lv").text("LV." + data.user.lv);
            $($(".num")[1]).text(data.mFocusCount);
            $($(".num")[2]).text(data.focusMCount);
            $($(".num")[3]).text(data.weiboCount);
            $(".userImgBox a img").attr({
                "src": data.user.headImg.url,
                "data-url": data.user.headImg.url,
                "alt": "",
                "title": data.user.headImg.name
            });
        },
        error: function (jxhr, Status, errorThrown) {
            alert("连接服务器失败");
        }
    });
}

// 加载微博内容
function loadWeiboMessage() {
    // 显示加载条
    $(".loading").css("display", "block");
    $.ajax({
        type: "post",
        url: "showweibo.do",
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

        $(".weiboList").append(
            '<div class="weiboBox">' + '<div class="userInfoBox clearfix">'
            + '<div class="userInfo_sm">'
            + '<div class="userImg fl" title="'
            + headImg.name
            + '">'
            + '<a href="#"><img class="userImg"  src="'
            + headImg.url
            + '" alt="" /></a>'
            + '</div>'
            + '<div class="Info fl" title="'
            + user.nickName
            + '">'
            + '<h3 class="userTit">'
            + '<a href="#">'
            + user.nickName
            + '<img src="img/vipIcon2.png" alt="" /></a>'
            + '<img src="img/lv_big.png" alt="" />'
            + '</h3>'
            + '<p class="time">'
            + '<em>'
            + getWeiboTime(weibo.createDate)
            + '</em>'
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
            + '<a target="_blank" href="#">'
            + weibo.title
            + '</a>'
            + '</div>'
            // 内容
            + '<div class="text text_cut s_txt2">'
            + weibo.content
            + '</div>'
            // 图片
            + '<div class="pic_mul">'
            + '<ul class="pic_m3 clearfix" id="'
            + pic_id
            + '"></ul>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="weiboTool">'
            + '<ul>'
            + '<li>'
            + '<a href="javascript:collect('
            + weibo.id
            + ')" class="collect iconNum">' + weibo.collectionStatus + '</a>'
            + '</li>'
            + '<li>'
            + '<a href="javascript:transportNum('
            + weibo.id
            + ')" class="transportNum iconNum">转发 '
            + weibo.forwardingCount
            + '</a>'
            + '</li>'
            + '<li>'
            + '<a href="javascript:talkNum('
            + weibo.id
            + ')" class="talkNum iconNum">评论 '
            + weibo.commentsCount
            + '</a>'
            + '</li>'
            + '<li>'
            + '<a href="javascript:goodNum('
            + weibo.id
            + ')" class="goodNum iconNum">赞 '
            + weibo.likesCount
            + '</a>' + '</li>' + '</ul>' + '</div>' + '</div>');
        var pic_m3 = $("#" + pic_id);
        for (var j in pictures) {
            var picture = pictures[j];
            var unlog_pic = $('<li class="unlog_pic"><img src="' + picture.url
                + '" alt="" class="piccut_v piccut_h"></li>');
            pic_m3.append(unlog_pic);
        }
    }
}

function collect(self) {

    $.ajax({
        type: "post",
        url: "collect.do",
        async: true,
        dataType: "json",
        data: {
            msgid: self
        },
        success: function (data, status) {
            if (data.code == 0) {
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

function transportNum(self) {

    $.ajax({
        type: "post",
        url: "forwarding.do",
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

function goodNum(self) {
    $.ajax({
        type: "post",
        url: "like.do",
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

function talkNum(self) {
    alert("该功能待开发");
}

// 发布微博
function publishWeibo() {
    var text = $('.w_input').val();
    var mtitle = $("input:text[name=title]").val();
    if (text.length == 0) {
        alert("输入内容不能为空");
        // $('.send').unbind('click');
    } else {
        $.ajaxFileUpload({
            type: "post",
            url: "publishiweibo.do",
            secureuri: false,
            fileElementId: 'fileid',
            dataType: 'json',
            data: {
                title: mtitle,
                content: text
            },
            success: function (data, status) {
                if (data.code == 0) {
                    $('.w_input').val("");
                    $("input:text[name=title]").val("");
                    loadUserInfo();
                    loadWeiboMessage();
                } else {
                    alert(data.msg);
                }
            },
            error: function (status, e) {
                alert(e);
            }
        })
    }
}

// 微博发表时间转换函数
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
