package com.bzdepot.offer.vo;

import java.math.BigDecimal;

public class OfferDetailVo {
    private Long id;

    private Integer nums;

    private BigDecimal xmoney;

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public BigDecimal getXmoney() {
        return xmoney;
    }

    public void setXmoney(BigDecimal xmoney) {
        this.xmoney = xmoney;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OfferDetailVo{" +
                "id=" + id +
                ", nums=" + nums +
                ", xmoney=" + xmoney +
                '}';
    }
}
