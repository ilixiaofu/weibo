package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.MessageComments;
import com.springcloud.weibo.pojo.MessageCommentsExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MessageCommentsMapper {
    long countByExample(MessageCommentsExample example);

    int deleteByExample(MessageCommentsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageComments record);

    int insertSelective(MessageComments record);

    List<MessageComments> selectByExample(MessageCommentsExample example);

    MessageComments selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageComments record, @Param("example") MessageCommentsExample example);

    int updateByExample(@Param("record") MessageComments record, @Param("example") MessageCommentsExample example);

    int updateByPrimaryKeySelective(MessageComments record);

    int updateByPrimaryKey(MessageComments record);
}