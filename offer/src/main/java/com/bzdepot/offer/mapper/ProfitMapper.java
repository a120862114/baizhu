package com.bzdepot.offer.mapper;

import com.bzdepot.offer.model.Profit;

import java.util.List;

public interface ProfitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Profit record);

    int insertSelective(Profit record);

    Profit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Profit record);

    int updateByPrimaryKey(Profit record);

    List<Profit> findOfferIdList(Long offerid);
}