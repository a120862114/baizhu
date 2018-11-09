package com.bzdepot.special.mapper;

import com.bzdepot.special.model.PaperSupplier;

import java.util.List;

public interface PaperSupplierMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaperSupplier record);

    int insertSelective(PaperSupplier record);

    PaperSupplier selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaperSupplier record);

    int updateByPrimaryKey(PaperSupplier record);

    List<PaperSupplier> selectBySellerId(Long sellerId);
}