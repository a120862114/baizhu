package com.bzdepot.offer.mapper;

import com.bzdepot.offer.model.OfferGroup;

import java.util.List;

public interface OfferGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OfferGroup record);

    int insertSelective(OfferGroup record);

    OfferGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OfferGroup record);

    int updateByPrimaryKey(OfferGroup record);

    List<OfferGroup> selectJoinData(Long offerid);
}