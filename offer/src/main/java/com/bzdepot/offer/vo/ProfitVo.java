package com.bzdepot.offer.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProfitVo {

    @NotNull(message = "固定报价配置编号不能为空!")
    private Long offerId;

    private Long parentOfferId;

    @NotNull(message = "类型不能为空!")
    @Min(value = 0,message = "类型的最小值不能小于零！")
    @Max(value = 2,message = "类型的最大值不能大于二！")
    private Byte types;

   // @NotNull(message = "利润配置参数不能为空!")
    //private List<ProfitAttrVo> config;

//    public List<ProfitAttrVo> getConfig() {
//        return config;
//    }
//
//    public void setConfig(List<ProfitAttrVo> config) {
//        this.config = config;
//    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getParentOfferId() {
        return parentOfferId;
    }

    public void setParentOfferId(Long parentOfferId) {
        this.parentOfferId = parentOfferId;
    }

    public Byte getTypes() {
        return types;
    }

    public void setTypes(Byte types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "ProfitVo{" +
                "offerId=" + offerId +
                ", parentOfferId=" + parentOfferId +
                ", types=" + types +
               // ", config=" + config +
                '}';
    }
}
