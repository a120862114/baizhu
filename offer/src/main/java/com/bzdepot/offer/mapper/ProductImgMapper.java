package com.bzdepot.offer.mapper;

import com.bzdepot.offer.model.ProductImg;
import com.bzdepot.offer.vo.ProductImgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductImgMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductImg record);

    int insertSelective(ProductImg record);

    ProductImg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductImg record);

    int updateByPrimaryKey(ProductImg record);

    List<ProductImgVo> getProductImgList(@Param("sellerid") Long sellerid, @Param("classid") Long classid, @Param("textureid") Long textureid);
}