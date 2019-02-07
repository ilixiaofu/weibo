package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.BlackList;
import com.springcloud.weibo.pojo.BlackListExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BlackListMapper {
    long countByExample(BlackListExample example);

    int deleteByExample(BlackListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlackList record);

    int insertSelective(BlackList record);

    List<BlackList> selectByExample(BlackListExample example);

    BlackList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlackList record, @Param("example") BlackListExample example);

    int updateByExample(@Param("record") BlackList record, @Param("example") BlackListExample example);

    int updateByPrimaryKeySelective(BlackList record);

    int updateByPrimaryKey(BlackList record);
}