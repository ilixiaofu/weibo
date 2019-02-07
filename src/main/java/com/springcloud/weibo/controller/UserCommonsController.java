package com.springcloud.weibo.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springcloud.weibo.service.UserCommonsService;

@Controller
public class UserCommonsController {

    @Autowired
    private UserCommonsService userCommonsService;

    @RequestMapping("/register.do")
    public @ResponseBody
    Map<String, Object> userRegister(String uid, String password) throws IOException {
        return userCommonsService.userRegister(uid, password);
    }

    @RequestMapping("/login.do")
    public @ResponseBody
    Map<String, Object> userLogin(String uid, String password, HttpSession session) throws IOException {
        return userCommonsService.userLogin(uid, password, session);
    }

    @RequestMapping("/logout.do")
    public String userLogout(HttpSession session) {
        return userCommonsService.userLogout(session);
    }
}
