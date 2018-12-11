package com.bzdepot.special.mapper;

import com.bzdepot.special.model.PrintingSpotConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrintingSpotConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PrintingSpotConfig record);

    int insertSelective(PrintingSpotConfig record);

    PrintingSpotConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PrintingSpotConfig record);

    int updateByPrimaryKey(PrintingSpotConfig record);

    List<PrintingSpotConfig> selectBySellerIdAndPrintingCostId(@Param("sellerId") Long sellerId,@Param("printingCostId") Long printingCostId);
}