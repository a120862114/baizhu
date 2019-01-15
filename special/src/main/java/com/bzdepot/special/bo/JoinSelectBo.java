package com.bzdepot.special.bo;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
}
