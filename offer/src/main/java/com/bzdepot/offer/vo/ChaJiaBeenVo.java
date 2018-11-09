package com.bzdepot.offer.vo;

import javax.validation.constraints.NotNull;

public class ChaJiaBeenVo {

    @NotNull(message = "报价配置编号不能为空!")
    private Long offerId;

    @NotNull(message = "查询数量不能为空!")
    private Integer nums;

    @NotNull(message = "快递编号不能为空!")
    private Long comanyId;

    @NotNull(message = "城市编号不能为空!")
    private Long cityId;

    @NotNull(message = "发票类型不能为空!")
    private Byte inTypes;

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Long getComanyId() {
        return comanyId;
    }

    public void setComanyId(Long comanyId) {
        this.comanyId = comanyId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Byte getInTypes() {
        return inTypes;
    }

    public void setInTypes(Byte inTypes) {
        this.inTypes = inTypes;
    }

    @Override
    public String toString() {
        return "ChaJiaBeenVo{" +
                "offerId=" + offerId +
                ", nums=" + nums +
                ", comanyId=" + comanyId +
                ", cityId=" + cityId +
                ", inTypes=" + inTypes +
                '}';
    }
}
