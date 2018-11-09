package com.bzdepot.transport.model;

public class TransportCompany {
    private Long id;

    private String companyName;

    private Long typeId;

    private Long createTime;

    private Long updateTime;

    private Long sellerId;

    private Long defaultRegionId;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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

    public Long getDefaultRegionId() {
        return defaultRegionId;
    }

    public void setDefaultRegionId(Long defaultRegionId) {
        this.defaultRegionId = defaultRegionId;
    }
}