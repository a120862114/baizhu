package com.bzdepot.special.mapper;

import com.bzdepot.special.model.ProductTechnology;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTechnologyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductTechnology record);

    int insertSelective(ProductTechnology record);

    ProductTechnology selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductTechnology record);

    int updateByPrimaryKey(ProductTechnology record);

    int deleteBySellerIdAndPconfigIdAndTechnologyId(@Param("sellerId") Long sellerId,@Param("pconfigId") Long pconfigId,@Param("technologyId") Long technologyId);

    ProductTechnology selectOneBySellerIdPconfigIdAndTechnologyId(@Param("sellerId") Long sellerId,@Param("pconfigId") Long pconfigId,@Param("technologyId") Long technologyId);

    List<ProductTechnology> selectBySellerIdAndPconfigIdForList(@Param("sellerId") Long sellerId,@Param("pconfigId") Long pconfigId);

    int updateByPrimaryKeySelectiveForDefaul(ProductTechnology record);
}