package com.bzdepot.special.bo;

import com.bzdepot.special.model.ProductTechnology;

import java.util.List;

public class ProductTechnologyBo {

    private Long sellerId;  //商家编号

    private Long pconfigId; //产品分类编号

    private List<ProductTechnology> technology; //产品配置的材质主数据数组

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getPconfigId() {
        return pconfigId;
    }

    public void setPconfigId(Long pconfigId) {
        this.pconfigId = pconfigId;
    }

    public List<ProductTechnology> getTechnology() {
        return technology;
    }

    public void setTechnology(List<ProductTechnology> technology) {
        this.technology = technology;
    }

    @Override
    public String toString() {
        return "ProductTechnologyBo{" +
                "sellerId=" + sellerId +
                ", pconfigId=" + pconfigId +
                ", technology=" + technology +
                '}';
    }
}
