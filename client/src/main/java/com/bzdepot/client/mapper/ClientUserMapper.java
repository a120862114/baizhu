package com.bzdepot.client.mapper;

import com.bzdepot.client.model.ClientUser;

public interface ClientUserMapper {
    int insert(ClientUser record);

    int insertSelective(ClientUser record);

    ClientUser getClientUser(Long id);



}