package com.bzdepot.client.model;

import java.math.BigDecimal;

public class UserCollection {
    private Long id;

    private Long offerId;

    private Long groupId;

    private Long sellerId;

    private Long detailId;

    private Long userId;

    private BigDecimal ysMoney;

    private Integer ysNumber;

    private String ysName;

    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getYsMoney() {
        return ysMoney;
    }

    public void setYsMoney(BigDecimal ysMoney) {
        this.ysMoney = ysMoney;
    }

    public Integer getYsNumber() {
        return ysNumber;
    }

    public void setYsNumber(Integer ysNumber) {
        this.ysNumber = ysNumber;
    }

    public String getYsName() {
        return ysName;
    }

    public void setYsName(String ysName) {
        this.ysName = ysName == null ? null : ysName.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}