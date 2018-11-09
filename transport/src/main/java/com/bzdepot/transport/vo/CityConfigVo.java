package com.bzdepot.transport.vo;

import java.math.BigDecimal;

public class CityConfigVo {

    private Long sellerId;

    private Long companyId;

    private Long cityId; //城市ID

    private BigDecimal totalWeight; //总重量

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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public BigDecimal getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(BigDecimal totalWeight) {
        this.totalWeight = totalWeight;
    }

    @Override
    public String toString() {
        return "CityConfigVo{" +
                "sellerId=" + sellerId +
                ", companyId=" + companyId +
                ", cityId=" + cityId +
                ", totalWeight=" + totalWeight +
                '}';
    }
}
