package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.Forwarding;
import com.springcloud.weibo.pojo.ForwardingExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ForwardingMapper {
    long countByExample(ForwardingExample example);

    int deleteByExample(ForwardingExample example);

    int insert(Forwarding record);

    int insertSelective(Forwarding record);

    List<Forwarding> selectByExample(ForwardingExample example);

    int updateByExampleSelective(@Param("record") Forwarding record, @Param("example") ForwardingExample example);

    int updateByExample(@Param("record") Forwarding record, @Param("example") ForwardingExample example);
}