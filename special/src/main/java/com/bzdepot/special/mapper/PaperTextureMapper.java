package com.bzdepot.special.mapper;

import com.bzdepot.special.model.PaperTexture;

import java.util.List;

public interface PaperTextureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaperTexture record);

    int insertSelective(PaperTexture record);

    PaperTexture selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PaperTexture record);

    int updateByPrimaryKey(PaperTexture record);

    List<PaperTexture> selectBySellerId(Long sellerId);
}