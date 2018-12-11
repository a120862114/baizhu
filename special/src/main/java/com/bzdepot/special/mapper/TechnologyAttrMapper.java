package com.bzdepot.special.mapper;

import com.bzdepot.special.bo.GetNamesBo;
import com.bzdepot.special.model.TechnologyAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TechnologyAttrMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TechnologyAttr record);

    int insertSelective(TechnologyAttr record);

    TechnologyAttr selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TechnologyAttr record);

    int updateByPrimaryKey(TechnologyAttr record);

    int deleteByTid(Long tId);

    Integer countAttrDataByTid(@Param("sellerId") Long sellerId,@Param("tId") Long tId);

    List<TechnologyAttr> listAttrDataByTid(Long tId);

    GetNamesBo selectByPrimaryKeyForNames(Long id);
}