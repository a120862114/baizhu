package com.bzdepot.profit.model;

import java.math.BigDecimal;

public class Profit {
    private Long id;

    private Long offerId;

    private Byte types;

    private BigDecimal startValue;

    private BigDecimal endValue;

    private Long levelId;

    private BigDecimal profitRate;

    private Byte rulerType;

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

    public Byte getRulerType() {
        return rulerType;
    }

    public void setRulerType(Byte rulerType) {
        this.rulerType = rulerType;
    }
}