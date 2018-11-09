package com.bzdepot.offer.mapper;

import com.bzdepot.offer.model.OfferSon;
import org.apache.ibatis.annotations.Param;

public interface OfferSonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OfferSon record);

    int insertSelective(OfferSon record);

    OfferSon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OfferSon record);

    int updateByPrimaryKeyWithBLOBs(OfferSon record);

    int updateByPrimaryKey(OfferSon record);

    OfferSon selectByClassIdAndTextureIdAndSellerId(@Param("sellerId") Long sellerId,@Param("classId") Long classId,@Param("textureId") Long textureId);
}