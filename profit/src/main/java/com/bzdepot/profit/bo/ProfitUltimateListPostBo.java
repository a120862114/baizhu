package com.bzdepot.profit.bo;

import javax.validation.constraints.NotNull;

public class ProfitUltimateListPostBo {

    @NotNull(message = "商家编号不能为空!")
    private Long sellerId;

    @NotNull(message = "分类编号不能为空!")
    private Long classId;

    @NotNull(message = "材质编号不能为空!")
    private Long textureId;

    @NotNull(message = "厚度编号不能为空!")
    private Long gramId;

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

    public Long getGramId() {
        return gramId;
    }

    public void setGramId(Long gramId) {
        this.gramId = gramId;
    }

    @Override
    public String toString() {
        return "ProfitUltimateListPostBo{" +
                "sellerId=" + sellerId +
                ", classId=" + classId +
                ", textureId=" + textureId +
                ", gramId=" + gramId +
                '}';
    }
}
