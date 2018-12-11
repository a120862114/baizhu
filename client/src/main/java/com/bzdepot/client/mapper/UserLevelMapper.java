package com.bzdepot.client.mapper;

import com.bzdepot.client.model.UserLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLevel record);

    int insertSelective(UserLevel record);

    UserLevel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLevel record);

    int updateByPrimaryKey(UserLevel record);

    List<UserLevel> getList(Long seller_id);

    UserLevel findDefault(UserLevel record);

    UserLevel selectBySellerIdAndLevelName(@Param("sellerId") Long sellerId,@Param("levelName") String levelName);
}