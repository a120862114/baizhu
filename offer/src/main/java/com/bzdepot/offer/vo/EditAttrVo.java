package com.bzdepot.offer.vo;

import java.math.BigDecimal;
import java.util.Arrays;

public class EditAttrVo {

    private Long[] ids;

    private String attrKey;

    private BigDecimal attrValue;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

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

    @Override
    public String toString() {
        return "EditAttrVo{" +
                "ids=" + Arrays.toString(ids) +
                ", attrKey='" + attrKey + '\'' +
                ", attrValue=" + attrValue +
                '}';
    }
}
