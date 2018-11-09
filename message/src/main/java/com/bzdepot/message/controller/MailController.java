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
    public  Object SmsemailSend(HttpServletRequest request, @Valid @ModelAttribute("message") Message message){
        int code=(int)((Math.random()*9+1)*100000);

        redisTemplate.opsForValue().set(message.getToEmail(),code,15,TimeUnit.SECONDS);

        message.setHostName(emailConfig.getHostName());
        message.setEmailName(emailConfig.getEmailName());
        message.setEmailPass(emailConfig.getEmailPass());
        //
        message.setAccessKeyId(smsConfig.getAccessKeyId());
        message.setAccesskeySecret(smsConfig.getAccesskeySecret());
        message.setSignName(smsConfig.getSignName());
        message.setRegistTempleteCode(smsConfig.getRegistTempleteCode());
        message.setIdentifyingTempleteCode(smsConfig.getIdentifyingTempleteCode());
       // JSONObject json=new JSONObject();
        int ret=10010;
        if("1".equals(message.getSms_sendType())){
            //单独发邮件
            if(EmailSender.SendEmail(message)){
                //json.put("msg","success");
                ret=0;
            }
        }else{
            //群发邮件
            if(EmailSender.SendEmails(message)){
               // json.put("msg","error");
                ret=0;
            }
        }

        return JsonReturn.SetMsg(ret,"","");


    }

    public  Object SmsemailSend_bak(@RequestBody String data){
        System.out.println(data);
        JSONObject json=new JSONObject();
        JSONObject jsonParam= JSONObject.parseObject(data);
        logger.info("jsonParam"+jsonParam.toJSONString());
        if("1".equals(jsonParam.get("SMS_TYPE"))){
            if("1".equals(jsonParam.getString("SMS_SENDTYPE"))){
                //单独发邮件
                if(EmailSender.SendEmail(jsonParam)){
                    json.put("msg","success");
                }
            }
            else{
                //群发邮件
                if(EmailSender.SendEmails(jsonParam)){
                    json.put("msg","error");
                }
            }
        }
        if("2".equals(jsonParam.getString("SMS_TYPE"))){
            String mobile=jsonParam.getString("ToEmail");
            String content=jsonParam.getString("CONTENT");
            //boolean flag= SmsSender.sendSms(mobile,content);
            //json.put("msg",flag);

        }
        return json;
    }
}
