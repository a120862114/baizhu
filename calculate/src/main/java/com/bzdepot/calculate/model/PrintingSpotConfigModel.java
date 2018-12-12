package com.bzdepot.calculate.model;

import java.math.BigDecimal;

public class PrintingSpotConfigModel {
    private Long id;

    private Long sellerId;

    private Long printingCostId;

    private Long spotColorId;

    private Byte status;

    private Integer inNums;

    private BigDecimal inMoney;

    private Integer moreNums;

    private BigDecimal moreMoney;

    private SpotColorModel colorDetail;

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

    public Long getPrintingCostId() {
        return printingCostId;
    }

    public void setPrintingCostId(Long printingCostId) {
        this.printingCostId = printingCostId;
    }

    public Long getSpotColorId() {
        return spotColorId;
    }

    public void setSpotColorId(Long spotColorId) {
        this.spotColorId = spotColorId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getInNums() {
        return inNums;
    }

    public void setInNums(Integer inNums) {
        this.inNums = inNums;
    }

    public BigDecimal getInMoney() {
        return inMoney;
    }

    public void setInMoney(BigDecimal inMoney) {
        this.inMoney = inMoney;
    }

    public Integer getMoreNums() {
        return moreNums;
    }

    public void setMoreNums(Integer moreNums) {
        this.moreNums = moreNums;
    }

    public BigDecimal getMoreMoney() {
        return moreMoney;
    }

    public void setMoreMoney(BigDecimal moreMoney) {
        this.moreMoney = moreMoney;
    }

    public SpotColorModel getColorDetail() {
        return colorDetail;
    }

    public void setColorDetail(SpotColorModel colorDetail) {
        this.colorDetail = colorDetail;
    }
}