package com.bzdepot.special.mapper;

import com.bzdepot.special.model.ProductTexture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTextureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductTexture record);

    int insertSelective(ProductTexture record);

    ProductTexture selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductTexture record);

    int updateByPrimaryKey(ProductTexture record);

    List<ProductTexture> selectByPconfigIdAndSellerId(@Param("sellerId") Long sellerId,@Param("pconfigId") Long pconfigId);

    ProductTexture selectByPconfigIdAndSellerIdAndTextureId(@Param("sellerId") Long sellerId,@Param("pconfigId") Long pconfigId,@Param("textureId") Long textureId);

    int setIsDefault(ProductTexture record);

    int deleteByMore(@Param("sellerId") Long sellerId,@Param("pconfigId") Long pconfigId,@Param("textureId") Long textureId);
}