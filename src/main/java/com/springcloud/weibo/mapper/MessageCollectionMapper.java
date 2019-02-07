package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.MessageCollection;
import com.springcloud.weibo.pojo.MessageCollectionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MessageCollectionMapper {
    long countByExample(MessageCollectionExample example);

    int deleteByExample(MessageCollectionExample example);

    int insert(MessageCollection record);

    int insertSelective(MessageCollection record);

    List<MessageCollection> selectByExample(MessageCollectionExample example);

    int updateByExampleSelective(@Param("record") MessageCollection record, @Param("example") MessageCollectionExample example);

    int updateByExample(@Param("record") MessageCollection record, @Param("example") MessageCollectionExample example);
}