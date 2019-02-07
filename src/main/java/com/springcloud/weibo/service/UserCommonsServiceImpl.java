package com.springcloud.weibo.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcloud.weibo.constant.ViewResourceConstant;
import com.springcloud.weibo.mapper.AdminMapper;
import com.springcloud.weibo.mapper.BlackListMapper;
import com.springcloud.weibo.mapper.UserMapper;
import com.springcloud.weibo.pojo.Admin;
import com.springcloud.weibo.pojo.AdminExample;
import com.springcloud.weibo.pojo.BlackList;
import com.springcloud.weibo.pojo.BlackListExample;
import com.springcloud.weibo.pojo.User;
import com.springcloud.weibo.util.ServiceUtils;


@Service
@Transactional
public class UserCommonsServiceImpl implements UserCommonsService {

    private final String TAG = UserCommonsServiceImpl.class.getName();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private BlackListMapper blackListMapper;

    @Autowired
    private InitUserRequireInfoService initUserRequireInfoService;

    @Override
    public Map<String, Object> userRegister(String uid, String password) {
        System.out.println(TAG + "::userRegister >>");

        password = ServiceUtils.MessageDigest(password);
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("password", password);
        User temp = userMapper.selectByPrimaryKeyAndPawword(map);

        Map<String, Object> data = new HashMap<>();
        if (temp == null) {
            User user = new User();
            user.setUid(uid);
            user.setPassword(password);
            user.setIntroduce("一句话介绍下自己吧，让别人更了解你");
            int count = userMapper.insertSelective(user);
            if (count > 0) {
                // 初始化头像
                new MyThread(uid, initUserRequireInfoService).start();
                data.put("code", 0);
                data.put("msg", "注册成功");
                data.put("data", ViewResourceConstant.HOME_PAGE);
                data.put("error", "");
            } else {
                data.put("code", -1);
                data.put("msg", "注册失败");
                data.put("data", "");
                data.put("error", "");
            }
        } else {
            data.put("code", 1);
            data.put("msg", "该用户名已存在");
            data.put("data", "");
            data.put("error", "");
        }
        return data;
    }

    @Override
    public Map<String, Object> userLogin(String uid, String password, HttpSession session) {
        System.out.println(TAG + "::userLogin >>");
        Map<String, Object> data = new HashMap<>();
        password = ServiceUtils.MessageDigest(password);

        BlackListExample blackListExample = new BlackListExample();
        BlackListExample.Criteria bcriteria = blackListExample.createCriteria();
        bcriteria.andUidEqualTo(uid);
        List<BlackList> blackLists = blackListMapper.selectByExample(blackListExample);
        BlackList blackList = blackLists.size() == 0 ? null : blackLists.get(0);

        if (blackList == null) {
            Map<String, String> map = new HashMap<>();
            map.put("uid", uid);
            map.put("password", password);
            User user = userMapper.selectByPrimaryKeyAndPawword(map);

            AdminExample example = new AdminExample();
            AdminExample.Criteria criteria = example.createCriteria();
            criteria.andAidEqualTo(uid);
            criteria.andPasswordEqualTo(password);
            List<Admin> admins = adminMapper.selectByExample(example);
            Admin admin = admins.size() == 0 ? null : admins.get(0);

            if (user != null) {
                user.setPassword(null);
                user.setRegisterDate(null);
                session.setAttribute("user", user);
                data.put("code", 0);
                data.put("msg", "登录成功");
                data.put("data", ViewResourceConstant.UserHomePage.USER_HOME_PAGE);
                data.put("error", "");
            } else if (admin != null) {
                session.setAttribute("admin", admin);
                admin.setPassword(null);
                data.put("code", 0);
                data.put("msg", "登录成功");
                data.put("data", ViewResourceConstant.BackStageManagement.MANAGMENT_PAGE);
                data.put("error", "");
            } else {
                data.put("code", 1);
                data.put("msg", "用户名密码错误");
                data.put("data", "");
                data.put("error", "");
            }
        } else {
            data.put("code", 1);
            data.put("msg", "该账号已被封号");
            data.put("data", "");
            data.put("error", "");
        }
        return data;
    }

    @Override
    public String userLogout(HttpSession session) {
        System.out.println(TAG + "::userLogout >>");
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/" + ViewResourceConstant.HOME_PAGE;
    }

    private class MyThread extends Thread {

        private final String TAG = MyThread.class.getName();

        private String uid;

        private InitUserRequireInfoService initUserRequireInfoService;

        public MyThread(String uid, InitUserRequireInfoService initUserRequireInfoService) {
            this.uid = uid;
            this.initUserRequireInfoService = initUserRequireInfoService;
        }

        @Override
        public void run() {
            System.out.println(TAG + "::run >>");
            String uuid = initUserRequireInfoService.initUserFilePath(uid);
            try {
                initUserRequireInfoService.initUserHeadImg(uuid, uid);
            } catch (IOException e) {
                System.out.println("头像初始化失败");
                e.printStackTrace();
            }
        }
    }

}
