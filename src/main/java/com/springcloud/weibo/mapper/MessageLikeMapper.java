package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.MessageLike;
import com.springcloud.weibo.pojo.MessageLikeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MessageLikeMapper {
    long countByExample(MessageLikeExample example);

    int deleteByExample(MessageLikeExample example);

    int insert(MessageLike record);

    int insertSelective(MessageLike record);

    List<MessageLike> selectByExample(MessageLikeExample example);

    int updateByExampleSelective(@Param("record") MessageLike record, @Param("example") MessageLikeExample example);

    int updateByExample(@Param("record") MessageLike record, @Param("example") MessageLikeExample example);
}