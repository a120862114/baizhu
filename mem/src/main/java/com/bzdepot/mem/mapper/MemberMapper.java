package com.bzdepot.mem.mapper;

import com.bzdepot.mem.model.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    Member findMemberByUsername(String uname);

    Member findMemberByEmail(String mail);

    Member findMemberByMobile(Long phone);
}