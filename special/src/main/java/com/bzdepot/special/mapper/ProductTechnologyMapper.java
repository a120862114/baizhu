package com.bzdepot.special.mapper;

import com.bzdepot.special.model.ProductTechnology;

public interface ProductTechnologyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductTechnology record);

    int insertSelective(ProductTechnology record);

    ProductTechnology selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductTechnology record);

    int updateByPrimaryKey(ProductTechnology record);
}