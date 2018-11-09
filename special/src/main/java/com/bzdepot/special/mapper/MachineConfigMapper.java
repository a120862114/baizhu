package com.bzdepot.special.mapper;

import com.bzdepot.special.model.MachineConfig;

public interface MachineConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MachineConfig record);

    int insertSelective(MachineConfig record);

    MachineConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MachineConfig record);

    int updateByPrimaryKey(MachineConfig record);
}