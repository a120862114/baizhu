package com.bzdepot.profit.model;

import java.math.BigDecimal;

public class ProfitUltimate {
    private Long id;

    private Long classId;

    private Long textureId;

    private Long gramId;

    private Byte types;

    private BigDecimal startValue;

    private BigDecimal endValue;

    private Long levelId;

    private BigDecimal profitRate;

    private Long sellerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getTextureId() {
        return textureId;
    }

    public void setTextureId(Long textureId) {
        this.textureId = textureId;
    }

    public Byte getTypes() {
        return types;
    }

    public void setTypes(Byte types) {
        this.types = types;
    }

    public BigDecimal getStartValue() {
        return startValue;
    }

    public void setStartValue(BigDecimal startValue) {
        this.startValue = startValue;
    }

    public BigDecimal getEndValue() {
        return endValue;
    }

    public void setEndValue(BigDecimal endValue) {
        this.endValue = endValue;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getGramId() {
        return gramId;
    }

    public void setGramId(Long gramId) {
        this.gramId = gramId;
    }

    @Override
    public String toString() {
        return "ProfitUltimate{" +
                "id=" + id +
                ", classId=" + classId +
                ", textureId=" + textureId +
                ", gramId=" + gramId +
                ", types=" + types +
                ", startValue=" + startValue +
                ", endValue=" + endValue +
                ", levelId=" + levelId +
                ", profitRate=" + profitRate +
                ", sellerId=" + sellerId +
                '}';
    }
}