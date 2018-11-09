package com.bzdepot.special.model;

public class Manufactor {
    private Long id;

    private String names;

    private String bs;

    private Long sellerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names == null ? null : names.trim();
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs == null ? null : bs.trim();
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}