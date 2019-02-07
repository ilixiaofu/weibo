package com.springcloud.weibo.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springcloud.weibo.pojo.User;
import com.springcloud.weibo.service.UserInfoService;

@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/UUIP/updateInfo.do")
    public @ResponseBody
    Map<String, Object> updateUserInfo(User user, HttpSession session) throws IOException {
        return userInfoService.updateUserInfo(user, session);
    }

    @RequestMapping("/m/updateInfo.do")
    public @ResponseBody
    Map<String, Object> mUpdateUserInfo(User user, HttpSession session) throws IOException {
        return userInfoService.updateUserInfo(user, session);
    }

    @RequestMapping("/UUPP/updatePwd.do")
    public @ResponseBody
    Map<String, Object> updatePwd(String oldPwd, String newPwd, HttpSession session) throws IOException {
        return userInfoService.updateUserPassword(oldPwd, newPwd, session);
    }
}
