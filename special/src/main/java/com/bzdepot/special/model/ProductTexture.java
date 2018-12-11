package com.bzdepot.special.model;

import com.bzdepot.special.bo.GetNamesBo;

public class ProductTexture {
    private Long id;

    private Long pconfigId;

    private Long sellerId;

    private Long textureId;

    private GetNamesBo textureName;

    private Byte isDefault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getTextureId() {
        return textureId;
    }

    public void setTextureId(Long textureId) {
        this.textureId = textureId;
    }

    public GetNamesBo getTextureName() {
        return textureName;
    }

    public void setTextureName(GetNamesBo textureName) {
        this.textureName = textureName;
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "ProductTexture{" +
                "id=" + id +
                ", pconfigId=" + pconfigId +
                ", sellerId=" + sellerId +
                ", textureId=" + textureId +
                ", textureName=" + textureName +
                ", isDefault=" + isDefault +
                '}';
    }
}