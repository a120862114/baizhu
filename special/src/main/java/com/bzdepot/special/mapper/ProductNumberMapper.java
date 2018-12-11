package com.bzdepot.special.mapper;

import com.bzdepot.special.model.ProductNumber;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductNumberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductNumber record);

    int insertSelective(ProductNumber record);

    ProductNumber selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductNumber record);

    int updateByPrimaryKey(ProductNumber record);

    List<ProductNumber> selectBySellerId(@Param("sellerId") Long sellerId, @Param("pconfigId") Long pconfigId);

    ProductNumber findByMoreCondition(ProductNumber record);

    int setIsDefault(ProductNumber record);
}