package com.bzdepot.offer.model;

public class ProductImg {
    private Long id;

    private Long sellerId;

    private String imgIds;

    private Long classId;

    private Long textureId;

    private Long createTime;

    private Long updateTime;

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

    public String getImgIds() {
        return imgIds;
    }

    public void setImgIds(String imgIds) {
        this.imgIds = imgIds == null ? null : imgIds.trim();
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

    @Override
    public String toString() {
        return "ProductImg{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", imgIds='" + imgIds + '\'' +
                ", classId=" + classId +
                ", textureId=" + textureId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}