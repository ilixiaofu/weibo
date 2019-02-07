$(function () {
    // 左侧导航滑动静止
    $(window).bind('scroll', function () {
        if ($(window).scrollTop() > 0) {
            $('.mainLeft').css('position', 'fixed').css('top', '60px');
            $('.mainRight').css('margin-left', '150px');
            $('.headerBox').css('box-shadow', '0 0 6px 2px  rgba(0,0,0,0.3)');

        } else if ($(window).scrollTop() == 0) {
            $('.mainLeft').css('position', 'static').css('top', '0px');
            $('.mainRight').css('margin-left', '0');
            $('.headerBox').css('box-shadow', '0 0 1px 0px  rgba(0,0,0,0.15)');
        }
        var friendTop = $('.friendTalk').offset().top + $('.friendTalk').outerHeight();
        var talkTop = $('.mainRight_l').height() - $('.hotTalk').height();
        if ($(window).scrollTop() > friendTop) {
            $('.hotTalk').css('position', 'fixed')
                .css('top', '60px')
                .css('margin-top', '0');
            if ($(window).scrollTop() >= talkTop) {
                $('.hotTalk')
                    .css('position', 'absolute')
                    .css('top', talkTop + 'px');
            }
        }
        else if ($(window).scrollTop() < friendTop) {
            $('.hotTalk').css('position', 'relative').css('top', '0').css('margin-top', '10px');
        }
    });
    // 发表框
    $('.w_input').bind('focus', function () {
        $('.textNum').css('display', 'block');
        $('.keys').css('display', 'none');
        check_textNum();
    }).bind('keyup', check_textNum).bind('paste', function () {
        setTimeout(check_textNum, 1);
    });
    $('.w_input').bind('blur', function () {
        if (check_textNum() == true) {
            $('.textNum').css('display', 'none');
            $('.keys').css('display', 'block');
        } else {
            $('.textErrorNum').css('display', 'block');
            $('.keys').css('display', 'none');
        }
    });

    function check_textNum() {
        var num = 140 - ($('.w_input').val().length);
        if (num >= 0) {
            $('.textNum .num').html(num);
            $('.textNum').css('display', 'block');
            $('.textErrorNum').css('display', 'none');
            return true;
        } else {
            $('.textErrorNum').css('display', 'block');
            $('.textErrorNum .errorNum').html(Math.abs(num));
            $('.textNum').css('display', 'none');
            return false;
        }
    }

    // 表情框
    $('.biaoqing').click(function () {
        $('.face_list').slideDown('fast');
    });
    $('.face_close').click(function () {
        $('.face_list').slideUp('fast')
    });
    // 搜索框
    $('.search').bind('focus', function () {
        $('.search_List').css('display', 'block');
    });
    for (var i = 0; i < $('.search_List li a').length; i++) {
        $('.search_List li a').eq(i).bind('mousedown', function () {
            $('.search').val($(this).text().substr(1));
            $('.search').bind('blur', function () {
                $('.search_List').css('display', 'none')
            })
        })
    }

    /*事件绑定
    for(var i=0;i<$('.userImg').length;i++){
            (function(i){
                    var timer1=null;
                    $('.userImg').eq(i).mouseover(function(){
                            clearTimeout(timer1);
                            timer1=setTimeout(function(){
                                    $('.userInfo_box').eq(i).slideDown('normal');
                            },200);
                    });
                    $('.userImg').eq(i).mouseout(function(){
                            timer1=setTimeout(function(){
                                    $('.userInfo_box').eq(i).slideUp('normal');
                            },200);
                    })
                    $('.userInfo_box').eq(i).mouseover(function(){
                            clearTimeout(timer1);
                    });
                    $('.userInfo_box').eq(i).mouseout(function(){
                            clearTimeout(timer1);
                            timer1=setTimeout(function(){
                                    $('.userInfo_box').eq(i).slideUp('normal');
                            },200);
                    })
            })(i);
    }
    */
});
