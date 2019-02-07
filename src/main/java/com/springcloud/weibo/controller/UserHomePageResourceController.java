package com.springcloud.weibo.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springcloud.weibo.service.UserHomePageResourceService;

@Controller
public class UserHomePageResourceController {
    @Autowired
    private UserHomePageResourceService userHomePageResourceService;

    @RequestMapping("/UHP/showuserinfo.do")
    public @ResponseBody
    Map<String, Object> loadUserInfo(HttpSession session) throws IOException {
        return userHomePageResourceService.loadUserInfo(session);
    }

    @RequestMapping("/UHP/showweibo.do")
    public @ResponseBody
    Map<String, Object> loadWeiboMessage(HttpSession session) throws IOException {
        return userHomePageResourceService.loadWeiboMessage(session);
    }
}
