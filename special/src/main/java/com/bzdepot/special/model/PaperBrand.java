package com.bzdepot.special.model;

public class PaperBrand {
    private Long id;

    private Long sellerId;

    private String pnames;

    private String pBs;

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

    public String getPnames() {
        return pnames;
    }

    public void setPnames(String pnames) {
        this.pnames = pnames == null ? null : pnames.trim();
    }

    public String getpBs() {
        return pBs;
    }

    public void setpBs(String pBs) {
        this.pBs = pBs == null ? null : pBs.trim();
    }
}