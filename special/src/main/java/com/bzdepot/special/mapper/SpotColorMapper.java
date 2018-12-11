package com.bzdepot.special.mapper;

import com.bzdepot.special.model.SpotColor;

import java.util.List;

public interface SpotColorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SpotColor record);

    int insertSelective(SpotColor record);

    SpotColor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SpotColor record);

    int updateByPrimaryKey(SpotColor record);

    List<SpotColor> selectAllData();
}