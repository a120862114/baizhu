package com.bzdepot.offer.mapper;

import com.bzdepot.offer.model.OfferSonExpress;

import java.util.List;

public interface OfferSonExpressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OfferSonExpress record);

    int insertSelective(OfferSonExpress record);

    OfferSonExpress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OfferSonExpress record);

    int updateByPrimaryKey(OfferSonExpress record);

    List<OfferSonExpress> selectByOfferSonId(Long offerSonId);
}