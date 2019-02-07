package com.springcloud.weibo.service;

import javax.servlet.http.HttpSession;

import com.springcloud.weibo.pojo.Message;
import com.springcloud.weibo.pojo.Picture;
import com.springcloud.weibo.pojo.Topic;

public interface UserMessageService {

    int saveMessage(Message message, Topic topic, Picture picture, HttpSession session);

}
