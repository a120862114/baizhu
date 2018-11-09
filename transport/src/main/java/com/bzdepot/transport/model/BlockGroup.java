package com.bzdepot.transport.model;

public class BlockGroup {
    private Long id;

    private Long sellerId;

    private Long companyId;

    private Long regionId;

    private Integer blockId;

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

    @Override
    public String toString() {
        return "BlockGroup{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", companyId=" + companyId +
                ", regionId=" + regionId +
                ", blockId=" + blockId +
                '}';
    }
}