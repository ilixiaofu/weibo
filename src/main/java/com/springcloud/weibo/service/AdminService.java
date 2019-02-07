package com.springcloud.weibo.service;

import java.util.Map;

public interface AdminService {

    Map<String, Object> findAllUser();

    Map<String, Object> blockUser(String uid);

    Map<String, Object> unBlockUser(String uid);

    Map<String, Object> deleteUser(String uid);

    Map<String, Object> findAllWeibo();

    Map<String, Object> deleteWeibo(Integer msgid);

    Map<String, Object> findAllPicture();

    Map<String, Object> deletePicture(Integer picid);
}
