package com.bzdepot.calculate.model;

import java.math.BigDecimal;

public class PaperDrumModel {
    private Long id;

    private BigDecimal drum;

    private Long sellerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDrum() {
        return drum;
    }

    public void setDrum(BigDecimal drum) {
        this.drum = drum;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}