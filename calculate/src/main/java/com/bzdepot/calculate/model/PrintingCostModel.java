package com.bzdepot.calculate.model;

import java.math.BigDecimal;
import java.util.List;

public class PrintingCostModel {
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

    private BigDecimal specialColorOneRate;

    private Byte isAnColorStatus;

    private Byte isBigSize;

    private Byte isBigColor;

    private Byte status;

    private Long createTime;

    private Long updateTime;

    private String robotSizeTitle;

    private String robotBrandTitle;

    private String robotColorTitle;

    private String manufactorTitle;

    private BigDecimal bigSizeOne;

    private BigDecimal bigSizeTwo;

    private BigDecimal bigSizeThree;

    private BigDecimal bigSizeFour;

    private BigDecimal bigSizeFive;

    private BigDecimal bigColorOne;

    private BigDecimal bigColorTwo;

    private BigDecimal bigColorThree;

    private BigDecimal bigColorFour;

    private BigDecimal bigColorFive;

    private BigDecimal cmykBootStrapMoney;

    private Integer cmykPrintNums;

    private BigDecimal cmykExceedMoney;

    private Integer cmykExceedNums;

    private Byte isPrintFourColor;

    private Integer colorNumOneIn;

    private BigDecimal colorNumOneMoney;

    private Integer colorNumTwoMore;

    private BigDecimal colorNumTwoMoreMoney;

    private CommonColorModel robotColor;

    public Byte getIsPrintFourColor() {
        return isPrintFourColor;
    }

    public void setIsPrintFourColor(Byte isPrintFourColor) {
        this.isPrintFourColor = isPrintFourColor;
    }

    public Integer getColorNumOneIn() {
        return colorNumOneIn;
    }

    public void setColorNumOneIn(Integer colorNumOneIn) {
        this.colorNumOneIn = colorNumOneIn;
    }

    public BigDecimal getColorNumOneMoney() {
        return colorNumOneMoney;
    }

    public void setColorNumOneMoney(BigDecimal colorNumOneMoney) {
        this.colorNumOneMoney = colorNumOneMoney;
    }

    public Integer getColorNumTwoMore() {
        return colorNumTwoMore;
    }

    public void setColorNumTwoMore(Integer colorNumTwoMore) {
        this.colorNumTwoMore = colorNumTwoMore;
    }

    public BigDecimal getColorNumTwoMoreMoney() {
        return colorNumTwoMoreMoney;
    }

    public void setColorNumTwoMoreMoney(BigDecimal colorNumTwoMoreMoney) {
        this.colorNumTwoMoreMoney = colorNumTwoMoreMoney;
    }

    public CommonColorModel getRobotColor() {
        return robotColor;
    }

    public void setRobotColor(CommonColorModel robotColor) {
        this.robotColor = robotColor;
    }

    public BigDecimal getCmykBootStrapMoney() {
        return cmykBootStrapMoney;
    }

    public void setCmykBootStrapMoney(BigDecimal cmykBootStrapMoney) {
        this.cmykBootStrapMoney = cmykBootStrapMoney;
    }

    public Integer getCmykPrintNums() {
        return cmykPrintNums;
    }

    public void setCmykPrintNums(Integer cmykPrintNums) {
        this.cmykPrintNums = cmykPrintNums;
    }

    public BigDecimal getCmykExceedMoney() {
        return cmykExceedMoney;
    }

    public void setCmykExceedMoney(BigDecimal cmykExceedMoney) {
        this.cmykExceedMoney = cmykExceedMoney;
    }

    public Integer getCmykExceedNums() {
        return cmykExceedNums;
    }

    public void setCmykExceedNums(Integer cmykExceedNums) {
        this.cmykExceedNums = cmykExceedNums;
    }

    private List<PrintingSpotConfigModel> colorConfigs;

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

    public BigDecimal getSpecialColorOneRate() {
        return specialColorOneRate;
    }

    public void setSpecialColorOneRate(BigDecimal specialColorOneRate) {
        this.specialColorOneRate = specialColorOneRate;
    }

    public Byte getIsAnColorStatus() {
        return isAnColorStatus;
    }

    public void setIsAnColorStatus(Byte isAnColorStatus) {
        this.isAnColorStatus = isAnColorStatus;
    }

    public Byte getIsBigSize() {
        return isBigSize;
    }

    public void setIsBigSize(Byte isBigSize) {
        this.isBigSize = isBigSize;
    }

    public Byte getIsBigColor() {
        return isBigColor;
    }

    public void setIsBigColor(Byte isBigColor) {
        this.isBigColor = isBigColor;
    }

    public BigDecimal getBigSizeOne() {
        return bigSizeOne;
    }

    public void setBigSizeOne(BigDecimal bigSizeOne) {
        this.bigSizeOne = bigSizeOne;
    }

    public BigDecimal getBigSizeTwo() {
        return bigSizeTwo;
    }

    public void setBigSizeTwo(BigDecimal bigSizeTwo) {
        this.bigSizeTwo = bigSizeTwo;
    }

    public BigDecimal getBigSizeThree() {
        return bigSizeThree;
    }

    public void setBigSizeThree(BigDecimal bigSizeThree) {
        this.bigSizeThree = bigSizeThree;
    }

    public BigDecimal getBigSizeFour() {
        return bigSizeFour;
    }

    public void setBigSizeFour(BigDecimal bigSizeFour) {
        this.bigSizeFour = bigSizeFour;
    }

    public BigDecimal getBigSizeFive() {
        return bigSizeFive;
    }

    public void setBigSizeFive(BigDecimal bigSizeFive) {
        this.bigSizeFive = bigSizeFive;
    }

    public BigDecimal getBigColorOne() {
        return bigColorOne;
    }

    public void setBigColorOne(BigDecimal bigColorOne) {
        this.bigColorOne = bigColorOne;
    }

    public BigDecimal getBigColorTwo() {
        return bigColorTwo;
    }

    public void setBigColorTwo(BigDecimal bigColorTwo) {
        this.bigColorTwo = bigColorTwo;
    }

    public BigDecimal getBigColorThree() {
        return bigColorThree;
    }

    public void setBigColorThree(BigDecimal bigColorThree) {
        this.bigColorThree = bigColorThree;
    }

    public BigDecimal getBigColorFour() {
        return bigColorFour;
    }

    public void setBigColorFour(BigDecimal bigColorFour) {
        this.bigColorFour = bigColorFour;
    }

    public BigDecimal getBigColorFive() {
        return bigColorFive;
    }

    public void setBigColorFive(BigDecimal bigColorFive) {
        this.bigColorFive = bigColorFive;
    }

    public List<PrintingSpotConfigModel> getColorConfigs() {
        return colorConfigs;
    }

    public void setColorConfigs(List<PrintingSpotConfigModel> colorConfigs) {
        this.colorConfigs = colorConfigs;
    }


}