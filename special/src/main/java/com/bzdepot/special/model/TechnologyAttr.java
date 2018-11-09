package com.bzdepot.special.model;

import java.math.BigDecimal;

public class TechnologyAttr {
    private Long id;

    private Long tId;

    private String category;

    private Integer hsInNum;

    private Integer hsHsNum;

    private Integer hsMdNum;

    private Integer hsMdHsNum;

    private Byte isEdition;

    private BigDecimal onMoney;

    private Integer machiningNums;

    private Byte calculationType;

    private BigDecimal calculationNumsMoney;

    private BigDecimal calculationMjMoney;

    private Byte setLimitType;

    private BigDecimal limitSizeMaxLong;

    private BigDecimal limitSizeMaxWight;

    private BigDecimal limitSizeMinLong;

    private BigDecimal limitSizeMinWight;

    private Long limitAreaId;

    private Byte status;

    private Long createTime;

    private Long updateTime;

    private Long sellerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Integer getHsInNum() {
        return hsInNum;
    }

    public void setHsInNum(Integer hsInNum) {
        this.hsInNum = hsInNum;
    }

    public Integer getHsHsNum() {
        return hsHsNum;
    }

    public void setHsHsNum(Integer hsHsNum) {
        this.hsHsNum = hsHsNum;
    }

    public Integer getHsMdNum() {
        return hsMdNum;
    }

    public void setHsMdNum(Integer hsMdNum) {
        this.hsMdNum = hsMdNum;
    }

    public Integer getHsMdHsNum() {
        return hsMdHsNum;
    }

    public void setHsMdHsNum(Integer hsMdHsNum) {
        this.hsMdHsNum = hsMdHsNum;
    }

    public Byte getIsEdition() {
        return isEdition;
    }

    public void setIsEdition(Byte isEdition) {
        this.isEdition = isEdition;
    }

    public BigDecimal getOnMoney() {
        return onMoney;
    }

    public void setOnMoney(BigDecimal onMoney) {
        this.onMoney = onMoney;
    }

    public Integer getMachiningNums() {
        return machiningNums;
    }

    public void setMachiningNums(Integer machiningNums) {
        this.machiningNums = machiningNums;
    }

    public Byte getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(Byte calculationType) {
        this.calculationType = calculationType;
    }

    public BigDecimal getCalculationNumsMoney() {
        return calculationNumsMoney;
    }

    public void setCalculationNumsMoney(BigDecimal calculationNumsMoney) {
        this.calculationNumsMoney = calculationNumsMoney;
    }

    public BigDecimal getCalculationMjMoney() {
        return calculationMjMoney;
    }

    public void setCalculationMjMoney(BigDecimal calculationMjMoney) {
        this.calculationMjMoney = calculationMjMoney;
    }

    public Byte getSetLimitType() {
        return setLimitType;
    }

    public void setSetLimitType(Byte setLimitType) {
        this.setLimitType = setLimitType;
    }

    public BigDecimal getLimitSizeMaxLong() {
        return limitSizeMaxLong;
    }

    public void setLimitSizeMaxLong(BigDecimal limitSizeMaxLong) {
        this.limitSizeMaxLong = limitSizeMaxLong;
    }

    public BigDecimal getLimitSizeMaxWight() {
        return limitSizeMaxWight;
    }

    public void setLimitSizeMaxWight(BigDecimal limitSizeMaxWight) {
        this.limitSizeMaxWight = limitSizeMaxWight;
    }

    public BigDecimal getLimitSizeMinLong() {
        return limitSizeMinLong;
    }

    public void setLimitSizeMinLong(BigDecimal limitSizeMinLong) {
        this.limitSizeMinLong = limitSizeMinLong;
    }

    public BigDecimal getLimitSizeMinWight() {
        return limitSizeMinWight;
    }

    public void setLimitSizeMinWight(BigDecimal limitSizeMinWight) {
        this.limitSizeMinWight = limitSizeMinWight;
    }

    public Long getLimitAreaId() {
        return limitAreaId;
    }

    public void setLimitAreaId(Long limitAreaId) {
        this.limitAreaId = limitAreaId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "TechnologyAttr{" +
                "id=" + id +
                ", tId=" + tId +
                ", category='" + category + '\'' +
                ", hsInNum=" + hsInNum +
                ", hsHsNum=" + hsHsNum +
                ", hsMdNum=" + hsMdNum +
                ", hsMdHsNum=" + hsMdHsNum +
                ", isEdition=" + isEdition +
                ", onMoney=" + onMoney +
                ", machiningNums=" + machiningNums +
                ", calculationType=" + calculationType +
                ", calculationNumsMoney=" + calculationNumsMoney +
                ", calculationMjMoney=" + calculationMjMoney +
                ", setLimitType=" + setLimitType +
                ", limitSizeMaxLong=" + limitSizeMaxLong +
                ", limitSizeMaxWight=" + limitSizeMaxWight +
                ", limitSizeMinLong=" + limitSizeMinLong +
                ", limitSizeMinWight=" + limitSizeMinWight +
                ", limitAreaId=" + limitAreaId +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", sellerId=" + sellerId +
                '}';
    }
}