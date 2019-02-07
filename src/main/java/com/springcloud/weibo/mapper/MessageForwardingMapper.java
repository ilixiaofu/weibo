package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.MessageForwarding;
import com.springcloud.weibo.pojo.MessageForwardingExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MessageForwardingMapper {
    long countByExample(MessageForwardingExample example);

    int deleteByExample(MessageForwardingExample example);

    int insert(MessageForwarding record);

    int insertSelective(MessageForwarding record);

    List<MessageForwarding> selectByExample(MessageForwardingExample example);

    int updateByExampleSelective(@Param("record") MessageForwarding record, @Param("example") MessageForwardingExample example);

    int updateByExample(@Param("record") MessageForwarding record, @Param("example") MessageForwardingExample example);
}