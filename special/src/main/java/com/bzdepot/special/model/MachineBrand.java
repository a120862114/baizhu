package com.bzdepot.special.model;

public class MachineBrand {
    private Long id;

    private String brandName;

    private String brandBs;

    private Long sellerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getBrandBs() {
        return brandBs;
    }

    public void setBrandBs(String brandBs) {
        this.brandBs = brandBs == null ? null : brandBs.trim();
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}