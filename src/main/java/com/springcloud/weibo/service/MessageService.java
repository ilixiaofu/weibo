package com.springcloud.weibo.service;

import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.springcloud.weibo.pojo.Message;

public interface MessageService {

    Map<String, Object> saveMessage(Message message, CommonsMultipartFile[] images, HttpSession session);

    Map<String, Object> collectMessage(String msgid, HttpSession session);

    Map<String, Object> forwardingMessage(String msgid, HttpSession session);

    Map<String, Object> likeMessage(String msgid, HttpSession session);

}
