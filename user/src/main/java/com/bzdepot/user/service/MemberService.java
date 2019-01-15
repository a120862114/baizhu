package com.bzdepot.user.service;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.user.fegin.ClientService;
import com.bzdepot.user.mapper.MemberMapper;
import com.bzdepot.user.model.ClientUser;
import com.bzdepot.user.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberService {

    private final static Logger loger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ClientService clientService;

    /**
     * 修改完善用户信息
     */
    @Transactional
    public int updateUserInfo(Member member){
        int Code = -1;
        try {
            Code = memberMapper.updateByPrimaryKeySelective(member);
            if(Code <= 0){
                throw new RuntimeException("更新用户主信息失败!");
            }
            ClientUser clientUser = member.getClientUser();
            clientUser.setUserId(member.getId());
            clientUser.setHeadImgId(member.getHeadImgId());
            clientUser.setNickname(member.getNickname());
            Code = this.updateClientUserInfo(clientUser);
            if(Code <= 0){
                throw new RuntimeException("更新用户详细信息失败!");
            }
        }catch (Exception e){
            e.printStackTrace();
            loger.error(e.toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return Code;
    }

    /**
     * 调用远程服务完成用户详细资料补充
     * @param clientUser
     * @return
     */
    public int updateClientUserInfo(ClientUser clientUser){
        Map<String,Object> Datas = new HashMap<String, Object>();
        Datas.put("userId",clientUser.getUserId());
        Datas.put("sellerId",clientUser.getSellerId());
        Datas.put("levelId",clientUser.getLevelId());
        Datas.put("updateTime",new Date().getTime());
        Datas.put("headImgId",clientUser.getHeadImgId());
        Datas.put("nickname",clientUser.getNickname());
        Datas.put("userType",clientUser.getUserType());
        Datas.put("alimsg",clientUser.getAlimsg());
        Datas.put("fullName",clientUser.getFullName());
        Datas.put("content",clientUser.getContent());
        Datas.put("bankName",clientUser.getBankName());
        Datas.put("bankAccess",clientUser.getBankAccess());
        Datas.put("bankCardNumber",clientUser.getBankCardNumber());
        Datas.put("bankFullName",clientUser.getBankFullName());
        Datas.put("alipayAccount",clientUser.getAlipayAccount());
        Datas.put("alipayFullName",clientUser.getAlipayFullName());
        Datas.put("classIds",clientUser.getClassIds());
        Object res = clientService.UpdateUserInfo(Datas);
        Integer Error = 10011;
        try {
            Map<String,Object> resMap = JsonReturn.Parse(res);
            Error = (Integer) resMap.get("error_code");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(Error == 0){
            return 1;
        }
        return -1;
    }
}
