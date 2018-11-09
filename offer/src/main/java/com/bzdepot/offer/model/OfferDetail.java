package com.bzdepot.offer.model;

import java.math.BigDecimal;

public class OfferDetail {
    private Long id;

    private Long offerId;

    private Long offerGroupId;

    private Integer nums;

    private BigDecimal xmoney;

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

    public Long getOfferGroupId() {
        return offerGroupId;
    }

    public void setOfferGroupId(Long offerGroupId) {
        this.offerGroupId = offerGroupId;
    }

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

    @Override
    public String toString() {
        return "OfferDetail{" +
                "id=" + id +
                ", offerId=" + offerId +
                ", offerGroupId=" + offerGroupId +
                ", nums=" + nums +
                ", xmoney=" + xmoney +
                '}';
    }
}