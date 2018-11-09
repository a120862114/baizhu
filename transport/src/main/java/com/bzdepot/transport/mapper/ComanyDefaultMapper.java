package com.bzdepot.transport.mapper;

import com.bzdepot.transport.model.ComanyDefault;

public interface ComanyDefaultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ComanyDefault record);

    int insertSelective(ComanyDefault record);

    ComanyDefault selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ComanyDefault record);

    int updateBySellerIdSelective(ComanyDefault record);

    int updateByPrimaryKey(ComanyDefault record);

    ComanyDefault selectBySellerId(Long sellerId);
}