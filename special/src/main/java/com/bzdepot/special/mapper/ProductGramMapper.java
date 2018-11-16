package com.bzdepot.special.mapper;

import com.bzdepot.special.model.ProductGram;

public interface ProductGramMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductGram record);

    int insertSelective(ProductGram record);

    ProductGram selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductGram record);

    int updateByPrimaryKey(ProductGram record);
}