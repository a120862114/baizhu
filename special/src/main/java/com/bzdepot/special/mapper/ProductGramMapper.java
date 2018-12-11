package com.bzdepot.special.mapper;

import com.bzdepot.special.model.ProductGram;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductGramMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductGram record);

    int insertSelective(ProductGram record);

    ProductGram selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductGram record);

    int updateByPrimaryKey(ProductGram record);

    ProductGram selectByMore(@Param("sellerId") Long sellerId,@Param("textureId") Long textureId,@Param("gramId") Long gramId);

    List<ProductGram> selectForList(@Param("sellerId") Long sellerId,@Param("textureId") Long textureId);

    int deleteByMore(@Param("sellerId") Long sellerId,@Param("textureId") Long textureId,@Param("gramId") Long gramId);

    int setIsDefault(@Param("sellerId") Long sellerId,@Param("textureId") Long textureId,@Param("gramId") Long gramId);
}