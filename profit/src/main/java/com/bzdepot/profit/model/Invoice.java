package com.bzdepot.profit.model;

import java.math.BigDecimal;

public class Invoice {
    private Long id;

    private Long sellerId;

    private Long levelId;

    private Long comanyId;

    private BigDecimal taxRate;

    private Long createTime;

    private Long updateTime;

    private Byte types;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getComanyId() {
        return comanyId;
    }

    public void setComanyId(Long comanyId) {
        this.comanyId = comanyId;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
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

    public Byte getTypes() {
        return types;
    }

    public void setTypes(Byte types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", levelId=" + levelId +
                ", comanyId=" + comanyId +
                ", taxRate=" + taxRate +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", types=" + types +
                '}';
    }
}