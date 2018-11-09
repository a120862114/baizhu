package com.bzdepot.offer.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Offer {
    private Long id;

    @NotNull(message = "商家编号不能为空!")
    private Long sellerId;

    @NotNull(message = "分类编号不能为空!")
    private Long classId;

    @NotNull(message = "材质编号不能为空!")
    private Long textureId;

    @NotNull(message = "厚度参数不能为空!")
    @Digits(integer=10, fraction=2,message = "厚度整数位不能超过10位，小数位不能超过2位！")
    private BigDecimal thickness;

    @NotNull(message = "厚度单位不能为空!")
    private String thicknessUtil;

    @NotNull(message = "重量参数不能为空!")
    @Digits(integer=10, fraction=2,message = "重量整数位不能超过10位，小数位不能超过2位！")
    private BigDecimal weight;

    @NotNull(message = "重量单位不能为空!")
    private String weightUtil;

    private Long createTime;

    private Long updateTime;

    private Byte status;

    private Byte isSupply;

    @NotNull(message = "报价类型不能为空!")
    private Byte types;

    private OfferSon offerSonData;

    public OfferSon getOfferSonData() {
        return offerSonData;
    }

    public void setOfferSonData(OfferSon offerSonData) {
        this.offerSonData = offerSonData;
    }

    public Byte getTypes() {
        return types;
    }

    public void setTypes(Byte types) {
        this.types = types;
    }

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

    public String getThicknessUtil() {
        return thicknessUtil;
    }

    public void setThicknessUtil(String thicknessUtil) {
        this.thicknessUtil = thicknessUtil == null ? null : thicknessUtil.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getWeightUtil() {
        return weightUtil;
    }

    public void setWeightUtil(String weightUtil) {
        this.weightUtil = weightUtil == null ? null : weightUtil.trim();
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsSupply() {
        return isSupply;
    }

    public void setIsSupply(Byte isSupply) {
        this.isSupply = isSupply;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", classId=" + classId +
                ", textureId=" + textureId +
                ", thickness=" + thickness +
                ", thicknessUtil='" + thicknessUtil + '\'' +
                ", weight=" + weight +
                ", weightUtil='" + weightUtil + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", isSupply=" + isSupply +
                ", types=" + types +
                ", offerSonData=" + offerSonData +
                '}';
    }
}