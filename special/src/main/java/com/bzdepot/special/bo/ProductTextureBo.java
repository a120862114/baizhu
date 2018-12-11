package com.bzdepot.special.bo;

import java.util.Arrays;

public class ProductTextureBo {

    private Long pconfigId;

    private Long sellerId;

    private Long[] textureId;

    public Long getPconfigId() {
        return pconfigId;
    }

    public void setPconfigId(Long pconfigId) {
        this.pconfigId = pconfigId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long[] getTextureId() {
        return textureId;
    }

    public void setTextureId(Long[] textureId) {
        this.textureId = textureId;
    }

    @Override
    public String toString() {
        return "ProductTextureBo{" +
                "pconfigId=" + pconfigId +
                ", sellerId=" + sellerId +
                ", textureId=" + Arrays.toString(textureId) +
                '}';
    }
}
