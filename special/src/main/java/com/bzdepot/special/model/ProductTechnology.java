package com.bzdepot.special.model;

import com.bzdepot.special.bo.GetNamesBo;

import java.util.List;

public class ProductTechnology {
    private Long id;

    private Long sellerId;

    private Long pconfigId;

    private Long technologyId;

    private Long technologyAttrId;

    private Integer sort;

    private GetNamesBo technologyClassName;

    private GetNamesBo technologyAttrName;

    private List<productTechnologySon> technologySon;

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

    public Long getPconfigId() {
        return pconfigId;
    }

    public void setPconfigId(Long pconfigId) {
        this.pconfigId = pconfigId;
    }

    public Long getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(Long technologyId) {
        this.technologyId = technologyId;
    }


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<productTechnologySon> getTechnologySon() {
        return technologySon;
    }

    public void setTechnologySon(List<productTechnologySon> technologySon) {
        this.technologySon = technologySon;
    }

    public Long getTechnologyAttrId() {
        return technologyAttrId;
    }

    public void setTechnologyAttrId(Long technologyAttrId) {
        this.technologyAttrId = technologyAttrId;
    }

    public GetNamesBo getTechnologyClassName() {
        return technologyClassName;
    }

    public void setTechnologyClassName(GetNamesBo technologyClassName) {
        this.technologyClassName = technologyClassName;
    }

    public GetNamesBo getTechnologyAttrName() {
        return technologyAttrName;
    }

    public void setTechnologyAttrName(GetNamesBo technologyAttrName) {
        this.technologyAttrName = technologyAttrName;
    }

    @Override
    public String toString() {
        return "ProductTechnology{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", pconfigId=" + pconfigId +
                ", technologyId=" + technologyId +
                ", technologyAttrId=" + technologyAttrId +
                ", sort=" + sort +
                ", technologyClassName=" + technologyClassName +
                ", technologyAttrName=" + technologyAttrName +
                ", technologySon=" + technologySon +
                '}';
    }
}