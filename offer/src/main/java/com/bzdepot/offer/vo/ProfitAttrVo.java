package com.bzdepot.offer.vo;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProfitAttrVo {

    private Long id;

    @NotNull(message = "开始范围不能为空!")
    private BigDecimal startValue;

    @NotNull(message = "结束范围不能为空!")
    private BigDecimal endValue;

    @NotNull(message = "等级编号不能为空!")
    private Long levelId;

    @NotNull(message = "利润利率不能为空!")
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
        return "ProfitAttrVo{" +
                "id=" + id +
                ", startValue=" + startValue +
                ", endValue=" + endValue +
                ", levelId=" + levelId +
                ", profitRate=" + profitRate +
                '}';
    }
}
