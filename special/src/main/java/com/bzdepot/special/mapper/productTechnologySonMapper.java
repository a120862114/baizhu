package com.bzdepot.special.mapper;

import com.bzdepot.special.model.productTechnologySon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface productTechnologySonMapper {
    int insert(productTechnologySon record);

    int insertSelective(productTechnologySon record);

    int updateBySelective(productTechnologySon record);

    List<productTechnologySon> selectAttrData(@Param("sellerId") Long sellerId,@Param("classId") Long classId,@Param("technologyId") Long technologyId);
}