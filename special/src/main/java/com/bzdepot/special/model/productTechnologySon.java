package com.bzdepot.special.model;

import com.bzdepot.special.bo.GetNamesBo;

public class productTechnologySon {
    private Long classId;

    private Long sellerId;

    private Long technologyId;

    private Long attrId;

    private GetNamesBo technologyAttrName;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getTechnologyId() {
        return technologyId;
    }

    public void setTechnologyId(Long technologyId) {
        this.technologyId = technologyId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public GetNamesBo getTechnologyAttrName() {
        return technologyAttrName;
    }

    public void setTechnologyAttrName(GetNamesBo technologyAttrName) {
        this.technologyAttrName = technologyAttrName;
    }

    @Override
    public String toString() {
        return "productTechnologySon{" +
                "classId=" + classId +
                ", sellerId=" + sellerId +
                ", technologyId=" + technologyId +
                ", attrId=" + attrId +
                ", technologyAttrName=" + technologyAttrName +
                '}';
    }
}