package com.bzdepot.message.controller;

import com.alibaba.fastjson.JSONObject;
import com.bzdepot.common.message.JsonReturn;
import com.bzdepot.message.model.EmailConfig;
import com.bzdepot.message.model.Message;
import com.bzdepot.message.model.SmsConfig;
import com.bzdepot.message.util.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;


@RestController
//@SpringBootConfiguration
@RequestMapping(value="/mail/")
public class MailController {

    private final static Logger logger = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private  EmailConfig emailConfig;
    @Autowired
    private  SmsConfig smsConfig;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping(value = "/send",method = RequestMethod.POST)
    public  Object mailSend(HttpServletRequest request, @Valid @ModelAttribute("message") Message message){
        int code=(int)((Math.random()*9+1)*100000);

        redisTemplate.opsForValue().set(message.getToEmail(),code,3,TimeUnit.MINUTES);

        message.setHostName(emailConfig.getHostName());
        message.setEmailName(emailConfig.getEmailName());
        message.setEmailPass(emailConfig.getEmailPass());

        message.setAccessKeyId(smsConfig.getAccessKeyId());
        message.setAccesskeySecret(smsConfig.getAccesskeySecret());
        message.setSignName(smsConfig.getSignName());
        message.setRegistTempleteCode(smsConfig.getRegistTempleteCode());
        message.setIdentifyingTempleteCode(smsConfig.getIdentifyingTempleteCode());
        message.setContent(String.valueOf(code));

        int ret=10010;
        String msg="邮件发送成功";
        if("1".equals(message.getSms_sendType())){
            //单独发邮件
            if(EmailSender.SendEmail(message)){
                ret=0;
            }
        }else{
            //群发邮件
            if(EmailSender.SendEmails(message)){
               ret=0;
            }
        }
        if(ret!=0)
            msg="邮件发送失败";
        return JsonReturn.SetMsg(ret,msg,"");


    }

}
