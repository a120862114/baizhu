package com.bzdepot.profit.mapper;

import com.bzdepot.profit.model.ProfitUltimate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProfitUltimateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProfitUltimate record);

    int insertSelective(ProfitUltimate record);

    ProfitUltimate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProfitUltimate record);

    int updateByPrimaryKey(ProfitUltimate record);

    List<ProfitUltimate> selectListData(@Param("sellerId") Long sellerId, @Param("classId") Long classId, @Param("textureId") Long textureId, @Param("gramId") Long gramId);
}