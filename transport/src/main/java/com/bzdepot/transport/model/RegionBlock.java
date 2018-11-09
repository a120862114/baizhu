package com.bzdepot.transport.model;

public class RegionBlock {
    private Long id;

    private Long sellerId;

    private Long companyId;

    private Long regionId;

    private Integer blockId;

    private Long cityId;

    private String cityName;

    private Long createTime;

    private Long updateTime;

    private Byte isSuspend;

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
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

    public Byte getIsSuspend() {
        return isSuspend;
    }

    public void setIsSuspend(Byte isSuspend) {
        this.isSuspend = isSuspend;
    }

    @Override
    public String toString() {
        return "RegionBlock{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", companyId=" + companyId +
                ", regionId=" + regionId +
                ", blockId=" + blockId +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isSuspend=" + isSuspend +
                '}';
    }
}