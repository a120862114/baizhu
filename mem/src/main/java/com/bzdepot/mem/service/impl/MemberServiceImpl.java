package com.bzdepot.mem.service.impl;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.common.util.Md5Encode;
import com.bzdepot.mem.fegin.ClientService;
import com.bzdepot.mem.mapper.MemberMapper;
import com.bzdepot.mem.model.Member;
import com.bzdepot.mem.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {

    private final static Logger loger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Value("${md5key}")
    private String md5key;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ClientService clientService;

    @Override
    public Long addUser(Member User){
        if(User.getNickname() == null){
            User.setNickname(User.getUsername());
        }
        //设置时间戳
        Long NowTime = new Date().getTime();
        User.setCreateTime(NowTime);
        User.setUpdateTime(NowTime);
        //给密码进行加密
        try {
            User.setPassword(Md5Encode.md5(User.getPassword(),this.md5key));
        }catch (Exception e){
            return null;
        }
        memberMapper.insertSelective(User);
        return User.getId();
    }

    @Override
    public Long addUserAndClient(Member User,Long SellerId,Byte UserTypes){
        Long UserId = this.addUser(User);
        if(UserId != null){
            Map<String,Object> Datas = new HashMap<String, Object>();
            Datas.put("userId",UserId);
            if(SellerId != null){
                Datas.put("sellerId",SellerId);
            }
            Datas.put("nickname",User.getNickname());
            Datas.put("userType",UserTypes);
            Object res = clientService.AddUserInfo(Datas);
            Integer Status = null;
            try {
                Status = (Integer) JsonReturn.Parse(res).get("error_code");
            }catch (Exception e){
                loger.error(e.getMessage());
                return null;
            }
            if(Status != null && Status == 0){
                return UserId;
            }else{
                //操作失败,删除主用户数据
                memberMapper.deleteByPrimaryKey(UserId);
            }
        }
        return null;
    }

    @Override
    public  Member findUserByUsername(String username){
        return memberMapper.findMemberByUsername(username);
    }

    @Override
    public  Member findUserByEmail(String email){
        return memberMapper.findMemberByEmail(email);
    }

    @Override
    public  Member findUserByMobile(Long mobile){
        return memberMapper.findMemberByMobile(mobile);
    }

    @Override
    public  Member loginForUserEmail(String Email,String Password){
        Member UserResult = findUserByEmail(Email);
        if(UserResult == null){
            return null;
        }

        if(UserResult.getPassword() == null){
            return null;
        }
        //两个密码不一致
        if(this.CheckPassword(UserResult.getPassword(),Password) == false){
            return null;
        }
        return UserResult;
    }

    @Override
    public  Member loginForUserMobile(Long Mobile,String Password){
        Member UserResult = findUserByMobile(Mobile);

        if(UserResult == null){
            return null;
        }
       
        if(UserResult.getPassword() == null){
            return null;
        }

        // 验证密码是否一致
        if(this.CheckPassword(UserResult.getPassword(),Password) == false){

            return null;
        }

        return UserResult;
    }

    @Override
    public  Member loginForUserMemberUsername(String Username,String Password){
        Member UserResult = findUserByUsername(Username);
        if(UserResult == null){
            return null;
        }
        if(UserResult.getPassword() == null){
            return  null;
        }
        // 验证密码是否一致
        if(this.CheckPassword(UserResult.getPassword(),Password) == false){
            return null;
        }
        return UserResult;
    }

    @Override
    public Object getClientUser(Long UserId) {
        Map<String,Object> Datas = new HashMap<String, Object>();
        Datas.put("id",UserId);
        return  clientService.getUserInfo(Datas);
    }

    @Override
    public int updateByMemberMobile(String password, Long id) {
    int i=0;
    Member user=new Member();
    user.setId(id);

        try {
            password=Md5Encode.md5(password,this.md5key);
            user.setPassword(password);
             i= memberMapper.updateByPrimaryKeySelective(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }


    //验证密码是否正确
    private  boolean CheckPassword(String UserPassword,String Password){
        try {
           boolean Ok = Md5Encode.verify(Password,this.md5key,UserPassword);
           if(Ok == true){
               return  true;
           }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    @Override
    public Member findUserById(Long userId){
        return memberMapper.selectByPrimaryKey(userId);
    }
}

