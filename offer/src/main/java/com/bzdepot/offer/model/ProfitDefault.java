package com.bzdepot.offer.model;

public class ProfitDefault {
    private Long id;

    private Long offerId;

    private Byte defaultType;

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

    public Byte getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(Byte defaultType) {
        this.defaultType = defaultType;
    }
}