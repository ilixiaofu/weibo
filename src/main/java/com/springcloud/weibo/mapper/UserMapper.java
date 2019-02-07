package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.User;
import com.springcloud.weibo.pojo.UserExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String uid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByPrimaryKeyAndPawword(Map<String, String> map);

    User selectUserAndHeadImgByPrimaryKey(String uid);

    List<User> selectAll();
}