package com.bzdepot.special.model;

import java.math.BigDecimal;

public class PrintingCost {
    private Long id;

    private String robotIdentifier;

    private Long sellerId;

    private Long robotSizeId;

    private Long robotBrandId;

    private Long robotColorId;

    private Byte isUv;

    private Long robotManufactorId;

    private BigDecimal bootStrapMoney;

    private Integer printNums;

    private BigDecimal exceedMoney;

    private Integer exceedNums;

    private BigDecimal thicknessRangeStart;

    private BigDecimal thicknessRangeEnd;

    private BigDecimal minBite;

    private BigDecimal paperFeedingMaxOne;

    private BigDecimal paperFeedingMaxTwo;

    private BigDecimal paperFeedingMinOne;

    private BigDecimal paperFeedingMinTwo;

    private Integer dischargeNumberIn;

    private Integer dischargeNumberInNums;

    private Integer dischargeNumberMax;

    private Integer dischargeNumberMaxNums;

    private Byte specialColorType;

    private BigDecimal specialColorStartMoney;

    private Integer specialColorStartNums;

    private Integer specialColorTwoNums;

    private BigDecimal specialColorTwoOneMoney;

    private BigDecimal specialColorTwoTwoMoney;

    private Integer specialColorThreeNums;

    private BigDecimal specialColorThreeOneMoney;

    private BigDecimal specialColorThreeTwoMoney;

    private Byte status;

    private Long createTime;

    private Long updateTime;

    private String robotSizeTitle;

    private String robotBrandTitle;

    private String robotColorTitle;

    private String manufactorTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRobotIdentifier() {
        return robotIdentifier;
    }

    public void setRobotIdentifier(String robotIdentifier) {
        this.robotIdentifier = robotIdentifier == null ? null : robotIdentifier.trim();
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getRobotSizeId() {
        return robotSizeId;
    }

    public void setRobotSizeId(Long robotSizeId) {
        this.robotSizeId = robotSizeId;
    }

    public Long getRobotBrandId() {
        return robotBrandId;
    }

    public void setRobotBrandId(Long robotBrandId) {
        this.robotBrandId = robotBrandId;
    }

    public Long getRobotColorId() {
        return robotColorId;
    }

    public void setRobotColorId(Long robotColorId) {
        this.robotColorId = robotColorId;
    }

    public Byte getIsUv() {
        return isUv;
    }

    public void setIsUv(Byte isUv) {
        this.isUv = isUv;
    }

    public Long getRobotManufactorId() {
        return robotManufactorId;
    }

    public void setRobotManufactorId(Long robotManufactorId) {
        this.robotManufactorId = robotManufactorId;
    }

    public BigDecimal getBootStrapMoney() {
        return bootStrapMoney;
    }

    public void setBootStrapMoney(BigDecimal bootStrapMoney) {
        this.bootStrapMoney = bootStrapMoney;
    }

    public Integer getPrintNums() {
        return printNums;
    }

    public void setPrintNums(Integer printNums) {
        this.printNums = printNums;
    }

    public BigDecimal getExceedMoney() {
        return exceedMoney;
    }

    public void setExceedMoney(BigDecimal exceedMoney) {
        this.exceedMoney = exceedMoney;
    }

    public Integer getExceedNums() {
        return exceedNums;
    }

    public void setExceedNums(Integer exceedNums) {
        this.exceedNums = exceedNums;
    }

    public BigDecimal getThicknessRangeStart() {
        return thicknessRangeStart;
    }

    public void setThicknessRangeStart(BigDecimal thicknessRangeStart) {
        this.thicknessRangeStart = thicknessRangeStart;
    }

    public BigDecimal getThicknessRangeEnd() {
        return thicknessRangeEnd;
    }

    public void setThicknessRangeEnd(BigDecimal thicknessRangeEnd) {
        this.thicknessRangeEnd = thicknessRangeEnd;
    }

    public BigDecimal getMinBite() {
        return minBite;
    }

    public void setMinBite(BigDecimal minBite) {
        this.minBite = minBite;
    }

    public BigDecimal getPaperFeedingMaxOne() {
        return paperFeedingMaxOne;
    }

    public void setPaperFeedingMaxOne(BigDecimal paperFeedingMaxOne) {
        this.paperFeedingMaxOne = paperFeedingMaxOne;
    }

    public BigDecimal getPaperFeedingMaxTwo() {
        return paperFeedingMaxTwo;
    }

    public void setPaperFeedingMaxTwo(BigDecimal paperFeedingMaxTwo) {
        this.paperFeedingMaxTwo = paperFeedingMaxTwo;
    }

    public BigDecimal getPaperFeedingMinOne() {
        return paperFeedingMinOne;
    }

    public void setPaperFeedingMinOne(BigDecimal paperFeedingMinOne) {
        this.paperFeedingMinOne = paperFeedingMinOne;
    }

    public BigDecimal getPaperFeedingMinTwo() {
        return paperFeedingMinTwo;
    }

    public void setPaperFeedingMinTwo(BigDecimal paperFeedingMinTwo) {
        this.paperFeedingMinTwo = paperFeedingMinTwo;
    }

    public Integer getDischargeNumberIn() {
        return dischargeNumberIn;
    }

    public void setDischargeNumberIn(Integer dischargeNumberIn) {
        this.dischargeNumberIn = dischargeNumberIn;
    }

    public Integer getDischargeNumberInNums() {
        return dischargeNumberInNums;
    }

    public void setDischargeNumberInNums(Integer dischargeNumberInNums) {
        this.dischargeNumberInNums = dischargeNumberInNums;
    }

    public Integer getDischargeNumberMax() {
        return dischargeNumberMax;
    }

    public void setDischargeNumberMax(Integer dischargeNumberMax) {
        this.dischargeNumberMax = dischargeNumberMax;
    }

    public Integer getDischargeNumberMaxNums() {
        return dischargeNumberMaxNums;
    }

    public void setDischargeNumberMaxNums(Integer dischargeNumberMaxNums) {
        this.dischargeNumberMaxNums = dischargeNumberMaxNums;
    }

    public Byte getSpecialColorType() {
        return specialColorType;
    }

    public void setSpecialColorType(Byte specialColorType) {
        this.specialColorType = specialColorType;
    }

    public BigDecimal getSpecialColorStartMoney() {
        return specialColorStartMoney;
    }

    public void setSpecialColorStartMoney(BigDecimal specialColorStartMoney) {
        this.specialColorStartMoney = specialColorStartMoney;
    }

    public Integer getSpecialColorStartNums() {
        return specialColorStartNums;
    }

    public void setSpecialColorStartNums(Integer specialColorStartNums) {
        this.specialColorStartNums = specialColorStartNums;
    }

    public Integer getSpecialColorTwoNums() {
        return specialColorTwoNums;
    }

    public void setSpecialColorTwoNums(Integer specialColorTwoNums) {
        this.specialColorTwoNums = specialColorTwoNums;
    }

    public BigDecimal getSpecialColorTwoOneMoney() {
        return specialColorTwoOneMoney;
    }

    public void setSpecialColorTwoOneMoney(BigDecimal specialColorTwoOneMoney) {
        this.specialColorTwoOneMoney = specialColorTwoOneMoney;
    }

    public BigDecimal getSpecialColorTwoTwoMoney() {
        return specialColorTwoTwoMoney;
    }

    public void setSpecialColorTwoTwoMoney(BigDecimal specialColorTwoTwoMoney) {
        this.specialColorTwoTwoMoney = specialColorTwoTwoMoney;
    }

    public Integer getSpecialColorThreeNums() {
        return specialColorThreeNums;
    }

    public void setSpecialColorThreeNums(Integer specialColorThreeNums) {
        this.specialColorThreeNums = specialColorThreeNums;
    }

    public BigDecimal getSpecialColorThreeOneMoney() {
        return specialColorThreeOneMoney;
    }

    public void setSpecialColorThreeOneMoney(BigDecimal specialColorThreeOneMoney) {
        this.specialColorThreeOneMoney = specialColorThreeOneMoney;
    }

    public BigDecimal getSpecialColorThreeTwoMoney() {
        return specialColorThreeTwoMoney;
    }

    public void setSpecialColorThreeTwoMoney(BigDecimal specialColorThreeTwoMoney) {
        this.specialColorThreeTwoMoney = specialColorThreeTwoMoney;
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

    public String getRobotSizeTitle() {
        return robotSizeTitle;
    }

    public void setRobotSizeTitle(String robotSizeTitle) {
        this.robotSizeTitle = robotSizeTitle == null ? null : robotSizeTitle.trim();
    }

    public String getRobotBrandTitle() {
        return robotBrandTitle;
    }

    public void setRobotBrandTitle(String robotBrandTitle) {
        this.robotBrandTitle = robotBrandTitle == null ? null : robotBrandTitle.trim();
    }

    public String getRobotColorTitle() {
        return robotColorTitle;
    }

    public void setRobotColorTitle(String robotColorTitle) {
        this.robotColorTitle = robotColorTitle == null ? null : robotColorTitle.trim();
    }

    public String getManufactorTitle() {
        return manufactorTitle;
    }

    public void setManufactorTitle(String manufactorTitle) {
        this.manufactorTitle = manufactorTitle == null ? null : manufactorTitle.trim();
    }
}