package com.bzdepot.user.model;

import java.math.BigDecimal;

public class ClientUser {
    private Long userId;

    private BigDecimal money;

    private Long sellerId;

    private Long levelId;

    private Long createTime;

    private Long updateTime;

    private String headImgId;

    private String nickname;

    private Byte userType;

    private String alimsg;

    private String fullName;

    private String content;

    private String bankName;

    private String bankAccess;

    private String bankCardNumber;

    private String bankFullName;

    private String alipayAccount;

    private String alipayFullName;

    private String classIds;

    public String getAlimsg() {
        return alimsg;
    }

    public void setAlimsg(String alimsg) {
        this.alimsg = alimsg;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccess() {
        return bankAccess;
    }

    public void setBankAccess(String bankAccess) {
        this.bankAccess = bankAccess;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getBankFullName() {
        return bankFullName;
    }

    public void setBankFullName(String bankFullName) {
        this.bankFullName = bankFullName;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public String getAlipayFullName() {
        return alipayFullName;
    }

    public void setAlipayFullName(String alipayFullName) {
        this.alipayFullName = alipayFullName;
    }

    public String getClassIds() {
        return classIds;
    }

    public void setClassIds(String classIds) {
        this.classIds = classIds;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getHeadImgId() {
        return headImgId;
    }

    public void setHeadImgId(String headImgId) {
        this.headImgId = headImgId == null ? null : headImgId.trim();
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "ClientUser{" +
                "userId=" + userId +
                ", money=" + money +
                ", sellerId=" + sellerId +
                ", levelId=" + levelId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", headImgId='" + headImgId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userType=" + userType +
                '}';
    }
}