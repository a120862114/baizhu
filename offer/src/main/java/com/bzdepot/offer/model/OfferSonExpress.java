package com.bzdepot.offer.model;

import java.math.BigDecimal;

public class OfferSonExpress {
    private Long id;

    private Long offerSonId;

    private Long cityId;

    private BigDecimal rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfferSonId() {
        return offerSonId;
    }

    public void setOfferSonId(Long offerSonId) {
        this.offerSonId = offerSonId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}