package com.bzdepot.transport.model;

public class TransportRegion {
    private Long id;

    private Long sellerId;

    private String describe;

    private Long companyId;

    private Long createTime;

    private Long updateTime;

    private Integer blockNums;

    private RegionDefault defaultData;

    public RegionDefault getDefaultData() {
        return defaultData;
    }

    public void setDefaultData(RegionDefault defaultData) {
        this.defaultData = defaultData;
    }

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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public Integer getBlockNums() {
        return blockNums;
    }

    public void setBlockNums(Integer blockNums) {
        this.blockNums = blockNums;
    }
}