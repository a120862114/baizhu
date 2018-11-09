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
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value="/regMes/")
public class RegistMesController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping(value = "/saveCode",method = RequestMethod.POST)
    public Object saveCode(HttpServletRequest request,@Valid @ModelAttribute("mobileEmail") String userKey){
        int code=(int)((Math.random()*9+1)*100000);

        //redisTemplate.opsForHash().put("mobileEmail",userKey,code);
        //redisTemplate.opsForValue().set(userKey,code);
        //redisTemplate.expire(userKey,5,TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(userKey,code,15,TimeUnit.SECONDS);

        //Map<String, Object> userCodeMap =(Map)redisTemplate.opsForHash().entries(userKey);
       // Map<String, Object> userMap =(Map)redisTemplate.opsForHash().entries("mobileEmail");

        return JsonReturn.SetMsg(0,"","");
    }

    @RequestMapping(value = "/getCode",method = RequestMethod.POST)
    public Object getCode(HttpServletRequest request,@Valid @ModelAttribute("mobileEmail") String userKey){

        //Map<String, Object> userCodeMap =(Map)redisTemplate.opsForHash().entries("mobileEmail");

       // int data=(Integer) redisTemplate.opsForValue().get(userKey);
        int data=-1;
        Object obj =redisTemplate.opsForValue().get(userKey);
        if(obj!=null) data=(Integer) obj;
        return JsonReturn.SetMsg(0,"",data);
    }

    @RequestMapping(value = "/validCode",method = RequestMethod.POST)
    public Object validCode(HttpServletRequest request,@Valid @ModelAttribute("mobileEmail") String userKey
                            ,@Valid @ModelAttribute("code") Integer code){
        int ret=10010;
        String message="";
        int data=-1;

        Object obj =redisTemplate.opsForValue().get(userKey);
        if(obj!=null) data=(Integer) obj;
        if(data==code) ret =0;

        if(ret!=0)
            message="验证码错误";
        else
            message="验证码正确";
        return JsonReturn.SetMsg(ret,"",message);
    }

}
