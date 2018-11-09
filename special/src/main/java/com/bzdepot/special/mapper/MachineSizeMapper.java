package com.bzdepot.special.mapper;

import com.bzdepot.special.model.MachineSize;

import java.util.List;

public interface MachineSizeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MachineSize record);

    int insertSelective(MachineSize record);

    MachineSize selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MachineSize record);

    int updateByPrimaryKey(MachineSize record);

    List<MachineSize> selectBySellerId(Long sellerId);
}