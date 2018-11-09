package com.bzdepot.user.service;

import com.bzdepot.user.mapper.MemberMapper;
import com.bzdepot.user.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 修改完善用户信息
     */
    public int updateUserInfo(Member member){
        return memberMapper.updateByPrimaryKeySelective(member);
    }
}
