package com.bzdepot.communal.mapper;

import com.bzdepot.communal.model.ConsigneeAccess;

public interface ConsigneeAccessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ConsigneeAccess record);

    int insertSelective(ConsigneeAccess record);

    ConsigneeAccess selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ConsigneeAccess record);

    int updateByPrimaryKey(ConsigneeAccess record);
}