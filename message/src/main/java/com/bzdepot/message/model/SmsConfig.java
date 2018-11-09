package com.bzdepot.message.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="sms")
public class SmsConfig {
    private String accessKeyId;
    private String accesskeySecret;
    private String signName;
    private String registTempleteCode;
    private String identifyingTempleteCode;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
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

    public String getAccesskeySecret() {
        return accesskeySecret;
    }

    public void setAccesskeySecret(String accesskeySecret) {
        this.accesskeySecret = accesskeySecret;
    }

    public String getIdentifyingTempleteCode() {
        return identifyingTempleteCode;
    }

    public void setIdentifyingTempleteCode(String identifyingTempleteCode) {
        this.identifyingTempleteCode = identifyingTempleteCode;
    }
}
