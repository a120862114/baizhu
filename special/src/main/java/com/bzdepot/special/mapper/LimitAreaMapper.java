package com.bzdepot.special.mapper;

import com.bzdepot.special.model.LimitArea;

import java.util.List;

public interface LimitAreaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LimitArea record);

    int insertSelective(LimitArea record);

    LimitArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LimitArea record);

    int updateByPrimaryKey(LimitArea record);

    List<LimitArea> selectBySellerId(Long sellerId);
}