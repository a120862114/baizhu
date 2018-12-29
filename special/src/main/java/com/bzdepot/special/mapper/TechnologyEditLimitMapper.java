package com.bzdepot.special.mapper;

import com.bzdepot.special.model.TechnologyEditLimit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TechnologyEditLimitMapper {

    int insert(@Param("limitEditData") List<TechnologyEditLimit> limitData, @Param("sellerId") Long sellerId, @Param("tClassId") Long tClassId, @Param("tAttrId") Long tAttrId);

    int insertSelective(TechnologyEditLimit record);

    int deleteData(@Param("sellerId") Long sellerId,@Param("tClassId") Long tClassId,@Param("tAttrId") Long tAttrId);

    List<TechnologyEditLimit> selectData(@Param("sellerId") Long sellerId,@Param("tClassId") Long tClassId,@Param("tAttrId") Long tAttrId);

}