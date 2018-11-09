package com.bzdepot.offer.mapper;

import com.bzdepot.offer.model.OfferDetail;

public interface OfferDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OfferDetail record);

    int insertSelective(OfferDetail record);

    OfferDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OfferDetail record);

    int updateByPrimaryKey(OfferDetail record);

    int delByGroupId(Long offergroupid);
}