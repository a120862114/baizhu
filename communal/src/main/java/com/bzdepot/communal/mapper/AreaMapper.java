package com.bzdepot.communal.mapper;

import com.bzdepot.communal.model.Area;

import java.util.List;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer areaid);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer areaid);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    List<Area> findCityParent(Integer parentid);
}