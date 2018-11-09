package com.bzdepot.transport.mapper;

import com.bzdepot.transport.model.BlockPrice;
import org.apache.ibatis.annotations.Param;

public interface BlockPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlockPrice record);

    int insertSelective(BlockPrice record);

    BlockPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BlockPrice record);

    int updateByPrimaryKey(BlockPrice record);

    BlockPrice findCityConfig(@Param("companyId") Long companyId,@Param("sellerId") Long sellerId,@Param("cityId") Long cityId);
}