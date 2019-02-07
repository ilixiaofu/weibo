package com.springcloud.weibo.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface UserCommonsService {

    public Map<String, Object> userRegister(String uid, String password);

    public Map<String, Object> userLogin(String uid, String password, HttpSession session);

    public String userLogout(HttpSession session);
}
