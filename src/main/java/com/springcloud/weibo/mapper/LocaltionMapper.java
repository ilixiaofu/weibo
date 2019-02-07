package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.Localtion;
import com.springcloud.weibo.pojo.LocaltionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LocaltionMapper {
    long countByExample(LocaltionExample example);

    int deleteByExample(LocaltionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Localtion record);

    int insertSelective(Localtion record);

    List<Localtion> selectByExample(LocaltionExample example);

    Localtion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Localtion record, @Param("example") LocaltionExample example);

    int updateByExample(@Param("record") Localtion record, @Param("example") LocaltionExample example);

    int updateByPrimaryKeySelective(Localtion record);

    int updateByPrimaryKey(Localtion record);
}