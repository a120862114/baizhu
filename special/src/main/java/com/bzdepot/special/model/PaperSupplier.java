package com.bzdepot.special.model;

public class PaperSupplier {
    private Long id;

    private Long sellerId;

    private String supplierName;

    private String supplierBs;

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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public String getSupplierBs() {
        return supplierBs;
    }

    public void setSupplierBs(String supplierBs) {
        this.supplierBs = supplierBs == null ? null : supplierBs.trim();
    }
}