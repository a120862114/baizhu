package com.bzdepot.profit.bo;

import java.math.BigDecimal;

public class ProfitUltimateSonBo {

    private Long id;

    private BigDecimal startValue;

    private BigDecimal endValue;

    private Byte types = new Byte("0");

    private Long levelId;

    private BigDecimal profitRate;

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

    public Byte getTypes() {
        return types;
    }

    public void setTypes(Byte types) {
        this.types = types;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProfitUltimateSonBo{" +
                "id=" + id +
                ", startValue=" + startValue +
                ", endValue=" + endValue +
                ", types=" + types +
                ", levelId=" + levelId +
                ", profitRate=" + profitRate +
                '}';
    }
}
