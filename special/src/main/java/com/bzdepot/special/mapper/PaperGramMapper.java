package com.bzdepot.special.mapper;

import com.bzdepot.special.model.PaperGram;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PaperGramMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaperGram record);

    int insertSelective(PaperGram record);

    PaperGram selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaperGram record);

    int updateByPrimaryKey(PaperGram record);

    List<PaperGram> selectBySellerId(Long sellerId);

    PaperGram selectBySellerIdAndGramNums(@Param("sellerId") Long sellerId, @Param("gramNums") BigDecimal gramNums);
}