package com.bzdepot.offer.mapper;

import com.bzdepot.offer.model.OfferAttr;

public interface OfferAttrMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OfferAttr record);

    int insertSelective(OfferAttr record);

    OfferAttr selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OfferAttr record);

    int updateByPrimaryKey(OfferAttr record);

    int delbyGroupId(Long offergroupid);
}