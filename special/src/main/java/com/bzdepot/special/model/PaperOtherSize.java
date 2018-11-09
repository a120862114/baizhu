package com.bzdepot.special.model;

import java.math.BigDecimal;

public class PaperOtherSize {
    private Long id;

    private BigDecimal sizeOne;

    private BigDecimal sizeTwo;

    private Long sellerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSizeOne() {
        return sizeOne;
    }

    public void setSizeOne(BigDecimal sizeOne) {
        this.sizeOne = sizeOne;
    }

    public BigDecimal getSizeTwo() {
        return sizeTwo;
    }

    public void setSizeTwo(BigDecimal sizeTwo) {
        this.sizeTwo = sizeTwo;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}