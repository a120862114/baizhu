package com.bzdepot.special.model;

import java.math.BigDecimal;

public class TechnologyLimit {
    private Long sellerId;

    private Long tClassId;

    private Long tAttrId;

    private Byte lessThanType;

    private BigDecimal sizeStartLongs;

    private BigDecimal sizeStartWidth;

    private Byte greaterThanType;

    private BigDecimal sizeEndLongs;

    private BigDecimal sizeEndWidth;

    private BigDecimal money;

    private Byte unitType;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long gettClassId() {
        return tClassId;
    }

    public void settClassId(Long tClassId) {
        this.tClassId = tClassId;
    }

    public Long gettAttrId() {
        return tAttrId;
    }

    public void settAttrId(Long tAttrId) {
        this.tAttrId = tAttrId;
    }

    public Byte getLessThanType() {
        return lessThanType;
    }

    public void setLessThanType(Byte lessThanType) {
        this.lessThanType = lessThanType;
    }

    public BigDecimal getSizeStartLongs() {
        return sizeStartLongs;
    }

    public void setSizeStartLongs(BigDecimal sizeStartLongs) {
        this.sizeStartLongs = sizeStartLongs;
    }

    public BigDecimal getSizeStartWidth() {
        return sizeStartWidth;
    }

    public void setSizeStartWidth(BigDecimal sizeStartWidth) {
        this.sizeStartWidth = sizeStartWidth;
    }

    public Byte getGreaterThanType() {
        return greaterThanType;
    }

    public void setGreaterThanType(Byte greaterThanType) {
        this.greaterThanType = greaterThanType;
    }

    public BigDecimal getSizeEndLongs() {
        return sizeEndLongs;
    }

    public void setSizeEndLongs(BigDecimal sizeEndLongs) {
        this.sizeEndLongs = sizeEndLongs;
    }

    public BigDecimal getSizeEndWidth() {
        return sizeEndWidth;
    }

    public void setSizeEndWidth(BigDecimal sizeEndWidth) {
        this.sizeEndWidth = sizeEndWidth;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Byte getUnitType() {
        return unitType;
    }

    public void setUnitType(Byte unitType) {
        this.unitType = unitType;
    }
}