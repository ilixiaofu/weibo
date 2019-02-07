package com.springcloud.weibo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcloud.weibo.mapper.FocusUserMapper;
import com.springcloud.weibo.mapper.MessageCollectionMapper;
import com.springcloud.weibo.mapper.MessageMapper;
import com.springcloud.weibo.mapper.PictureMapper;
import com.springcloud.weibo.mapper.UserMapper;
import com.springcloud.weibo.pojo.FocusUserExample;
import com.springcloud.weibo.pojo.Message;
import com.springcloud.weibo.pojo.MessageCollection;
import com.springcloud.weibo.pojo.MessageCollectionExample;
import com.springcloud.weibo.pojo.MessageExample;
import com.springcloud.weibo.pojo.PictureExample;
import com.springcloud.weibo.pojo.User;


@Service
public class UserHomePageResourceServiceImpl implements UserHomePageResourceService {

    private final String TAG = UserHomePageResourceServiceImpl.class.getName();

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private FocusUserMapper focusUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private MessageCollectionMapper messageCollectionMapper;

    @Override
    public Map<String, Object> loadUserInfo(HttpSession session) {
        System.out.println(TAG + "::loadUserInfo >>");

        User user = (User) session.getAttribute("user");
        User temp = userMapper.selectUserAndHeadImgByPrimaryKey(user.getUid());
        temp.getHeadImg().setCreateDate(null);
        temp.getHeadImg().setIsDel(null);
        temp.getHeadImg().setIsCurrentHead(null);

        // 用户发布的微博总数量
        MessageExample messageExample = new MessageExample();
        MessageExample.Criteria criteria = messageExample.createCriteria();
        criteria.andUidEqualTo(user.getUid());
        Long weiboCount = messageMapper.countByExample(messageExample);

        // 用户关注的用户数
        FocusUserExample focusUserExample = new FocusUserExample();
        FocusUserExample.Criteria criteria2 = focusUserExample.createCriteria();
        criteria2.andUidEqualTo(user.getUid());
        Long mFocusCount = focusUserMapper.countByExample(focusUserExample);

        // 用户的粉丝数
        FocusUserExample focusUserExample2 = new FocusUserExample();
        FocusUserExample.Criteria criteria3 = focusUserExample.createCriteria();
        criteria3.andFocusUidEqualTo(user.getUid());
        Long focusMCount = focusUserMapper.countByExample(focusUserExample2);

        Map<String, Object> data = new HashMap<>();
        data.put("user", temp);
        data.put("weiboCount", weiboCount);
        data.put("mFocusCount", mFocusCount);
        data.put("focusMCount", focusMCount);
        return data;
    }

    @Override
    public Map<String, Object> loadWeiboMessage(HttpSession session) {
        System.out.println(TAG + "::loadWeiboMessage >>");

        User user = (User) session.getAttribute("user");
        List<Message> messages = messageMapper.selectAllMessageByUid(user.getUid());
        for (Message message : messages) {
            PictureExample pictureExample = new PictureExample();
            PictureExample.Criteria criteria = pictureExample.createCriteria();
            criteria.andIsDelEqualTo("N");
            criteria.andMsgIdEqualTo(message.getId());
            criteria.andUidEqualTo(user.getUid());
            message.setPictures(pictureMapper.selectByExample(pictureExample));

            MessageCollectionExample messageCollectionExample = new MessageCollectionExample();
            MessageCollectionExample.Criteria mcriteria = messageCollectionExample.createCriteria();
            mcriteria.andMsgIdEqualTo(message.getId());
            mcriteria.andUidEqualTo(user.getUid());
            List<MessageCollection> messageCollections = messageCollectionMapper.selectByExample(messageCollectionExample);

            if (messageCollections.size() > 0) {
                message.setCollectionStatus("取消收藏");
            } else {
                message.setCollectionStatus("收藏");
            }
        }

        Map<String, Object> data = new HashMap<>();
        data.put("weibolist", messages);
        return data;
    }
}
