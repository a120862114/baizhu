package com.bzdepot.special.model;

import java.util.Arrays;
import java.util.List;

public class TechnologyClass {
    private Long id;

    private String technologyName;

    private Long sellerId;

    private Integer sort;

    private Byte status;

    private Long defaultAttrId;

    private Byte isCommonlyUsed;

    private Long createTime;

    private Long updateTime;

    private String enableAttrIds;

    private TechnologyAttr[] detail;

    private List<TechnologyAttr> listDetail;

    public List<TechnologyAttr> getListDetail() {
        return listDetail;
    }

    public void setListDetail(List<TechnologyAttr> listDetail) {
        this.listDetail = listDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName == null ? null : technologyName.trim();
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getDefaultAttrId() {
        return defaultAttrId;
    }

    public void setDefaultAttrId(Long defaultAttrId) {
        this.defaultAttrId = defaultAttrId;
    }

    public Byte getIsCommonlyUsed() {
        return isCommonlyUsed;
    }

    public void setIsCommonlyUsed(Byte isCommonlyUsed) {
        this.isCommonlyUsed = isCommonlyUsed;
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

    public String getEnableAttrIds() {
        return enableAttrIds;
    }

    public void setEnableAttrIds(String enableAttrIds) {
        this.enableAttrIds = enableAttrIds == null ? null : enableAttrIds.trim();
    }

    public TechnologyAttr[] getDetail() {
        return detail;
    }

    public void setDetail(TechnologyAttr[] detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "TechnologyClass{" +
                "id=" + id +
                ", technologyName='" + technologyName + '\'' +
                ", sellerId=" + sellerId +
                ", sort=" + sort +
                ", status=" + status +
                ", defaultAttrId=" + defaultAttrId +
                ", isCommonlyUsed=" + isCommonlyUsed +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", enableAttrIds='" + enableAttrIds + '\'' +
                ", detail=" + Arrays.toString(detail) +
                '}';
    }
}