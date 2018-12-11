package com.bzdepot.special.mapper;

import com.bzdepot.special.model.CommonColor;

import java.util.List;

public interface CommonColorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommonColor record);

    int insertSelective(CommonColor record);

    CommonColor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommonColor record);

    int updateByPrimaryKey(CommonColor record);

    List<CommonColor> selectList();
}