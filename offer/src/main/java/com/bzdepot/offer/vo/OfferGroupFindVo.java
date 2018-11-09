package com.bzdepot.offer.vo;

import javax.validation.constraints.NotNull;

public class OfferGroupFindVo {
    @NotNull(message = "报价配置主标号不能为空!")
   private Long offer_id;

    public Long getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(Long offer_id) {
        this.offer_id = offer_id;
    }

    @Override
    public String toString() {
        return "OfferGroupFindVo{" +
                "offer_id=" + offer_id +
                '}';
    }
}
