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
import com.springcloud.weibo.pojo.Message;
import com.springcloud.weibo.service.MessageService;

@Controller
@RequestMapping("UHP")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/publishiweibo.do")
    public void publishiWeibo(Message message, @RequestParam("file") CommonsMultipartFile[] images,
                              HttpServletResponse response, HttpSession session) throws IOException {
        Map<String, Object> data = messageService.saveMessage(message, images, session);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(data);
        System.out.println(jsonObject);
        response.getWriter().write(jsonObject.toJSONString());
    }

    @RequestMapping("/collect.do")
    public @ResponseBody
    Map<String, Object> collectWeibo(String msgid, HttpSession session) {
        return messageService.collectMessage(msgid, session);
    }

    @RequestMapping("/forwarding.do")
    public @ResponseBody
    Map<String, Object> forwardingWeibo(String msgid, HttpSession session) {
        return messageService.forwardingMessage(msgid, session);
    }

    @RequestMapping("/like.do")
    public @ResponseBody
    Map<String, Object> likeWeibo(String msgid, HttpSession session) {
        return messageService.likeMessage(msgid, session);
    }
}
