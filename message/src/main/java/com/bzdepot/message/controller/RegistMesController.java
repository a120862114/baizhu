package com.bzdepot.message.controller;

import com.bzdepot.common.message.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value="/regMes/")
public class RegistMesController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping(value = "/validCode",method = RequestMethod.POST)
    public Object validCode(HttpServletRequest request,@Valid @ModelAttribute("mobileEmail") String userKey
                            ,@Valid @ModelAttribute("code") Integer code){
        int ret=10010;
        String message="验证码正确";
        int data=-1;

        Object obj =redisTemplate.opsForValue().get(userKey);
        if(obj!=null) data=(Integer) obj;
        if(data==code) ret =0;

        if(ret!=0)
            message="验证码错误";

        return JsonReturn.SetMsg(ret,message,message);
    }


}
