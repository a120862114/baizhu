package com.bzdepot.special.mapper;

import com.bzdepot.special.model.Manufactor;

import java.util.List;

public interface ManufactorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Manufactor record);

    int insertSelective(Manufactor record);

    Manufactor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Manufactor record);

    int updateByPrimaryKey(Manufactor record);

    List<Manufactor> selectBySellerId(Long sellerId);
}