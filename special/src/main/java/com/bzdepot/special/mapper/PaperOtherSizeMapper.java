package com.bzdepot.special.mapper;

import com.bzdepot.special.model.PaperOtherSize;

import java.util.List;

public interface PaperOtherSizeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaperOtherSize record);

    int insertSelective(PaperOtherSize record);

    PaperOtherSize selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaperOtherSize record);

    int updateByPrimaryKey(PaperOtherSize record);

    List<PaperOtherSize> selectBySellerId(Long sellerId);
}