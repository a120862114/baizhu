package com.bzdepot.mem.service.impl;

import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.mem.fegin.FRegisterService;
import com.bzdepot.mem.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    private final static Logger loger = LoggerFactory.getLogger(RegisterServiceImpl.class);
    @Autowired
    private FRegisterService registerService;

    public Object validRegister(String mobileEmail,String code){
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("mobileEmail",mobileEmail);
        data.put("code",code);
        Object res =registerService.getRegisterInfo(data);
        Integer status = null;
        try {
            status = (Integer) JsonReturn.Parse(res).get("error_code");
        }catch (Exception e){
            loger.error(e.getMessage());
            return null;
        }
        return status;
    }
}
