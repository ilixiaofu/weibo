package com.springcloud.weibo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcloud.weibo.mapper.BlackListMapper;
import com.springcloud.weibo.mapper.MessageMapper;
import com.springcloud.weibo.mapper.PictureMapper;
import com.springcloud.weibo.mapper.UserMapper;
import com.springcloud.weibo.pojo.BlackList;
import com.springcloud.weibo.pojo.BlackListExample;
import com.springcloud.weibo.pojo.Message;
import com.springcloud.weibo.pojo.MessageExample;
import com.springcloud.weibo.pojo.Picture;
import com.springcloud.weibo.pojo.PictureExample;
import com.springcloud.weibo.pojo.User;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final String TAG = AdminServiceImpl.class.getName();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlackListMapper blackListMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public Map<String, Object> findAllUser() {
        System.out.println(TAG + "::findAllUser>>");
        List<User> users = userMapper.selectAll();
        Map<String, Object> data = new HashMap<>();
        System.out.println(users);
        data.put("users", users);
        return data;
    }

    @Override
    public Map<String, Object> blockUser(String uid) {
        System.out.println(TAG + "::blockUser>>");
        Map<String, Object> data = new HashMap<>();
        BlackList blackList = new BlackList();
        blackList.setUid(uid);
        int count = blackListMapper.insertSelective(blackList);
        if (count > 0) {
            data.put("code", 0);
            data.put("msg", "封锁成功");
            data.put("data", "");
            data.put("error", "");
        } else {
            data.put("code", 1);
            data.put("msg", "封锁失败");
            data.put("data", "");
            data.put("error", "发生未知异常");
        }
        return data;
    }

    @Override
    public Map<String, Object> unBlockUser(String uid) {
        System.out.println(TAG + "::unBlockUser>>");
        Map<String, Object> data = new HashMap<>();
        BlackListExample example = new BlackListExample();
        BlackListExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        int count = blackListMapper.deleteByExample(example);
        if (count > 0) {
            data.put("code", 0);
            data.put("msg", "解除封锁成功");
            data.put("data", "");
            data.put("error", "");
        } else {
            data.put("code", 1);
            data.put("msg", "解除封锁失败");
            data.put("data", "");
            data.put("error", "发生未知异常");
        }
        return data;
    }

    @Override
    public Map<String, Object> deleteUser(String uid) {
        System.out.println(TAG + "::deleteUser>>");
        Map<String, Object> data = new HashMap<>();
        int count = userMapper.deleteByPrimaryKey(uid);
        if (count > 0) {
            data.put("code", 0);
            data.put("msg", "注销成功");
            data.put("data", "");
            data.put("error", "");
        } else {
            data.put("code", 1);
            data.put("msg", "注销失败");
            data.put("data", "");
            data.put("error", "发生未知异常");
        }
        return data;
    }

    @Override
    public Map<String, Object> findAllWeibo() {
        System.out.println(TAG + "::findAllWeibo>>");
        Map<String, Object> data = new HashMap<>();
        MessageExample example = new MessageExample();
        MessageExample.Criteria criteria = example.createCriteria();
        List<Message> messages = messageMapper.selectByExample(example);
        data.put("weiboList", messages);
        return data;
    }

    @Override
    public Map<String, Object> deleteWeibo(Integer msgid) {
        Map<String, Object> data = new HashMap<>();
        int count = messageMapper.deleteByPrimaryKey(msgid);
        if (count > 0) {
            data.put("code", 0);
            data.put("msg", "删除成功");
            data.put("data", "");
            data.put("error", "");
        } else {
            data.put("code", 1);
            data.put("msg", "删除失败");
            data.put("data", "");
            data.put("error", "发生未知异常");
        }
        return data;
    }

    @Override
    public Map<String, Object> findAllPicture() {
        System.out.println(TAG + "::findAllPicture>>");
        Map<String, Object> data = new HashMap<>();
        PictureExample example = new PictureExample();
        PictureExample.Criteria criteria = example.createCriteria();
        List<Picture> pictures = pictureMapper.selectByExample(example);
        data.put("pictures", pictures);
        return data;
    }

    @Override
    public Map<String, Object> deletePicture(Integer picid) {
        System.out.println(TAG + "::deletePicture>>");
        Map<String, Object> data = new HashMap<>();
        int count = pictureMapper.deleteByPrimaryKey(picid);
        if (count > 0) {
            data.put("code", 0);
            data.put("msg", "删除成功");
            data.put("data", "");
            data.put("error", "");
        } else {
            data.put("code", 1);
            data.put("msg", "删除失败");
            data.put("data", "");
            data.put("error", "发生未知异常");
        }
        return data;
    }

}
