package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.UserFilePath;
import com.springcloud.weibo.pojo.UserFilePathExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserFilePathMapper {
    long countByExample(UserFilePathExample example);

    int deleteByExample(UserFilePathExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(UserFilePath record);

    int insertSelective(UserFilePath record);

    List<UserFilePath> selectByExample(UserFilePathExample example);

    UserFilePath selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") UserFilePath record, @Param("example") UserFilePathExample example);

    int updateByExample(@Param("record") UserFilePath record, @Param("example") UserFilePathExample example);

    int updateByPrimaryKeySelective(UserFilePath record);

    int updateByPrimaryKey(UserFilePath record);

    UserFilePath selectByUid(String uid);
}