package com.bzdepot.special.mapper;

import com.bzdepot.special.model.PaperBrand;

import java.util.List;

public interface PaperBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaperBrand record);

    int insertSelective(PaperBrand record);

    PaperBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaperBrand record);

    int updateByPrimaryKey(PaperBrand record);

    List<PaperBrand> selectBySellerId(Long sellerId);
}