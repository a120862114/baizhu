package com.bzdepot.offer.model;

import javax.validation.constraints.NotNull;

public class TextureGroup {
    private Long id;

    @NotNull(message = "材质分组名称不能为空!")
    private String groupName;

    private Byte status;

    private Long createTime;

    private Long updateTime;

    @NotNull(message = "材质所属商家编号不能为空!")
    private Long sellerId;

    @NotNull(message = "材质所属分类编号不能为空!")
    private Long classId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
}