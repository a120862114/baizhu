package com.bzdepot.profit.model;

import java.math.BigDecimal;

public class Constitute {
    private Long id;

    private Byte types;

    private Byte isS;

    private Byte isY;

    private Byte isF;

    private BigDecimal srate;

    private Long offerId;

    private Long sellerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getTypes() {
        return types;
    }

    public void setTypes(Byte types) {
        this.types = types;
    }

    public Byte getIsS() {
        return isS;
    }

    public void setIsS(Byte isS) {
        this.isS = isS;
    }

    public Byte getIsY() {
        return isY;
    }

    public void setIsY(Byte isY) {
        this.isY = isY;
    }

    public Byte getIsF() {
        return isF;
    }

    public void setIsF(Byte isF) {
        this.isF = isF;
    }

    public BigDecimal getSrate() {
        return srate;
    }

    public void setSrate(BigDecimal srate) {
        this.srate = srate;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "Constitute{" +
                "id=" + id +
                ", types=" + types +
                ", isS=" + isS +
                ", isY=" + isY +
                ", isF=" + isF +
                ", srate=" + srate +
                ", offerId=" + offerId +
                ", sellerId=" + sellerId +
                '}';
    }
}