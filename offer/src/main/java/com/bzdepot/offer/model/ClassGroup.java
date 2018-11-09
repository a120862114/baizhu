package com.bzdepot.offer.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ClassGroup {
    private Long id;

    @NotNull(message = "分组名不能为空!")
    private String groupName;

    private Byte status;

    private Long createTime;

    private Long updateTime;

    @NotNull(message = "商家编号不能为空!")
    private Long sellerId;

    private List<Classfiy> classfiy;

    public List<Classfiy> getClassfiy() {
        return classfiy;
    }

    public void setClassfiy(List<Classfiy> classfiy) {
        this.classfiy = classfiy;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

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
}