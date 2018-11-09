package com.bzdepot.special.model;

public class PackingMethod {
    private Long id;

    private String packingName;

    private String packingBs;

    private Long sellerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackingName() {
        return packingName;
    }

    public void setPackingName(String packingName) {
        this.packingName = packingName == null ? null : packingName.trim();
    }

    public String getPackingBs() {
        return packingBs;
    }

    public void setPackingBs(String packingBs) {
        this.packingBs = packingBs == null ? null : packingBs.trim();
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}