package com.bzdepot.offer.mapper;

import com.bzdepot.offer.model.TextureGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TextureGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TextureGroup record);

    int insertSelective(TextureGroup record);

    TextureGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TextureGroup record);

    int updateByPrimaryKey(TextureGroup record);

    List<TextureGroup> findJionGroupData(@Param("sellerid") Long sellerid,@Param("classid") Long classid);

    int countTextureGroup(@Param("sellerid") Long sellerid,@Param("classid") Long classid);

    TextureGroup selectBySellerIdAndGroupName(@Param("sellerId") Long sellerId,@Param("groupName") String groupName);

    TextureGroup countTextureGroupByMore(@Param("sellerid") Long sellerid,@Param("classid") Long classid,@Param("groupName") String groupName);
}