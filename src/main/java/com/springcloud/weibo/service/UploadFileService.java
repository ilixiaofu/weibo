package com.springcloud.weibo.service;

import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.springcloud.weibo.pojo.User;

public interface UploadFileService {

    Map<String, Object> batchUploadFile(Map<String, Object> map, CommonsMultipartFile[] files);

    Map<String, Object> batchUploadFile(User user, CommonsMultipartFile[] files);

}
