package com.bzdepot.calculate.bo;

/**
 * 专版报价的工艺参数
 */
public class AttrBo {

    private Long technologyId;

    private Long attrId;

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

    @Override
    public String toString() {
        return "AttrBo{" +
                "technologyId=" + technologyId +
                ", attrId=" + attrId +
                '}';
    }
}
