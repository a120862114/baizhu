package com.bzdepot.images.mapper;

import com.bzdepot.images.model.Server;

public interface ServerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Server record);

    int insertSelective(Server record);

    Server selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Server record);

    int updateByPrimaryKey(Server record);
}