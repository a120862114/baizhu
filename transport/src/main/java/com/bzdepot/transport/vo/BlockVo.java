package com.bzdepot.transport.vo;

import javax.validation.constraints.NotNull;

public class BlockVo {

    private Long id; //此为区块打包的ID编号

    @NotNull(message = "商家编号不能为空!")
    private Long sellerId;

    @NotNull(message = "运输公司编号不能为空!")
    private Long companyId;

    @NotNull(message = "关联区域编号不能为空!")
    private Long regionId;

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

    @Override
    public String toString() {
        return "BlockVo{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", companyId=" + companyId +
                ", regionId=" + regionId +
                '}';
    }
}
