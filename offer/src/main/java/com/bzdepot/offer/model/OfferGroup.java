package com.bzdepot.offer.model;

import java.math.BigDecimal;
import java.util.List;

public class OfferGroup {
    private Long id;

    private Long offerId;

    private BigDecimal smoney;

    private Byte isSend;

    private Long sendSellerId;

    private List<OfferAttr> offer_attr;

    private List<OfferDetail> offer_detail;

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

    public List<OfferAttr> getOffer_attr() {
        return offer_attr;
    }

    public void setOffer_attr(List<OfferAttr> offer_attr) {
        this.offer_attr = offer_attr;
    }

    public List<OfferDetail> getOffer_detail() {
        return offer_detail;
    }

    public void setOffer_detail(List<OfferDetail> offer_detail) {
        this.offer_detail = offer_detail;
    }

    @Override
    public String toString() {
        return "OfferGroup{" +
                "id=" + id +
                ", offerId=" + offerId +
                ", smoney=" + smoney +
                ", isSend=" + isSend +
                ", sendSellerId=" + sendSellerId +
                ", offer_attr=" + offer_attr +
                ", offer_detail=" + offer_detail +
                '}';
    }
}