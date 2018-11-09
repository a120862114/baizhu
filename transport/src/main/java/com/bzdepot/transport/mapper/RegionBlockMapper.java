package com.bzdepot.transport.mapper;

import com.bzdepot.transport.model.RegionBlock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionBlockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RegionBlock record);

    int insertSelective(RegionBlock record);

    RegionBlock selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RegionBlock record);

    int updateByPrimaryKey(RegionBlock record);

    List<RegionBlock> getCityDataByCompanyIdAndSellerId(RegionBlock record);

    List<RegionBlock> selectIsSuspendByContidion(@Param("sellerId") Long sellerId,@Param("companyId") Long companyId,@Param("regionId") Long regionId,@Param("blockId") Integer blockId);
}