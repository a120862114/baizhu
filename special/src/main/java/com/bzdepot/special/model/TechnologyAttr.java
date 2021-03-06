package com.bzdepot.special.model;

import java.math.BigDecimal;
import java.util.List;

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

    private Byte status;

    private Long createTime;

    private Long updateTime;

    private Long sellerId;

    private Byte selectUitType;

    private BigDecimal robotSizeMaxLongs;

    private BigDecimal robotSizeMaxWidth;

    private BigDecimal robotSizeMinLongs;

    private BigDecimal robotSizeMinWidth;

    private Byte limitType;

    private BigDecimal limitNumber;

    private BigDecimal foldingMaxLength;

    private BigDecimal foldingMaxWidth;

    private BigDecimal foldingMinLength;

    private BigDecimal foldingMinWidth;

    private Integer foldingFrequency;

    private BigDecimal foldingClipWidth;

    private BigDecimal foldingClipGram;

    private Byte foldingIsClose;

    private Byte foldingStatus;

    public BigDecimal getFoldingMaxLength() {
        return foldingMaxLength;
    }

    public void setFoldingMaxLength(BigDecimal foldingMaxLength) {
        this.foldingMaxLength = foldingMaxLength;
    }

    public BigDecimal getFoldingMaxWidth() {
        return foldingMaxWidth;
    }

    public void setFoldingMaxWidth(BigDecimal foldingMaxWidth) {
        this.foldingMaxWidth = foldingMaxWidth;
    }

    public BigDecimal getFoldingMinLength() {
        return foldingMinLength;
    }

    public void setFoldingMinLength(BigDecimal foldingMinLength) {
        this.foldingMinLength = foldingMinLength;
    }

    public BigDecimal getFoldingMinWidth() {
        return foldingMinWidth;
    }

    public void setFoldingMinWidth(BigDecimal foldingMinWidth) {
        this.foldingMinWidth = foldingMinWidth;
    }



    public BigDecimal getFoldingClipWidth() {
        return foldingClipWidth;
    }

    public void setFoldingClipWidth(BigDecimal foldingClipWidth) {
        this.foldingClipWidth = foldingClipWidth;
    }

    public BigDecimal getFoldingClipGram() {
        return foldingClipGram;
    }

    public void setFoldingClipGram(BigDecimal foldingClipGram) {
        this.foldingClipGram = foldingClipGram;
    }

    public Byte getFoldingIsClose() {
        return foldingIsClose;
    }

    public void setFoldingIsClose(Byte foldingIsClose) {
        this.foldingIsClose = foldingIsClose;
    }

    private List<TechnologyLimit> limitList;

    private List<TechnologyEditLimit> limitEditList;

    public List<TechnologyEditLimit> getLimitEditList() {
        return limitEditList;
    }

    public void setLimitEditList(List<TechnologyEditLimit> limitEditList) {
        this.limitEditList = limitEditList;
    }

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

    public Byte getSelectUitType() {
        return selectUitType;
    }

    public void setSelectUitType(Byte selectUitType) {
        this.selectUitType = selectUitType;
    }

    public BigDecimal getRobotSizeMaxLongs() {
        return robotSizeMaxLongs;
    }

    public void setRobotSizeMaxLongs(BigDecimal robotSizeMaxLongs) {
        this.robotSizeMaxLongs = robotSizeMaxLongs;
    }

    public BigDecimal getRobotSizeMaxWidth() {
        return robotSizeMaxWidth;
    }

    public void setRobotSizeMaxWidth(BigDecimal robotSizeMaxWidth) {
        this.robotSizeMaxWidth = robotSizeMaxWidth;
    }

    public BigDecimal getRobotSizeMinLongs() {
        return robotSizeMinLongs;
    }

    public void setRobotSizeMinLongs(BigDecimal robotSizeMinLongs) {
        this.robotSizeMinLongs = robotSizeMinLongs;
    }

    public BigDecimal getRobotSizeMinWidth() {
        return robotSizeMinWidth;
    }

    public void setRobotSizeMinWidth(BigDecimal robotSizeMinWidth) {
        this.robotSizeMinWidth = robotSizeMinWidth;
    }

    public Byte getLimitType() {
        return limitType;
    }

    public void setLimitType(Byte limitType) {
        this.limitType = limitType;
    }

    public BigDecimal getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(BigDecimal limitNumber) {
        this.limitNumber = limitNumber;
    }

    public List<TechnologyLimit> getLimitList() {
        return limitList;
    }

    public void setLimitList(List<TechnologyLimit> limitList) {
        this.limitList = limitList;
    }

    public Integer getFoldingFrequency() {
        return foldingFrequency;
    }

    public void setFoldingFrequency(Integer foldingFrequency) {
        this.foldingFrequency = foldingFrequency;
    }

    public Byte getFoldingStatus() {
        return foldingStatus;
    }

    public void setFoldingStatus(Byte foldingStatus) {
        this.foldingStatus = foldingStatus;
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
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", sellerId=" + sellerId +
                ", selectUitType=" + selectUitType +
                ", robotSizeMaxLongs=" + robotSizeMaxLongs +
                ", robotSizeMaxWidth=" + robotSizeMaxWidth +
                ", robotSizeMinLongs=" + robotSizeMinLongs +
                ", robotSizeMinWidth=" + robotSizeMinWidth +
                ", limitType=" + limitType +
                ", limitNumber=" + limitNumber +
                ", foldingMaxLength=" + foldingMaxLength +
                ", foldingMaxWidth=" + foldingMaxWidth +
                ", foldingMinLength=" + foldingMinLength +
                ", foldingMinWidth=" + foldingMinWidth +
                ", foldingFrequency=" + foldingFrequency +
                ", foldingClipWidth=" + foldingClipWidth +
                ", foldingClipGram=" + foldingClipGram +
                ", foldingIsClose=" + foldingIsClose +
                ", foldingStatus=" + foldingStatus +
                ", limitList=" + limitList +
                ", limitEditList=" + limitEditList +
                '}';
    }
}