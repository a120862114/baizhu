package com.bzdepot.mem.service;

import com.bzdepot.mem.model.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberService {

    Long addUser(Member User);

    Long addUserAndClient(Member User,Long SellerId,Byte UserTypes);

    Member findUserByUsername(String username);

    Member findUserByEmail(String email);

    Member findUserByMobile(Long mobile);

    Member loginForUserEmail(String Email,String Password);

    Member loginForUserMobile(Long Mobile,String Password);

    Member loginForUserMemberUsername(String Username,String Password);

    Object getClientUser(Long UserId);

    int updateByMemberMobile(@Param(value="password") String password,@Param(value="id") Long id);

    Member findUserById(Long userId);

}
