package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.FocusUser;
import com.springcloud.weibo.pojo.FocusUserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FocusUserMapper {
    long countByExample(FocusUserExample example);

    int deleteByExample(FocusUserExample example);

    int insert(FocusUser record);

    int insertSelective(FocusUser record);

    List<FocusUser> selectByExample(FocusUserExample example);

    int updateByExampleSelective(@Param("record") FocusUser record, @Param("example") FocusUserExample example);

    int updateByExample(@Param("record") FocusUser record, @Param("example") FocusUserExample example);
}