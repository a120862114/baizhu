package com.bzdepot.images.mapper;

import com.bzdepot.images.model.Images;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImagesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Images record);

    int insertSelective(Images record);

    Images selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Images record);

    int updateByPrimaryKey(Images record);

    List<Images> findImagesByMd5(@Param("md5") String md5,@Param("user_id") Long user_id);

    List<Images> selectUserImgPage(@Param("userid") Long userid,@Param("imgname") String imgname,@Param("starttime") Long starttime,@Param("endtime") Long endtime);
}