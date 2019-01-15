package com.bzdepot.calculate.bo;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class JoinSelectBo {

    @NotNull(message = "商家编号不能为空!")
    private Long sellerId;

    @NotNull(message = "材质编号不能为空!")
    private Long textureId;

    @NotNull(message = "厚度参数不能为为空!")
    private BigDecimal gramNums;

    private Long printingColorId;

    @NotNull(message = "自定义尺寸长不能为空!")
    private BigDecimal Longs;

    @NotNull(message = "自定义尺寸宽不能为空!")
    private BigDecimal Width;

    @NotNull(message = "制作数量不能为空!")
    private Integer makeNumber;

    private BigDecimal hemorrhageNums; //出血的值

    private BigDecimal gaugeNums; //规线的值

    private BigDecimal biteNums; //可借咬口数量

    private List<AttrBo> attrList; //需要附加的工艺的请求参数列表

    private ColorBo colorData; //颜色相关的参数

    public ColorBo getColorData() {
        return colorData;
    }

    public void setColorData(ColorBo colorData) {
        this.colorData = colorData;
    }

    public List<AttrBo> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<AttrBo> attrList) {
        this.attrList = attrList;
    }

    public BigDecimal getBiteNums() {
        return biteNums;
    }

    public void setBiteNums(BigDecimal biteNums) {
        this.biteNums = biteNums;
    }

    public BigDecimal getGaugeNums() {
        return gaugeNums;
    }

    public void setGaugeNums(BigDecimal gaugeNums) {
        this.gaugeNums = gaugeNums;
    }

    public BigDecimal getHemorrhageNums() {
        return hemorrhageNums;
    }

    public void setHemorrhageNums(BigDecimal hemorrhageNums) {
        this.hemorrhageNums = hemorrhageNums;
    }

    public Integer getMakeNumber() {
        return makeNumber;
    }

    public void setMakeNumber(Integer makeNumber) {
        this.makeNumber = makeNumber;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getTextureId() {
        return textureId;
    }

    public void setTextureId(Long textureId) {
        this.textureId = textureId;
    }

    public BigDecimal getGramNums() {
        return gramNums;
    }

    public void setGramNums(BigDecimal gramNums) {
        this.gramNums = gramNums;
    }

    public Long getPrintingColorId() {
        return printingColorId;
    }

    public void setPrintingColorId(Long printingColorId) {
        this.printingColorId = printingColorId;
    }

    public BigDecimal getLongs() {
        return Longs;
    }

    public void setLongs(BigDecimal longs) {
        Longs = longs;
    }

    public BigDecimal getWidth() {
        return Width;
    }

    public void setWidth(BigDecimal width) {
        Width = width;
    }

    @Override
    public String toString() {
        return "JoinSelectBo{" +
                "sellerId=" + sellerId +
                ", textureId=" + textureId +
                ", gramNums=" + gramNums +
                ", printingColorId=" + printingColorId +
                ", Longs=" + Longs +
                ", Width=" + Width +
                ", makeNumber=" + makeNumber +
                ", hemorrhageNums=" + hemorrhageNums +
                ", gaugeNums=" + gaugeNums +
                ", biteNums=" + biteNums +
                ", attrList=" + attrList +
                ", colorData=" + colorData +
                '}';
    }
}
