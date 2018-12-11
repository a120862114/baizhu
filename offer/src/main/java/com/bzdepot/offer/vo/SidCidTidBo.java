package com.bzdepot.offer.vo;

public class SidCidTidBo {

    private Long sellerId;

    private Long classId;

    private Long textureId;

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

    @Override
    public String toString() {
        return "SidCidTidBo{" +
                "sellerId=" + sellerId +
                ", classId=" + classId +
                ", textureId=" + textureId +
                '}';
    }
}
