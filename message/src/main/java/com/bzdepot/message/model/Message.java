package com.bzdepot.message.model;

public class Message {
    //private String sms_type;
    private String content;
    //private String code;
    //email
    private String from_name;
    private String toEmail;
    private String sms_sendType;
    //email config
    private String hostName;
    private String emailName;
    private String emailPass;
    //sms
    private String mobile;
    //sms config
    private String accessKeyId;
    private String accesskeySecret;
    private String signName;
    private String registTempleteCode;
    private String identifyingTempleteCode;
    private String username;
    private String password;

    public String getSms_sendType() {
        return sms_sendType;
    }

    public void setSms_sendType(String sms_sendType) {
        this.sms_sendType = sms_sendType;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public String getEmailPass() {
        return emailPass;
    }

    public void setEmailPass(String emailPass) {
        this.emailPass = emailPass;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccesskeySecret() {
        return accesskeySecret;
    }

    public void setAccesskeySecret(String accesskeySecret) {
        this.accesskeySecret = accesskeySecret;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getRegistTempleteCode() {
        return registTempleteCode;
    }

    public void setRegistTempleteCode(String registTempleteCode) {
        this.registTempleteCode = registTempleteCode;
    }

    public String getIdentifyingTempleteCode() {
        return identifyingTempleteCode;
    }

    public void setIdentifyingTempleteCode(String identifyingTempleteCode) {
        this.identifyingTempleteCode = identifyingTempleteCode;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
}
