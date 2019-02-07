package com.springcloud.weibo.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcloud.weibo.constant.ViewResourceConstant;
import com.springcloud.weibo.mapper.UserMapper;
import com.springcloud.weibo.pojo.User;
import com.springcloud.weibo.util.ServiceUtils;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    private final String TAG = UserInfoServiceImpl.class.getName();

    @Autowired
    private UserMapper UserMapper;

    @Override
    public Map<String, Object> updateUserInfo(User User, HttpSession session) {
        System.out.println(TAG + "::updateUserInfo >>");
        User temp = (User) session.getAttribute("user");
        User.setUid(temp.getUid());
        int count = UserMapper.updateByPrimaryKeySelective(User);
        Map<String, Object> data = new HashMap<>();
        if (count > 0) {
            data.put("code", 0);
            data.put("msg", "修改成功");
            data.put("data", session.getServletContext().getContextPath() + "/" + ViewResourceConstant.UserHomePage.USER_HOME_PAGE);
            data.put("error", "");
        } else {
            data.put("code", 1);
            data.put("msg", "修改失败");
            data.put("data", "");
            data.put("error", "发生未知异常");
        }
        return data;
    }

    @Override
    public Map<String, Object> updateUserPassword(String oldPwd, String newPwd, HttpSession session) {
        System.out.println(TAG + "::updateUserPassword >>");
        User user = (User) session.getAttribute("user");
        Map<String, Object> data = new HashMap<>();
        oldPwd = ServiceUtils.MessageDigest(oldPwd);
        newPwd = ServiceUtils.MessageDigest(oldPwd);
        Map<String, String> map = new HashMap<>();
        map.put("uid", user.getUid());
        map.put("password", oldPwd);
        User temp = UserMapper.selectByPrimaryKeyAndPawword(map);
        if (temp != null) {
            temp.setPassword(newPwd);
            int count = UserMapper.updateByPrimaryKeySelective(temp);
            if (count > 0) {
                data.put("code", 0);
                data.put("msg", "修改成功 您需要重新登录");
                data.put("data", session.getServletContext().getContextPath() + "/" + ViewResourceConstant.HOME_PAGE);
                data.put("error", "");
            } else {
                data.put("code", -1);
                data.put("msg", "修改失败");
                data.put("data", "");
                data.put("error", "发生未知异常");
            }
        } else {
            data.put("code", 1);
            data.put("msg", "原密码错误");
            data.put("data", "");
            data.put("error", "");
        }
        return data;
    }

    @Override
    public Map<String, Object> updateUserHeadPicture(HttpSession session) {
        System.out.println(TAG + "::updateUserHeadPicture >>");
        return null;
    }

}
