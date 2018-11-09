package com.bzdepot.message.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.bzdepot.message.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class SmsSender {
    /**引入logger4j*/
    protected final static Logger logger = LoggerFactory.getLogger(SmsSender.class);
    //加载配置文件
    protected static Properties pro=null;
    // 产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";
    //此处需要替换成开发者自己的AK(在阿里云控制台寻找)
    private static String  accessKeyId;
    private static String  accesskeySecret;
    private static String  signName;
    private static String identifyingTempleteCode;
    private static String registTempleteCode;
    /**
     * 初始化阿里云短信服务相关配置*/
    /*static{
        if(pro==null){
            pro=new Properties();
            try {
                pro=new Properties();
                pro.load(ClassLoader.getSystemResourceAsStream("sms.properties"));
                accessKeyId=pro.getProperty("accessKeyId");
                accesskeySecret=pro.getProperty("accesskeySecret","accesskeySecret");
                signName=pro.getProperty("signName","signName");
                identifyingTempleteCode=pro.getProperty("identifyingTempleteCode","identifyingTempleteCode");
                registTempleteCode=pro.getProperty("registTempleteCode","registTempleteCode");
                System.out.println(accessKeyId);
            } catch (IOException e) {
                logger.error("初始化阿里云配置ERROR:  "+e.getMessage());
            }
        }
    }*/
    /**
     * @param mobile 发送手机号码
     * @param templateCode 短信模板号码
     * @param templateParam 短信模板*/
    public static SendSmsResponse sendSms(String mobile, String templateParam, String templateCode)
            throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accesskeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();

        // 必填:待发送手机号
        request.setPhoneNumbers(mobile);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);

        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(templateParam);
        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
    public static SendSmsResponse sendSmsMessage(Message message, String templateParam, String templateCode)
            throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", message.getAccessKeyId(), message.getAccesskeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();

        // 必填:待发送手机号
        request.setPhoneNumbers(message.getMobile());
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(message.getSignName());
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);

        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(templateParam);
        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");
        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        // hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
    public static SendSmsResponse sendIdentifyingCode(Message message) {
        try {
            return sendSmsMessage(message, "{\"code\":\"" + message.getContent() + "\"}", message.getIdentifyingTempleteCode());
        } catch (ClientException e) {
            logger.error("短信发送异常: "+e.getMessage());
            return null;
        }
    }

    public static SendSmsResponse sendNewUserNotice(Message message) {
        try {
            return sendSmsMessage(message, "{\"username\":\"" + message.getUsername() + "\", \"password\":\"" + message.getPassword() + "\"}",
                    message.getRegistTempleteCode());
        } catch (ClientException e) {
            logger.error("短信发送异常"+e.getMessage());
            return null;
        }
    }

    public static boolean sendSms(Message message)
    {
        boolean flag=false;
        SendSmsResponse response=null;
        if(message.getIdentifyingTempleteCode()!=null && !"".equals(message.getIdentifyingTempleteCode())){
            response=sendIdentifyingCode(message);
        }
        if(response==null){
            if(message.getUsername()!=null && !"".equals(message.getUsername())
                    && message.getPassword()!=null && !"".equals(message.getPassword())) {
                response = sendNewUserNotice(message);
            }
        }

        if(response!=null){
            if(response.getCode()!=null&&"OK".equals(response.getCode())){
                flag=true;
            }
        }
        return flag;
    }
}
