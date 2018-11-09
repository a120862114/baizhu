package com.bzdepot.transport.vo;

public class DefaultCityFindVo {

    private Long companyId;

    private Long sellerId;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "DefaultCityFindVo{" +
                "companyId=" + companyId +
                ", sellerId=" + sellerId +
                '}';
    }
}
