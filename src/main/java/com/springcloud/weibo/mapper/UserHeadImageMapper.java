package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.UserHeadImage;
import com.springcloud.weibo.pojo.UserHeadImageExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserHeadImageMapper {
    long countByExample(UserHeadImageExample example);

    int deleteByExample(UserHeadImageExample example);

    int insert(UserHeadImage record);

    int insertSelective(UserHeadImage record);

    List<UserHeadImage> selectByExample(UserHeadImageExample example);

    int updateByExampleSelective(@Param("record") UserHeadImage record, @Param("example") UserHeadImageExample example);

    int updateByExample(@Param("record") UserHeadImage record, @Param("example") UserHeadImageExample example);
}