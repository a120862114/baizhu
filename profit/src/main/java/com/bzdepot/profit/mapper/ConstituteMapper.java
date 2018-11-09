package com.bzdepot.profit.mapper;

import com.bzdepot.profit.model.Constitute;
import org.apache.ibatis.annotations.Param;

public interface ConstituteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Constitute record);

    int insertSelective(Constitute record);

    Constitute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Constitute record);

    int updateByPrimaryKey(Constitute record);

    Constitute selectByOfferIdAndSellerId(@Param("offerId") Long offerId,@Param("sellerId") Long sellerId);
}