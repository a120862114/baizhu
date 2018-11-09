package com.bzdepot.transport.model;

import java.math.BigDecimal;

public class BlockPrice {
    private Long id;

    private Long sellerId;

    private Long companyId;

    private Long regionId;

    private Integer blockId;

    private BigDecimal weightStart;

    private BigDecimal weightEnd;

    private BigDecimal startPrice;

    private BigDecimal endPrice;

    private Byte countType;

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public BigDecimal getWeightStart() {
        return weightStart;
    }

    public void setWeightStart(BigDecimal weightStart) {
        this.weightStart = weightStart;
    }

    public BigDecimal getWeightEnd() {
        return weightEnd;
    }

    public void setWeightEnd(BigDecimal weightEnd) {
        this.weightEnd = weightEnd;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
    }

    public Byte getCountType() {
        return countType;
    }

    public void setCountType(Byte countType) {
        this.countType = countType;
    }

    @Override
    public String toString() {
        return "BlockPrice{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", companyId=" + companyId +
                ", regionId=" + regionId +
                ", blockId=" + blockId +
                ", weightStart=" + weightStart +
                ", weightEnd=" + weightEnd +
                ", startPrice=" + startPrice +
                ", endPrice=" + endPrice +
                ", countType=" + countType +
                '}';
    }
}