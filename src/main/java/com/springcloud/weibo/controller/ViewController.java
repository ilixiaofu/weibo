package com.springcloud.weibo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springcloud.weibo.constant.ViewResourceConstant;

@Controller
public class ViewController {

    @RequestMapping("/homePage")
    public String registerView() {
        return "redirect:" + ViewResourceConstant.HOME_PAGE;
    }

    @RequestMapping("/updateInfoPage")
    public String updateInfoPage() {
        return "redirect:" + ViewResourceConstant.UpdateUserInfoPage.UPDATE_USER_INFO_PAGE;
    }

    @RequestMapping("/updatePwd")
    public String updatePwdPage() {
        return "redirect:" + ViewResourceConstant.UpdatePasswordPage.UPDATE_USER_PASSWORD_PAGE;
    }

    @RequestMapping("/myPage")
    public String myPage() {
        return "redirect:" + ViewResourceConstant.MyPage.MY_WEIBO_PAGE;
    }

}
