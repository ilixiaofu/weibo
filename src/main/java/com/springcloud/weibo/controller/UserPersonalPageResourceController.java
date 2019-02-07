package com.springcloud.weibo.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.weibo.service.UserHomePageResourceService;
import com.springcloud.weibo.service.UserPersonalPageResourceService;

@Controller
public class UserPersonalPageResourceController {

    @Autowired
    private UserHomePageResourceService userHomePageResourceService;

    @Autowired
    private UserPersonalPageResourceService userPersonalPageResourceService;

    @RequestMapping("/m/showuserinfo.do")
    public @ResponseBody
    Map<String, Object> loadMyInfo(HttpSession session) throws IOException {
        return userHomePageResourceService.loadUserInfo(session);
    }

    @RequestMapping("/m/showmweibo.do")
    public @ResponseBody
    Map<String, Object> loadMWeiboMessage(HttpSession session) throws IOException {
        return userPersonalPageResourceService.loadMWeibo(session);
    }

    @RequestMapping("/m/shoAlbumPicture.do")
    public @ResponseBody
    Map<String, Object> shoAlbumPicture(HttpSession session) throws IOException {
        return userPersonalPageResourceService.loadUserAlbum(session);
    }

    @RequestMapping("/m/uploadPicture.do")
    public void uploadPicture(
            @RequestParam("file") CommonsMultipartFile[] images,
            HttpServletResponse response,
            HttpSession session) throws IOException {
        Map<String, Object> map = userPersonalPageResourceService.uploadPicture(images, session);
        JSONObject data = (JSONObject) JSONObject.toJSON(map);
        System.out.println(data);
        response.getWriter().write(data.toJSONString());
    }

    @RequestMapping("/m/deleteweibo.do")
    public @ResponseBody
    Map<String, Object> deleteWeibo(String msgid, HttpSession session) throws IOException {
        return userPersonalPageResourceService.deleteWeibo(msgid, session);
    }

    @RequestMapping("/m/deletePicture.do")
    public @ResponseBody
    Map<String, Object> deletePicture(String picid, HttpSession session) throws IOException {
        return userPersonalPageResourceService.deletePicture(picid, session);
    }

}
