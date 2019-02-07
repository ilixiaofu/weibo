package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.WeiboUser;
import com.springcloud.weibo.pojo.WeiboUserExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface WeiboUserMapper {
    long countByExample(WeiboUserExample example);

    int deleteByExample(WeiboUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(WeiboUser record);

    int insertSelective(WeiboUser record);

    List<WeiboUser> selectByExample(WeiboUserExample example);

    WeiboUser selectByPrimaryKey(String id);

    WeiboUser selectByPrimaryKeyAndPassword(Map<String, String> map);

    int updateByExampleSelective(@Param("record") WeiboUser record, @Param("example") WeiboUserExample example);

    int updateByExample(@Param("record") WeiboUser record, @Param("example") WeiboUserExample example);

    int updateByPrimaryKeySelective(WeiboUser record);

    int updateByPrimaryKey(WeiboUser record);
}