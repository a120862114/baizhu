package com.bzdepot.special.mapper;

import com.bzdepot.special.model.ProductNumber;

public interface ProductNumberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductNumber record);

    int insertSelective(ProductNumber record);

    ProductNumber selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductNumber record);

    int updateByPrimaryKey(ProductNumber record);
}