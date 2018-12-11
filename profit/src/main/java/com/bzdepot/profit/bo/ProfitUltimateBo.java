package com.bzdepot.profit.bo;

import java.util.List;

public class ProfitUltimateBo {

    private Long sellerId;

    private Long classId;

    private Long textureId;

    private Long gramId;

    private List<ProfitUltimateSonBo> profitDetail;

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

    public List<ProfitUltimateSonBo> getProfitDetail() {
        return profitDetail;
    }

    public void setProfitDetail(List<ProfitUltimateSonBo> profitDetail) {
        this.profitDetail = profitDetail;
    }

    public Long getGramId() {
        return gramId;
    }

    public void setGramId(Long gramId) {
        this.gramId = gramId;
    }

    @Override
    public String toString() {
        return "ProfitUltimateBo{" +
                "sellerId=" + sellerId +
                ", classId=" + classId +
                ", textureId=" + textureId +
                ", gramId=" + gramId +
                ", profitDetail=" + profitDetail +
                '}';
    }
}
