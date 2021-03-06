package com.bzdepot.special.model;

import java.math.BigDecimal;

public class ProductSize {
    private Long id;

    private Long pconfigId;

    private BigDecimal longs;

    private BigDecimal width;

    private BigDecimal height;

    private Long sellerId;

    private Byte isDefault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPconfigId() {
        return pconfigId;
    }

    public void setPconfigId(Long pconfigId) {
        this.pconfigId = pconfigId;
    }

    public BigDecimal getLongs() {
        return longs;
    }

    public void setLongs(BigDecimal longs) {
        this.longs = longs;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "ProductSize{" +
                "id=" + id +
                ", pconfigId=" + pconfigId +
                ", longs=" + longs +
                ", width=" + width +
                ", height=" + height +
                ", sellerId=" + sellerId +
                ", isDefault=" + isDefault +
                '}';
    }
}