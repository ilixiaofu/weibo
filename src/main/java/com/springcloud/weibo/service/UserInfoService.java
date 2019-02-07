package com.springcloud.weibo.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.springcloud.weibo.pojo.User;

public interface UserInfoService {

    public Map<String, Object> updateUserInfo(User user, HttpSession session);

    public Map<String, Object> updateUserPassword(String oldPwd, String newPwd, HttpSession session);

    public Map<String, Object> updateUserHeadPicture(HttpSession session);
}
