package com.bzdepot.special.mapper;

import com.bzdepot.special.model.ProductSize;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductSizeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSize record);

    int insertSelective(ProductSize record);

    ProductSize selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductSize record);

    int updateByPrimaryKey(ProductSize record);

    List<ProductSize> selectBySellerId(@Param("sellerId") Long sellerId,@Param("pconfigId") Long pconfigId);

    ProductSize findSizeByOutId(ProductSize record);

    int setIsDefault(ProductSize record);
}