package com.bzdepot.offer.mapper;

import com.bzdepot.offer.model.Offer;
import com.bzdepot.offer.vo.SelectOfferVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OfferMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Offer record);

    int insertSelective(Offer record);

    Offer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Offer record);

    int updateByPrimaryKey(Offer record);

    List<Offer> selectBySidCidTid(@Param("sellerid") Long sellerid,@Param("classid") Long classid,@Param("textureid") Long textureid,@Param("types") Byte types);

    List<Offer> selectBySidCidTidAll(@Param("sellerid") Long sellerid,@Param("classid") Long classid,@Param("textureid") Long textureid);

    SelectOfferVo findMoney(@Param("offerId") Long offerId,@Param("nums") Integer nums);

    List<SelectOfferVo> findOfferAllData(@Param("sellerId") Long sellerId, @Param("classId") Long classId, @Param("textureId") Long textureId, @Param("nums") Integer nums, @Param("thickness") BigDecimal thickness,@Param("weight") BigDecimal weight);

    List<SelectOfferVo> findOfferAllDataForSumer(@Param("sellerId") Long sellerId, @Param("classId") Long classId, @Param("textureId") Long textureId,@Param("thickness") BigDecimal thickness,@Param("weight") BigDecimal weight,@Param("typesData") Byte typesData);
}