package com.bzdepot.special.mapper;

import com.bzdepot.special.model.PaperDrum;

import java.util.List;

public interface PaperDrumMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaperDrum record);

    int insertSelective(PaperDrum record);

    PaperDrum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaperDrum record);

    int updateByPrimaryKey(PaperDrum record);

    List<PaperDrum> selectBySellerId(Long sellerId);

    List<PaperDrum> selectByInIds(String Ids);
}