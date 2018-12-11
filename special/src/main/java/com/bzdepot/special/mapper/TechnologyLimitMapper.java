package com.bzdepot.special.mapper;

import com.bzdepot.special.model.TechnologyLimit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TechnologyLimitMapper {
    int insert(@Param("limitData") List<TechnologyLimit> limitData,@Param("sellerId") Long sellerId,@Param("tClassId") Long tClassId,@Param("tAttrId") Long tAttrId);

    int insertSelective(TechnologyLimit record);

    int deleteData(@Param("sellerId") Long sellerId,@Param("tClassId") Long tClassId,@Param("tAttrId") Long tAttrId);

    List<TechnologyLimit> selectData(@Param("sellerId") Long sellerId,@Param("tClassId") Long tClassId,@Param("tAttrId") Long tAttrId);
}