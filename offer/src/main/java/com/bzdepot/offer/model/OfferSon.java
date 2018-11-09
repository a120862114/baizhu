package com.bzdepot.offer.model;

import java.math.BigDecimal;
import java.util.List;

public class OfferSon {
    private Long id;

    private Byte offerTypes;

    private Byte isDesign;

    private Byte isFreeShipping;

    private Long sellerId;

    private Long classId;

    private Long textureId;

    private BigDecimal postageProfit;

    private String exceptCityIds;

    private List<OfferSonExpress> offerSonExpressesData;

    public List<OfferSonExpress> getOfferSonExpressesData() {
        return offerSonExpressesData;
    }

    public void setOfferSonExpressesData(List<OfferSonExpress> offerSonExpressesData) {
        this.offerSonExpressesData = offerSonExpressesData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getOfferTypes() {
        return offerTypes;
    }

    public void setOfferTypes(Byte offerTypes) {
        this.offerTypes = offerTypes;
    }

    public Byte getIsDesign() {
        return isDesign;
    }

    public void setIsDesign(Byte isDesign) {
        this.isDesign = isDesign;
    }

    public Byte getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(Byte isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getTextureId() {
        return textureId;
    }

    public void setTextureId(Long textureId) {
        this.textureId = textureId;
    }

    public BigDecimal getPostageProfit() {
        return postageProfit;
    }

    public void setPostageProfit(BigDecimal postageProfit) {
        this.postageProfit = postageProfit;
    }

    public String getExceptCityIds() {
        return exceptCityIds;
    }

    public void setExceptCityIds(String exceptCityIds) {
        this.exceptCityIds = exceptCityIds == null ? null : exceptCityIds.trim();
    }

    @Override
    public String toString() {
        return "OfferSon{" +
                "id=" + id +
                ", offerTypes=" + offerTypes +
                ", isDesign=" + isDesign +
                ", isFreeShipping=" + isFreeShipping +
                ", sellerId=" + sellerId +
                ", classId=" + classId +
                ", textureId=" + textureId +
                ", postageProfit=" + postageProfit +
                ", exceptCityIds='" + exceptCityIds + '\'' +
                ", offerSonExpressesData=" + offerSonExpressesData +
                '}';
    }
}