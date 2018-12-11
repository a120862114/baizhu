package com.bzdepot.special.mapper;

import com.bzdepot.special.bo.GetNamesBo;
import com.bzdepot.special.model.TechnologyClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TechnologyClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TechnologyClass record);

    int insertSelective(TechnologyClass record);

    TechnologyClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TechnologyClass record);

    int updateByPrimaryKeyWithBLOBs(TechnologyClass record);

    int updateByPrimaryKey(TechnologyClass record);

    List<TechnologyClass> selectPageClassAndAttr(@Param("sellerId") Long sellerId);

    GetNamesBo selectByPrimaryKeyForNames(Long id);
}