package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.HeadImg;
import com.springcloud.weibo.pojo.HeadImgExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface HeadImgMapper {
    long countByExample(HeadImgExample example);

    int deleteByExample(HeadImgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HeadImg record);

    int insertSelective(HeadImg record);

    List<HeadImg> selectByExample(HeadImgExample example);

    HeadImg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HeadImg record, @Param("example") HeadImgExample example);

    int updateByExample(@Param("record") HeadImg record, @Param("example") HeadImgExample example);

    int updateByPrimaryKeySelective(HeadImg record);

    int updateByPrimaryKey(HeadImg record);
}