package com.springcloud.weibo.mapper;

import com.springcloud.weibo.pojo.PhotoAlbum;
import com.springcloud.weibo.pojo.PhotoAlbumExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PhotoAlbumMapper {
    long countByExample(PhotoAlbumExample example);

    int deleteByExample(PhotoAlbumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PhotoAlbum record);

    int insertSelective(PhotoAlbum record);

    List<PhotoAlbum> selectByExample(PhotoAlbumExample example);

    PhotoAlbum selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PhotoAlbum record, @Param("example") PhotoAlbumExample example);

    int updateByExample(@Param("record") PhotoAlbum record, @Param("example") PhotoAlbumExample example);

    int updateByPrimaryKeySelective(PhotoAlbum record);

    int updateByPrimaryKey(PhotoAlbum record);
}