package com.bzdepot.special.mapper;

import com.bzdepot.special.model.MachineBrand;

import java.util.List;

public interface MachineBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MachineBrand record);

    int insertSelective(MachineBrand record);

    MachineBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MachineBrand record);

    int updateByPrimaryKey(MachineBrand record);

    List<MachineBrand> selectBySellerId(Long sellerId);
}