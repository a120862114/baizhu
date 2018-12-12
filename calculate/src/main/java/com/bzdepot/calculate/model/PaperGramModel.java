package com.bzdepot.calculate.model;

import java.math.BigDecimal;

public class PaperGramModel {
    private Long id;

    private Long sellerId;

    private BigDecimal gramNums;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public BigDecimal getGramNums() {
        return gramNums;
    }

    public void setGramNums(BigDecimal gramNums) {
        this.gramNums = gramNums;
    }
}