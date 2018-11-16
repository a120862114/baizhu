package com.bzdepot.special.model;

public class ProductTechnology {
    private Long id;

    private Long sellerId;

    private Long pconfigId;

    private Long technologyId;

    private Long echnologyAttrId;

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

    public Long getEchnologyAttrId() {
        return echnologyAttrId;
    }

    public void setEchnologyAttrId(Long echnologyAttrId) {
        this.echnologyAttrId = echnologyAttrId;
    }
}