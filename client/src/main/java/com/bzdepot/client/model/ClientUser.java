package com.bzdepot.client.model;

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