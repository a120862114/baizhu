package com.bzdepot.offer.mapper;

import com.bzdepot.offer.model.Classfiy;
import org.apache.ibatis.annotations.Param;

public interface ClassfiyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Classfiy record);

    int insertSelective(Classfiy record);

    Classfiy selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Classfiy record);

    int updateByPrimaryKey(Classfiy record);

    Long countClassfiyForFroup(Long groupid);

    Classfiy selectByTitle(@Param("sellerId") Long sellerId,@Param("title") String title);
}