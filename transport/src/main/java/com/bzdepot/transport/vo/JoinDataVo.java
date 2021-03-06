package com.bzdepot.transport.vo;

import javax.validation.constraints.NotNull;

public class JoinDataVo {

    @NotNull(message = "商家编号不能为空!")
    private Long sellerId;

    @NotNull(message = "运输公司编号不能为空!")
    private Long companyId;

    @NotNull(message = "区域编号不能为空!")
    private Long regionId;

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
        return "JoinDataVo{" +
                "sellerId=" + sellerId +
                ", companyId=" + companyId +
                ", regionId=" + regionId +
                '}';
    }
}
