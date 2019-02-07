package com.springcloud.weibo.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface UserPersonalPageResourceService {

    Map<String, Object> loadUserAlbum(HttpSession session);

    Map<String, Object> loadMWeibo(HttpSession session);

    Map<String, Object> uploadPicture(CommonsMultipartFile[] images, HttpSession session);

    Map<String, Object> deleteWeibo(String msgid, HttpSession session);

    Map<String, Object> deletePicture(String picid, HttpSession session);
}
