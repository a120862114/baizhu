package com.bzdepot.client.mapper;

import com.bzdepot.client.model.UserCollection;

public interface UserCollectionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserCollection record);

    int insertSelective(UserCollection record);

    UserCollection selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserCollection record);

    int updateByPrimaryKey(UserCollection record);
}