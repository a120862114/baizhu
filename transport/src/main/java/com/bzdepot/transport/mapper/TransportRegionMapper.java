package com.bzdepot.transport.mapper;

import com.bzdepot.transport.model.TransportRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransportRegionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TransportRegion record);

    int insertSelective(TransportRegion record);

    TransportRegion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransportRegion record);

    int updateByPrimaryKey(TransportRegion record);

    List<TransportRegion> findBlockListByCid(@Param("sellerid") Long sellerid,@Param("companyid") Long companyid);
}