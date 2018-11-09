package com.bzdepot.special.mapper;

import com.bzdepot.special.model.MachineColor;

import java.util.List;

public interface MachineColorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MachineColor record);

    int insertSelective(MachineColor record);

    MachineColor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MachineColor record);

    int updateByPrimaryKey(MachineColor record);

    List<MachineColor> selectBySellerId(Long sellerId);
}