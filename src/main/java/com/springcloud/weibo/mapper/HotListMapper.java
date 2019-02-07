package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.HotList;
import com.springcloud.weibo.pojo.HotListExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HotListMapper {
    long countByExample(HotListExample example);

    int deleteByExample(HotListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HotList record);

    int insertSelective(HotList record);

    List<HotList> selectByExample(HotListExample example);

    HotList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HotList record, @Param("example") HotListExample example);

    int updateByExample(@Param("record") HotList record, @Param("example") HotListExample example);

    int updateByPrimaryKeySelective(HotList record);

    int updateByPrimaryKey(HotList record);
}