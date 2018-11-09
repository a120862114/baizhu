package com.bzdepot.offer.mapper;

import com.bzdepot.offer.model.ClassGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassGroup record);

    int insertSelective(ClassGroup record);

    ClassGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClassGroup record);

    int updateByPrimaryKey(ClassGroup record);

    List<ClassGroup> selectGroupAndClass(Long sellerid);

    ClassGroup selectByGroupNameAndSellerId(@Param("sellerId") Long sellerId,@Param("groupName") String groupName);
}