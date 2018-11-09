package com.bzdepot.transport.vo;

import java.math.BigDecimal;

public class BlockPriceVo {

    private Long id;

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
        return "BlockPriceVo{" +
                "id=" + id +
                ", blockId=" + blockId +
                ", weightStart=" + weightStart +
                ", weightEnd=" + weightEnd +
                ", startPrice=" + startPrice +
                ", endPrice=" + endPrice +
                ", countType=" + countType +
                '}';
    }
}
