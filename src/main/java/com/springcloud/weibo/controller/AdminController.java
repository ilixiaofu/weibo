package com.springcloud.weibo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springcloud.weibo.service.AdminService;

@Controller
@RequestMapping("admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/showalluser")
    public @ResponseBody
    Map<String, Object> loadAllUser() {
        return adminService.findAllUser();
    }

    @RequestMapping("/deleteuser")
    public @ResponseBody
    Map<String, Object> deleteUser(String uid) {
        return adminService.deleteUser(uid);
    }

    @RequestMapping("/blockuser")
    public @ResponseBody
    Map<String, Object> blockUser(String uid) {
        return adminService.blockUser(uid);
    }

    @RequestMapping("/unblockuser")
    public @ResponseBody
    Map<String, Object> unBlockUser(String uid) {
        return adminService.unBlockUser(uid);
    }

    @RequestMapping("/showweibolist")
    public @ResponseBody
    Map<String, Object> findAllWeibo() {
        return adminService.findAllWeibo();
    }

    @RequestMapping("/deleteweibo")
    public @ResponseBody
    Map<String, Object> deleteWeibo(Integer msgid) {
        return adminService.deleteWeibo(msgid);
    }

    @RequestMapping("/showpics")
    public @ResponseBody
    Map<String, Object> findAllPicture() {
        return adminService.findAllPicture();
    }

    @RequestMapping("/deletepicture")
    public @ResponseBody
    Map<String, Object> deletePicture(Integer picid) {
        return adminService.deletePicture(picid);
    }
}
