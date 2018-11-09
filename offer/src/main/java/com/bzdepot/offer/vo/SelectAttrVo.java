package com.bzdepot.offer.vo;

import java.math.BigDecimal;

public class SelectAttrVo {

    private String attr_key;

    private BigDecimal attr_value;

    public String getAttr_key() {
        return attr_key;
    }

    public void setAttr_key(String attr_key) {
        this.attr_key = attr_key;
    }

    public BigDecimal getAttr_value() {
        return attr_value;
    }

    public void setAttr_value(BigDecimal attr_value) {
        this.attr_value = attr_value;
    }

    @Override
    public String toString() {
        return "SelectAttrVo{" +
                "attr_key='" + attr_key + '\'' +
                ", attr_value=" + attr_value +
                '}';
    }
}
