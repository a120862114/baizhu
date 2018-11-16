package com.bzdepot.special.mapper;

import com.bzdepot.special.model.ProductTexture;

public interface ProductTextureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductTexture record);

    int insertSelective(ProductTexture record);

    ProductTexture selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductTexture record);

    int updateByPrimaryKey(ProductTexture record);
}