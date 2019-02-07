package com.springcloud.weibo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.springcloud.weibo.mapper.MessageMapper;
import com.springcloud.weibo.mapper.PictureMapper;
import com.springcloud.weibo.pojo.Message;
import com.springcloud.weibo.pojo.MessageExample;
import com.springcloud.weibo.pojo.Picture;
import com.springcloud.weibo.pojo.PictureExample;
import com.springcloud.weibo.pojo.User;

@Service
public class UserPersonalPageResourceServiceImpl implements UserPersonalPageResourceService {

    private final String TAG = UserPersonalPageResourceServiceImpl.class.getName();

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public Map<String, Object> loadUserAlbum(HttpSession session) {
        System.out.println(TAG + "::loadUserAlbum >>");
        User user = (User) session.getAttribute("user");
        PictureExample pictureExample = new PictureExample();
        pictureExample.setOrderByClause("create_date desc");
        PictureExample.Criteria criteria = pictureExample.createCriteria();
        criteria.andUidEqualTo(user.getUid());
        List<Picture> pictures = pictureMapper.selectByExample(pictureExample);
        Map<String, Object> data = new HashMap<>();
        data.put("pictures", pictures);
        return data;
    }

    @Override
    public Map<String, Object> loadMWeibo(HttpSession session) {
        System.out.println(TAG + "::loadMWeibo >>");
        User user = (User) session.getAttribute("user");
        List<Message> messages = messageMapper.selectUserMessageByUid(user.getUid());
        for (Message message : messages) {
            PictureExample pictureExample = new PictureExample();
            PictureExample.Criteria criteria = pictureExample.createCriteria();
            criteria.andIsDelEqualTo("N");
            criteria.andMsgIdEqualTo(message.getId());
            criteria.andUidEqualTo(user.getUid());
            message.setPictures(pictureMapper.selectByExample(pictureExample));
        }
        Map<String, Object> data = new HashMap<>();
        data.put("weibolist", messages);
        return data;
    }

    @Override
    public Map<String, Object> uploadPicture(CommonsMultipartFile[] images, HttpSession session) {
        System.out.println(TAG + "::uploadPicture >>");
        User user = (User) session.getAttribute("user");
        Map<String, Object> data = uploadFileService.batchUploadFile(user, images);
        return data;
    }

    @Override
    public Map<String, Object> deleteWeibo(String msgid, HttpSession session) {
        System.out.println(TAG + "::deleteWeibo >>");
        Map<String, Object> data = new HashMap<>();
        User user = (User) session.getAttribute("user");
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andIdEqualTo(Integer.parseInt(msgid));
        criteria.andUidEqualTo(user.getUid());
        int count = messageMapper.deleteByExample(messageExample);
        if (count > 0) {
            data.put("code", 0);
            data.put("msg", "删除成功");
            data.put("data", "");
            data.put("error", "");
        } else {
            data.put("code", -1);
            data.put("msg", "删除失败");
            data.put("data", "");
            data.put("error", "发生未知错误");
        }
        return data;
    }

    @Override
    public Map<String, Object> deletePicture(String picid, HttpSession session) {
        System.out.println(TAG + "::deletePicture >>");
        Map<String, Object> data = new HashMap<>();
        User user = (User) session.getAttribute("user");
        PictureExample pictureExample = new PictureExample();
        PictureExample.Criteria criteria = pictureExample.createCriteria();
        criteria.andIdEqualTo(Integer.parseInt(picid));
        criteria.andUidEqualTo(user.getUid());
        int count = pictureMapper.deleteByExample(pictureExample);
        if (count > 0) {
            data.put("code", 0);
            data.put("msg", "删除成功");
            data.put("data", "");
            data.put("error", "");
        } else {
            data.put("code", -1);
            data.put("msg", "删除失败");
            data.put("data", "");
            data.put("error", "发生未知错误");
        }
        return data;
    }
}
