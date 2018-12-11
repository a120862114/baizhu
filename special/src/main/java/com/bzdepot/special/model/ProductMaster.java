package com.bzdepot.special.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductMaster {
    private Long id;

    @NotNull(message = "产品分类编号不能为空!")
    private Long classId;

    private Long boxId;

    private BigDecimal smoney;

    @NotNull(message = "商家编号不能为空!")
    private Long sellerId;

    private Long createTime;

    private Long updateTime;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public BigDecimal getSmoney() {
        return smoney;
    }

    public void setSmoney(BigDecimal smoney) {
        this.smoney = smoney;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}