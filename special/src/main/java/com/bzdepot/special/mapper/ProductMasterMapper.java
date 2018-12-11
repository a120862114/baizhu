package com.bzdepot.special.mapper;

import com.bzdepot.special.model.ProductMaster;
import org.apache.ibatis.annotations.Param;

public interface ProductMasterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductMaster record);

    int insertSelective(ProductMaster record);

    ProductMaster selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductMaster record);

    int updateByPrimaryKeyWithBLOBs(ProductMaster record);

    int updateByPrimaryKey(ProductMaster record);

    ProductMaster selectBySellerIdAndClassId(@Param("sellerId") Long sellerId,@Param("classId") Long classId);
}