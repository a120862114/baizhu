package com.bzdepot.special.mapper;

import com.bzdepot.special.model.PackingMethod;

import java.util.List;

public interface PackingMethodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PackingMethod record);

    int insertSelective(PackingMethod record);

    PackingMethod selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PackingMethod record);

    int updateByPrimaryKey(PackingMethod record);

    List<PackingMethod> selectBySellerId(Long sellerId);
}