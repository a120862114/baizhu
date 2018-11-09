package com.bzdepot.offer.vo;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class SelectOfferMoneyVo {

    @NotNull(message = "商家编号不能为空!")
    private Long sellerId;

    @NotNull(message = "产品报价分类不能为空!")
    private Long classId;

    @NotNull(message = "产品报价材质不能为空!")
    private Long textureId;

    @NotNull(message = "产品报价厚度不能为空!")
    private BigDecimal thickness;

    @NotNull(message = "产品报价重量不能为空!")
    private BigDecimal weight;

    @NotNull(message = "查询数量不能为空!")
    private Integer nums;

    @NotNull(message = "快递编号不能为空!")
    private Long comanyId;

    @NotNull(message = "城市编号不能为空!")
    private Long cityId;

    @NotNull(message = "发票类型不能为空!")
    private Byte inTypes;

    private Byte isS = new Byte("0"); //是否需要设计

    private Byte isY = new Byte("0"); //是否需要邮寄

    private Byte isF = new Byte("0"); //是否需要发票

    private BigDecimal longsData; //长的值

    private BigDecimal widthData; //宽的值

    private BigDecimal heightData; //高的值

    private Long levelId; //用户等级

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

    public BigDecimal getThickness() {
        return thickness;
    }

    public void setThickness(BigDecimal thickness) {
        this.thickness = thickness;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Long getComanyId() {
        return comanyId;
    }

    public void setComanyId(Long comanyId) {
        this.comanyId = comanyId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Byte getInTypes() {
        return inTypes;
    }

    public void setInTypes(Byte inTypes) {
        this.inTypes = inTypes;
    }

    public Byte getIsS() {
        return isS;
    }

    public void setIsS(Byte isS) {
        this.isS = isS;
    }

    public Byte getIsY() {
        return isY;
    }

    public void setIsY(Byte isY) {
        this.isY = isY;
    }

    public Byte getIsF() {
        return isF;
    }

    public void setIsF(Byte isF) {
        this.isF = isF;
    }

    public BigDecimal getLongsData() {
        return longsData;
    }

    public void setLongsData(BigDecimal longsData) {
        this.longsData = longsData;
    }

    public BigDecimal getWidthData() {
        return widthData;
    }

    public void setWidthData(BigDecimal widthData) {
        this.widthData = widthData;
    }

    public BigDecimal getHeightData() {
        return heightData;
    }

    public void setHeightData(BigDecimal heightData) {
        this.heightData = heightData;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    @Override
    public String toString() {
        return "SelectOfferMoneyVo{" +
                "sellerId=" + sellerId +
                ", classId=" + classId +
                ", textureId=" + textureId +
                ", thickness=" + thickness +
                ", weight=" + weight +
                ", nums=" + nums +
                ", comanyId=" + comanyId +
                ", cityId=" + cityId +
                ", inTypes=" + inTypes +
                ", isS=" + isS +
                ", isY=" + isY +
                ", isF=" + isF +
                ", longsData=" + longsData +
                ", widthData=" + widthData +
                ", heightData=" + heightData +
                ", levelId=" + levelId +
                '}';
    }
}
