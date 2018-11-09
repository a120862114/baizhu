package com.bzdepot.offer.vo;

import java.math.BigDecimal;

public class OfferAttrVo {

    private Long id;

    private String attrKey;

    private BigDecimal attrValue;

    public String getAttrKey() {
        return attrKey;
    }

    public void setAttrKey(String attrKey) {
        this.attrKey = attrKey;
    }

    public BigDecimal getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(BigDecimal attrValue) {
        this.attrValue = attrValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OfferAttrVo{" +
                "id=" + id +
                ", attrKey='" + attrKey + '\'' +
                ", attrValue=" + attrValue +
                '}';
    }
}
