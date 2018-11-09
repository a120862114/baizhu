package com.bzdepot.offer.model;

import java.math.BigDecimal;

public class OfferAttr {
    private Long id;

    private Long offerId;

    private Long offerGroupId;

    private String attrKey;

    private BigDecimal attrValue;

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

    public String getAttrKey() {
        return attrKey;
    }

    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey == null ? null : attrKey.trim();
    }

    public BigDecimal getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(BigDecimal attrValue) {
        this.attrValue = attrValue;
    }

    @Override
    public String toString() {
        return "OfferAttr{" +
                "id=" + id +
                ", offerId=" + offerId +
                ", offerGroupId=" + offerGroupId +
                ", attrKey='" + attrKey + '\'' +
                ", attrValue=" + attrValue +
                '}';
    }
}