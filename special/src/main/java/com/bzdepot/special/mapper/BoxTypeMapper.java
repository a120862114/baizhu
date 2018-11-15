package com.bzdepot.special.mapper;

import com.bzdepot.special.model.BoxType;

import java.util.List;

public interface BoxTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BoxType record);

    int insertSelective(BoxType record);

    BoxType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BoxType record);

    int updateByPrimaryKey(BoxType record);

    List<BoxType> selectBySellerId(Long sellerId);
}