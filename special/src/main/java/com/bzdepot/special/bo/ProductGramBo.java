package com.bzdepot.special.bo;

import java.util.Arrays;

public class ProductGramBo {

    private Long sellerId;

    private Long textureId;

    private Long[] gramId;

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

    public Long[] getGramId() {
        return gramId;
    }

    public void setGramId(Long[] gramId) {
        this.gramId = gramId;
    }

    @Override
    public String toString() {
        return "ProductGramBo{" +
                "sellerId=" + sellerId +
                ", textureId=" + textureId +
                ", gramId=" + Arrays.toString(gramId) +
                '}';
    }
}
