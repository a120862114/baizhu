package com.bzdepot.transport.mapper;

import com.bzdepot.transport.model.RegionDefault;

public interface RegionDefaultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RegionDefault record);

    int insertSelective(RegionDefault record);

    RegionDefault selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RegionDefault record);

    int updateByPrimaryKey(RegionDefault record);
}