package com.bzdepot.special.mapper;

import com.bzdepot.special.model.PrintingCost;

import java.util.List;

public interface PrintingCostMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PrintingCost record);

    int insertSelective(PrintingCost record);

    PrintingCost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PrintingCost record);

    int updateByPrimaryKey(PrintingCost record);

    List<PrintingCost> selectBySellerId(Long sellerId);
}