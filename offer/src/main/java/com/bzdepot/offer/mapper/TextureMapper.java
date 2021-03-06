package com.bzdepot.offer.mapper;

import com.bzdepot.offer.model.Texture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TextureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Texture record);

    int insertSelective(Texture record);

    Texture selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Texture record);

    int updateByPrimaryKey(Texture record);

    int countTexture(@Param("classid") Long classid,@Param("sellerid") Long sellerid,@Param("groupid") Long groupid);

    Texture selectByTitleAndSellerId(@Param("sellerId") Long sellerId,@Param("title") String title);

    Texture selectByTitleAndSellerIdAndClassId(@Param("sellerId") Long sellerId,@Param("classId") Long classId,@Param("title") String title);

    List<Texture> selectBySellerId(Long sellerId);
}