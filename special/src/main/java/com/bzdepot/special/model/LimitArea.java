package com.bzdepot.special.model;

import java.math.BigDecimal;

public class LimitArea {
    private Long id;

    private BigDecimal oneLongs;

    private BigDecimal oneWight;

    private BigDecimal twoLongs;

    private BigDecimal twoWight;

    private Long sellerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getOneLongs() {
        return oneLongs;
    }

    public void setOneLongs(BigDecimal oneLongs) {
        this.oneLongs = oneLongs;
    }

    public BigDecimal getOneWight() {
        return oneWight;
    }

    public void setOneWight(BigDecimal oneWight) {
        this.oneWight = oneWight;
    }

    public BigDecimal getTwoLongs() {
        return twoLongs;
    }

    public void setTwoLongs(BigDecimal twoLongs) {
        this.twoLongs = twoLongs;
    }

    public BigDecimal getTwoWight() {
        return twoWight;
    }

    public void setTwoWight(BigDecimal twoWight) {
        this.twoWight = twoWight;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}