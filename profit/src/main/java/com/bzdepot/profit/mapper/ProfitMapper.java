package com.bzdepot.profit.mapper;

import com.bzdepot.profit.model.Profit;

public interface ProfitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Profit record);

    int insertSelective(Profit record);

    Profit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Profit record);

    int updateByPrimaryKey(Profit record);
}