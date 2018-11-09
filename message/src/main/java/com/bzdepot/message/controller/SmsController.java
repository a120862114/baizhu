package com.bzdepot.message.controller;

import com.alibaba.fastjson.JSONObject;
import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.message.model.EmailConfig;
import com.bzdepot.message.model.Message;
import com.bzdepot.message.model.SmsConfig;
import com.bzdepot.message.util.SmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value="/sms/")
public class SmsController {
    private final static Logger logger = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private SmsConfig smsConfig;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @RequestMapping(value = "/send",method = RequestMethod.POST)
    public  Object SmsSend(HttpServletRequest request, @Valid @ModelAttribute("message") Message message){
        int code=(int)((Math.random()*9+1)*100000);
        redisTemplate.opsForValue().set(message.getMobile(),code,15,TimeUnit.SECONDS);

        message.setHostName(emailConfig.getHostName());
        message.setEmailName(emailConfig.getEmailName());
        message.setEmailPass(emailConfig.getEmailPass());
        message.setAccessKeyId(smsConfig.getAccessKeyId());
        message.setAccesskeySecret(smsConfig.getAccesskeySecret());
        message.setSignName(smsConfig.getSignName());
        message.setRegistTempleteCode(smsConfig.getRegistTempleteCode());
        message.setIdentifyingTempleteCode(smsConfig.getIdentifyingTempleteCode());
        JSONObject json=new JSONObject();
        int ret=10010;
        String msg="短信发送成功";
        String mobile=message.getMobile();
        message.setMobile(mobile);
        message.setContent(String.valueOf(code));
        boolean flag= SmsSender.sendSms(message);
        if(flag) ret=0;
        if(ret!=0) msg="短信发送失败";
        return JsonReturn.SetMsg(ret,msg,"");
    }
}
