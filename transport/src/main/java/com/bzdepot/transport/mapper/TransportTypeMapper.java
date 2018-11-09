package com.bzdepot.transport.mapper;

import com.bzdepot.transport.model.TransportType;

import java.util.List;

public interface TransportTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TransportType record);

    int insertSelective(TransportType record);

    TransportType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransportType record);

    int updateByPrimaryKey(TransportType record);

    List<TransportType> findAll();
}