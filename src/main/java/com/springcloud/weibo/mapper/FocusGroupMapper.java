package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.FocusGroup;
import com.springcloud.weibo.pojo.FocusGroupExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FocusGroupMapper {
    long countByExample(FocusGroupExample example);

    int deleteByExample(FocusGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FocusGroup record);

    int insertSelective(FocusGroup record);

    List<FocusGroup> selectByExample(FocusGroupExample example);

    FocusGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FocusGroup record, @Param("example") FocusGroupExample example);

    int updateByExample(@Param("record") FocusGroup record, @Param("example") FocusGroupExample example);

    int updateByPrimaryKeySelective(FocusGroup record);

    int updateByPrimaryKey(FocusGroup record);
}