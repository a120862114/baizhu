package com.bzdepot.offer.vo;

import java.math.BigDecimal;
import java.util.List;

public class OfferGroupVo {

    private Long id;

    private BigDecimal smoney;

    private Byte isSend;

    private Long sendSellerId;

    private List<OfferAttrVo> attrarray; //属性的键值对数组

    private List<OfferDetailVo> detailarray; //价格的键值对数据

    public BigDecimal getSmoney() {
        return smoney;
    }

    public void setSmoney(BigDecimal smoney) {
        this.smoney = smoney;
    }

    public Byte getIsSend() {
        return isSend;
    }

    public void setIsSend(Byte isSend) {
        this.isSend = isSend;
    }

    public Long getSendSellerId() {
        return sendSellerId;
    }

    public void setSendSellerId(Long sendSellerId) {
        this.sendSellerId = sendSellerId;
    }

    public List<OfferAttrVo> getAttrarray() {
        return attrarray;
    }

    public void setAttrarray(List<OfferAttrVo> attrarray) {
        this.attrarray = attrarray;
    }

    public List<OfferDetailVo> getDetailarray() {
        return detailarray;
    }

    public void setDetailarray(List<OfferDetailVo> detailarray) {
        this.detailarray = detailarray;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OfferGroupVo{" +
                "id=" + id +
                ", smoney=" + smoney +
                ", isSend=" + isSend +
                ", sendSellerId=" + sendSellerId +
                ", attrarray=" + attrarray +
                ", detailarray=" + detailarray +
                '}';
    }
}
