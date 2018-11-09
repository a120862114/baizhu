package com.bzdepot.offer.model;

import javax.validation.constraints.NotNull;

public class Texture {
    private Long id;

    @NotNull(message = "材质名称不能为空!")
    private String title;

    @NotNull(message = "所属分类编号不能为空!")
    private Long classId;

    @NotNull(message = "商家编号不能为空!")
    private Long sellerId;

    private Byte status;

    private Long createTime;

    private Long updateTime;

    @NotNull(message = "所属材质分组编号不能为空!")
    private Long groupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}