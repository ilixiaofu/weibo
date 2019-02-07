package com.springcloud.weibo.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

public interface UserHomePageResourceService {

    Map<String, Object> loadUserInfo(HttpSession session);

    Map<String, Object> loadWeiboMessage(HttpSession session);


}
